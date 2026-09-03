package de.muenchen.dave.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("no-security")
public class NoSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) {
        http
                .authorizeHttpRequests(request -> request.anyRequest().permitAll());
        return http.build();
    }

}
