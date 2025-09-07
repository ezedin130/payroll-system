package com.payroll.payroll_system.service;

import com.payroll.payroll_system.dto.CompanyDto.CompanyOutDto;
import com.payroll.payroll_system.dto.RoleChangeDto.RoleChangeInDto;
import com.payroll.payroll_system.dto.RoleChangeDto.RoleChangeOutDto;
import com.payroll.payroll_system.enums.status.RoleChangeStatus;
import com.payroll.payroll_system.mapper.RoleChangeMapper;
import com.payroll.payroll_system.model.Role;
import com.payroll.payroll_system.model.RoleChangeRequest;
import com.payroll.payroll_system.model.User;
import com.payroll.payroll_system.repo.RoleChangeRequestRepo;
import com.payroll.payroll_system.repo.RoleRepo;
import com.payroll.payroll_system.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleChangeService {
    private final RoleChangeRequestRepo requestRepo;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final RoleChangeMapper mapper;

    public RoleChangeOutDto createRoleChangeRequest(RoleChangeInDto dto) {
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepo.findById(dto.getRequestedRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        RoleChangeRequest request = mapper.toEntity(dto,user,role);
        request.setStatus(RoleChangeStatus.PENDING);
        RoleChangeRequest savedRequest = requestRepo.save(request);
        return mapper.toDto(savedRequest);
    }

    public RoleChangeOutDto processRoleChangeRequest(Long requestId, Long approverId, boolean approve) {
        RoleChangeRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Role change request not found"));

        User approver = userRepo.findById(approverId)
                .orElseThrow(() -> new RuntimeException("Approver not found"));

        request.setReviewedBy(approver);
        request.setStatus(approve ? RoleChangeStatus.APPROVED : RoleChangeStatus.REJECTED);

        if (approve) {
            User user = request.getUserId();
            user.setRoleId(request.getRequestedRoleId());
            userRepo.save(user);
        }

        RoleChangeRequest savedRequest = requestRepo.save(request);
        return mapper.toDto(savedRequest);
    }
    public List<RoleChangeOutDto> getAllRequests(){
        return requestRepo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    public RoleChangeRequest findRequestById(Long id){
        return requestRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User with this id is not found"));
    }
}
