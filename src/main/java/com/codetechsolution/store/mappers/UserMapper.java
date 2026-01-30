package com.codetechsolution.store.mappers;

import com.codetechsolution.store.dtos.RegisterUserRequest;
import com.codetechsolution.store.dtos.UserDto;
import com.codetechsolution.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
}
