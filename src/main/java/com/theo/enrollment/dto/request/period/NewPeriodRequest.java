package com.theo.enrollment.dto.request.period;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPeriodRequest {
    private String id;
    private String period;
}
