package com.users.userservice.services.impl;

import com.users.userservice.dao.User;
import com.users.userservice.dto.request.LogingRequestDto;
import com.users.userservice.dto.response.LoginResponseDto;
import com.users.userservice.repository.UserRepository;
import com.users.userservice.services.RegistrationService;
import com.users.userservice.services.UserCreationService;
import org.springframework.security.core.token.Token;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    UserRepository userRepository;
    UserCreationService userCreationService;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    RegistrationServiceImpl(UserRepository userRepository,UserCreationService userCreationService,
                            BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userRepository=userRepository;
        this.userCreationService=userCreationService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }



    @Override
    public void registerUser(LogingRequestDto logingRequestDto) {
        validateUser(logingRequestDto);
        userRepository.save(userCreationService.createUser(logingRequestDto));

    }

    private boolean validateUser(LogingRequestDto logingRequestDto) {
    return false;
    }

    @Override
    public LoginResponseDto login(LogingRequestDto logingRequestDto) {
        Optional<User> user=userRepository.findByUsername(logingRequestDto.getUserName());
        if(bCryptPasswordEncoder.matches(user.get().getPasswordHash(), logingRequestDto.getPassword()))
        {
//return token;
        }
        return null;
    }

    @Override
    public void logout(String token) {

    }
}
