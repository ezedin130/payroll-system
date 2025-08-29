package com.payroll.payroll_system.dto.UserDto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UserOutDto {
    private Long id;
    private String username;
    private String password;
    private LocalDate createdAt;
    private Long empId;
    private Long roleId;
}
