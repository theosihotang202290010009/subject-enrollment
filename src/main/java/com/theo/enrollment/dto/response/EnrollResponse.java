package com.theo.enrollment.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollResponse {
    private String date;
    private String studentName;
    private List<EnrollDetailResponse> detailResponses;
}
