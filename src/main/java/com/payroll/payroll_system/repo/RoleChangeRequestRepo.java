package com.payroll.payroll_system.repo;

import com.payroll.payroll_system.model.RoleChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleChangeRequestRepo extends JpaRepository<RoleChangeRequest,Long> {
}
