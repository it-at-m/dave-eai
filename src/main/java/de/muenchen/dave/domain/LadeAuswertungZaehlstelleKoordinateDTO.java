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
@CsvRecord(
        generateHeaderColumns = true,
        separator = ";",
        crlf = "UNIX"
)
public class LadeAuswertungZaehlstelleKoordinateDTO {

    @DataField(pos = 1, columnName = "ZAEHLSTELLE_NUMMER")
    private String nummer;

    @DataField(pos = 2, columnName = "LETZTE_ZAEHLUNG")
    private String letzteZaehlung;

    @DataField(pos = 3, columnName = "KOMMENTAR")
    private String kommentar;

    @DataField(pos = 4, columnName = "LAT")
    private String lat;

    @DataField(pos = 5, columnName = "LNG")
    private String lng;

}
