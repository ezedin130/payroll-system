package com.payroll.payroll_system.controller;

import com.payroll.payroll_system.dto.LeaveRequestDto.LeaveRequestInDto;
import com.payroll.payroll_system.dto.LeaveRequestDto.LeaveRequestOutDto;
import com.payroll.payroll_system.mapper.LeaveRequestMapper;
import com.payroll.payroll_system.model.LeaveRequest;
import com.payroll.payroll_system.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
@Validated
@RequiredArgsConstructor
public class LeaveRequestController {
    private final LeaveRequestService service;
    private final LeaveRequestMapper mapper;

    @PostMapping("/create-request")
    public ResponseEntity<LeaveRequestOutDto> createRequest(@RequestBody LeaveRequestInDto dto){
        LeaveRequestOutDto result = service.createRequest(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-all-requests")
    public ResponseEntity<List<LeaveRequestOutDto>> getAllRequests(){
        List<LeaveRequestOutDto> result = service.getAllRequests();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-request-by-id/{id}")
    public ResponseEntity<LeaveRequestOutDto> getRequestById(@PathVariable Long id){
        LeaveRequest result = service.searchRequestById(id);
        LeaveRequestOutDto dto = mapper.toDto(result);
        return ResponseEntity.ok(dto);
    }
}
