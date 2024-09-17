package com.rest.jwt_auth.service.impl;

import static com.rest.jwt_auth.utils.Constants.ENTITY_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.rest.jwt_auth.utils.Constants.ROLE_ADMIN;
import static com.rest.jwt_auth.utils.Constants.ROLE_USER;
import static com.rest.jwt_auth.utils.Constants.USERNAME_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.rest.jwt_auth.utils.ResponseUtils.DATA_SOURCE_LOOKUP_FAILURE_EXCEPTION_MESSAGE;

import com.rest.jwt_auth.domain.Role;
import com.rest.jwt_auth.domain.User;
import com.rest.jwt_auth.dto.UserDto;
import com.rest.jwt_auth.dto.UserRegistrationDto;
import com.rest.jwt_auth.mapper.UserMapper;
import com.rest.jwt_auth.repository.RoleRepository;
import com.rest.jwt_auth.repository.UserRepository;
import com.rest.jwt_auth.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public void save(UserRegistrationDto userRegistrationDto) {
        userRegistrationDto.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        User user = userMapper.convertToDomain(userRegistrationDto);
        Optional<Role> optionalRole = roleRepository.findByName(ROLE_USER);
        if (optionalRole.isPresent()) {
            user.setRole(optionalRole.get());
            userRepository.save(user);
        } else {
            throw new DataSourceLookupFailureException(DATA_SOURCE_LOOKUP_FAILURE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public boolean isUserExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void setRoleAdmin(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            Optional<Role> optionalRole = roleRepository.findByName(ROLE_ADMIN);
            if (optionalRole.isPresent()) {
                User user = userOptional.get();
                Role role = optionalRole.get();
                user.setRole(role);
                userRepository.save(user);
            } else {
                throw new DataSourceLookupFailureException(DATA_SOURCE_LOOKUP_FAILURE_EXCEPTION_MESSAGE);
            }
        } else {
            throw new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::convertToDto)
                .toList();
    }
}
