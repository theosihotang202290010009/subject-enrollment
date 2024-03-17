package com.theo.enrollment.service;

import com.theo.enrollment.dto.request.period.NewPeriodRequest;
import com.theo.enrollment.dto.request.period.UpdatePeriodRequest;
import com.theo.enrollment.dto.response.PeriodResponse;
import com.theo.enrollment.entity.Period;

import java.util.List;

public interface PeriodService {
    PeriodResponse create(NewPeriodRequest request);
    PeriodResponse update(UpdatePeriodRequest request);
    PeriodResponse getById(String id);
    Period getOneById(String id);
    List<PeriodResponse> getAll();
}
