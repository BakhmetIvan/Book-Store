package com.library.mapper;

import com.library.config.MapperConfig;
import com.library.dto.user.UserRegistrationRequestDto;
import com.library.dto.user.UserResponseDto;
import com.library.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    @Mapping(target = "password", source = "password")
    User toModel(UserRegistrationRequestDto requestDto);
}
