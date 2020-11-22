package proyecto.tic.ui.client;

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
import proyecto.tic.CApplicationFX;
import proyecto.tic.services.UsuarioService;
import proyecto.tic.services.entities.Usuario;

import java.io.IOException;

@Component
@FxmlView("/applicationLogin.fxml")
public class ApplicationLoginController {
    @Autowired
    private UsuarioService us;

    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton volverButton;
    @FXML
    private JFXTextField usuarioID;
    @FXML
    private JFXPasswordField usuarioPassword;

    private Usuario usuario=null;


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

    public void login(ActionEvent actionEvent) {
        boolean next=true;
        int uCi= Integer.parseInt(usuarioID.getText());
        String password= usuarioPassword.getText();
        if(us.getUsuario(uCi)==null){
            next=false;
        }
        if(us.getUsuario(uCi)!=null && us.getUsuario(uCi).getPassword()!=password){
            next=false;
        }
        if(next==true){
            this.usuario= us.getUsuario(uCi);
        }
    }
}
