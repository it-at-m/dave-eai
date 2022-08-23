/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2021
 */
package de.muenchen.dave.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AuswertungVisumRouteBuilder extends RouteBuilder {

    public static final String ROUTE_EXCEPTION = "direct:visum_exceptionHandling";

    @Override
    public void configure() {

        errorHandler(
                deadLetterChannel(ROUTE_EXCEPTION).useOriginalMessage()
        );
        exceptionHandling();

        from("servlet:lade-auswertung-visum")
                .to("http://{{backend.uri}}/lade-auswertung-visum?bridgeEndpoint=true&throwExceptionOnFailure=false")
        ;
    }

    private void exceptionHandling() {
        from(ROUTE_EXCEPTION)
                .to("log:de.muenchen.dave?showAll=true&multiline=true");
    }

}
