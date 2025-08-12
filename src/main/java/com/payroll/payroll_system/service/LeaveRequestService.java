package com.payroll.payroll_system.service;

import com.payroll.payroll_system.dto.LeaveRequestDto.LeaveRequestInDto;
import com.payroll.payroll_system.dto.LeaveRequestDto.LeaveRequestOutDto;
import com.payroll.payroll_system.mapper.LeaveRequestMapper;
import com.payroll.payroll_system.model.Employee;
import com.payroll.payroll_system.model.LeaveRequest;
import com.payroll.payroll_system.repo.EmployeeRepo;
import com.payroll.payroll_system.repo.LeaveRequestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {
    @Autowired
    private final LeaveRequestRepo reqRepo;
    @Autowired
    private final EmployeeRepo emplRepo;
    @Autowired
    private final LeaveRequestMapper mapper;

    public LeaveRequestOutDto createRequest(LeaveRequestInDto dto){
        Employee empl = emplRepo.findById(dto.getEmpId())
                .orElseThrow(() -> new RuntimeException("Employee id not found"));
        LeaveRequest request = mapper.toEntity(dto,empl);
        LeaveRequest savedRequest = reqRepo.save(request);
        return mapper.toDto(savedRequest);
    }
    public List<LeaveRequestOutDto> getAllRequests(){
        return reqRepo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    public LeaveRequest searchRequestById(Long id){
        return reqRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Request with this is id is not found"));
    }
}
