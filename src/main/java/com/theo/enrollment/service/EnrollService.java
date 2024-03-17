package com.theo.enrollment.service;

import com.theo.enrollment.dto.request.enroll.NewEnrollRequest;
import com.theo.enrollment.dto.request.enroll.SearchEnrollRequest;
import com.theo.enrollment.dto.response.EnrollResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EnrollService {
    EnrollResponse createEnroll(NewEnrollRequest request);

    Page<EnrollResponse> getAll(SearchEnrollRequest request);
}
