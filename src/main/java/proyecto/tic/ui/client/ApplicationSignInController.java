package proyecto.tic.ui.client;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.tic.CApplicationFX;
import proyecto.tic.services.UsuarioService;
import proyecto.tic.services.entities.Usuario;
import proyecto.tic.services.exceptions.ClientAlreadyExists;
import proyecto.tic.services.exceptions.InvalidInformation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@Component
@FxmlView("/applicationSignIn.fxml")
public class ApplicationSignInController implements Initializable {
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
    private void signIn(ActionEvent event) throws IOException, ClientAlreadyExists, InvalidInformation {
        Integer id = null;
        String firstName = nameBox.getText();
        String lastName = lastNameBox.getText();
        String email = mailBox.getText();
        Integer telefono = null;
        String direccion = dirBox.getText();
        String password = pasBox.getText();
        if (ciBox.getText() == null || nameBox.getText() == null || lastNameBox.getText() == null || mailBox.getText() == null || telBox.getText() == null || dirBox.getText() == null || pasBox.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Información incorrecta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese los datos nuevamente");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();

        }
        else if(service.getUsuario(Integer.parseInt(ciBox.getText()))!= null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Usuario ya existe");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese los datos nuevamente");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();

        }
        else{

            Usuario client = new Usuario(id, firstName, lastName, email, password, telefono, direccion);
            service.addClient(client);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
            Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationMenu.fxml"));
            Scene paginaInicio = new Scene(inicioSesion, 780, 450);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(paginaInicio);
            window.show();
        }
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationMenu.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ciBox.setText(null);
        nameBox.setText(null);
        lastNameBox.setText(null);
        mailBox.setText(null);
        telBox.setText(null);
        dirBox.setText(null);
        pasBox.setText(null);
    }
}