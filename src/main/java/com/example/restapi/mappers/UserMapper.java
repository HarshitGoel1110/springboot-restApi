package com.example.restapi.mappers;

import com.example.restapi.dtos.UserMsDto;
import com.example.restapi.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "email" , target = "emailAddress"),
            @Mapping(source = "role" , target = "roleName")
    })

    // User to UserMsDto
    UserMsDto userToUserMsDto(User user);

    // List<User> to List<UserMsDto>
    List<UserMsDto> usersToUserMsDtos(List<User> users);

}
