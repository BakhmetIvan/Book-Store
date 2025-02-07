package com.library.service.impl;

import com.library.dto.user.UserRegistrationRequestDto;
import com.library.dto.user.UserResponseDto;
import com.library.mapper.UserMapper;
import com.library.model.Role;
import com.library.model.User;
import com.library.service.ShoppingCartService;
import com.library.service.UserService;
import lombok.RequiredArgsConstructor;
import com.library.exception.RegistrationException;
import com.library.repository.role.RoleRepository;
import com.library.repository.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ShoppingCartService shoppingCartService;

    @Transactional
    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Email already registered: " + requestDto.getEmail());
        }
        User user = userMapper.toModel(requestDto);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(Role.RoleName.ROLE_USER));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user = userRepository.save(user);
        shoppingCartService.createShoppingCart(user);
        return userMapper.toDto(user);
    }
}
