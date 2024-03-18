package com.theo.enrollment.service.impl;

import com.theo.enrollment.constant.ResponseMessage;
import com.theo.enrollment.dto.request.course.NewCourseRequest;
import com.theo.enrollment.dto.response.CourseResponse;
import com.theo.enrollment.entity.Course;
import com.theo.enrollment.repository.CourseRepository;
import com.theo.enrollment.service.CourseService;
import com.theo.enrollment.dto.request.course.SearchCourseRequest;
import com.theo.enrollment.dto.request.course.UpdateCourseRequest;
import com.theo.enrollment.specification.CourseSpecification;
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

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CourseResponse create(NewCourseRequest request) {
        String uuid = UUID.randomUUID().toString();
        courseRepository.createCourse(uuid, request.getCourseName(), request.getSks());
        Course course = courseRepository.getCourseId(uuid);
        return convertToResponse(course);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CourseResponse> getAll(SearchCourseRequest request) {
        Specification<Course> specification = CourseSpecification.getSpecification(request);

        if (request.getPage() <= 0) request.setPage(1);
        Sort sort = Sort.by(Sort.Direction.fromString(request.getDirection()), request.getSortBy());
        Pageable pageable = PageRequest.of((request.getPage() - 1), request.getSize(), sort);

        Page<Course> allCourses = courseRepository.findAll(specification, pageable);
        return allCourses.map(course -> CourseResponse.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .sks(course.getSks())
                .build());
    }

    @Transactional(readOnly = true)
    @Override
    public CourseResponse findById(String id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessage.ERROR_NOT_FOUND));
        return convertToResponse(course);
    }

    @Transactional(readOnly = true)
    @Override
    public Course getOneById(String id) {
        return courseRepository.getCourseId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CourseResponse update(UpdateCourseRequest request) {
        courseRepository.updateCourse(request.getId(), request.getCourseName(), request.getSks());
        return findById(request.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        findById(id);
        courseRepository.deleteCourse(id);
    }

    private static CourseResponse convertToResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .sks(course.getSks())
                .build();
    }
}
