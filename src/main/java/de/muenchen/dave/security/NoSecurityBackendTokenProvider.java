package de.muenchen.dave.security;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("no-security")
public class NoSecurityBackendTokenProvider implements TokenProvider {

    @Override
    public String getBearerToken(String clientRegistrationId) {
        return null;
    }

}
