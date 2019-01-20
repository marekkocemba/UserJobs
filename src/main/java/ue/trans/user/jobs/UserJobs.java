package ue.trans.user.jobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class UserJobs extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(UserJobs.class, args);
    }
}
