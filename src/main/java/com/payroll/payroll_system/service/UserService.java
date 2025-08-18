package com.payroll.payroll_system.service;

import com.payroll.payroll_system.dto.UserDto.UserInDto;
import com.payroll.payroll_system.dto.UserDto.UserOutDto;
import com.payroll.payroll_system.mapper.UserMapper;
import com.payroll.payroll_system.model.Employee;
import com.payroll.payroll_system.model.User;
import com.payroll.payroll_system.repo.EmployeeRepo;
import com.payroll.payroll_system.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final EmployeeRepo empRepo;
    @Autowired
    private final UserMapper mapper;
    private PasswordEncoder passwordEncoder;
    public UserOutDto createUser(UserInDto dto){
        Employee empl = empRepo.findById(dto.getEmpId())
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
        User user = mapper.toEntity(dto,empl);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User savedUser = userRepo.save(user);
        return mapper.toDto(savedUser);
    }
    public List<UserOutDto> getAllUsers(){
        return userRepo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    public User findUserById(Long id){
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User with this id is not found"));
    }
}
