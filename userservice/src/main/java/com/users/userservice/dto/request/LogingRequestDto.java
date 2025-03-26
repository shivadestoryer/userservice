package com.users.userservice.dto.request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class LogingRequestDto {
    private String userName;
    private String email;
    private String password;
    private List<UUID> roleId;
}
