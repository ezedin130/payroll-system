package com.payroll.payroll_system.repo;

import com.payroll.payroll_system.model.PaySlip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaySlipRepo extends JpaRepository<PaySlip,Long> {
}
