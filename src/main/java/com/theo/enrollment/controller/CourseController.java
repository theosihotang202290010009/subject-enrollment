package com.theo.enrollment.controller;

import com.theo.enrollment.constant.APIUrl;
import com.theo.enrollment.constant.ResponseMessage;
import com.theo.enrollment.dto.request.course.NewCourseRequest;
import com.theo.enrollment.dto.response.CommonResponse;
import com.theo.enrollment.dto.response.CourseResponse;
import com.theo.enrollment.dto.response.PagingResponse;
import com.theo.enrollment.service.CourseService;
import com.theo.enrollment.dto.request.course.SearchCourseRequest;
import com.theo.enrollment.dto.request.course.UpdateCourseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.COURSE_API)
public class CourseController {
    private final CourseService courseService;


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<CourseResponse>> createCourse(@RequestBody NewCourseRequest request){
        CourseResponse courseResponse = courseService.create(request);

        CommonResponse<CourseResponse> response = CommonResponse.<CourseResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESSFULLY_CREATE_NEW_DATA)
                .data(courseResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<List<CourseResponse>>> getAll(
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "sks", required = false) Integer sks,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "courseName") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ){
        SearchCourseRequest request = SearchCourseRequest.builder()
                .id(id)
                .courseName(name)
                .sks(sks)
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();

        Page<CourseResponse> getAll = courseService.getAll(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(getAll.getTotalPages())
                .totalElement(getAll.getTotalElements())
                .page(getAll.getPageable().getPageNumber() + 1)
                .size(getAll.getPageable().getPageSize())
                .hasNext(getAll.hasNext())
                .hasPrevious(getAll.hasPrevious())
                .build();

        CommonResponse<List<CourseResponse>> response = CommonResponse.<List<CourseResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESSFULLY_GET_ALL_DATA)
                .paging(pagingResponse)
                .data(getAll.getContent())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<CourseResponse>> findById(@PathVariable String id){
        CourseResponse courseResponse = courseService.findById(id);
        CommonResponse<CourseResponse> response = CommonResponse.<CourseResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESSFULLY_GET_DATA + " with ID " +id)
                .data(courseResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<CourseResponse>> update(@RequestBody UpdateCourseRequest request){
        CourseResponse update = courseService.update(request);

        CommonResponse<CourseResponse> response = CommonResponse.<CourseResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESSFULLY_UPDATE_DATA)
                .data(update)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<String>> delete(@PathVariable String id){
        courseService.delete(id);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESSFULLY_DELETE_DATA)
                .build();

        return ResponseEntity.ok(response);
    }
}
