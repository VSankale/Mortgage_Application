package nl.ing.app;

import jakarta.ws.rs.ApplicationPath;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "nl.ing.app")
@EntityScan("nl.ing.app.entity")
@EnableConfigurationProperties
@ApplicationPath("/")
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }
}