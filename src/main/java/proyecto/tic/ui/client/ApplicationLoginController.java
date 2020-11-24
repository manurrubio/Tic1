package proyecto.tic.ui.client;

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
import proyecto.tic.CApplicationFX;
import proyecto.tic.services.UsuarioService;
import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/applicationLogin.fxml")
public class ApplicationLoginController implements Initializable {
    @Autowired
    private UsuarioService us;
    @Autowired
    private ApplicationProductController pc;
    @Autowired
    private ApplicationMenuController mc;

    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton volverButton;
    @FXML
    private JFXTextField usuarioID;
    @FXML
    private JFXPasswordField usuarioPassword;

    private Usuario usuario=null;
    private Item item=null;

    void setItem(Item item){
        this.item=item;
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        if(item==null) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
            Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationMenu.fxml"));
            Scene paginaInicio = new Scene(inicioSesion, 780, 450);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(paginaInicio);
            window.show();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
            pc.setItem(item);
            Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationProduct.fxml"));
            Scene paginaInicio = new Scene(inicioSesion, 780, 450);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(paginaInicio);
            window.show();
        }

    }

    public void login(ActionEvent event) throws IOException {
        boolean next=true;
        Integer uCi= null;
        String password= usuarioPassword.getText();
        if(usuarioID.getText() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Usuario nulo");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese un usuario");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
        else if(us.getUsuario(Integer.parseInt(usuarioID.getText()))==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Usuario inexistente");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese un usuario existente");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();

        }
        else if(us.getUsuario(Integer.parseInt(usuarioID.getText()))!=null && !us.getUsuario(Integer.parseInt(usuarioID.getText())).getPassword().equals(password)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Contraseña incorrecta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor intente nuevamente");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
        else{
            uCi = Integer.parseInt(usuarioID.getText());
            this.usuario= us.getUsuario(uCi);
            if(item==null) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
                mc.inicioSesion(usuario);
                Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationMenu.fxml"));
                Scene paginaInicio = new Scene(inicioSesion, 780, 450);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(paginaInicio);
                window.show();
            }else{
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
                pc.setItem(item);
                pc.inicioSesion(usuario);
                Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationProduct.fxml"));
                Scene paginaInicio = new Scene(inicioSesion, 780, 450);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(paginaInicio);
                window.show();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usuarioID.setText(null);
        usuarioPassword.setText(null);
    }
}
