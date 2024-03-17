package com.theo.enrollment.service.impl;

import com.theo.enrollment.dto.request.enrollDetail.EnrollDetailRequest;
import com.theo.enrollment.repository.EnrollDetailRepository;
import com.theo.enrollment.service.EnrollDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnrollDetailServiceImpl implements EnrollDetailService {
    private final EnrollDetailRepository enrollDetailRepository;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(EnrollDetailRequest request) {
        enrollDetailRepository.create(request.getId(), request.getCourseId(), request.getEnrollId(), request.getPeriodId());
    }
}
