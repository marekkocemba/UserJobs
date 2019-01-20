package ue.trans.user.jobs.dto;

import lombok.Data;
import ue.trans.user.jobs.enums.TaskStatusEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Data
public class TaskDto {

    private Long id;

    @NotNull
    @Size(min = 5)
    private String taskName;

    @NotNull
    private ZonedDateTime dateExecute;

    private TaskStatusEnum taskStatus;

    @NotNull
    private Long userId;
}
