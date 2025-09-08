package com.payroll.payroll_system.service;

import com.payroll.payroll_system.dto.LeaveRequestDto.LeaveRequestInDto;
import com.payroll.payroll_system.dto.LeaveRequestDto.LeaveRequestOutDto;
import com.payroll.payroll_system.enums.status.EmployeeStatus;
import com.payroll.payroll_system.enums.status.LeaveRequestStatus;
import com.payroll.payroll_system.mapper.LeaveRequestMapper;
import com.payroll.payroll_system.model.Employee;
import com.payroll.payroll_system.model.LeaveRequest;
import com.payroll.payroll_system.model.User;
import com.payroll.payroll_system.repo.EmployeeRepo;
import com.payroll.payroll_system.repo.LeaveRequestRepo;
import com.payroll.payroll_system.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {
    @Autowired
    private final LeaveRequestRepo reqRepo;
    @Autowired
    private final EmployeeRepo emplRepo;
    @Autowired
    private final LeaveRequestMapper mapper;
    @Autowired
    private final LeaveRequestRepo leaveRepo;
    @Autowired
    private final UserRepo userRepo;

    public LeaveRequestOutDto createLeaveRequest(LeaveRequestInDto dto) {
        Employee employee = emplRepo.findById(dto.getEmpId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        LeaveRequest request = mapper.toEntity(dto, employee);
        LeaveRequest saved = leaveRepo.save(request);
        return mapper.toDto(saved);
    }

    @Transactional
    public LeaveRequestOutDto processLeaveRequest(Long requestId, boolean approve, Long approverId) {
        LeaveRequest request = leaveRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        User approver = userRepo.findById(approverId)
                .orElseThrow(() -> new RuntimeException("Approver not found"));

        request.setReviewedBy(approver);
        request.setReviewedAt(LocalDate.now());

        if (approve) {
            request.setStatus(LeaveRequestStatus.APPROVED);

            // Update employee status to ON_LEAVE
            Employee employee = request.getEmpId();
            employee.setStatus(EmployeeStatus.OnLeave);
            emplRepo.save(employee);
        } else {
            request.setStatus(LeaveRequestStatus.REJECTED);
        }

        LeaveRequest saved = leaveRepo.save(request);
        return mapper.toDto(saved);
    }
    public List<LeaveRequestOutDto> getAllRequests(){
        return reqRepo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    public LeaveRequest searchRequestById(Long id){
        return reqRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Request with this is id is not found"));
    }
}
