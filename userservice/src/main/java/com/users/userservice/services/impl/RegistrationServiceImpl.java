package com.users.userservice.services.impl;

import com.users.userservice.dao.Token;
import com.users.userservice.dao.TokenStatus;
import com.users.userservice.dao.User;
import com.users.userservice.dto.request.LogingRequestDto;
import com.users.userservice.dto.response.LoginResponseDto;
import com.users.userservice.repository.UserRepository;
import com.users.userservice.services.RegistrationService;
import com.users.userservice.services.UserCreationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final UserCreationService userCreationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;// Inject BCryptPasswordEncoder

    @Value("${jwt.secret}")
    private String secretKey;

    public RegistrationServiceImpl(UserRepository userRepository,
                                   UserCreationService userCreationService,
                                   BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userCreationService = userCreationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
        log.info(String.valueOf(logingRequestDto));

        Optional<User> user = userRepository.findByUsername(logingRequestDto.getUserName());
        LoginResponseDto responseDto=new LoginResponseDto();
        if (user.isPresent()) {
            // Fix: Correct argument order in matches()
            log.info(String.valueOf(user));
            if (bCryptPasswordEncoder.matches(logingRequestDto.getPassword(), user.get().getPasswordHash())) {
                // Additional Claims

              User user1=  user.get();
                Map<String, Object> claims = new HashMap<>();
                claims.put("role", "temp user");
                claims.put("email", user1.getEmail());
                claims.put("organization", "temp org");
                claims.put("permissions","temp permissions");
                claims.put("jti", UUID.randomUUID().toString());

                // Generate JWT token
                String token1 = Jwts.builder()
                        .setClaims(claims)
                        .setSubject(user1.getUsername()) // 'sub' claim
                        .setIssuer("MyApp") // 'iss' claim
                        .setIssuedAt(new Date()) // 'iat' claim
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expiry (1 hour)
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact();




                Token token = new Token();
                token.setTokenValue(token1);
                token.setUser(user.get());
                token.setStatus(TokenStatus.ACTIVE);

                responseDto.setStatus("Active");
                responseDto.setToken(token);


                return  responseDto;
            }

        } else {
            responseDto.setStatus("password mismatch");
            return responseDto;
        }
        return responseDto;
    }
    @Override
    public void logout(String token) {

    }
}
