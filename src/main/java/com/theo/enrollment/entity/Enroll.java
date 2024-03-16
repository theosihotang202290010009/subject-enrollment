package com.theo.enrollment.entity;

import com.theo.enrollment.constant.MsTablesConstant;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = MsTablesConstant.ENROLL_TABLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enroll {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "enroll")
    private List<EnrollDetail> details;
}
