package com.rest.jwt_auth.service;

import com.rest.jwt_auth.domain.User;
import com.rest.jwt_auth.dto.UserDto;
import com.rest.jwt_auth.dto.UserRegistrationDto;
import java.util.List;

public interface UserService {

    User getUserByUsername(String username);

    void save(UserRegistrationDto userRegistrationDto);

    boolean isUserExist(String username);

    void setRoleAdmin(Long id);

    List<UserDto> getAllUsers();
}
