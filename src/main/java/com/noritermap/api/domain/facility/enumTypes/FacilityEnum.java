package com.noritermap.api.domain.facility.enumTypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class FacilityEnum {

    @Getter
    @RequiredArgsConstructor
    public enum Indoor{
        INDOOR("실내"),
        OUTDOOR("실외"),
        UNDEFINED("undefined"),
        ;

        private final String value;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Category{
        C1("주택단지"),
        C2("도시공원"),
        C3("어린이집"),
        C4("놀이제공영업소"),
        C5("식품접객업소"),
        C6("주상복합"),
        C7("아동복지시설"),
        C8("종교시설"),
        C9("대규모점포"),
        C10("육아종합지원센터"),
        C11("박물관"),
        C12("야영장"),
        C13("기타"),
        ;

        private final String value;
    }

    @Getter
    @RequiredArgsConstructor
    public enum prvtPblc{
        PRIVATE("민간"),
        PUBLIC("공공"),
        ;

        private final String value;
    }
}
