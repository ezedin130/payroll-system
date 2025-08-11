package com.payroll.payroll_system.mapper;

import com.payroll.payroll_system.dto.UserDto.UserInDto;
import com.payroll.payroll_system.dto.UserDto.UserOutDto;
import com.payroll.payroll_system.model.Employee;
import com.payroll.payroll_system.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserMapper {
    public User toEntity(UserInDto dto, Employee empl){
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .createdAt(LocalDate.now())
                .empId(empl)
                .build();
    }
    public UserOutDto toDto(User user){
        UserOutDto dto = new UserOutDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setEmpId(user.getEmpId().getId());
        return dto;
    }
}
