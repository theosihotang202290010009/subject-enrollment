package com.theo.enrollment.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {
    private String id;
    private String courseName;
    private Integer sks;
}
