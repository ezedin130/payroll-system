package com.payroll.payroll_system.dto.LeaveRequestDto;

import com.payroll.payroll_system.enums.LeaveType;
import com.payroll.payroll_system.model.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestInDto {
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Long empId;
}
