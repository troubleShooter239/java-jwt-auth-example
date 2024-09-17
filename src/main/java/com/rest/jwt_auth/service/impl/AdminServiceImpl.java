package com.rest.jwt_auth.service.impl;

import com.rest.jwt_auth.dto.UserDto;
import com.rest.jwt_auth.service.AdminService;
import com.rest.jwt_auth.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserService userService;

    @Override
    public void setAdmin(Long id) {
        userService.setRoleAdmin(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
