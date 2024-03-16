package com.theo.enrollment.entity;

import com.theo.enrollment.constant.MsTablesConstant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = MsTablesConstant.ENROLL_DETAIL_TABLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "enroll_id")
    private Enroll enroll;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "period_id")
    private Period period;
}
