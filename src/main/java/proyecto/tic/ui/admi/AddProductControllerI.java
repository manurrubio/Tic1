package proyecto.tic.ui.admi;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import proyecto.tic.AApplicationFX;
import proyecto.tic.CApplicationFX;
import proyecto.tic.persistence.BrandRepository;
import proyecto.tic.persistence.StoreRepository;
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

    @FXML
    private void next(ActionEvent event) throws IOException{
        boolean next=true;
        String iName= itemName.getText();

        String iType= itemType.getText();

        String iDesc= itemDescription.getText();

        int iPrice = Integer.parseInt(itemPrice.getText());
        if(iPrice<=0){
                itemPrice.setText("Precio incorrecto");
                next=false;
        }

        String iCat= itemCategory.getText();
        if(!iCat.equals("hombre")&&!iCat.equals("mujer")&&!iCat.equals("niño")){
            itemCategory.setText("Categoría incorrecta");
            next=false;
        }

        String iBrand= itemBrand.getText();
        Brand brand=  br.findOneByName(iBrand);
        if(br.findOneByName(iBrand)==null) {
            itemBrand.setText("Marca no existe");
            next = false;
        }

        String iStore= itemStore.getText();
        Store store= sr.findOneByName(iStore);
        if(sr.findOneByName(iStore)==null){
            itemStore.setText("Tienda no existe");
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
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationMenuAdmi.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }


}
