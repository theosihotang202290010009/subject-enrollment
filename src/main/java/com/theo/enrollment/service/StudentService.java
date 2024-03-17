package com.theo.enrollment.service;

import com.theo.enrollment.dto.request.student.SearchStudentRequest;
import com.theo.enrollment.dto.request.student.NewStudentRequest;
import com.theo.enrollment.dto.request.student.UpdateStudentRequest;
import com.theo.enrollment.dto.response.StudentResponse;
import com.theo.enrollment.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    StudentResponse create(NewStudentRequest request);
    Page<Student> getAll(SearchStudentRequest request);

    List<StudentResponse> findByName(String name);

    StudentResponse findById(String id);

    Student getOneById(String id);
    StudentResponse update(UpdateStudentRequest request);
    void updateStatus(String id, Boolean status);
}
