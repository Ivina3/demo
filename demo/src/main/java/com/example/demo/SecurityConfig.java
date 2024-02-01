package com.example.demo;

import com.example.demo.services.users.UserDetailsServiceImpl;
import com.example.demo.services.users.UserService;
import com.example.demo.services.users.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/addPost").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(loginConfigurer ->
                        loginConfigurer
                                .loginPage("/login") // Своя страница входа
                                .loginProcessingUrl("/submitlog") // Изменил адрес для обработки логина
                                .defaultSuccessUrl("/index", true) // URL при успешной аутентификации
                                .failureUrl("/login?error=true") // URL при ошибке аутентификации
                                .permitAll()
                )
                .logout(logoutConfigurer ->
                        logoutConfigurer
                                .logoutSuccessUrl("/index") // URL при успешном выходе
                                .permitAll()
                )
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Используйте нужный вам репозиторий
                );

        return http.build();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
