package com.users.userservice.controller;

import com.users.userservice.dto.request.LogingRequestDto;
import com.users.userservice.dto.response.LoginResponseDto;
import com.users.userservice.services.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/access-management")
public class LoginController {

    RegistrationService registrationService;
    public LoginController(RegistrationService registrationService)
    {
        this.registrationService=registrationService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Void> signUp(@RequestBody LogingRequestDto loginRequestDto) {
        registrationService.registerUser(loginRequestDto);
        return ResponseEntity.accepted().build();
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LogingRequestDto logingRequestDto) {
        LoginResponseDto responseDto=registrationService.login(logingRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logOut(@RequestBody String token) {
        registrationService.logout(token);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<LoginResponseDto> validateToken(LogingRequestDto logingRequestDto) {
        return null;
    }


}
