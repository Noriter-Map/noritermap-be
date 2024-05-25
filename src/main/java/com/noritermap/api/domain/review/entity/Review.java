package com.noritermap.api.domain.review.entity;

import com.noritermap.api.domain.facility.entity.Facility;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID")
    private Long id;

    private String nickname;
    private String content;
    private Double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FACILITY_ID")
    private Facility facility;

    public Review setFacility(Facility facility){
        if (this.facility == null){
            this.facility = facility;
        }

        return this;
    }
}
