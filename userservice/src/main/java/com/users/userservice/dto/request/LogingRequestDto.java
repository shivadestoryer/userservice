package com.users.userservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogingRequestDto {
    private String userName;
    private String email;
    private String password;
    private List<UUID> roleId;

}
