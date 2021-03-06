package proyecto.tic.ui.client;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import proyecto.tic.CApplicationFX;
import proyecto.tic.services.StockService;
import proyecto.tic.services.TarjetaService;
import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Stock;
import proyecto.tic.services.entities.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
@FxmlView("/applicationComprar.fxml")
public class ApplicationComprarController implements Initializable {
    @Autowired
    private StockService ss;
    @Autowired
    private TarjetaService ts;
    @Autowired
    private ApplicationAddTarjetaController at;
    @Autowired
    private ApplicationMenuController mc;

    @FXML
    private JFXButton buyButton;

    @FXML
    private JFXButton volverButton;

    @FXML
    private JFXTextField nTarjeta;

    @FXML
    private JFXTextField cvv;

    @FXML
    private JFXButton createTarjeta;

    @FXML
    private JFXComboBox<String> envio;

    private String selectedSize;

    private String selectedColor;

    private Item item;

    private Usuario usuario;

    void setUsuario(Usuario usuario){
        this.usuario=usuario;
    }

    void setColorAndSize(String color, String size){
        this.selectedColor=color;
        this.selectedSize=size;
    }

    void setItem(Item item){
        this.item=item;
    }

    @FXML
    void addTarjeta(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
        at.setUsuario(usuario);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationAddTarjeta.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }

    @FXML
    void buy(ActionEvent event) throws IOException {

        Long tarjeta = null;
        if(nTarjeta.getText() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Número de tarjeta nulo");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese el número de su tarjeta");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
        else if(cvv.getText() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("CVV de tarjeta nulo");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese el cvv de su tarjeta");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
        else if (ts.getTarjeta(Long.parseLong(nTarjeta.getText())) == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Numero de tarjeta incorrecta");
            alert.setHeaderText(null);
            alert.setContentText("Porfavor introduzca un numero de tarjeta que este registrada");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
        else if(!ts.getTarjeta(Long.parseLong(nTarjeta.getText())).getCvc().equals(Integer.parseInt(cvv.getText()))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Numero de tarjeta o cvv incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("El numero de tarjeta no coincide con el cvv");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
        else if( envio.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleccione opcion de envío");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar una opcion de envío");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
        else {
                tarjeta = Long.parseLong(nTarjeta.getText());
                Integer selectedCvv = Integer.parseInt(cvv.getText());
                if (ts.getTarjeta(tarjeta).getCvc().equals(selectedCvv)) {
                    ss.buyStock(item.getName() + " " + item.getStore().getName() + " " + selectedColor + " " + selectedSize, (long) 1);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Compra exitosa");
                    alert.setHeaderText(null);
                    alert.setContentText("Su compra fue realizada con éxito");
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(
                            getClass().getResource("/myDialogs.css").toExternalForm());
                    dialogPane.getStyleClass().add("myDialog");
                    ButtonType buttonTypeOk = new ButtonType("Ok");
                    alert.getButtonTypes().setAll(buttonTypeOk);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOk) {
                        mc.inicioSesion(usuario);
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
                        at.setUsuario(usuario);
                        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationMenu.fxml"));
                        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(paginaInicio);
                        window.show();
                    }
            }
        }
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
        at.setUsuario(usuario);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationProduct.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listEnvio = FXCollections.observableArrayList("Retiro en el local", "Envío a domicilio");
        envio.setItems(listEnvio);
        cvv.setText(null);
        nTarjeta.setText(null);
    }


}
