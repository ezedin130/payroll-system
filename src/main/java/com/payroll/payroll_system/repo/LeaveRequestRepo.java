package com.payroll.payroll_system.repo;

import com.payroll.payroll_system.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestRepo extends JpaRepository<LeaveRequest,Long> {
}
