package de.muenchen.dave.route;

import de.muenchen.dave.security.BackendTokenProvider;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AuswertungVisumRouteBuilder extends RouteBuilder {

    public static final String ROUTE_EXCEPTION = "direct:visum_exceptionHandling";

    private final BackendTokenProvider backendTokenProvider;

    public AuswertungVisumRouteBuilder(BackendTokenProvider backendTokenProvider) {
        this.backendTokenProvider = backendTokenProvider;
    }

    @Override
    public void configure() {

        errorHandler(
                deadLetterChannel(ROUTE_EXCEPTION).useOriginalMessage());
        exceptionHandling();

        from("servlet:lade-auswertung-visum")
                .setHeader("Authorization", method(backendTokenProvider, "getBearerToken"))
                .to("http://{{backend.uri}}/lade-auswertung-visum?bridgeEndpoint=true&throwExceptionOnFailure=false");
    }

    private void exceptionHandling() {
        from(ROUTE_EXCEPTION)
                .to("log:de.muenchen.dave?showAll=true&multiline=true");
    }

}
