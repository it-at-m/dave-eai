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
    
    public static void main(final String[] args) {
        SpringApplication.run(EaiApplication.class, args);
    }

}
