package com.theo.enrollment.dto.request.enrollDetail;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollDetailRequest {
    private String id;
    private String courseId;
    private String enrollId;
    private String periodId;
}
