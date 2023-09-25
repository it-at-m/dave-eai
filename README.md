# dave-eai

## About the project
Dieses Repository ist eine der 5 Komponenten der Anwendung "DAVe" (Datenbank und Auswertung für Verkehrszählungen).

Bitte beachten Sie, dass diese Komponente nicht ohne das [dave-backend](https://github.com/it-at-m/dave-backend) betrieben werden kann!

Um einen genaueren Überblick über die verschiedenen Komponenten der Anwendung und deren Zusammenhänge zu bekommen, bitte die Dokumentation des [dave-backend](https://github.com/it-at-m/dave-backend) zu Hilfe nehmen.

Diese EAI dient innerhalb der LHM dazu, für DAVe Schnittstellen zu anderen Systemen zur Verfügung zu stellen. Das geschieht indem Daten direkt im CSV- oder JSON-Format ausgegeben werden. Folgende Funktionen werden angeboten:
-	Ausgabe aller Zählstellen mit Koordinaten als CSV-Datei
- Ausgabe der Spitzenstunde einer bestimmten Zählung als CSV-Datei
- Daten aller Zählstellen und Zählungen des angegebenen Monats werden im JSON-Format zurückgegeben

## EAI-Routen
### Auswertung Zählstellenkoordinate

Routenendpunkt: `GET /lade-auswertung-zaehlstellen-koordinate`

Routenziel: `dave-backend-service GET /lade-auswertung-zaehlstellen-koordinate`

Aufgabe: Durchleiten des Requests an das Backend und Umwandlung der JSON-Response des Backends in CSV und Rückgabe der CSV an den Aufrufenden.

### Auswertung Spitzenstunde

Routenendpunkt: `GET /lade-auswertung-spitzenstunde`

Routenziel: `dave-backend-service GET /lade-auswertung-spitzenstunde`

Aufgabe: Durchleiten des Requests an das Backend und Umwandlung der JSON-Response des Backends in CSV und Rückgabe der CSV an den Aufrufenden.

### Auswertung aller Zählungen eines Monats
Routenendpunkt: `GET /lade-auswertung-visum`

Routenziel: `dave-backend-service GET /lade-auswertung-visum`

Aufgabe: Durchleiten des Requests an das Backend und Rückgabe der JSON-Response des Backends an den Aufrufenden.

## Built with
    Java 11

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are greatly appreciated.

If you have a suggestion that would make this better, please open an issue with the tag "enhancement", fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement". Don't forget to give the project a star! Thanks again!

    Open an issue with the tag "enhancement"
    Fork the Project
    Create your Feature Branch (git checkout -b feature/AmazingFeature)
    Commit your Changes (git commit -m 'Add some AmazingFeature')
    Push to the Branch (git push origin feature/AmazingFeature)
    Open a Pull Request

### Coding Conventions

We use the [itm-java-codeformat](https://github.com/it-at-m/itm-java-codeformat) project to apply code formatting conventions.
To add those conventions to your favorite IDE, please have a look at the [README of itm-java-codeformat](https://github.com/it-at-m/itm-java-codeformat#verwendung).

## License

Distributed under the MIT License. See LICENSE for more information.
## Contact

it@m - opensource@muenchen.de
