package com.payroll.payroll_system.mapper;

import com.payroll.payroll_system.dto.CompanyDto.CompanyInDto;
import com.payroll.payroll_system.dto.CompanyDto.CompanyOutDto;
import com.payroll.payroll_system.model.Company;
import org.springframework.stereotype.Service;

@Service
public class CompanyMapper {
    public Company toEntity(CompanyInDto dto){
        return Company.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .deposit(dto.getDeposit())
                .build();
    }
    public CompanyOutDto toDto(Company company){
        CompanyOutDto dto = new CompanyOutDto();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setAddress(company.getAddress());
        dto.setDeposit(company.getDeposit());
        return dto;
    }

}
