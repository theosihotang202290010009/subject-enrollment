package com.theo.enrollment.service;

import com.theo.enrollment.dto.request.course.NewCourseRequest;
import com.theo.enrollment.dto.request.course.SearchCourseRequest;
import com.theo.enrollment.dto.request.course.UpdateCourseRequest;
import com.theo.enrollment.dto.response.CourseResponse;
import com.theo.enrollment.entity.Course;
import org.springframework.data.domain.Page;

public interface CourseService {
    CourseResponse create(NewCourseRequest request);
    Page<CourseResponse> getAll(SearchCourseRequest request);
    CourseResponse findById(String id);
    Course getOneById(String id);

    CourseResponse update(UpdateCourseRequest request);

    void delete(String id);
}
