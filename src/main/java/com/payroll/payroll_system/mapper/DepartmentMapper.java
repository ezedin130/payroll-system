package com.payroll.payroll_system.mapper;

import com.payroll.payroll_system.dto.DepartmentDto.DepartmentInDto;
import com.payroll.payroll_system.dto.DepartmentDto.DepartmentOutDto;
import com.payroll.payroll_system.model.Department;
import org.springframework.stereotype.Service;

@Service
public class DepartmentMapper {
    public Department toEntity(DepartmentInDto dto){
        return Department.builder()
                .name(dto.getName())
                .build();
    }
    public DepartmentOutDto toDto(Department dept){
        DepartmentOutDto dto = new DepartmentOutDto();
        dto.setId(dept.getId());
        dto.setName(dept.getName());
        return dto;
    }
}
