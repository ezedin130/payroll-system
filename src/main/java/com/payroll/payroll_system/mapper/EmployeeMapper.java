package com.payroll.payroll_system.mapper;

import com.payroll.payroll_system.dto.EmployeeDto.EmployeeInDto;
import com.payroll.payroll_system.dto.EmployeeDto.EmployeeOutDto;
import com.payroll.payroll_system.enums.status.EmployeeStatus;
import com.payroll.payroll_system.model.Department;
import com.payroll.payroll_system.model.Employee;
import com.payroll.payroll_system.model.Role;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmployeeMapper {
    public Employee toEntity(EmployeeInDto dto, Department dept , Role role){
        return Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phoneNumber(dto.getPhoneNumber())
                .bankAccountNumber(dto.getBankAccountNumber())
                .hireDate(LocalDate.now())
                .workingdays(dto.getWorkingdays())
                .baseSalary(dto.getBaseSalary())
                .deposit(dto.getDeposit())
                .status(EmployeeStatus.valueOf(dto.getStatus()))
                .deptId(dept)
                .roleId(role)
                .build();
    }
    public EmployeeOutDto toDto(Employee empl){
        EmployeeOutDto dto = new EmployeeOutDto();
        dto.setId(empl.getId());
        dto.setFirstName(empl.getFirstName());
        dto.setLastName(empl.getLastName());
        dto.setPhoneNumber(empl.getPhoneNumber());
        dto.setBankAccountNumber(empl.getBankAccountNumber());
        dto.setHireDate(empl.getHireDate());
        dto.setWorkingdays(empl.getWorkingdays());
        dto.setBaseSalary(empl.getBaseSalary());
        dto.setDeposit(empl.getDeposit());
        dto.setStatus(empl.getStatus().name());
        dto.setDeptId(empl.getDeptId().getId());
        dto.setRoleId(empl.getRoleId().getId());
        return dto;
    }
}
