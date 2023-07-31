package com.example.PracticaSpringBoot2023.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csfr -> csfr.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/bootstrap/**").permitAll()
                        .requestMatchers(toH2Console()).permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/registration/**").permitAll()
                        .requestMatchers("/clubsportiv").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/register").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/clubsportiv/formclub").hasRole("ADMIN")
                        .requestMatchers("/clubsportiv/*/editclubform").hasRole( "ADMIN")
                        .requestMatchers("/clubsportiv/*/stergeClub").hasRole( "ADMIN")
                        .requestMatchers("/jucatori/*/editjucatorform").hasRole( "ADMIN")
                        .requestMatchers("/jucatori/*/formjucatori").hasRole( "ADMIN")
                        .requestMatchers("/jucatori/*/stergeJucator").hasRole( "ADMIN")
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable())
                .csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console()))
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/clubsportiv")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedPage("/access-denied"));
        return http.build();
    }
}