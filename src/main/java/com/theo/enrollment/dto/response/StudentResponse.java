package com.theo.enrollment.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private String id;
    private String birthDate;
    private String major;
    private String name;
    private Boolean status;
}
