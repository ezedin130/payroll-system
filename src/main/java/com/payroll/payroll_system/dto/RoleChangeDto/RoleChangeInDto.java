package com.payroll.payroll_system.dto.RoleChangeDto;

import lombok.Data;

@Data
public class RoleChangeInDto {
    private Long userId;
    private Long requestedRoleId;
}
