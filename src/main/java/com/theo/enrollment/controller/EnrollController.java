package com.theo.enrollment.controller;

import com.theo.enrollment.constant.APIUrl;
import com.theo.enrollment.constant.ResponseMessage;
import com.theo.enrollment.dto.request.enroll.NewEnrollRequest;
import com.theo.enrollment.dto.request.enroll.SearchEnrollRequest;
import com.theo.enrollment.dto.response.CommonResponse;
import com.theo.enrollment.dto.response.EnrollResponse;
import com.theo.enrollment.dto.response.PagingResponse;
import com.theo.enrollment.service.EnrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.ENROLL_API)
public class EnrollController {
    private final EnrollService enrollService;

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<EnrollResponse>> create(@RequestBody NewEnrollRequest request){
        EnrollResponse enroll = enrollService.createEnroll(request);

        CommonResponse<EnrollResponse> response = CommonResponse.<EnrollResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESSFULLY_CREATE_NEW_DATA)
                .data(enroll)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<List<EnrollResponse>>> getAll(
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "date", required = false) String date,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "studentName") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ){
        SearchEnrollRequest request = SearchEnrollRequest.builder()
                .id(id)
                .studentName(name)
                .date(date)
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();

        Page<EnrollResponse> getAll = enrollService.getAll(request);
        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(getAll.getTotalPages())
                .totalElement(getAll.getTotalElements())
                .page(getAll.getPageable().getPageNumber() + 1)
                .size(getAll.getPageable().getPageSize())
                .hasNext(getAll.hasNext())
                .hasPrevious(getAll.hasPrevious())
                .build();

        CommonResponse<List<EnrollResponse>> response = CommonResponse.<List<EnrollResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESSFULLY_GET_ALL_DATA)
                .paging(pagingResponse)
                .data(getAll.getContent())
                .build();

        return ResponseEntity.ok(response);
    }
}
