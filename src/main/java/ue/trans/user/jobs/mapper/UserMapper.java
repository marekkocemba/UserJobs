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


   // @Mappings({})
    UserDto toUserDto(User user);

   // @Mappings({})
    User toUser(UserDto userDto);


}
