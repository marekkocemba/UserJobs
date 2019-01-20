package ue.trans.user.jobs.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    private Long id;
    @NotNull
    @Size(min = 5)
    private String login;
    @NotNull
    @Size(min = 5)
    private String password;
    private Boolean active;

}
