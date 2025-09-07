package com.payroll.payroll_system.controller;

import com.payroll.payroll_system.dto.RoleChangeDto.RoleChangeInDto;
import com.payroll.payroll_system.dto.RoleChangeDto.RoleChangeOutDto;
import com.payroll.payroll_system.dto.UserDto.UserOutDto;
import com.payroll.payroll_system.mapper.RoleChangeMapper;
import com.payroll.payroll_system.model.RoleChangeRequest;
import com.payroll.payroll_system.model.User;
import com.payroll.payroll_system.model.UserPrincipal;
import com.payroll.payroll_system.repo.UserRepo;
import com.payroll.payroll_system.service.RoleChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role-change")
@RequiredArgsConstructor
public class RoleChangeController {
    @Autowired
    private final RoleChangeService service;
    @Autowired
    private final RoleChangeMapper mapper;
    @Autowired
    private final UserRepo repo;

    @PostMapping("/request")
    public ResponseEntity<RoleChangeOutDto> createRequest(@RequestBody RoleChangeInDto dto) {
        RoleChangeOutDto createdRequest = service.createRoleChangeRequest(dto);
        return ResponseEntity.ok(createdRequest);
    }

    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @PutMapping("/process/{requestId}")
    public ResponseEntity<RoleChangeOutDto> processRequest(
            @PathVariable Long requestId,
            @RequestParam boolean approve
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long approverId = repo.findByUsername(username).getId();


        RoleChangeOutDto processedRequest = service.processRoleChangeRequest(requestId, approverId, approve);
        return ResponseEntity.ok(processedRequest);
    }

    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @GetMapping("/all")
    public ResponseEntity<List<RoleChangeOutDto>> getAllRequests() {
        List<RoleChangeOutDto> requests = service.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @GetMapping("/{id}")
    public ResponseEntity<RoleChangeOutDto> getRequest(@PathVariable Long id) {
        RoleChangeRequest result = service.findRequestById(id);
        RoleChangeOutDto dto = mapper.toDto(result);
        return ResponseEntity.ok(dto);
    }
}
