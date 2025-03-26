package com.users.userservice.services;

import com.users.userservice.dao.User;
import com.users.userservice.dto.request.LogingRequestDto;

public interface UserCreationService {
    User createUser(LogingRequestDto logingRequestDto);
}
