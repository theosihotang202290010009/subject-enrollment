package com.theo.enrollment.dto.request.student;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchStudentRequest {
    private String id;
    private String birthDate;
    private String major;
    private String name;
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
}
