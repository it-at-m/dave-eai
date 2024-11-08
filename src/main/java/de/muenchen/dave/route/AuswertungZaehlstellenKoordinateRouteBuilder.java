/*
 * The MIT License
 * Copyright © 2023 Landeshauptstadt München | it@M
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.muenchen.dave.route;

import de.muenchen.dave.domain.LadeAuswertungZaehlstelleKoordinateDTO;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.springframework.stereotype.Component;

@Component
public class AuswertungZaehlstellenKoordinateRouteBuilder extends RouteBuilder {

    public static final String ROUTE_ID_JSON_TO_CSV = "zaehlstellen_koordinate_json_to_csv";

    public static final String ROUTE_JSON_TO_CSV = "direct:" + ROUTE_ID_JSON_TO_CSV;

    public static final String ROUTE_EXCEPTION = "direct:zaehlstellen_koordinate_exceptionHandling";

    private static final String HEADER_NAME_CONTENT_TYPE = "content-type";

    private static final String HEADER_VALUE_CONTENT_TYPE_TEXT_CSV = "text/csv";

    private final ListJacksonDataFormat json = new ListJacksonDataFormat(LadeAuswertungZaehlstelleKoordinateDTO.class);

    private final BindyCsvDataFormat csv = new BindyCsvDataFormat(LadeAuswertungZaehlstelleKoordinateDTO.class);

    @Override
    public void configure() {

        errorHandler(
                deadLetterChannel(ROUTE_EXCEPTION).useOriginalMessage());
        exceptionHandling();

        // @formatter:off
        from("servlet:lade-auswertung-zaehlstellen-koordinate")
                .to("http://{{backend.uri}}/lade-auswertung-zaehlstellen-koordinate?bridgeEndpoint=true&throwExceptionOnFailure=false")
                .choice()
                    .when(header(Exchange.HTTP_RESPONSE_CODE).isLessThan(300))
                        // Bearbeiten der erfolgreichen Response vom Backend
                        .to(ROUTE_JSON_TO_CSV)
                    .otherwise()
                        // Zurueckgeben der Fehlerresponse vom Backend
                        .end();

        from(ROUTE_JSON_TO_CSV)
                .routeId(ROUTE_ID_JSON_TO_CSV)
                .unmarshal(json)
                .marshal(csv)
                // Entferne Header "content-type: application/json"
                .removeHeader(HEADER_NAME_CONTENT_TYPE)
                // Füge Header "content-type: text/csv" hinzu
                .setHeader(
                        HEADER_NAME_CONTENT_TYPE,
                        constant(HEADER_VALUE_CONTENT_TYPE_TEXT_CSV)
                );
        // @formatter:on

    }

    private void exceptionHandling() {
        // @formatter:off
        from(ROUTE_EXCEPTION)
            .to("log:de.muenchen.dave?showAll=true&multiline=true");
        // @formatter:on
    }

}
