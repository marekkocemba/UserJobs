package ue.trans.user.jobs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ue.trans.user.jobs.dto.TaskDto;
import ue.trans.user.jobs.dto.UserDto;
import ue.trans.user.jobs.enums.TaskStatusEnum;
import ue.trans.user.jobs.model.Task;
import ue.trans.user.jobs.model.User;
import ue.trans.user.jobs.repository.TaskRepository;
import ue.trans.user.jobs.repository.UserRepository;
import ue.trans.user.jobs.utils.exception.handler.ClientException;
import ue.trans.user.jobs.utils.exception.handler.ExceptionCodeEnum;

import java.util.List;
import java.util.stream.Collectors;

import static ue.trans.user.jobs.mapper.TaskMapper.TASK_MAPPER;
import static ue.trans.user.jobs.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    public TaskDto addTask(TaskDto taskDto) {
        validateNewTask(taskDto);
        Task task = TASK_MAPPER.toTask(taskDto);
        return TASK_MAPPER.toTaskDto(taskRepository.save(task));
    }


    private void validateNewTask(TaskDto taskDto) {
        userRepository.findById(taskDto.getUserId()).orElseThrow(
                () -> new ClientException("User by given id not exists", ExceptionCodeEnum.SAMPLE_ERROR_CODE_2)
        );
        taskRepository.findByUserIdAndDateExecuteBetween(
                taskDto.getUserId(),
                taskDto.getDateExecute().minusHours(1),
                taskDto.getDateExecute().plusHours(1))
                .stream()
                .findFirst()
                .ifPresent(
                        (task) -> {
                            throw new ClientException("Should be at least one hour space between tasks", ExceptionCodeEnum.SAMPLE_ERROR_CODE_3);
                        });
    }

    @Transactional
    public void updateTask(Long id, TaskStatusEnum taskStatus) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ClientException("Task by given id not exists", ExceptionCodeEnum.SAMPLE_ERROR_CODE_2)
        );
        task.setTaskStatus(taskStatus);
    }

    public List<TaskDto> getTasksByUserAndStatus(Long id, TaskStatusEnum taskStatus) {
        return taskRepository.findByUserIdAndTaskStatus(id, taskStatus)
                .stream()
                .map(task -> TASK_MAPPER.toTaskDto(task))
                .collect(Collectors.toList());
    }
}
