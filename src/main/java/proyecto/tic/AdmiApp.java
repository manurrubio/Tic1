package proyecto.tic;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdmiApp {
    public static void main(String[] args) {
        Application.launch(AApplicationFX.class, args);
    }
}