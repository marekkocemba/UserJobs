package ue.trans.user.jobs.model;


import lombok.Data;
import org.springframework.boot.actuate.audit.listener.AuditListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditListener.class)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String login;
    private String email;
    private String password;
    private Boolean active;
    @Column(name="date_create")
    private ZonedDateTime dateCreate;
    @OneToMany(mappedBy="user")
    private List<Task> tasks;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active == true ? true : false;
    }

    @PrePersist
    private void beforePersist() {
        dateCreate = ZonedDateTime.now();
        if (this.active == null){
            active = true;
        }
    }
}
