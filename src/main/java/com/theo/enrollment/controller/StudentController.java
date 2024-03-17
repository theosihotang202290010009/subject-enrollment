package com.theo.enrollment.controller;

import com.theo.enrollment.constant.APIUrl;
import com.theo.enrollment.constant.ResponseMessage;
import com.theo.enrollment.dto.request.student.NewStudentRequest;
import com.theo.enrollment.dto.request.student.SearchStudentRequest;
import com.theo.enrollment.dto.request.student.UpdateStudentRequest;
import com.theo.enrollment.dto.response.CommonResponse;
import com.theo.enrollment.dto.response.PagingResponse;
import com.theo.enrollment.dto.response.StudentResponse;
import com.theo.enrollment.entity.Student;
import com.theo.enrollment.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = APIUrl.STUDENT_API)
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<StudentResponse>> createStudent(@RequestBody NewStudentRequest request) {
        StudentResponse studentResponse = studentService.create(request);
        CommonResponse<StudentResponse> response = CommonResponse.<StudentResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESSFULLY_CREATE_NEW_DATA)
                .data(studentResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CommonResponse<List<StudentResponse>>> getByName(@PathVariable String name) {
        List<StudentResponse> studentResponses = studentService.findByName(name);

        CommonResponse<List<StudentResponse>> response = CommonResponse.<List<StudentResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESSFULLY_GET_DATA)
                .data(studentResponses)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Student>>> getAllFilter(
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "major", required = false) String major,
            @RequestParam(name = "birthDate", required = false) String birthDate,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {
        SearchStudentRequest request = SearchStudentRequest.builder()
                .id(id)
                .name(name)
                .major(major)
                .birthDate(birthDate)
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();
        Page<Student> getAll = studentService.getAll(request);
        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(getAll.getTotalPages())
                .totalElement(getAll.getTotalElements())
                .page(getAll.getPageable().getPageNumber() + 1)
                .size(getAll.getSize())
                .hasNext(getAll.hasNext())
                .hasPrevious(getAll.hasPrevious())
                .build();

        CommonResponse<List<Student>> response = CommonResponse.<List<Student>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESSFULLY_GET_ALL_DATA)
                .paging(pagingResponse)
                .data(getAll.getContent())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CommonResponse<StudentResponse>> getById(@PathVariable String id){
        StudentResponse student = studentService.findById(id);

        CommonResponse<StudentResponse> response = CommonResponse.<StudentResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESSFULLY_GET_DATA)
                .data(student)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<StudentResponse>> update(@RequestBody UpdateStudentRequest request){
        StudentResponse update = studentService.update(request);
        CommonResponse<StudentResponse> response = CommonResponse.<StudentResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESSFULLY_UPDATE_DATA)
                .data(update)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CommonResponse<?>> updateStatus(@PathVariable String id, @RequestParam Boolean status){
        studentService.updateStatus(id,status);

        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESSFULLY_UPDATE_STATUS)
                .build();

        return ResponseEntity.ok(response);
    }
}

//    @GetMapping
//    public ResponseEntity<CommonResponse<List<Student>>> getAll(){
//        List<Student> allStudents = studentService.getAll();
//        CommonResponse<List<Student>> response = CommonResponse.<List<Student>>builder()
//                .statusCode(HttpStatus.OK.value())
//                .message(ResponseMessage.SUCCESSFULLY_GET_ALL_DATA)
//                .data(allStudents)
//                .build();
//        return ResponseEntity.ok(response);
//    }
