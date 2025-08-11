package com.payroll.payroll_system.dto.CompanyDto;

import lombok.Data;

@Data
public class CompanyOutDto {
    private Long id;
    private String name;
    private String address;
    private double deposit;
}
