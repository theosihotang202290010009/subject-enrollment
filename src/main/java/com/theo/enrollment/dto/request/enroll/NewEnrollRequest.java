package com.theo.enrollment.dto.request.enroll;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.theo.enrollment.dto.request.enrollDetail.EnrollDetailRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewEnrollRequest {
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String date;
    private String studentId;
    private List<EnrollDetailRequest> detailRequests;
}
