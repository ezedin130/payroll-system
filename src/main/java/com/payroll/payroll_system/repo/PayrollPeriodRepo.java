package com.payroll.payroll_system.repo;

import com.payroll.payroll_system.model.PayrollPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollPeriodRepo extends JpaRepository<PayrollPeriod,Long> {
}
