package ue.trans.user.jobs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ue.trans.user.jobs.dto.UserDto;
import ue.trans.user.jobs.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);


    @Mappings({
            @Mapping(target="password", ignore = true)
    })
    UserDto toUserDto(User user);

    @Mappings({
            @Mapping(target="dateCreate", ignore = true),
            @Mapping(target="authorities", ignore = true)
    })
    User toUser(UserDto userDto);


}
