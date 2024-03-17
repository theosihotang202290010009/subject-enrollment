package com.theo.enrollment.controller;

import com.theo.enrollment.constant.APIUrl;
import com.theo.enrollment.constant.ResponseMessage;
import com.theo.enrollment.dto.request.period.NewPeriodRequest;
import com.theo.enrollment.dto.request.period.UpdatePeriodRequest;
import com.theo.enrollment.dto.response.CommonResponse;
import com.theo.enrollment.dto.response.PeriodResponse;
import com.theo.enrollment.service.PeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIUrl.PERIOD_API)
@RequiredArgsConstructor
public class PeriodController {
    private final PeriodService periodService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<PeriodResponse>> create(@RequestBody NewPeriodRequest request) {
        PeriodResponse periodResponse = periodService.create(request);

        CommonResponse<PeriodResponse> response = CommonResponse.<PeriodResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESSFULLY_CREATE_NEW_DATA)
                .data(periodResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<List<PeriodResponse>>> getAll() {
        List<PeriodResponse> getAll = periodService.getAll();

        CommonResponse<List<PeriodResponse>> response = CommonResponse.<List<PeriodResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESSFULLY_GET_ALL_DATA)
                .data(getAll)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<PeriodResponse>> getById(@PathVariable String id) {
        PeriodResponse periodResponse = periodService.getById(id);

        CommonResponse<PeriodResponse> response = CommonResponse.<PeriodResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESSFULLY_GET_DATA)
                .data(periodResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<PeriodResponse>> update(@RequestBody UpdatePeriodRequest request) {
        PeriodResponse update = periodService.update(request);

        CommonResponse<PeriodResponse> response = CommonResponse.<PeriodResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESSFULLY_UPDATE_DATA)
                .data(update)
                .build();

        return ResponseEntity.ok(response);
    }
}
