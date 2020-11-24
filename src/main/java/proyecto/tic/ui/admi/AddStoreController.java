package proyecto.tic.ui.admi;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
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
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
@FxmlView("/applicationAddStore.fxml")
public class AddStoreController implements Initializable {
    @Autowired
    private StoreService ss;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXButton addStoreButton;

    @FXML
    private JFXButton volverButton;

    @FXML
    private Text storeAlreadyExists;

    @FXML
    private JFXTextField storeName;

    @FXML
    private JFXTextField storeDir;

    private Admin admi;

    void login(Admin admi){
        this.admi=admi;
    }

    @FXML
    void addStore(ActionEvent event) throws StoreAlreadyExists, InvalidInformation, IOException {
        if(storeName.getText() == null || storeDir.getText() == null){
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
        else if(ss.getStore(storeName.getText())!=null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Tienda ya existe");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese una tienda nueva");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
        else{
            Store toAdd= new Store(storeName.getText(),storeDir.getText(), admi);
            ss.addStore(toAdd);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tienda ingresada");
            alert.setHeaderText(null);
            alert.setContentText("Tienda ingresada con éxito");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        storeDir.setText(null);
        storeName.setText(null);
    }
}
