package com.payroll.payroll_system.dto.RoleDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleOutDto {
    private Long id;
    private String name;
}
