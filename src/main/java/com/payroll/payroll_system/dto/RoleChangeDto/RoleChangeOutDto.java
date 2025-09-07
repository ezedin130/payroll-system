package com.payroll.payroll_system.dto.RoleChangeDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RoleChangeOutDto {
    private Long id;
    private Long userId;
    private Long requestedRoleId;
    private String status;
    private Long reviewedBy;
    private LocalDate createdAt;
}
