package com.payroll.payroll_system.mapper;

import com.payroll.payroll_system.dto.LeaveRequestDto.LeaveRequestInDto;
import com.payroll.payroll_system.dto.LeaveRequestDto.LeaveRequestOutDto;
import com.payroll.payroll_system.enums.LeaveType;
import com.payroll.payroll_system.enums.status.LeaveRequestStatus;
import com.payroll.payroll_system.model.Employee;
import com.payroll.payroll_system.model.LeaveRequest;
import org.springframework.stereotype.Service;

@Service
public class LeaveRequestMapper {
    public LeaveRequest toEntity(LeaveRequestInDto dto , Employee empl){
        return LeaveRequest.builder()
                .leaveType(LeaveType.valueOf(dto.getLeaveType()))
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(LeaveRequestStatus.valueOf(dto.getStatus()))
                .empId(empl)
                .build();
    }
    public LeaveRequestOutDto toDto(LeaveRequest request){
        LeaveRequestOutDto dto = new LeaveRequestOutDto();
        dto.setId(request.getId());
        dto.setLeaveType(request.getLeaveType().name());
        dto.setStartDate(request.getStartDate());
        dto.setEndDate(request.getEndDate());
        dto.setStatus(request.getStatus().name());
        dto.setEmpId(request.getEmpId().getId());
        return dto;
    }
}
