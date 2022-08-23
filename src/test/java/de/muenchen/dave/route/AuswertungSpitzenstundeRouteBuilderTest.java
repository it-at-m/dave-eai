/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2021
 */
package de.muenchen.dave.route;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;


@SpringBootTest()
@ActiveProfiles(profiles = {"test"})
@CamelSpringBootTest
class AuswertungSpitzenstundeRouteBuilderTest {

    private static final String JSON_TEST_FILE = "src/test/resources/InputJsonSpitzenstundeAuswertungKreisverkehr.json";

    private static final String CSV_TEST_FILE = "src/test/resources/OutputCsvSpitzenstundeAuswertungKreisverkehr.csv";

    @Produce(AuswertungSpitzenstundeRouteBuilder.ROUTE_JSON_TO_CSV)
    private ProducerTemplate producer;

    @Autowired
    private CamelContext camelContext;

    @Test
    void testJsonToCsv() throws IOException {
        var jsonInput = FileUtils.readFileToString(new File(JSON_TEST_FILE), "UTF-8");
        var csvOutputExpected = FileUtils.readFileToString(new File(CSV_TEST_FILE), "UTF-8");

        var exchange = ExchangeBuilder.anExchange(camelContext).build();
        exchange.getIn().setBody(jsonInput);
        var csvResult = producer.send(exchange).getIn().getBody(String.class);
        assertEquals(csvOutputExpected, csvResult);
    }

}
