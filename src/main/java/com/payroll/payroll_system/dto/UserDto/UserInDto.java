package com.payroll.payroll_system.dto.UserDto;

import lombok.Data;

@Data
public class UserInDto {
    private String username;
    private String password;
    private Long empId;
    private Long roleId;
}
