package com.salesianos.satapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails alumno = User.withUsername("alumno")
                .password("{noop}1234")
                .roles("ALUMNO")
                .build();

        UserDetails tecnico = User.withUsername("tecnico")
                .password("{noop}admin")
                .roles("TECNICO")
                .build();

        UserDetails profesor = User.withUsername("profesor")
                .password("{noop}profesor")
                .roles("PROFESOR")
                .build();

        return new InMemoryUserDetailsManager(alumno, tecnico, profesor);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers(HttpMethod.POST, "/tecnico/**").hasRole("TECNICO")
                .requestMatchers(HttpMethod.GET, "/incidencia/**").hasRole("TECNICO")
                .requestMatchers(HttpMethod.POST, "/incidencia/**").hasRole("ALUMNO")
                .requestMatchers(HttpMethod.PUT, "/incidencia/**").hasRole("TECNICO")
                .requestMatchers(HttpMethod.GET, "/tecnico/**").hasRole("TECNICO")
                .requestMatchers(HttpMethod.POST, "/profesor/**").hasRole("PROFESOR")
                .requestMatchers(HttpMethod.GET, "/profesor/**").hasRole("PROFESOR")
                .requestMatchers(HttpMethod.POST, "/alumno/**").hasRole("ALUMNO")
                .requestMatchers(HttpMethod.GET, "/alumno/**").hasRole("ALUMNO")
                .anyRequest().authenticated()
        );

        return http.build();
    }


}
