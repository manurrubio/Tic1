package proyecto.tic.ui.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
@FxmlView("/applicationAddTarjeta.fxml")
public class ApplicationAddTarjetaController implements Initializable {
    @Autowired
    private TarjetaService ts;
    @Autowired
    private ApplicationComprarController cc;

    @FXML
    private JFXButton regTarjeta;

    @FXML
    private JFXButton volverButton;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField apellido;

    @FXML
    private JFXTextField numero;

    @FXML
    private JFXTextField cvv;

    @FXML
    private JFXTextField vencimiento;


    private Usuario usuario;


    void setUsuario(Usuario usuario){
        this.usuario=usuario;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText(null);
        apellido.setText(null);
        numero.setText(null);
        cvv.setText(null);
        vencimiento.setText(null);
    }
    @FXML
    public void registrar(ActionEvent event) throws InvalidInformation, TarjetaAlreadyExists, IOException {
        if (name.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nombre del titular nulo");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese el nombre del titular");
            alert.showAndWait();
        } else if (apellido.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Apellido del titular nulo");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese el apellido del titular");
            alert.showAndWait();
        } else if (numero.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Número de tarjeta incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese el número de tarjeta");
            alert.showAndWait();
        } else if (cvv.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("CVV de tarjeta incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese el CVV de su tarjeta");
            alert.showAndWait();
        } else if (vencimiento.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vencimiento de tarjeta nulo");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese el vencimiento de su tarjeta");
            alert.showAndWait();
        } else {
            String firstName = name.getText();
            String lastName = apellido.getText();
            Long tarjeta = Long.valueOf(numero.getText());
            Integer inputCVV = Integer.valueOf(cvv.getText());
            String venc = vencimiento.getText();
            Tarjeta toAdd = new Tarjeta(tarjeta, venc, firstName, lastName, inputCVV, usuario);
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
