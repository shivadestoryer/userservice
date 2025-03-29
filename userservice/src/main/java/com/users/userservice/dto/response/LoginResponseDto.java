package com.users.userservice.dto.response;

import com.users.userservice.dao.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private String userName;
    private LocalDateTime timestamp;
    private Token token;
    private String status;


}
