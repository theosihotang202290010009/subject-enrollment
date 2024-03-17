package com.theo.enrollment.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollDetailResponse {
    private String id;
    private String courseName;
    private String enrollId;
    private String period;
}
