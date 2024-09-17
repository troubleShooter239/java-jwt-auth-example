package com.rest.jwt_auth.mapper;

import com.rest.jwt_auth.domain.User;
import com.rest.jwt_auth.dto.UserDto;
import com.rest.jwt_auth.dto.UserRegistrationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = BaseMapper.class)
public interface UserMapper {

    User convertToDomain(UserRegistrationDto userRegistrationDto);

    @Mapping(target = "role", source = "user.role.name")
    UserDto convertToDto(User user);
}
