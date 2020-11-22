package proyecto.tic.ui.client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.tic.CApplicationFX;
import proyecto.tic.services.ItemService;
import proyecto.tic.services.UsuarioService;
import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Usuario;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
@FxmlView("/applicationProductWFilter.fxml")
public class ApplicationProductWFilterController  implements Initializable{
    @Autowired
    private UsuarioService service;
    @Autowired
    private ApplicationProductController pc;
    @Autowired
    private ItemService is;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private JFXTextField search;
    @FXML
    private ImageView searchButton;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton signInButton;
    @FXML
    private GridPane grid;
    @FXML
    private ImageView toAdd;
    private Usuario usuario=null;
    private String category;
    boolean isBrand= false;
    private String brand;
    private Image[] images = new Image[150];

    public void setAtributo(String category){
        this.category=category;
    }
    void inicioSesion(Usuario usuario){this.usuario=usuario;}


    private Image[] addImagesToArray(String filter) {
        //tengo una lista de fotos en forma
        Image[] toReturn = new Image[150];

        List<Item> allItems = is.getRepository().findByCategory(category);
        for (int i = 0; i < allItems.size(); i++) {
            byte[] img = allItems.get(i).getImagenPrincipal();
            ByteArrayInputStream bis = new ByteArrayInputStream(img);
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();

            }
            Image toAdd = SwingFXUtils.toFXImage(bImage, null);
            if(toAdd!=null){
                toReturn[i] = toAdd;
            }
        }
        return toReturn;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (usuario!=null){
            loginButton.setText("Cerrar sesión");
        }

        grid.getChildren().clear();
        grid.setPadding(new Insets(90, 7, 80, 7));
        grid.setHgap(150);
        grid.setVgap(100);
        boolean complete = false;
        images = addImagesToArray(category);
        int i = 0;
        int m = 0; // m es la columna
        while (!complete) {
            for (int n = 0; n < 4 && !complete; n++) { //n es la fila
                toAdd = new ImageView();
                toAdd.setFitWidth(150);
                toAdd.setFitHeight(150);
                toAdd.setImage(images[i]);
                grid.add(toAdd, n, m);
                // boton :)
                int finalI = i;
                toAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        List<Item> allItems = is.getRepository().findByCategory(category);
                        Item item = allItems.get(finalI);

                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
                        pc.inicioSesion(usuario);
                        Parent inicioSesion = null;
                        try {
                            pc.setItem(item);
                            inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationProduct.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(paginaInicio);
                        window.show();

                    }
                });
                //
                if (images[i + 1] == null) {
                    complete = true;
                } else {
                    i = i + 1;
                }
            }
            if (images[i + 1] == null) {
                complete = true;
            } else {
                m = m + 1;
                grid.addRow(m); //le agrego otra fila
            }

        }
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        if(usuario!=null){
            usuario=null;
            loginButton.setText("Iniciar sesión");
        }else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
            Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationLogin.fxml"));
            Scene paginaInicio = new Scene(inicioSesion, 780, 450);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(paginaInicio);
            window.show();
        }


}

    @FXML
    private void signIn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationSignIn.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }

    @FXML
    private void goToModaH(ActionEvent event) throws IOException {
        grid.getChildren().clear();
        category="hombre";
        images=null;
        filter(category);
    }

    @FXML
    private void goToModaM(ActionEvent event) throws IOException {
        grid.getChildren().clear();
        category="mujer";
        images=null;
        filter(category);

    }

    @FXML
    private void goToModaN(ActionEvent event) throws IOException {
        grid.getChildren().clear();
        category="niño";
        images=null;
        filter(category);
    }


    private void filter(String category){
        images= addImagesToArray(category);
        List<Item> allItems = is.getRepository().findByCategory(category);
        boolean complete=false;
        int i = 0;
        int m = 0; // m es la columna
        while (!complete) {
            for (int n = 0; n < 4 && !complete; n++) { //n es la fila
                toAdd = new ImageView();
                toAdd.setFitWidth(150);
                toAdd.setFitHeight(150);
                toAdd.setImage(images[i]);
                grid.add(toAdd, n, m);
                int finalI = i;
                toAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        List<Item> allItems = is.getRepository().findByCategory(category);
                        Item item = allItems.get(finalI);

                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
                        Parent inicioSesion = null;
                        try {
                            inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationProduct.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(paginaInicio);
                        window.show();

                    }
                });
                if (images[i + 1] == null) {
                    complete = true;
                } else {
                    i = i + 1;
                }
            }
            if (images[i + 1] == null) {
                complete = true;
            } else {
                m = m + 1;
                grid.addRow(m); //le agrego otra fila
            }
        }
    }
}