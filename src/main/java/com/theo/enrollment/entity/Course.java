package com.theo.enrollment.entity;

import com.theo.enrollment.constant.MsTablesConstant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = MsTablesConstant.COURSE_TABLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "sks")
    private Integer sks;
}
