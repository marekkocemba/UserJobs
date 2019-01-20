package ue.trans.user.jobs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ue.trans.user.jobs.dto.TaskDto;
import ue.trans.user.jobs.enums.TaskStatusEnum;
import ue.trans.user.jobs.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping(value = "/tasks")
    public TaskDto addTask(@RequestBody @Valid TaskDto taskDto) {
        return taskService.addTask(taskDto);
    }

    @GetMapping(value = "/tasks/user/{id}/task-status/{taskStatus}")
    public List<TaskDto> getTasksByUserAndStatus(@PathVariable Long id, @PathVariable TaskStatusEnum taskStatus) {
        return taskService.getTasksByUserAndStatus(id, taskStatus);
    }

    @PatchMapping(value = "/tasks/{id}/task-status/{taskStatus}")
    public ResponseEntity updateTask(@PathVariable Long id, @PathVariable TaskStatusEnum taskStatus) {
        taskService.updateTask(id, taskStatus);
        return ResponseEntity.ok().build();
    }
}
