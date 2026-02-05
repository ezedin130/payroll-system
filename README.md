# Payroll Management System

[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-orange)](LICENSE)

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [System Architecture](#system-architecture)
- [Database Models](#database-models)
- [APIs](#apis)
- [Authentication & Authorization](#authentication--authorization)
- [Running the Project](#running-the-project)
- [Future Enhancements](#future-enhancements)

---

## Overview
This is a **Payroll Management System** built with Spring Boot, Hibernate, and MySQL.  
It automates payroll calculations, employee leave management, and user role handling while ensuring security with JWT-based authentication.

The system handles:
- Automatic payroll processing based on monthly schedules.
- Employee leave requests and approval workflow.
- User registration and role management.
- Password management with hashing.
- Company deposit tracking and salary disbursement simulation.

---

## Features

### Employee Management
- Register new employees with default roles.
- Calculate **gross salary**, **total allowances**, **deductions**, **income tax**, and **net salary** automatically.
- Track employee deposit balance and salary payments.

### Payroll Management
- Automatic monthly payroll execution for active employees.
- Updates employee deposits and company deposits accordingly.
- Generates payslips automatically for each payment.

### Leave Management
- Employees can request leave.
- Leave requests can be approved or rejected by Admin/HR.
- Employee status changes to `ON_LEAVE` when leave is approved.

### User & Role Management
- Users have roles (Employee, Admin, HR) with permissions.
- JWT-based authentication for API security.
- Users can change their own password; Admin can reset others' passwords.

---

## Technologies
- **Java 17**
- **Spring Boot 3**
- **Spring Security with JWT**
- **Hibernate / JPA**
- **MySQL** (or any relational DB)
- **Lombok**
- **Maven**
- **Postman** for API testing

---

## System Architecture
+ Client (Postman / Frontend)

+ Spring Boot REST API

+ EmployeeService / PayrollService / LeaveService / UserService
|
+ MySQL Database


---

## Database Models

### Employee
| Field           | Type        | Description                     |
|-----------------|------------|---------------------------------|
| id              | Long       | Primary Key                     |
| firstName       | String     | Employee first name             |
| lastName        | String     | Employee last name              |
| baseSalary      | Double     | Base salary                     |
| totalAllowance  | Double     | Sum of allowances               |
| totalDeduction  | Double     | Sum of deductions               |
| netSalary       | Double     | Net salary after deductions     |
| deposit         | Double     | Employee account balance        |
| status          | Enum       | ACTIVE / ON_LEAVE / INACTIVE   |

### PaySlip
| Field           | Type        | Description                     |
|-----------------|------------|---------------------------------|
| id              | Long       | Primary Key                     |
| grossSalary     | Double     | Base + Allowances               |
| totalAllowance  | Double     | Total allowance                 |
| totalDeduction  | Double     | Pension + Tax                   |
| netSalary       | Double     | Net salary                      |
| employeePension | Double     | Pension deduction               |
| incomeTax       | Double     | Calculated income tax           |
| empId           | Employee   | Reference to Employee           |

### Payroll
| Field           | Type        | Description                     |
|-----------------|------------|---------------------------------|
| id              | Long       | Primary Key                     |
| startDate       | LocalDate  | Payroll period start             |
| endDate         | LocalDate  | Payroll period end               |
| status          | String     | COMPLETED / PENDING             |
| processedAt     | LocalDateTime | Timestamp of processing       |

### LeaveRequest
| Field           | Type        | Description                     |
|-----------------|------------|---------------------------------|
| id              | Long       | Primary Key                     |
| leaveType       | Enum       | SICK / VACATION / UNPAID        |
| startDate       | LocalDate  | Leave start date                |
| endDate         | LocalDate  | Leave end date                  |
| status          | Enum       | PENDING / APPROVED / REJECTED  |
| empId           | Employee   | Reference to Employee           |

---

## APIs

### Authentication
- **POST** `/user/login`  
  Request:
  ```json
  {
    "username": "jdoe",
    "password": "jdoe12"
  }
  Response:
  {
    "token": "Bearer <jwt-token>"
  }

## API Endpoints

### Employee
- **POST** `/employee/create-employee`  
  Registers an employee and automatically creates a user account.

- **GET** `/employee/{id}`  
  Get employee details, including calculated salary fields (total allowance, net salary, etc.).

---

### Payroll
- **POST** `/payroll/run`  
  Run payroll for active employees. Requires JWT token in `Authorization` header.

- **GET** `/payroll/all`  
  List all payrolls.

---

### Leave
- **POST** `/leave/request`  
  Create a leave request for an employee.

- **PUT** `/leave/process/{id}?approve=true`  
  Approve or reject a leave request. Only accessible by Admin/HR.

---

### User
- **PUT** `/user/change-password`  
  Change own password.

- **PUT** `/user/{id}/reset-password`  
  Admin resets a user's password.

- **PUT** `/user/{id}/change-role`  
  Request a user role change. Requires approval by Admin/HR.

---

## Authentication & Authorization
- **JWT-based stateless authentication**.
- Roles determine API access:
    - **Employee** → Can view own info, request leave, change own password.
    - **Admin / HR** → Can approve leave, reset passwords, process role change requests, run payroll.
- Secured endpoints require the following header: Bearer token
- 
---
## Running the Project

1. **Clone the repository**
   ```bash
   git clone https://github.com/ezedin130/payroll-system.git
2. **Configure the database**
   ```bash
   Update application.properties with your database credentials.

3. **Build and run teh project**
   ```bash
    mvn clean install
    mvn spring-boot:run
4. **Test the APIs**
   ```bash
   Use Postman or any REST client with JWT tokens for authentication.
   
---

## 🚀 Future Enhancements

- **Frontend UI:** Interactive dashboards for both Employees and Admin/HR.
- **Automated Notifications:** Email alerts for payroll disbursements and leave status updates.
- **Multi-tenant Architecture:** Full support for multi-company management.
- **Audit Trails:** Detailed logging for all configuration and data changes.
- **Dynamic Tax Engine:** Configurable tax slabs stored in the database for advanced calculations.
