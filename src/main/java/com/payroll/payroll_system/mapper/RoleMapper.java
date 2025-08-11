package com.payroll.payroll_system.mapper;

import com.payroll.payroll_system.dto.RoleDto.RoleInDto;
import com.payroll.payroll_system.dto.RoleDto.RoleOutDto;
import com.payroll.payroll_system.model.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper {
    public Role toEntity(RoleInDto dto){
        return Role.builder()
                .name(dto.getName())
                .build();
    }
    public RoleOutDto toDto(Role role){
        RoleOutDto dto = new RoleOutDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }
}
