package com.example.taskmanagement.config;

import com.example.taskmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Wyłączenie CSRF dla uproszczenia, ale w produkcji lepiej mieć włączone
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/register", "/styles.css", "/login").permitAll()  // Zezwól na dostęp do tych ścieżek bez logowania
                        .anyRequest().authenticated()  // Wymagaj logowania dla wszystkich innych żądań
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Strona logowania
                        .defaultSuccessUrl("/app", true)  // Domyślna strona po zalogowaniu
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
