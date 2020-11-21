package proyecto.tic;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApp {
    public static void main(String[] args) {
        Application.launch(CApplicationFX.class, args);
    }
}
