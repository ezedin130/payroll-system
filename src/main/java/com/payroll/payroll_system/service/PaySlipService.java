package com.payroll.payroll_system.service;

import com.payroll.payroll_system.dto.PaySlipDto.PaySlipInDto;
import com.payroll.payroll_system.dto.PaySlipDto.PaySlipOutDto;
import com.payroll.payroll_system.enums.AllowanceType;
import com.payroll.payroll_system.enums.Position;
import com.payroll.payroll_system.mapper.PaySlipMapper;
import com.payroll.payroll_system.model.Employee;
import com.payroll.payroll_system.model.PaySlip;
import com.payroll.payroll_system.repo.EmployeeRepo;
import com.payroll.payroll_system.repo.PaySlipRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaySlipService {
    @Autowired
    private final PaySlipRepo slipRepo;
    @Autowired
    private final EmployeeRepo empRepo;
    @Autowired
    private final PaySlipMapper mapper;

    public PaySlipOutDto createPaySlip(PaySlipInDto dto){
        Employee empl = empRepo.findById(dto.getEmpId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        double totalAllowance = calculateTotalAllowance(empl);
        double grossSalary = calculateGrossSalary(empl,totalAllowance);
        double pension = calculatePension(grossSalary);
        double taxableIncome = calculateTaxableIncome(grossSalary,pension);
        double incomeTax = calculateIncomeTax(taxableIncome);
        double totalDeduction = calculateTotalDeduction(incomeTax,pension);
        double netSalary = calculateNetSalary(grossSalary,pension,incomeTax);

        PaySlip slip = PaySlip.builder()
                .empId(empl)
                .grossSalary(grossSalary)
                .totalDeduction(totalDeduction)
                .totalAllowance(totalAllowance)
                .taxableIncome(taxableIncome)
                .employeePension(pension)
                .incomeTax(incomeTax)
                .netSalary(netSalary)
                .build();
        PaySlip savedSlip = slipRepo.save(slip);
        return mapper.toDto(savedSlip);
    }

    public double calculatePension(double grossSalary) {
        return grossSalary * 0.07;
    }

    public double calculateTotalAllowance(Employee emp) {
        Position position = Position.valueOf(emp.getPosition());
        double total = 0.0;
        for(AllowanceType type : AllowanceType.values()){
            double amount = type.getAmountForPosition(position);
            total += amount;
        }
        return total;
    }

    public double calculateGrossSalary(Employee empl, double totalAllowance) {
        return empl.getBaseSalary() + totalAllowance;
    }

    public double calculateTotalDeduction(double pension, double incomeTax) {
        return pension + incomeTax;
    }

    public double calculateTaxableIncome(double grossSalary, double pension){

        return grossSalary - pension;
    }
    public double calculateIncomeTax(double taxableIncome){
        double rate = getRate(taxableIncome);
        double deductible = getDeductible(taxableIncome);
        return (taxableIncome * rate) - deductible;
    }

    private double getRate(double taxableIncome) {
        if (taxableIncome <= 2000) return 0.0;
        else if (taxableIncome <= 4000) return 0.15;
        else if (taxableIncome <= 7000) return 0.20;
        else if (taxableIncome <= 10000) return 0.25;
        else if (taxableIncome <= 14000) return 0.30;
        else return 0.35;
    }

    private double getDeductible(double taxableIncome) {
        if (taxableIncome <= 2000) return 0.0;
        else if (taxableIncome <= 4000) return 300;
        else if (taxableIncome <= 7000) return 500;
        else if (taxableIncome <= 10000) return 850;
        else if (taxableIncome <= 14000) return 1350;
        else return 2050;
    }

    public double calculateNetSalary(double grossSalary, double pension, double incomeTax) {
        return grossSalary - pension - incomeTax;
    }

    public List<PaySlipOutDto> getAllSlips(){
        return slipRepo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}
