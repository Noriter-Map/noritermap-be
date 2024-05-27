package com.noritermap.api.domain.facility.repository;

import com.noritermap.api.domain.facility.dto.QFacilityResponseDto_FacilityInfoBaseDto;
import com.noritermap.api.domain.facility.dto.QFacilityResponseDto_FacilityInfoDetailDto;
import com.noritermap.api.domain.facility.dto.QFacilityResponseDto_FacilitySearchResultDto;
import com.noritermap.api.domain.facility.dto.QFacilityResponseDto_RatingAndReviewCntDto;
import com.noritermap.api.domain.facility.enumTypes.FacilityEnum;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Objects;

import static com.noritermap.api.domain.facility.dto.FacilityResponseDto.*;
import static com.noritermap.api.domain.facility.entity.QFacility.*;
import static com.noritermap.api.domain.review.entity.QReview.*;
import static org.springframework.util.StringUtils.*;

@RequiredArgsConstructor
public class FacilityRepositoryImpl implements FacilityRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private static final Double GWANGJU_CITY_HALL_LATITUDE = 35.160048;
    private static final Double GWANGJU_CITY_HALL_LONGITUDE = 126.851309;

    @Override
    public Page<FacilitySearchResultDto> searchWithQueries(String keyword, List<String> idrodr, List<String> category, List<String> prvtPblc, String latitudeString, String longitudeString, Pageable pageable) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        Double latitude; Double longitude;

        if (hasText(keyword)){
            booleanBuilder.and(facility.pfctNm.contains(keyword));
        }

        if (Objects.nonNull(idrodr) && !idrodr.isEmpty()){
            List<String> conditions = idrodr.stream()
                            .map(str -> FacilityEnum.Idrodr.valueOf(str.toUpperCase()).getValue()).toList();

            booleanBuilder.and(facility.idrodrCdNm.in(conditions));

        }

        if (Objects.nonNull(category) && !category.isEmpty()){
            List<String> conditions = category.stream()
                    .map(str -> FacilityEnum.Category.valueOf(str.toUpperCase()).getValue()).toList();

            booleanBuilder.and(facility.instlPlaceCdNm.in(conditions));
        }

        if (Objects.nonNull(prvtPblc) && !prvtPblc.isEmpty()){
            List<String> conditions = prvtPblc.stream()
                    .map(str -> FacilityEnum.prvtPblc.valueOf(str.toUpperCase()).getValue()).toList();

            booleanBuilder.and(facility.prvtPblcYnCdNm.in(conditions));
        }

        if (hasText(latitudeString) && hasText(longitudeString)){
            latitude = Double.parseDouble(latitudeString);
            longitude = Double.parseDouble(longitudeString);
        }else{
            latitude = GWANGJU_CITY_HALL_LATITUDE;
            longitude = GWANGJU_CITY_HALL_LONGITUDE;
        }

        // 거리 계산식 (ST_DISTANCE_SPHERE 사용)
        Expression<Double> distanceExpression = Expressions.numberTemplate(Double.class,
                "ST_DISTANCE_SPHERE(POINT({0}, {1}), POINT({2}, {3}))", facility.lotCrtsVl, facility.latCrtsVl, longitude, latitude);

        // 정렬을 위한 OrderSpecifier 생성
        OrderSpecifier<Double> distanceOrder = new OrderSpecifier<>(com.querydsl.core.types.Order.ASC, distanceExpression);

        List<FacilitySearchResultDto> results = queryFactory
                .select(new QFacilityResponseDto_FacilitySearchResultDto(facility.id, facility.pfctNm, facility.ronaAddr, facility.lotnoAddr, facility.instlPlaceCdNm, facility.prvtPblcYnCdNm, facility.idrodrCdNm, facility.latCrtsVl, facility.lotCrtsVl, review.rating.avg().coalesce(0.0), review.count().coalesce(0L), distanceExpression))
                .from(facility)
                .leftJoin(review)
                .on(review.facility.eq(facility))
                .where(booleanBuilder)
                .groupBy(facility)
                .orderBy(distanceOrder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(facility.count())
                .from(facility)
                .where(booleanBuilder);

        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
    }

    @Override
    public FacilityInfoBaseDto getInfoBase(Long facilityId) {
        return queryFactory
                .select(new QFacilityResponseDto_FacilityInfoBaseDto(facility.id, facility.pfctNm, facility.exfcYn, facility.instlPlaceCdNm, review.rating.avg().coalesce(0.0), review.count().coalesce(0L), facility.latCrtsVl, facility.lotCrtsVl))
                .from(facility)
                .leftJoin(review)
                .on(review.facility.eq(facility))
                .where(facility.id.eq(facilityId))
                .groupBy(facility)
                .fetchOne();
    }

    @Override
    public FacilityInfoDetailDto getInfoDetail(Long facilityId) {
        return queryFactory
                .select(new QFacilityResponseDto_FacilityInfoDetailDto(facility.id, facility.pfctSn, facility.pfctNm, facility.zip, facility.ronaAddr, facility.lotnoAddr, facility.instlYmd, facility.instlPlaceCdNm, facility.prvtPblcYnCdNm, facility.idrodrCdNm, facility.latCrtsVl, facility.lotCrtsVl, facility.incld_water, facility.cctvCnt, facility.insurance, facility.safetyInsp))
                .from(facility)
                .where(facility.id.eq(facilityId))
                .fetchOne();
    }

    @Override
    public RatingAndReviewCntDto getRatingAndReviewCnt(Long facilityId) {
        return queryFactory
                .select(new QFacilityResponseDto_RatingAndReviewCntDto(facility.id, review.rating.avg().coalesce(0.0), review.count().coalesce(0L)))
                .from(facility)
                .leftJoin(review)
                .on(review.facility.eq(facility))
                .where(facility.id.eq(facilityId))
                .groupBy(facility)
                .fetchOne();
    }
}
