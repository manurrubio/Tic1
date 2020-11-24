package proyecto.tic.ui.admi;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.tic.AApplicationFX;
import proyecto.tic.CApplicationFX;
import proyecto.tic.services.StoreService;
import proyecto.tic.services.entities.Admin;
import proyecto.tic.services.entities.Store;
import proyecto.tic.services.exceptions.InvalidInformation;
import proyecto.tic.services.exceptions.StoreAlreadyExists;

import java.io.IOException;
import java.util.Optional;

@Component
@FxmlView("/applicationAddStore.fxml")
public class AddStoreController {
    @Autowired
    private StoreService ss;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTextField storeName;

    @FXML
    private JFXButton addStoreButton;

    @FXML
    private JFXButton volverButton;

    @FXML
    private Text storeAlreadyExists;

    @FXML
    private JFXTextField storeDir;

    private Admin admi;

    void login(Admin admi){
        this.admi=admi;
    }

    @FXML
    void addStore(ActionEvent event) throws StoreAlreadyExists, InvalidInformation, IOException {
        boolean next=true;
        String sName= storeName.getText();
        if(ss.getStore(sName)!=null){
            next=false;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Tienda ya existe");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese una tienda nueva");
            alert.showAndWait();
        }
        String sDir= storeDir.getText();
        if(sName==null||sDir==null){
            next=false;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo nulo");
            alert.setHeaderText(null);
            alert.setContentText("Por favor complete todos los campos");
            alert.showAndWait();
        }
        if(next=true){
            Store toAdd= new Store(sName,sDir, admi);
            ss.addStore(toAdd);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tienda ingresada");
            alert.setHeaderText(null);
            alert.setContentText("Tienda ingresada con éxito");
            ButtonType buttonTypeOk = new ButtonType("Ok");
            alert.getButtonTypes().setAll(buttonTypeOk);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOk) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AApplicationFX.getContext()::getBean);
                Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationMenuAdmiII.fxml"));
                Scene paginaInicio = new Scene(inicioSesion, 780, 450);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(paginaInicio);
                window.show();
            }
        }

    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationMenuAdmiII.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }

}
