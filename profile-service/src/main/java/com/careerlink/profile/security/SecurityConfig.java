package com.careerlink.profile.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())

            .authorizeHttpRequests(auth -> auth

                // Actuator
                .requestMatchers("/actuator/**").permitAll()

                // View profiles
                .requestMatchers(HttpMethod.GET, "/profiles/**")
                .hasAnyRole("JOB_SEEKER", "RECRUITER")

                // Create profile
                .requestMatchers(HttpMethod.POST, "/profiles")
                .hasRole("JOB_SEEKER")

                // Update profile
                .requestMatchers(HttpMethod.PUT, "/profiles/**")
                .hasRole("JOB_SEEKER")

                // Delete profile
                .requestMatchers(HttpMethod.DELETE, "/profiles/**")
                .hasRole("JOB_SEEKER")

                // Everything else
                .anyRequest().authenticated()
            )

            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}