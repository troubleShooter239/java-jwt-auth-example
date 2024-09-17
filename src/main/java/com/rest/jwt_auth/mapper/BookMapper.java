package com.rest.jwt_auth.mapper;

import com.rest.jwt_auth.domain.Book;
import com.rest.jwt_auth.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = BaseMapper.class)
public interface BookMapper {

    BookDto convertToDto(Book book);

    Book convertToDomain(BookDto bookDto);
}
