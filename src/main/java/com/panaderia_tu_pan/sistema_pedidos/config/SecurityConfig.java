package com.panaderia_tu_pan.sistema_pedidos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        //acceso público
                        .requestMatchers("/", "/productos/menu", "/css/**", "/js/**").permitAll()

                        //acceso por ROLES
                        .requestMatchers("/pedidos/nuevo").hasAnyRole("CLIENTE", "ADMIN")
                        .requestMatchers("/pedidos/historial").hasAnyRole("CLIENTE", "ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN") //solo admin puede gestionar estados

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/pedidos/historial", true)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}