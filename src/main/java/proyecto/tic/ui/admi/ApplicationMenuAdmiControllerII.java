package proyecto.tic.ui.admi;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.tic.AApplicationFX;
import proyecto.tic.services.entities.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/applicationMenuAdmiII.fxml")
public class ApplicationMenuAdmiControllerII implements Initializable {
    @Autowired
    private AddBrandController ab;
    @Autowired
    private AddProductControllerI ap;
    @Autowired
    private AddStoreController as;

    @FXML
    private JFXButton addProductButton;

    @FXML
    private JFXButton addBrandButton;

    @FXML
    private JFXButton addStoreButton;

    private Admin admi;

    void login(Admin admi){
        this.admi=admi;
    }
    @FXML
    void addBrand(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AApplicationFX.getContext()::getBean);
        ab.login(admi);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationAddBrand.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }

    @FXML
    void addProduct(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AApplicationFX.getContext()::getBean);
        ap.login(admi);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationAddProductI.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }

    @FXML
    void addStore(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AApplicationFX.getContext()::getBean);
        as.login(admi);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationAddStore.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
