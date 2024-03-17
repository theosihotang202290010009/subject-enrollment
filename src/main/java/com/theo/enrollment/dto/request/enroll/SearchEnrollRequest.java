package com.theo.enrollment.dto.request.enroll;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchEnrollRequest {
    private String id;
    private String date;
    private String studentName;
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
}
