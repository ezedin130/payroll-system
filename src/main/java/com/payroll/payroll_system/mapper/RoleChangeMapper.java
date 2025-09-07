package com.payroll.payroll_system.mapper;

import com.payroll.payroll_system.dto.RoleChangeDto.RoleChangeInDto;
import com.payroll.payroll_system.dto.RoleChangeDto.RoleChangeOutDto;
import com.payroll.payroll_system.model.Role;
import com.payroll.payroll_system.model.RoleChangeRequest;
import com.payroll.payroll_system.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class RoleChangeMapper {
    public RoleChangeRequest toEntity(RoleChangeInDto dto, User user, Role role){
        return RoleChangeRequest.builder()
                .userId(user)
                .requestedRoleId(role)
                .createdAt(LocalDate.now())
                .build();
    }
    public RoleChangeOutDto toDto(RoleChangeRequest request){
        RoleChangeOutDto roleChange = new RoleChangeOutDto();
        roleChange.setId(request.getId());
        roleChange.setUserId(request.getUserId().getId());
        roleChange.setRequestedRoleId(request.getRequestedRoleId().getId());
        roleChange.setStatus(request.getStatus().name());
        roleChange.setCreatedAt(request.getCreatedAt());
        roleChange.setReviewedBy(request.getReviewedBy().getId());
        roleChange.setRequestedRoleId(request.getRequestedRoleId().getId());
        return roleChange;
    }
}
