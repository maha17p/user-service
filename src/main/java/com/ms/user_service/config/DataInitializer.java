package com.ms.user_service.config;

import com.ms.user_service.entity.User;
import com.ms.user_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner createAdmin(UserRepository repo, PasswordEncoder encoder){
        return  args -> {
            if(repo.findByUsername("admin").isEmpty()){
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin1234"));
//                admin.setRole("ADMIN");
                repo.save(admin);
                System.out.println("Default admin user created");
            }
        };
    }
}
