package com.theo.enrollment.service.impl;

import com.theo.enrollment.constant.ResponseMessage;
import com.theo.enrollment.dto.request.student.NewStudentRequest;
import com.theo.enrollment.dto.request.student.SearchStudentRequest;
import com.theo.enrollment.dto.request.student.UpdateStudentRequest;
import com.theo.enrollment.dto.response.StudentResponse;
import com.theo.enrollment.entity.Student;
import com.theo.enrollment.repository.StudentRepository;
import com.theo.enrollment.service.StudentService;
import com.theo.enrollment.specification.StudentSpecification;
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
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public StudentResponse create(NewStudentRequest request) {
        String uuid = UUID.randomUUID().toString();
        Date birhDate = DateUtil.parseDate(request.getBirthDate(), "yyyy-MM-dd");
        System.out.println(birhDate);
        studentRepository.createStudent(uuid, birhDate, request.getMajor(), request.getName());

        return findById(uuid);
    }


    @Transactional(readOnly = true)
    @Override
    public Page<Student> getAll(SearchStudentRequest request) {
        Specification<Student> specification = StudentSpecification.getSpecification(request);
        if (request.getPage() < 0) request.setPage(1);
        Sort sort = Sort.by(Sort.Direction.fromString(request.getDirection()), request.getSortBy());
        Pageable pageable = PageRequest.of((request.getPage() - 1), request.getSize(), sort);
        return studentRepository.findAll(specification, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentResponse> findByName(String name) {
        List<Student> students = studentRepository.findName(name);
        return students.stream().map(StudentServiceImpl::convertToResponse).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public StudentResponse findById(String id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessage.ERROR_NOT_FOUND));
        return convertToResponse(student);
    }

    @Override
    public Student getOneById(String id) {
        return studentRepository.getStudentId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public StudentResponse update(UpdateStudentRequest request) {
        Date date = DateUtil.parseDate(request.getBirthDate(), "yyyy-MM-dd");
        studentRepository.update(request.getId(), request.getName(), request.getMajor(), date);
        Student student = studentRepository.getStudentId(request.getId());
        return convertToResponse(student);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStatus(String id, Boolean status) {
        studentRepository.updateStatus(id, status);
    }

    private static StudentResponse convertToResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .major(student.getMajor())
                .birthDate(student.getBirthDate().toString())
                .status(student.getStatus())
                .build();
    }
}
