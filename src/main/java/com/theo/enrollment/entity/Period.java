package com.theo.enrollment.entity;

import com.theo.enrollment.constant.MsTablesConstant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = MsTablesConstant.PERIOD_TABLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "period")
    private String period;
}
