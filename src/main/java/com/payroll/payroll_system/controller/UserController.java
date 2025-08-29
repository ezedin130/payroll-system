package com.payroll.payroll_system.controller;

import com.payroll.payroll_system.dto.AuthResponse;
import com.payroll.payroll_system.dto.UserDto.UserInDto;
import com.payroll.payroll_system.dto.UserDto.UserLoginDto;
import com.payroll.payroll_system.dto.UserDto.UserOutDto;
import com.payroll.payroll_system.mapper.UserMapper;
import com.payroll.payroll_system.model.User;
import com.payroll.payroll_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @PostMapping("/create-user")
    public ResponseEntity<UserOutDto> createUser(@RequestBody UserInDto dto){
        UserOutDto result = service.createUser(dto);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserLoginDto user) {
        return service.verify(user);
    }
    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserOutDto>> getAllUsers(){
        List<UserOutDto> result = service.getAllUsers();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-user-by-id/{id}")
    public ResponseEntity<UserOutDto> getUserById(@PathVariable Long id){
        User result = service.findUserById(id);
        UserOutDto dto = mapper.toDto(result);
        return ResponseEntity.ok(dto);
    }
}
