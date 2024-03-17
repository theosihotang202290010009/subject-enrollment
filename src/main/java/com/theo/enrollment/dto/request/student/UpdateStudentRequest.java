package com.theo.enrollment.dto.request.student;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStudentRequest {
    private String id;
    private String birthDate;
    private String major;
    private String name;
}
