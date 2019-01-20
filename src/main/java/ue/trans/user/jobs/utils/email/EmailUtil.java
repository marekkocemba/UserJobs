package ue.trans.user.jobs.utils.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ue.trans.user.jobs.model.Task;

import javax.mail.internet.MimeMessage;


@Component
@RequiredArgsConstructor
public class EmailUtil {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.sender}")
    private String sender;

    @Async
    private void sendSimpleMessage(String to, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {

        }
    }

    @Async
    public void sendTaskReminder(Task task) {
        sendSimpleMessage(
                task.getUser().getEmail(),
                getTaskReminderSubject(task),
                getTaskReminderText(task));
    }

    private String getTaskReminderSubject(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Task reminder (");
        sb.append(task.getTaskName());
        sb.append(") - ");
        sb.append(task.getDateExecute());
        return sb.toString();
    }

    private String getTaskReminderText(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Some text that remindst that task ");
        sb.append(task.getTaskName());
        sb.append(" is scheduled at ");
        sb.append(task.getDateExecute());
        sb.append(".");
        return sb.toString();
    }
}
