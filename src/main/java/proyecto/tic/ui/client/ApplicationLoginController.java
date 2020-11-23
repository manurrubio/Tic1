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
import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Usuario;

import java.io.IOException;

@Component
@FxmlView("/applicationLogin.fxml")
public class ApplicationLoginController {
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
        int uCi= Integer.parseInt(usuarioID.getText());
        String password= usuarioPassword.getText();
        if(us.getUsuario(uCi)==null){
            next=false;
        }
        if(us.getUsuario(uCi)!=null && !us.getUsuario(uCi).getPassword().equals(password)){
            next=false;
        }
        if(next==true){
            this.usuario= us.getUsuario(uCi);
            //Agregué el usuario, hago cambio de pantalla
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
}
