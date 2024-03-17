package com.theo.enrollment.dto.request.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewStudentRequest {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthDate;
    private String major;
    private String name;
}
