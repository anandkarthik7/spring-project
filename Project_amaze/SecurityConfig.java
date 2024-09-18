package com.Project_amaze;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/token").permitAll()
                        .requestMatchers("/auth/signup").permitAll()
                        .requestMatchers("/tests/search").permitAll()
                        .requestMatchers("/tests/pending").permitAll()
                        .requestMatchers("/tests/{testId}/result").permitAll()
                        .requestMatchers("/doctor/add").hasRole("ADMIN")
                        .requestMatchers("/doctor/schedule/add/{doctorId}").hasAnyRole("DOCTOR","ADMIN")
                        .requestMatchers("/book-appointment/{patientId}/{doctorId}").hasAnyRole("ADMIN","DCOTOR","PATIENT")
                        .requestMatchers("/patient-opd/add").hasAnyRole("ADMIN","PATIENT")
                        .requestMatchers("/appointments/patient/{patientId}").hasAnyRole("ADMIN","PATIENT")
                        .requestMatchers("/doctor/{doctorId}").hasAnyRole("ADMIN","DOCTOR")
                        .requestMatchers("/patient-opd/history/add/{pid}").hasAnyRole("ADMIN","PATIENT","DOCTOR")
                        .requestMatchers("/patient-opd/history/medicine-info/add/{historyId}").hasAnyRole("ADMIN","PATIENT","DOCTOR")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
     AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
     PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
     DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}