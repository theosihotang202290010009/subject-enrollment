package com.theo.enrollment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.theo.enrollment.constant.MsTablesConstant;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = MsTablesConstant.STUDENT_TABLE)
public class Student {
    @Id
    private String id;

    @Column(name = "student_name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Column(name = "major")
    private String major;

    @Column(name = "is_active")
    private Boolean status;

}
