package de.muenchen.dave.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@Profile("!no-security")
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) {
        http.authorizeHttpRequests(request -> request
                .requestMatchers(
                        PathPatternRequestMatcher.withDefaults().matcher("/lade-auswertung-visum"),
                        PathPatternRequestMatcher.withDefaults().matcher("/lade-auswertung-spitzenstunde"),
                        PathPatternRequestMatcher.withDefaults().matcher("/lade-auswertung-zaehlstellen-koordinate"),
                        // allow access to /actuator/info
                        PathPatternRequestMatcher.withDefaults().matcher("/actuator/info"),
                        // allow access to /actuator/health for OpenShift Health Check
                        PathPatternRequestMatcher.withDefaults().matcher("/actuator/health"),
                        // allow access to /actuator/health/liveness for OpenShift Liveness Check
                        PathPatternRequestMatcher.withDefaults().matcher("/actuator/health/liveness"),
                        // allow access to /actuator/health/readiness for OpenShift Readiness Check
                        PathPatternRequestMatcher.withDefaults().matcher("/actuator/health/readiness"),
                        // allow access to /actuator/metrics for Prometheus monitoring in OpenShift
                        PathPatternRequestMatcher.withDefaults().matcher("/actuator/metrics"))
                .permitAll()
                .anyRequest()
                .denyAll());

        return http.build();
    }

}
