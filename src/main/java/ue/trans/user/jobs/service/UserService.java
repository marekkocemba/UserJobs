package ue.trans.user.jobs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ue.trans.user.jobs.dto.UserDto;
import ue.trans.user.jobs.enums.ExceptionCodeEnum;
import ue.trans.user.jobs.model.User;
import ue.trans.user.jobs.repository.UserRepository;
import ue.trans.user.jobs.utils.exception.handler.ClientException;

import java.util.List;
import java.util.stream.Collectors;

import static ue.trans.user.jobs.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserDto addUser(UserDto userDto) {
        validateNewUser(userDto);
        User user = USER_MAPPER.toUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return USER_MAPPER.toUserDto(userRepository.save(user));
    }
    //M.K. Metoda diagnostyczna
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> USER_MAPPER.toUserDto(user))
                .collect(Collectors.toList());
    }

    private void validateNewUser(UserDto userDto) {
        userRepository.findByLogin(userDto.getLogin()).ifPresent(login -> {
            throw new ClientException("Login already exists", ExceptionCodeEnum.SAMPLE_ERROR_CODE_1);
        });
    }
}
