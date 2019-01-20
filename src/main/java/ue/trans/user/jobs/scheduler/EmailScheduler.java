package ue.trans.user.jobs.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ue.trans.user.jobs.service.EmailService;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final EmailService emailService;

    @Scheduled(cron = "0 0 7 * * ?")
    public void reportCurrentTime() {
        log.info("Sending task reminders");
        emailService.sendTaskReminders();
    }
}
