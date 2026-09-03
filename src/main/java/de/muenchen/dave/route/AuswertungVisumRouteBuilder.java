package de.muenchen.dave.route;

import de.muenchen.dave.security.BackendTokenProvider;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuswertungVisumRouteBuilder extends RouteBuilder {

    public static final String ROUTE_EXCEPTION = "direct:visum_exceptionHandling";

    private final BackendTokenProvider backendTokenProvider;

    private final String clientRegistrationId;

    public AuswertungVisumRouteBuilder(
            BackendTokenProvider backendTokenProvider,
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
                .process(exchange -> exchange.getMessage().setHeader("Authorization", backendTokenProvider.getBearerToken(clientRegistrationId)))
                .to("http://{{backend.uri}}/lade-auswertung-visum?bridgeEndpoint=true&throwExceptionOnFailure=false");
    }

    private void exceptionHandling() {
        from(ROUTE_EXCEPTION)
                .to("log:de.muenchen.dave?showAll=true&multiline=true");
    }

}
