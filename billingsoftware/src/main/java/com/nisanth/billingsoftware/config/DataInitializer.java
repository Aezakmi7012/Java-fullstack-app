package com.nisanth.billingsoftware.config;

import com.nisanth.billingsoftware.entity.UserEntity;
import com.nisanth.billingsoftware.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Check if any admin user exists
        long userCount = userRepository.count();
        System.out.println("Total users in database: " + userCount);
        
        if (userCount == 0) {
            // Create default admin user
            UserEntity adminUser = UserEntity.builder()
                    .userId(UUID.randomUUID().toString())
                    .name("Admin")
                    .email("admin@billing.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role("ADMIN")
                    .build();
            
            userRepository.save(adminUser);
            System.out.println("=================================");
            System.out.println("DEFAULT ADMIN USER CREATED:");
            System.out.println("Email: admin@billing.com");
            System.out.println("Password: admin123");
            System.out.println("=================================");
        }
        
        // Create a regular user for testing
        if (userRepository.findByEmail("user@billing.com").isEmpty()) {
            UserEntity regularUser = UserEntity.builder()
                    .userId(UUID.randomUUID().toString())
                    .name("Test User")
                    .email("user@billing.com")
                    .password(passwordEncoder.encode("user123"))
                    .role("USER")
                    .build();
            
            userRepository.save(regularUser);
            System.out.println("=================================");
            System.out.println("DEFAULT USER CREATED:");
            System.out.println("Email: user@billing.com");
            System.out.println("Password: user123");
            System.out.println("=================================");
        } else {
            System.out.println("User user@billing.com already exists");
        }
        
        // Let's also create a test admin user if it doesn't exist
        if (userRepository.findByEmail("admin@billing.com").isEmpty()) {
            UserEntity adminUser = UserEntity.builder()
                    .userId(UUID.randomUUID().toString())
                    .name("Admin User")
                    .email("admin@billing.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role("ADMIN")
                    .build();
            
            userRepository.save(adminUser);
            System.out.println("=================================");
            System.out.println("ADMIN USER CREATED:");
            System.out.println("Email: admin@billing.com");
            System.out.println("Password: admin123");
            System.out.println("=================================");
        } else {
            System.out.println("Admin admin@billing.com already exists");
        }
    }
}
