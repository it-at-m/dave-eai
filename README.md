# dave-eai

## Erforderliche JVM

***Java 11*** wird benötigt.

## EAI-Routen

### Auswertung Spitzenstunde

Routenendpunkt: `GET /lade-auswertung-spitzenstunde`

Routenziel: `dave-backend-service GET /lade-auswertung-spitzenstunde`

Aufgabe: Durchleiten des Requests an das Backend und Umwandlung der JSON-Response des Backends in CSV und Rückgabe der CSV an den Aufrufenden.

### Auswertung Zählstellenkoordinate

Routenendpunkt: `GET /lade-auswertung-zaehlstellen-koordinate`

Routenziel: `dave-backend-service GET /lade-auswertung-zaehlstellen-koordinate`

Aufgabe: Durchleiten des Requests an das Backend und Umwandlung der JSON-Response des Backends in CSV und Rückgabe der CSV an den Aufrufenden.
