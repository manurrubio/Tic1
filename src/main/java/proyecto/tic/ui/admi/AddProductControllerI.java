package proyecto.tic.ui.admi;

import javafx.event.ActionEvent;
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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import proyecto.tic.AApplicationFX;
import proyecto.tic.persistence.BrandRepository;
import proyecto.tic.persistence.StoreRepository;
import proyecto.tic.services.entities.Admin;
import proyecto.tic.services.entities.Brand;
import proyecto.tic.services.entities.Store;
import proyecto.tic.ui.client.ApplicationProductWFilterController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/applicationAddProductI.fxml")
public class AddProductControllerI implements Initializable {
    @Autowired
    private ApplicationProductWFilterController ap;
    @Autowired
    private BrandRepository br;
    @Autowired
    private StoreRepository sr;

    @Autowired
    private AddProductControllerII apii;

    @FXML
    private JFXButton siguienteButton;

    @FXML
    private JFXButton volverButton;

    @FXML
    private JFXTextField itemName;

    @FXML
    private JFXTextField itemDescription;

    @FXML
    private JFXTextField itemPrice;

    @FXML
    private JFXTextField itemBrand;

    @FXML
    private JFXTextField itemCategory;

    @FXML
    private JFXTextField itemType;

    @FXML
    private JFXTextField itemStore;

    private Admin admi;

    void login(Admin admi){
        this.admi=admi;
    }

    @FXML
    private void next(ActionEvent event) throws IOException{
        if(itemName.getText() == null || itemPrice.getText() == null || itemBrand.getText() == null || itemCategory.getText() == null || itemType.getText() == null || itemStore.getText() == null || itemDescription.getText()==null){
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
        else if(Integer.parseInt(itemPrice.getText()) <= 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Precio incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese el precio nuevamente");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
        else if(!itemCategory.getText().equals("hombre")&&!itemCategory.getText().equals("mujer")&&!itemCategory.getText().equals("niño")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Categoría incorrecta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese una categoría válida (hombre, mujer, niño)");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
        else if(br.findOneByName(itemBrand.getText())==null) {
            Brand brand=  br.findOneByName(itemBrand.getText());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Marca no existe");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese una marca existente");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
        else if(sr.findOneByName(itemStore.getText())==null){
            Store store= sr.findOneByName(itemStore.getText());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Tienda no existe");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese una tienda existente");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }else
            {
                int iPrice = Integer.parseInt(itemPrice.getText());
                Brand iBrand = br.findOneByName(itemBrand.getText());
                Store iStore = sr.findOneByName(itemStore.getText());
                apii.setAtributos(itemName.getText(), itemType.getText(), itemDescription.getText(), iPrice, itemCategory.getText(), iBrand, iStore);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AApplicationFX.getContext()::getBean);
                Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationAddProductII.fxml"));
                Scene paginaInicio = new Scene(inicioSesion, 780, 450);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(paginaInicio);
                window.show();
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
        itemName.setText(null);
        itemDescription.setText(null);
        itemPrice.setText(null);
        itemBrand.setText(null);
        itemCategory.setText(null);
        itemType.setText(null);
        itemStore.setText(null);
    }
}
