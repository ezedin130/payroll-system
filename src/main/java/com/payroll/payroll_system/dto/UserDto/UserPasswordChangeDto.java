package com.payroll.payroll_system.dto.UserDto;

import lombok.Data;

@Data
public class UserPasswordChangeDto {
    private String currentPassword;
    private String newPassword;
}
