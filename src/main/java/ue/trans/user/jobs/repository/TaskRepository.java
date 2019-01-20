package ue.trans.user.jobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ue.trans.user.jobs.enums.TaskStatusEnum;
import ue.trans.user.jobs.model.Task;

import java.time.ZonedDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserIdAndDateExecuteBetween(Long userId, ZonedDateTime minHours, ZonedDateTime maxHours);

    List<Task> findByUserIdAndTaskStatus(Long id, TaskStatusEnum taskStatus);

    List<Task>  findByTaskStatusAndDateExecuteBetween(TaskStatusEnum taskStatus, ZonedDateTime minHours, ZonedDateTime maxHours);
}