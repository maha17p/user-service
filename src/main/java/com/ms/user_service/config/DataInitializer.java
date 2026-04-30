package com.ms.user_service.config;

import com.ms.user_service.entity.Role;
import com.ms.user_service.entity.User;
import com.ms.user_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner createAdmin(UserRepository repo, PasswordEncoder encoder){
        return  args -> {
            if(repo.findByUsername("admin").isEmpty()){
                User admin =  User.builder()
                        .email("admin@gmail.com")
                        .username("admin@1234")
                        .password(encoder.encode("admin@123456"))
                        .roles(Set.of(Role.ROLE_ADMIN))
                        .build();
                repo.save(admin);
                System.out.println("Default admin user created");
            }
        };
    }
}
