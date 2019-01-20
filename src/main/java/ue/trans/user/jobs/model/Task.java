package ue.trans.user.jobs.model;

import lombok.Data;
import org.springframework.boot.actuate.audit.listener.AuditListener;
import ue.trans.user.jobs.enums.TaskStatusEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Data
@Entity
@EntityListeners(AuditListener.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name="task_name")
    private String taskName;

    @Column(name="date_execute")
    private ZonedDateTime dateExecute;

    @Column(name="task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatusEnum taskStatus;

    @Column(name="date_create")
    private ZonedDateTime dateCreate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @PrePersist
    private void beforePersist() {
        dateCreate = ZonedDateTime.now();
    }
}
