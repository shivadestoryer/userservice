package com.users.userservice.services.impl;

import com.users.userservice.dao.Role;
import com.users.userservice.dao.User;
import com.users.userservice.dto.request.LogingRequestDto;
import com.users.userservice.services.UserCreationService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserCreationServiceImpl implements UserCreationService {

    BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserCreationServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
    @Override
    public User createUser(LogingRequestDto logingRequestDto) {
        User user=new User();
        user.setEmail(logingRequestDto.getEmail());
        user.setUsername(logingRequestDto.getUserName());
        user.setCreatedAt(LocalDateTime.now());
        user.setPasswordHash(EncryptPassword(logingRequestDto.getPassword()));
        user.setRoles(getRole(logingRequestDto.getRoleId()));
        return null;
    }

    private String EncryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    private Set<Role> getRole(List<UUID> roleId) {
        return null;
    }
}
