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
    void buy(ActionEvent event) {

        Long tarjeta= Long.valueOf(nTarjeta.getText());
        if(ts.getTarjeta(tarjeta)==null){
            //alert field
        }else{
            String opcion= envio.getValue();
            String selectedCvv= cvv.getText();
            if(ts.getTarjeta(tarjeta).getCvc().equals(selectedCvv)){
                ss.buyStock(item.getName()+ " " + item.getStore().getName() +" " + selectedColor +" " +selectedSize, (long) 1);
            }else{
                //CVV incorrecto
            }



        }



    }

    @FXML
    void volver(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listEnvio = FXCollections.observableArrayList("Retiro en el local", "Envío a domicilio");
        envio.setItems(listEnvio);
    }
}
