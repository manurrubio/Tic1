package proyecto.tic.ui.admi;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.tic.AApplicationFX;
import proyecto.tic.CApplicationFX;
import proyecto.tic.services.AdminService;
import proyecto.tic.services.UsuarioService;
import proyecto.tic.services.entities.Admin;
import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Usuario;
import proyecto.tic.ui.client.ApplicationMenuController;
import proyecto.tic.ui.client.ApplicationProductController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/applicationLoginAdmi.fxml")
public class ApplicationLoginAdmiController implements Initializable {
    @Autowired
    private AdminService as;
    @Autowired
    private ApplicationMenuAdmiControllerII am;

    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton volverButton;
    @FXML
    private JFXTextField usuarioID;
    @FXML
    private JFXPasswordField usuarioPassword;
    private Admin admi;

    @FXML
    private void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationMenuAdmiI.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();

    }

    public void login(ActionEvent event) throws IOException {
        if(usuarioID.getText() == null || usuarioPassword.getText() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos nulos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese todos los campos");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
        else if(as.getAdmi(Integer.parseInt(usuarioID.getText()))==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Administrador no existe");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese nuevamente los datos");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
        else if(as.getAdmi(Integer.parseInt(usuarioID.getText()))!=null && !as.getAdmi(Integer.parseInt(usuarioID.getText())).getPassword().equals(usuarioPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Contraseña incorrecta");
            alert.setHeaderText(null);
            alert.setContentText("Ingrese contraseña nuevamente");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
        else{
            this.admi= as.getAdmi(Integer.parseInt(usuarioID.getText()));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(AApplicationFX.getContext()::getBean);
            am.login(admi);
            Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationMenuAdmiII.fxml"));
            Scene paginaInicio = new Scene(inicioSesion, 780, 450);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(paginaInicio);
            window.show();

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usuarioID.setText(null);
        usuarioPassword.setText(null);
    }
}
