package com.rest.jwt_auth.service;

import com.rest.jwt_auth.dto.UserDto;
import java.util.List;

public interface AdminService {

    void setAdmin(Long id);

    List<UserDto> getAllUsers();
}
