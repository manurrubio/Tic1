package proyecto.tic.ui.admi;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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

@Component
@FxmlView("/applicationLoginAdmi.fxml")
public class ApplicationLoginAdmiController {
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
        boolean next=true;
        int uCi= Integer.parseInt(usuarioID.getText());
        String password= usuarioPassword.getText();
        if(as.getAdmi(uCi)==null){
            next=false;
        }
        if(as.getAdmi(uCi)!=null && !as.getAdmi(uCi).getPassword().equals(password)){
            next=false;
        }
        if(next==true){
            this.admi= as.getAdmi(uCi);
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
}
