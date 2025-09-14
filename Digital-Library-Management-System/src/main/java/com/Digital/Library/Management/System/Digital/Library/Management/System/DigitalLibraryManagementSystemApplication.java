package com.Digital.Library.Management.System.Digital.Library.Management.System;

import com.Digital.Library.Management.System.Digital.Library.Management.System.Enums.Role;
import com.Digital.Library.Management.System.Digital.Library.Management.System.model.Member;
import com.Digital.Library.Management.System.Digital.Library.Management.System.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DigitalLibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalLibraryManagementSystemApplication.class, args);
	}
    @Bean
    public CommandLineRunner initAdmin(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if admin exists
            if (memberRepository.findByUsername("admin").isEmpty()) {
                Member admin = Member.builder()
                        .username("admin")
                        .passwordHash(passwordEncoder.encode("admin123"))
                        .fullName("Administrator")
                        .email("admin@example.com")
                        .role(Role.ADMIN)
                        .active(true)
                        .build();
                memberRepository.save(admin);
                System.out.println("Admin created: username=admin, password=admin123");
            }
        };
    }

}
