/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2016
 */
package de.muenchen.dave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Eine Spring Boot Anwendung, die alle Camel Routen startet.
 */
@SpringBootApplication
public class EaiApplication {

    /**
     * Startet die Anwendung.
     */
    public static void main(String[] args) {
        SpringApplication.run(EaiApplication.class, args);
    }


}