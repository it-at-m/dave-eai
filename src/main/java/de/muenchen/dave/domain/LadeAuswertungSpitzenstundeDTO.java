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
package de.muenchen.dave.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@CsvRecord(generateHeaderColumns = true, separator = ";", crlf = "UNIX")
public class LadeAuswertungSpitzenstundeDTO {

    @DataField(pos = 1, columnName = "VON")
    private Integer von;

    @DataField(pos = 2, columnName = "NACH")
    private Integer nach;

    // Info Zählstelle
    @DataField(pos = 20, columnName = "NUMMER_ZAEHLSTELLE")
    private String nummerZaehlstelle;

    @DataField(pos = 21, columnName = "STADTBEZIRK")
    private String stadtbezirk;

    @DataField(pos = 22, columnName = "STADTBEZIRK_NUMMER")
    private Integer stadtbezirkNummer;

    // Info Zählung
    @DataField(pos = 23, columnName = "ZAEHLDATUM")
    private String datum;

    @DataField(pos = 24, columnName = "ZAEHLART")
    private String zaehlart;

    @DataField(pos = 25, columnName = "KREUZUNGSNAME")
    private String kreuzungsname;

    @DataField(pos = 26, columnName = "SONDERZAEHLUNG")
    private Boolean sonderzaehlung;

    @DataField(pos = 27, columnName = "ZAEHLSITUATION")
    private String zaehlsituation;

    @DataField(pos = 28, columnName = "ZAEHLSITUATION_ERWEITERT")
    private String zaehlsituationErweitert;

    // Spitzenstunde
    @DataField(pos = 3, columnName = "TYPE")
    private String type;

    @DataField(pos = 4, columnName = "STARTUHRZEIT")
    private String startUhrzeit;

    @DataField(pos = 5, columnName = "ENDEUHRZEIT")
    private String endeUhrzeit;

    @DataField(pos = 6, columnName = "PKW")
    private Integer pkw;

    @DataField(pos = 7, columnName = "LKW")
    private Integer lkw;

    @DataField(pos = 8, columnName = "LASTZUEGE")
    private Integer lastzuege;

    @DataField(pos = 9, columnName = "BUSSE")
    private Integer busse;

    @DataField(pos = 10, columnName = "KRAFTRAEDER")
    private Integer kraftraeder;

    @DataField(pos = 11, columnName = "FAHRRADFAHRER")
    private Integer fahrradfahrer;

    @DataField(pos = 12, columnName = "FUSSGAENGER")
    private Integer fussgaenger;

    @DataField(pos = 13, columnName = "PKW_EINHEITEN")
    private Integer pkwEinheiten;

    @DataField(pos = 14, columnName = "GESAMT")
    private String gesamt;

    @DataField(pos = 15, columnName = "KFZ")
    private String kfz;

    @DataField(pos = 16, columnName = "SV")
    private String schwerverkehr;

    @DataField(pos = 17, columnName = "GV")
    private String gueterverkehr;

    @DataField(pos = 18, columnName = "SV%")
    private String anteilSchwerverkehrAnKfzProzent;

    @DataField(pos = 19, columnName = "GV%")
    private String anteilGueterverkehrAnKfzProzent;

}
