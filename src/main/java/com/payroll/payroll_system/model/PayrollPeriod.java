package com.payroll.payroll_system.model;

import com.payroll.payroll_system.enums.status.PayrollStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayrollPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "start date is required")
    private LocalDate startDate;
    @NotNull(message = "end date is required")
    private LocalDate endDate;
    @NotNull(message = "start date is required")
    @Enumerated(EnumType.STRING)
    private PayrollStatus status;
    @NotNull(message = "processed at is required")
    private LocalDateTime processedAt;
}
