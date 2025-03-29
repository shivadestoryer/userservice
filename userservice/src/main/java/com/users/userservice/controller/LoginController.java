package com.users.userservice.controller;

import com.users.userservice.dto.request.LogingRequestDto;
import com.users.userservice.dto.response.LoginResponseDto;
import com.users.userservice.services.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody LogingRequestDto loginRequestDto) {
        LoginResponseDto responseDto = registrationService.login(loginRequestDto);
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
