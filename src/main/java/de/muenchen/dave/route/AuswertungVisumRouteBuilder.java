package de.muenchen.dave.route;

import de.muenchen.dave.security.TokenProvider;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuswertungVisumRouteBuilder extends RouteBuilder {

    public static final String ROUTE_EXCEPTION = "direct:visum_exceptionHandling";

    private final TokenProvider backendTokenProvider;

    private final String clientRegistrationId;

    public AuswertungVisumRouteBuilder(
            TokenProvider backendTokenProvider,
            @Value("${dave.oauth2.client-registration-id}") final String clientRegistrationId) {
        this.backendTokenProvider = backendTokenProvider;
        this.clientRegistrationId = clientRegistrationId;
    }

    @Override
    public void configure() {

        errorHandler(
                deadLetterChannel(ROUTE_EXCEPTION).useOriginalMessage());
        exceptionHandling();

        from("servlet:lade-auswertung-visum")
                .process(exchange -> {
                    String token = backendTokenProvider.getBearerToken(clientRegistrationId);
                    if (token != null) {
                        exchange.getMessage().setHeader("Authorization", token);
                    }
                })
                .to("http://{{backend.uri}}/lade-auswertung-visum?bridgeEndpoint=true&throwExceptionOnFailure=false");
    }

    private void exceptionHandling() {
        from(ROUTE_EXCEPTION)
                .to("log:de.muenchen.dave?showAll=true&multiline=true");
    }

}
