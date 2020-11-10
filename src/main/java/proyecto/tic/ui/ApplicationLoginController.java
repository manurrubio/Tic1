package proyecto.tic.ui;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.tic.ApplicationFX;
import proyecto.tic.services.UsuarioService;

import java.io.IOException;

@Component
@FxmlView("/applicationLogin.fxml")
public class ApplicationLoginController {

    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton volverButton;
    @Autowired
    private UsuarioService service;

    @FXML
    private void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationMenu.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();

    }

    public void login(ActionEvent actionEvent) {

    }
}
