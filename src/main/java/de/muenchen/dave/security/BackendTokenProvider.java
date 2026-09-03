package de.muenchen.dave.security;

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Component
public class BackendTokenProvider {


    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public BackendTokenProvider(final OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    public String getBearerToken(final String clientRegistrationId) {
        final var request = OAuth2AuthorizeRequest
                .withClientRegistrationId(clientRegistrationId)
                .principal(clientRegistrationId)
                .build();

        final var client = authorizedClientManager.authorize(request);

        if (client == null) {
            throw new IllegalStateException("Konnte keinen Access Token für Backend abrufen.");
        }

        return "Bearer " + client.getAccessToken().getTokenValue();
    }

}
