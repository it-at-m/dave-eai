package de.muenchen.dave.security;

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Component
public class BackendTokenProvider {

    public static final String CLIENT_REGISTRATION_ID = "keycloak";

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public BackendTokenProvider(final OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    public String getBearerToken() {
        final var request = OAuth2AuthorizeRequest
                .withClientRegistrationId(CLIENT_REGISTRATION_ID)
                .principal(CLIENT_REGISTRATION_ID)
                .build();

        final var client = authorizedClientManager.authorize(request);

        if (client == null) {
            throw new IllegalStateException("Konnte keinen Access Token für Backend abrufen.");
        }

        return "Bearer " + client.getAccessToken().getTokenValue();
    }

}
