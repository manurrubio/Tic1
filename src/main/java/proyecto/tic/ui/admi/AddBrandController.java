package proyecto.tic.ui.admi;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.tic.AApplicationFX;
import proyecto.tic.services.BrandService;
import proyecto.tic.services.entities.Admin;
import proyecto.tic.services.entities.Brand;
import proyecto.tic.services.exceptions.BrandAlreadyExists;
import proyecto.tic.services.exceptions.BrandNotExist;
import proyecto.tic.services.exceptions.InvalidInformation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


@Component
@FxmlView("/applicationAddBrand.fxml")
public class AddBrandController {
    @Autowired
    private BrandService bs;

    @FXML
    private JFXTextField brandName;

    @FXML
    private JFXButton addBrandButton;

    @FXML
    private JFXButton volverButton;

    @FXML
    private Text brandAlreadyExists;

    @FXML
    private JFXButton logoButton;

    @FXML
    private Text imageNotFound;

    @FXML
    private AnchorPane anchorPane;

    private byte[] pic;

    private Admin admi;

    void login(Admin admi){
        this.admi=admi;
    }

    @FXML
    void addBrand(ActionEvent event) throws BrandNotExist, BrandAlreadyExists, InvalidInformation, IOException {
        boolean next=true;
        String bName= brandName.getText();
        if(bs.getRepository().findOneByName(bName)!=null){
            next=false;
            brandAlreadyExists.setText("Marca ya existe");
        }
        if(bName==null){
            next=false;
        }
        if(pic==null){
            next=false;
            imageNotFound.setText("Imagen no ingresada");
        }
        if(next==true) {
            Brand toAdd = new Brand(bName, pic);
            bs.addBrand(toAdd);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(AApplicationFX.getContext()::getBean);
            Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationMenuAdmiII.fxml"));
            Scene paginaInicio = new Scene(inicioSesion, 780, 450);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(paginaInicio);
            window.show();
        }
    }

    @FXML
    void insertLogo(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elegir logo de la marca");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            this.pic = Files.readAllBytes(file.toPath());


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
