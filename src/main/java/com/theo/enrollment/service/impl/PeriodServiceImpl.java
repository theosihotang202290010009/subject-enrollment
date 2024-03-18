package com.theo.enrollment.service.impl;

import com.theo.enrollment.constant.ResponseMessage;
import com.theo.enrollment.dto.request.period.NewPeriodRequest;
import com.theo.enrollment.dto.request.period.UpdatePeriodRequest;
import com.theo.enrollment.dto.response.PeriodResponse;
import com.theo.enrollment.entity.Period;
import com.theo.enrollment.repository.PeriodRepository;
import com.theo.enrollment.service.PeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PeriodServiceImpl implements PeriodService {
    private final PeriodRepository periodRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PeriodResponse create(NewPeriodRequest request) {
        String uuid = UUID.randomUUID().toString();
        periodRepository.create(uuid, request.getPeriod());
        return getById(uuid);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PeriodResponse update(UpdatePeriodRequest request) {
        PeriodResponse response = getById(request.getId());
        periodRepository.update(request.getId(), request.getPeriod());
        response.setPeriod(request.getPeriod());
        return response;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PeriodResponse> getAll() {
        List<Period> periods = periodRepository.findAll();
        return periods.stream().map(PeriodServiceImpl::convertToResponse).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public PeriodResponse getById(String id) {
        Period period = periodRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessage.ERROR_NOT_FOUND));
        return convertToResponse(period);
    }

    @Transactional(readOnly = true)
    @Override
    public Period getOneById(String id) {
        return periodRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessage.ERROR_NOT_FOUND));
    }

    private static PeriodResponse convertToResponse(Period period) {
        return PeriodResponse.builder()
                .id(period.getId())
                .period(period.getPeriod())
                .build();
    }
}
