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

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AuswertungVisumRouteBuilder extends RouteBuilder {

    public static final String ROUTE_EXCEPTION = "direct:visum_exceptionHandling";

    @Override
    public void configure() {

        errorHandler(
                deadLetterChannel(ROUTE_EXCEPTION).useOriginalMessage());
        exceptionHandling();

        from("servlet:lade-auswertung-visum")
                .to("http://{{backend.uri}}/lade-auswertung-visum?bridgeEndpoint=true&throwExceptionOnFailure=false");
    }

    private void exceptionHandling() {
        from(ROUTE_EXCEPTION)
                .to("log:de.muenchen.dave?showAll=true&multiline=true");
    }

}
