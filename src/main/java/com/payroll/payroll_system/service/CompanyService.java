package com.payroll.payroll_system.service;

import com.payroll.payroll_system.dto.CompanyDto.CompanyInDto;
import com.payroll.payroll_system.dto.CompanyDto.CompanyOutDto;
import com.payroll.payroll_system.mapper.CompanyMapper;
import com.payroll.payroll_system.model.Company;
import com.payroll.payroll_system.repo.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    @Autowired
    private final CompanyRepo repo;
    @Autowired
    private final CompanyMapper mapper;

    public CompanyOutDto createCompany(CompanyInDto dto){
        Company company = mapper.toEntity(dto);
        Company savedCompany = repo.save(company);
        return mapper.toDto(savedCompany);
    }
    public List<CompanyOutDto> getAllCompanies(){
        return repo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
