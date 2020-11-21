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
import proyecto.tic.CApplicationFX;
import proyecto.tic.services.BrandService;
import proyecto.tic.services.ItemService;
import proyecto.tic.services.entities.Brand;
import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Stock;
import proyecto.tic.services.entities.Store;
import proyecto.tic.services.exceptions.BrandNotExist;
import proyecto.tic.services.exceptions.InvalidInformation;
import proyecto.tic.services.exceptions.ItemAlreadyExists;
import proyecto.tic.ui.client.ApplicationProductWFilterController;

import java.io.IOException;

@Component
@FxmlView("/applicationAddProductII.fxml")
public class AddProductControllerII  {
    @Autowired
    private ApplicationProductWFilterController ap;
    @Autowired
    private ItemService is;
    @Autowired
    private BrandService bs;

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
    private JFXButton modaH;

    @FXML
    private JFXButton modaM;

    @FXML
    private JFXButton modaN;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton volverButton;

    @FXML
    private JFXTextField itemSize;

    @FXML
    private JFXTextField itemColor;

    @FXML
    private JFXTextField itemStore;

    @FXML
    private JFXButton pic4;

    @FXML
    private JFXButton pic2;

    @FXML
    private JFXButton pic1;

    @FXML
    private JFXButton pic3;

    @FXML
    private JFXTextField itemStock;

    private String itemName;
    private String itemType;
    private String itemDescription;
    private int itemPrice;
    private String itemCategory;
    private String itemBrand;

    public void setAtributos(String iName, String iType, String iDesc, int iPrice, String iCat,String iBrand){
        this.itemName=iName;
        this.itemType= iType;
        this.itemDescription= iDesc;
        this.itemPrice=iPrice;
        this.itemCategory=iCat;
        this.itemBrand=iBrand;
    }
/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        FileChooser fileChooser= new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        pic1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage= new Stage();
                File selectedFile= fileChooser.showOpenDialog(stage);
            }
        });

    }

 */ //COMO PONER IMÁGENES AYUDA

    private void imageFilter(){

    }

    @FXML
    void addProduct(ActionEvent event) throws InvalidInformation, ItemAlreadyExists, BrandNotExist, IOException {
        int id=0;
        //Brand brand= bs.getBrand(itemBrand); todavia no hay marcas
        Brand brand= new Brand();
        Long iStock= Long.valueOf(itemStock.getText());
        Stock stock= new Stock("nproducto+ color +talle","color","L", "Store", iStock);
        Store store= new Store();
        Item item= new Item(id,itemName,itemType,itemDescription,itemPrice,itemCategory,brand,stock,store,null,null,null,null);
        is.addItem(item);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationMenu.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();

    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationLogin.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();


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
        ap.setAtributo("hombre");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
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
        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
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
        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationProductWFilter.fxml"));
        ApplicationProductWFilterController controller = fxmlLoader.getController();
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationAddProductI.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }

}
