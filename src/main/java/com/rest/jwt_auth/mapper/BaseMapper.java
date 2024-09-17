package com.rest.jwt_auth.mapper;

import com.rest.jwt_auth.domain.BaseDomain;
import com.rest.jwt_auth.dto.BaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BaseMapper {

    BaseDto convertToDto(BaseDomain baseDomain);
}
