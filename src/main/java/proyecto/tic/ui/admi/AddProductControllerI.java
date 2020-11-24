package proyecto.tic.ui.admi;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

@Component
@FxmlView("/applicationAddProductI.fxml")
public class AddProductControllerI {
    @Autowired
    private ApplicationProductWFilterController ap;
    @Autowired
    private BrandRepository br;
    @Autowired
    private StoreRepository sr;

    @Autowired
    private AddProductControllerII apii;

    @FXML
    private JFXTextField itemName;

    @FXML
    private JFXButton siguienteButton;

    @FXML
    private JFXButton volverButton;

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
        boolean next=true;
        String iName= itemName.getText();

        String iType= itemType.getText();

        String iDesc= itemDescription.getText();

        int iPrice = Integer.parseInt(itemPrice.getText());
        if(iPrice<=0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Precio incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese el precio nuevamente");
            alert.showAndWait();
                next=false;
        }

        String iCat= itemCategory.getText();
        if(!iCat.equals("hombre")&&!iCat.equals("mujer")&&!iCat.equals("niño")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Categoría incorrecta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese una categoría válida (hombre, mujer, niño)");
            alert.showAndWait();
            next=false;
        }

        String iBrand= itemBrand.getText();
        Brand brand=  br.findOneByName(iBrand);
        if(br.findOneByName(iBrand)==null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Marca no existe");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese una marca existente");
            alert.showAndWait();
            next = false;
        }

        String iStore= itemStore.getText();
        Store store= sr.findOneByName(iStore);
        if(sr.findOneByName(iStore)==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Tienda no existe");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese una tienda existente");
            alert.showAndWait();
            next=false;
        }

        if(next) {
            apii.setAtributos(iName, iType, iDesc, iPrice, iCat, brand,store);
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


}
