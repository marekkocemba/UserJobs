package ue.trans.user.jobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ue.trans.user.jobs.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}