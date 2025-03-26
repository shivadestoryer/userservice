package com.users.userservice.services;

import com.users.userservice.dto.request.LogingRequestDto;
import com.users.userservice.dto.response.LoginResponseDto;

public interface RegistrationService {
    public void registerUser(LogingRequestDto logingRequestDto);

    public LoginResponseDto login(LogingRequestDto logingRequestDto);

    void logout(String token);
}
