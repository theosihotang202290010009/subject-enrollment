package com.theo.enrollment.service.impl;

import com.theo.enrollment.constant.ResponseMessage;
import com.theo.enrollment.dto.request.enroll.NewEnrollRequest;
import com.theo.enrollment.dto.request.enroll.SearchEnrollRequest;
import com.theo.enrollment.dto.request.enrollDetail.EnrollDetailRequest;
import com.theo.enrollment.dto.response.EnrollDetailResponse;
import com.theo.enrollment.dto.response.EnrollResponse;
import com.theo.enrollment.entity.*;
import com.theo.enrollment.repository.EnrollRepository;
import com.theo.enrollment.service.*;
import com.theo.enrollment.specification.EnrollSpecification;
import com.theo.enrollment.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrollServiceImpl implements EnrollService {
    private final EnrollRepository enrollRepository;
    private final StudentService studentService;
    private final EnrollDetailService enrollDetailService;
    private final CourseService courseService;
    private final PeriodService periodService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public EnrollResponse createEnroll(NewEnrollRequest request) {
        Student student = studentService.getOneById(request.getStudentId());
        String uuid = UUID.randomUUID().toString();
        enrollRepository.create(uuid, new Date(), student.getId());
        Enroll enroll = enrollRepository.findById(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessage.ERROR_NOT_FOUND));
        List<EnrollDetail> enrollDetails = request.getDetailRequests().stream().map(detailRequest -> {
            String detailId = UUID.randomUUID().toString();
            Course course = courseService.getOneById(detailRequest.getCourseId());
            Period period = periodService.getOneById(detailRequest.getPeriodId());

            return EnrollDetail.builder()
                    .id(detailId)
                    .enroll(enroll)
                    .course(course)
                    .period(period)
                    .build();
        }).toList();
        for (EnrollDetail enrollDetail : enrollDetails) {
            EnrollDetailRequest detailRequest = EnrollDetailRequest.builder()
                    .id(enrollDetail.getId())
                    .enrollId(enrollDetail.getEnroll().getId())
                    .courseId(enrollDetail.getCourse().getId())
                    .periodId(enrollDetail.getPeriod().getId())
                    .build();
            enrollDetailService.create(detailRequest);
        }

        enroll.setDetails(enrollDetails);
        return EnrollResponse.builder()
                .date(enroll.getDate().toString())
                .studentName(enroll.getStudent().getName())
                .detailResponses(enrollDetails.stream().map(enrollDetail -> EnrollDetailResponse.builder()
                        .id(enrollDetail.getId())
                        .enrollId(enrollDetail.getEnroll().getId())
                        .courseName(enrollDetail.getCourse().getCourseName())
                        .period(enrollDetail.getPeriod().getPeriod())
                        .build()).toList())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<EnrollResponse> getAll(SearchEnrollRequest request) {

        Specification<Enroll> specification = EnrollSpecification.getSpecification(request);
        if (request.getPage() <= 0) request.setPage(1);
        Sort sort = Sort.by(Sort.Direction.fromString(request.getDirection()), request.getSortBy());
        Pageable pageable = PageRequest.of((request.getPage() - 1), request.getSize(), sort);
        Page<Enroll> allEnroll = enrollRepository.findAll(specification, pageable);
        return allEnroll.map(enroll -> EnrollResponse.builder()
                .date(enroll.getDate().toString())
                .studentName(enroll.getStudent().getName())
                .detailResponses(enroll.getDetails().stream().map(enrollDetail ->
                        EnrollDetailResponse.builder()
                                .id(enrollDetail.getId())
                                .enrollId(enrollDetail.getId())
                                .courseName(enrollDetail.getCourse().getCourseName())
                                .period(enrollDetail.getPeriod().getPeriod())
                                .build()).toList())
                .build());
    }
}
