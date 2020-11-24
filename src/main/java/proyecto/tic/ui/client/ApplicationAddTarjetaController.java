package proyecto.tic.ui.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import proyecto.tic.CApplicationFX;
import proyecto.tic.services.TarjetaService;
import proyecto.tic.services.entities.Tarjeta;
import proyecto.tic.services.entities.Usuario;
import proyecto.tic.services.exceptions.InvalidInformation;
import proyecto.tic.services.exceptions.TarjetaAlreadyExists;

import java.io.IOException;
import java.util.Optional;

@Component
@FxmlView("/applicationAddTarjeta.fxml")
public class ApplicationAddTarjetaController {
    @Autowired
    private TarjetaService ts;
    @Autowired
    private ApplicationComprarController cc;

    @FXML
    private JFXButton regTarjeta;

    @FXML
    private JFXButton volverButton;

    @FXML
    private JFXTextField nTarjeta;

    @FXML
    private JFXTextField cvv;

    @FXML
    private JFXTextField tVencimiento;

    @FXML
    private JFXTextField tLastName;

    @FXML
    private JFXTextField tName;


    private Usuario usuario;

    private String firstName = null;

    private String lastName = null;

    private Long tarjeta = null;

    private Integer inputCVV = null;

    private String vencimiento = null;

    void setUsuario(Usuario usuario){
        this.usuario=usuario;
    }

    @FXML
    void regTarjetaButton(ActionEvent event) throws InvalidInformation, TarjetaAlreadyExists, IOException {
        if(tName.getText() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nombre del titular nulo");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese el nombre del titular");
            alert.showAndWait();
        }
        else if (tLastName.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Apellido del titular nulo");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese el apellido del titular");
            alert.showAndWait();
        }
        else if (tVencimiento.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fecha de vencimiento nula");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese una fecha de vencimiento");
            alert.showAndWait();
        }
        else if (cvv.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cvv nulo");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese un cvv válido");
            alert.showAndWait();
        }
        else if (nTarjeta.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Numero de tarjeta nulo");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese un numero de tarjeta válido");
            alert.showAndWait();
        }
        else if(ts.getTarjeta(Long.valueOf(nTarjeta.getText())) != null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ya existe una tarjeta con este numero");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese una tarjeta que ya no se encuentre asociada");

            alert.showAndWait();

        }
        else {
            firstName = tName.getText();
            lastName = tLastName.getText();
            tarjeta = Long.valueOf(nTarjeta.getText());
            inputCVV = Integer.valueOf(cvv.getText());
            vencimiento = tVencimiento.getText();
            Tarjeta toAdd = new Tarjeta(tarjeta, vencimiento, firstName, lastName, inputCVV, usuario);
            ts.addTarjeta(toAdd);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tarjeta ingresada");
            alert.setHeaderText(null);
            alert.setContentText("Tarjeta ingresada con éxito");
            ButtonType buttonTypeOk = new ButtonType("Ok");
            alert.getButtonTypes().setAll(buttonTypeOk);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOk) {
                cc.setUsuario(usuario);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
                Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationComprar.fxml"));
                Scene paginaInicio = new Scene(inicioSesion, 780, 450);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(paginaInicio);
                window.show();
            }
            alert.showAndWait();
        }
    }


    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
        cc.setUsuario(usuario);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationComprar.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }


}
