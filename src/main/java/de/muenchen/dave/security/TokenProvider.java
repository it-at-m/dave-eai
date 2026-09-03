package de.muenchen.dave.security;

public interface TokenProvider {

    String getBearerToken(String clientRegistrationId);

}
