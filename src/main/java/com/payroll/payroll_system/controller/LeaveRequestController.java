package com.payroll.payroll_system.controller;

import com.payroll.payroll_system.dto.LeaveRequestDto.LeaveRequestInDto;
import com.payroll.payroll_system.dto.LeaveRequestDto.LeaveRequestOutDto;
import com.payroll.payroll_system.mapper.LeaveRequestMapper;
import com.payroll.payroll_system.model.LeaveRequest;
import com.payroll.payroll_system.repo.UserRepo;
import com.payroll.payroll_system.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
@Validated
@RequiredArgsConstructor
public class LeaveRequestController {
    @Autowired
    private final LeaveRequestService service;
    @Autowired
    private final LeaveRequestMapper mapper;
    @Autowired
    private final UserRepo repo;

    @PostMapping("/create-request")
    public ResponseEntity<LeaveRequestOutDto> createRequest(@RequestBody LeaveRequestInDto dto){
        LeaveRequestOutDto result = service.createLeaveRequest(dto);
        return ResponseEntity.ok(result);
    }
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @PutMapping("/process/{requestId}")
    public ResponseEntity<LeaveRequestOutDto> processLeaveRequest(
            @PathVariable Long requestId,
            @RequestParam boolean approve
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long approverId = repo.findByUsername(username).getId();
        LeaveRequestOutDto dto = service.processLeaveRequest(requestId,approve,approverId);
        return ResponseEntity.ok(dto);
    }
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @GetMapping("/get-all-requests")
    public ResponseEntity<List<LeaveRequestOutDto>> getAllRequests(){
        List<LeaveRequestOutDto> result = service.getAllRequests();
        return ResponseEntity.ok(result);
    }
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @GetMapping("/get-request-by-id/{id}")
    public ResponseEntity<LeaveRequestOutDto> getRequestById(@PathVariable Long id){
        LeaveRequest result = service.searchRequestById(id);
        LeaveRequestOutDto dto = mapper.toDto(result);
        return ResponseEntity.ok(dto);
    }
}
