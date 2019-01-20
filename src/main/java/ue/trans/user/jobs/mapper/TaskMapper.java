package ue.trans.user.jobs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ue.trans.user.jobs.dto.TaskDto;
import ue.trans.user.jobs.dto.UserDto;
import ue.trans.user.jobs.model.Task;
import ue.trans.user.jobs.model.User;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper TASK_MAPPER = Mappers.getMapper(TaskMapper.class);

    @Mappings({
            @Mapping(source = "user.id", target = "userId")
    })
    TaskDto toTaskDto(Task task);

    @Mappings({
            @Mapping(target="dateCreate", ignore = true),
            @Mapping(source = "userId", target = "user.id")
    })
    Task toTask(TaskDto taskDto);


}
