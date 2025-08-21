package com.payroll.payroll_system.service;

import com.payroll.payroll_system.dto.PaySlipDto.PaySlipInDto;
import com.payroll.payroll_system.dto.PaySlipDto.PaySlipOutDto;
import com.payroll.payroll_system.dto.PayrollPeriodDto.PayrollPeriodInDto;
import com.payroll.payroll_system.dto.PayrollPeriodDto.PayrollPeriodOutDto;
import com.payroll.payroll_system.enums.status.EmployeeStatus;
import com.payroll.payroll_system.enums.status.PayrollStatus;
import com.payroll.payroll_system.mapper.PayrollPeriodMapper;
import com.payroll.payroll_system.model.Company;
import com.payroll.payroll_system.model.Employee;
import com.payroll.payroll_system.model.PaySlip;
import com.payroll.payroll_system.model.PayrollPeriod;
import com.payroll.payroll_system.repo.CompanyRepo;
import com.payroll.payroll_system.repo.EmployeeRepo;
import com.payroll.payroll_system.repo.PaySlipRepo;
import com.payroll.payroll_system.repo.PayrollPeriodRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayrollPeriodService {
    private final EmployeeRepo employeeRepo;
    private final PaySlipService paySlipService;
    private final PaySlipRepo paySlipRepo;
    private final PayrollPeriodRepo payrollRepo;
    private final CompanyRepo companyRepo;
    private final PayrollPeriodMapper mapper;

    // Scheduled to run at 1st of every month at 00:00
    @Scheduled(cron = "0 0 0 1 * *")
    public void runPayroll() {
        List<Employee> activeEmployees = employeeRepo.findByStatus(EmployeeStatus.Active);

        if (activeEmployees.isEmpty()) return;

        Company company = companyRepo.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Company not found"));

        double totalPayrollCost = 0;

        PayrollPeriod payroll = PayrollPeriod.builder()
                .startDate(LocalDate.now().withDayOfMonth(1))
                .endDate(LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()))
                .status(PayrollStatus.PROCESSING)
                .processedAt(LocalDateTime.now())
                .build();

        payrollRepo.save(payroll);

        for (Employee emp : activeEmployees) {
            double totalAllowance = paySlipService.calculateTotalAllowance(emp);
            double grossSalary = paySlipService.calculateGrossSalary(emp, totalAllowance);
            double pension = paySlipService.calculatePension(grossSalary);
            double taxableIncome = paySlipService.calculateTaxableIncome(grossSalary, pension);
            double incomeTax = paySlipService.calculateIncomeTax(taxableIncome);
            double totalDeduction = paySlipService.calculateTotalDeduction(pension, incomeTax);
            double netSalary = paySlipService.calculateNetSalary(grossSalary, pension, incomeTax);

            emp.setDeposit(emp.getDeposit() + netSalary);
            employeeRepo.save(emp);

            company.setDeposit(company.getDeposit() - netSalary);

            PaySlip slip = PaySlip.builder()
                    .empId(emp)
                    .grossSalary(grossSalary)
                    .totalAllowance(totalAllowance)
                    .totalDeduction(totalDeduction)
                    .taxableIncome(taxableIncome)
                    .employeePension(pension)
                    .incomeTax(incomeTax)
                    .netSalary(netSalary)
                    .build();

            paySlipRepo.save(slip);

            totalPayrollCost += netSalary;
        }

        companyRepo.save(company);

        payroll.setStatus(PayrollStatus.COMPLETED);
        payrollRepo.save(payroll);

        System.out.println("Payroll processed. Total cost: " + totalPayrollCost);
    }
    public List<PayrollPeriodOutDto> getAllPayrolls(){
        return payrollRepo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public PayrollPeriod getPayrollById(Long id) {
        return payrollRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll with this id is not found"));
    }
}
