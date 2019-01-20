package ue.trans.user.jobs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ue.trans.user.jobs.repository.TaskRepository;
import ue.trans.user.jobs.utils.email.EmailUtil;

import java.time.LocalTime;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final TaskRepository taskRepository;
    private final EmailUtil emailUtil;

    public void sendTaskReminders() {
        ZonedDateTime today = ZonedDateTime.now();
        taskRepository.findByDateExecuteBetween(today.with(LocalTime.MIN), today.with(LocalTime.MAX))
                .forEach(task -> {
                    emailUtil.sendTaskReminder(task);
                });
    }
}
