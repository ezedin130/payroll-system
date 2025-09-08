package com.payroll.payroll_system.dto.LeaveRequestDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestOutDto {
    private Long id;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Long empId;
    private Long reviewedBy;
    private LocalDate reviewedAt;
}
