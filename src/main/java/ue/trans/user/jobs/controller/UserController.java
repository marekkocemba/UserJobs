package ue.trans.user.jobs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ue.trans.user.jobs.dto.UserDto;
import ue.trans.user.jobs.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping(value = "/users")
    public UserDto addUser(@RequestBody @Valid UserDto userDto) {
        log.info("Adding user");
        return userService.addUser(userDto);
    }

    //M.K. Metoda diagnostyczna
    @GetMapping(value = "/users")
    public List<UserDto> getUsers() {
        log.info("Get user list");
        return userService.getUsers();
    }
}