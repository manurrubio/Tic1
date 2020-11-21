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
import org.springframework.stereotype.Component;
import proyecto.tic.AApplicationFX;

import java.io.File;
import java.io.IOException;


@Component
@FxmlView("/applicationAddBrand.fxml")
public class AddBrandController {

    @FXML
    private JFXTextField itemName;

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

    private File iFile;

    @FXML
    void addBrand(ActionEvent event) {
        brandAlreadyExists.setText("Marca ya existe");
        imageNotFound.setText("Imagen no ingresada");
    }

    @FXML
    void insertLogo(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Elegir logo de la marca");

        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        this.iFile=file;

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
