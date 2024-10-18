package com.library.service;

import com.library.dto.user.UserRegistrationRequestDto;
import com.library.dto.user.UserResponseDto;
import com.library.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
