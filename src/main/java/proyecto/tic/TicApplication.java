package proyecto.tic;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicApplication {
    public static void main(String[] args) {
        Application.launch(ApplicationFX.class, args);
    }
}
