package com.payroll.payroll_system.model;

import com.payroll.payroll_system.enums.status.RoleChangeStatus;
import jakarta.persistence.*;
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
public class RoleChangeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User userId;

    @ManyToOne
    private Role requestedRoleId;

    @Enumerated(EnumType.STRING)
    private RoleChangeStatus status;

    private LocalDate createdAt;

    @ManyToOne
    private User reviewedBy;
}
