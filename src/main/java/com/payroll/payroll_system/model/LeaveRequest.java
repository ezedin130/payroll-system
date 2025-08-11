package com.payroll.payroll_system.model;

import com.payroll.payroll_system.enums.LeaveType;
import com.payroll.payroll_system.enums.status.LeaveRequestStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "leave type is required")
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;
    @NotNull(message = "start date is required")
    private LocalDate startDate;
    @NotNull(message = "end date is required")
    private LocalDate endDate;
    @NotNull(message = "leave status is required")
    @Enumerated(EnumType.STRING)
    private LeaveRequestStatus status;
    @NotNull(message = "leave status is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee empId;
}
