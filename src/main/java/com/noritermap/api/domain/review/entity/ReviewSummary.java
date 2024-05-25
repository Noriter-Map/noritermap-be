package com.noritermap.api.domain.review.entity;

import com.noritermap.api.domain.facility.entity.Facility;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewSummary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_SUMMARY_ID")
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "FACILITY_ID")
    private Facility facility;

    @Column(length = 500)
    private String content;

}
