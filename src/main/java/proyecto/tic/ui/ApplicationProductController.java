package proyecto.tic.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.tic.ApplicationFX;
import proyecto.tic.services.ItemService;
import proyecto.tic.services.entities.Brand;
import proyecto.tic.services.entities.Item;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
@FxmlView("/applicationProduct.fxml")
public class ApplicationProductController implements Initializable {

    @Autowired
    private ItemService is;
    @Autowired
    private ApplicationProductWFilterController ap;

    @FXML
    private JFXTextField search;

    @FXML
    private ImageView searchButton;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton signInButton;

    @FXML
    private ImageView carritoButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane grid;

    @FXML
    private JFXButton comprar;

    @FXML
    private Label productName;

    @FXML
    private GridPane pImage;

    @FXML
    private Label productBrand;

    @FXML
    private Label productSize;

    @FXML
    private Label productStore;

    @FXML
    private Label productPrice;

    @FXML
    private Label pDescription;

    @FXML
    private JFXButton modaH;

    @FXML
    private JFXButton modaM;

    @FXML
    private JFXButton modaN;

    private Item toShow;

    void setItem(Item item){
        this.toShow=item;
    }

    private List<Image> getImages(Item item){
        List<Image> allImages= new ArrayList<>();
        if(item.getImagenPrincipal()!=null){
            byte[] img= item.getImagenPrincipal();
            allImages.add(getImage(img));
        }
        if(item.getImagen1()!=null){
            byte[] img= item.getImagen1();
            allImages.add(getImage(img));
        }if(item.getImagen2()!=null){
            byte[] img= item.getImagen2();
            allImages.add(getImage(img));
        }if(item.getImagen3()!=null){
            byte[] img= item.getImagen3();
            allImages.add(getImage(img));
        }if(item.getImagen4()!=null){
            byte[] img= item.getImagen4();
            allImages.add(getImage(img));
        }if(item.getImagen5()!=null){
            byte[] img= item.getImagen5();
            allImages.add(getImage(img));
        }
        return allImages;
    }

// no anda lo de ver la marca por alguna razon
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(toShow.getType()!=null && toShow.getBrand()!=null){
            String name= toShow.getType()+" "+toShow.getBrand().getName();
            productName.setText(name.toUpperCase());
        }else if(toShow.getType()!=null){
            productName.setText(toShow.getType().toUpperCase());
        }
        if(toShow.getBrand()!=null){
            productBrand.setText("MARCA: " + toShow.getBrand().getName());
        }else{
            productBrand.setText(null);
        }
        if(toShow.getSize()!=null){
            productSize.setText("TALLE: " +toShow.getSize());
        }
        if((Integer)toShow.getPrice()!=null){
            productPrice.setText("PRECIO: "+ ((Integer)toShow.getPrice()).toString());
        }
        if((getImage(toShow.getImagenPrincipal()))!=null) {
            pImage.getChildren().clear();
            ImageView toAdd = new ImageView();
            toAdd.setFitWidth(250);
            toAdd.setFitHeight(250);
            toAdd.setImage(getImage(toShow.getImagenPrincipal()));
            pImage.add(toAdd,0,0);

        }
        sideGrid();
    }

    private void sideGrid(){
        grid.getChildren().clear();
        grid.setPadding(new Insets(90, 7, 80, 7));
        grid.setHgap(100);
        grid.setVgap(100);
        boolean complete = false;
        List <Image>images = getImages(toShow);
        while(!complete) {
            for (int i = 0; i < images.size(); i++) {
                ImageView toAdd = new ImageView();
                toAdd.setFitWidth(150);
                toAdd.setFitHeight(150);
                toAdd.setImage(images.get(i));
                grid.add(toAdd, 0, i);
                //asdf
                int finalI = i;
                //cuando clickeo una foto la abre!
                toAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        Image img = images.get(finalI);
                        pImage.getChildren().clear();
                        ImageView add = new ImageView();
                        add.setFitWidth(250);
                        add.setFitHeight(250);
                        add.setImage(img);
                        pImage.add(add, 0, 0);
                    }
                });
                if (i == images.size() - 1) {
                    complete = true;
                } else if (images.get(i + 1) != null) {
                    grid.addRow(i + 1);
                } else {
                    complete = true;
                }
            }
        }
    }

    private Image getImage(byte[] img) {
        ByteArrayInputStream bis = new ByteArrayInputStream(img);
        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image toAdd = SwingFXUtils.toFXImage(bImage, null);
        return toAdd;
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationLogin.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();


    }

    @FXML
    private void signIn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationSignIn.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }

    @FXML
    private void goToModaH(ActionEvent event) throws IOException {
        ap.setAtributo("hombre");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationProductWFilter.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }

    @FXML
    private void goToModaM(ActionEvent event) throws IOException {
        ap.setAtributo("mujer");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationProductWFilter.fxml"));
        ApplicationProductWFilterController controller = fxmlLoader.getController();
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }

    @FXML
    private void goToModaN(ActionEvent event) throws IOException {
        ap.setAtributo("niño");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationProductWFilter.fxml"));
        ApplicationProductWFilterController controller = fxmlLoader.getController();
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }


}