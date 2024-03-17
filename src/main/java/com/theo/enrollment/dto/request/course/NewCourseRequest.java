package com.theo.enrollment.dto.request.course;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCourseRequest {
    private String id;
    private String courseName;
    private Integer sks;
}
