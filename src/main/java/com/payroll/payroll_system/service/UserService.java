package com.payroll.payroll_system.service;

import com.payroll.payroll_system.dto.AuthResponse;
import com.payroll.payroll_system.dto.UserDto.UserInDto;
import com.payroll.payroll_system.dto.UserDto.UserLoginDto;
import com.payroll.payroll_system.dto.UserDto.UserOutDto;
import com.payroll.payroll_system.mapper.UserMapper;
import com.payroll.payroll_system.model.Employee;
import com.payroll.payroll_system.model.Role;
import com.payroll.payroll_system.model.User;
import com.payroll.payroll_system.repo.EmployeeRepo;
import com.payroll.payroll_system.repo.RoleRepo;
import com.payroll.payroll_system.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    @Autowired
    private final RoleRepo roleRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private final AuthenticationManager authManager;
    @Autowired
    private final JwtService jwtService;
    public UserOutDto createUser(UserInDto dto){
        Employee empl = empRepo.findById(dto.getEmpId())
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
        Role role = roleRepo.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role Not Found"));
        User user = mapper.toEntity(dto,empl,role);
        user.setPassword(encoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user);
        return mapper.toDto(savedUser);
    }
    public AuthResponse verify(UserLoginDto user) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (auth.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String accessToken = jwtService.generateToken(user.getUsername());

            User users = userRepo.findByUsername(user.getUsername());

            return new AuthResponse(
                    accessToken,
                    users.getUsername(),
                    users.getRoleId().getName()
            );
        }
        return null;
    }
    public UserOutDto createUserForEmployee(Employee employee) {
        String username = generateUsername(employee.getFirstName(), employee.getLastName());
        String rawPassword = generateRandomPassword(6);
        String hashedPassword = encoder.encode(rawPassword);

        Role role = roleRepo.findByName("EMPLOYEE")
                .orElseThrow(() -> new RuntimeException("Default role EMPLOYEE not found"));

        User user = User.builder()
                .username(username)
                .password(hashedPassword)
                .createdAt(LocalDate.now())
                .empId(employee)
                .roleId(role)
                .build();

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
    private String generateUsername(String firstName, String lastName) {
        return firstName.toLowerCase() + lastName.charAt(0);
    }

    private String generateRandomPassword(int length) {
        return java.util.UUID.randomUUID().toString().substring(0, length);
    }
}
