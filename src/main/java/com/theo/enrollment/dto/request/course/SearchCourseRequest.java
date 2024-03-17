package com.theo.enrollment.dto.request.course;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCourseRequest {
    private String id;
    private String courseName;
    private Integer sks;
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
}
