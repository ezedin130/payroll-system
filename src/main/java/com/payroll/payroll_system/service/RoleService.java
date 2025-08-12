package com.payroll.payroll_system.service;

import com.payroll.payroll_system.dto.RoleDto.RoleInDto;
import com.payroll.payroll_system.dto.RoleDto.RoleOutDto;
import com.payroll.payroll_system.mapper.RoleMapper;
import com.payroll.payroll_system.model.Role;
import com.payroll.payroll_system.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    @Autowired
    private final RoleRepo repo;
    @Autowired
    private final RoleMapper mapper;

    public RoleOutDto createRole(RoleInDto dto){
        Role role = mapper.toEntity(dto);
        Role savedRole = repo.save(role);
        return mapper.toDto(savedRole);
    }
    public List<RoleOutDto> getAllRoles(){
        return repo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
