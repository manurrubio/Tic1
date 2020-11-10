package proyecto.tic.ui;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.tic.ApplicationFX;
import proyecto.tic.services.UsuarioService;
import proyecto.tic.services.entities.Client;
import proyecto.tic.services.exceptions.ClientAlreadyExists;
import proyecto.tic.services.exceptions.InvalidInformation;

import java.io.IOException;


@Component
@FxmlView("/applicationSignIn.fxml")
public class ApplicationSignInController{
    @FXML
    private TextField ciBox;
    @FXML
    private TextField nameBox;
    @FXML
    private TextField lastNameBox;
    @FXML
    private TextField mailBox;
    @FXML
    private TextField telBox;
    @FXML
    private TextField dirBox;
    @FXML
    private JFXPasswordField pasBox;
    @FXML
    private JFXButton signInButton;
    @FXML
    private JFXButton volverButton;

    @Autowired
    private UsuarioService service;

    @FXML
    private void signIn(ActionEvent event)  {
        int id= Integer.parseInt(ciBox.getText());
        String firstName= nameBox.getText();
        String lastName= lastNameBox.getText();
        String email= mailBox.getText();
        int telefono= Integer.parseInt(telBox.getText());
        String direccion= dirBox.getText();
        String password= pasBox.getText();
//agregar en scene builder
        try {
            Client client = new Client(id,firstName,lastName,email,password,telefono,direccion);
            service.addClient(client);
        } catch (InvalidInformation | ClientAlreadyExists invalidInformation) {
            clean();
        }

        clean();

    }

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

    private void clean(){
        ciBox.clear();
        nameBox.clear();
        lastNameBox.clear();
        mailBox.clear();
    }



}