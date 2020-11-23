package proyecto.tic.ui.client;

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
import javafx.scene.control.CheckBox;
import proyecto.tic.CApplicationFX;
import proyecto.tic.services.TarjetaService;
import proyecto.tic.services.entities.Tarjeta;
import proyecto.tic.services.entities.Usuario;
import proyecto.tic.services.exceptions.InvalidInformation;
import proyecto.tic.services.exceptions.TarjetaAlreadyExists;

import java.io.IOException;

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

    void setUsuario(Usuario usuario){
        this.usuario=usuario;
    }

    @FXML
    void regTarjetaButton(ActionEvent event) throws InvalidInformation, TarjetaAlreadyExists {
        boolean next=true;

        String firstName=null;
        if(tName.getText()==null){
            //error
            next=false;
        }else{
            firstName=tName.getText();
        }

        String lastName=null;
        if(tLastName.getText()==null){
            //error
            next=false;
        }else{
            lastName=tLastName.getText();
        }

        Long tarjeta=null;
        if(nTarjeta.getText()==null){
            //error
            next=false;
        }else{
            tarjeta= Long.valueOf(nTarjeta.getText());
            if(ts.getTarjeta(tarjeta)!=null){
                //tarjeta ya existe
                next=false;
            }
        }

        Integer inputCVV=null;
        if(cvv.getText()==null){
            //error
            next=false;
        }else{
            inputCVV= Integer.valueOf(cvv.getText());
        }

        String vencimiento=null;
        if(tVencimiento.getText()==null){
            //error
            next=false;
        }else{
            vencimiento= tVencimiento.getText();
        }

        if(next){
            Tarjeta toAdd= new Tarjeta(tarjeta,vencimiento,firstName,lastName,inputCVV,usuario);
            ts.addTarjeta(toAdd);
            //imprimir tarjeta ingresada con exito
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
