package com.group.libraryapp.config;

import com.group.libraryapp.repository.user.UserJdbcRepository;
import com.group.libraryapp.service.user.UserServiceV1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class UserConfiguration {

//    @Bean
    public UserJdbcRepository userRepository(JdbcTemplate jdbcTemplate) {
        return new UserJdbcRepository(jdbcTemplate);
    }

//    @Bean
    public UserServiceV1 userService(UserJdbcRepository userJdbcRepository) {
        return new UserServiceV1(userJdbcRepository);
    }
}
