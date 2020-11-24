package proyecto.tic.ui.admi;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import proyecto.tic.AApplicationFX;
import proyecto.tic.services.BrandService;
import proyecto.tic.services.ItemService;
import proyecto.tic.services.StockService;
import proyecto.tic.services.StoreService;
import proyecto.tic.services.entities.*;
import proyecto.tic.services.exceptions.BrandNotExist;
import proyecto.tic.services.exceptions.InvalidInformation;
import proyecto.tic.services.exceptions.ItemAlreadyExists;
import proyecto.tic.ui.client.ApplicationProductWFilterController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

@Component
@FxmlView("/applicationAddProductII.fxml")
public class AddProductControllerII implements Initializable {
    @Autowired
    private ApplicationProductWFilterController ap;
    @Autowired
    private ItemService is;
    @Autowired
    private BrandService bs;
    @Autowired
    private StoreService ss;
    @Autowired
    private StockService stockService;

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
    private JFXTextField itemStock;

    @FXML
    private JFXButton pic4;

    @FXML
    private JFXButton pic2;

    @FXML
    private JFXButton pic1;

    @FXML
    private JFXButton pic3;


    private String itemName;
    private String itemType;
    private String itemDescription;
    private int itemPrice;
    private String itemCategory;
    private Brand itemBrand;
    private Store itemStore;
    private byte[] iPic1;
    private byte[] iPic2;
    private byte[] iPic3;
    private byte[] iPic4;

    private Admin admi;

    void login(Admin admi){
        this.admi=admi;
    }


    public void setAtributos(String iName, String iType, String iDesc, int iPrice, String iCat,Brand iBrand, Store iStore){
        this.itemName=iName;
        this.itemType= iType;
        this.itemDescription= iDesc;
        this.itemPrice=iPrice;
        this.itemCategory=iCat;
        this.itemBrand=iBrand;
        this.itemStore=iStore;
    }

    @FXML
    void addProduct(ActionEvent event) throws InvalidInformation, ItemAlreadyExists, BrandNotExist, IOException {
        if (itemSize.getText() == null || itemColor.getText() == null || itemStock.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos nulos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese todos los campos");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        } else if (Long.parseLong(itemStock.getText()) <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Stock no puede ser negativo");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese un stock válido");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }else if(iPic1 == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No ingreso imagen principal");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese una imagen principal");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
        else {
            Long iStock = Long.parseLong(itemStock.getText());
            String[] colors = itemColor.getText().split(", ");
            String[] sizes = itemSize.getText().split(", ");

            Item item = new Item(itemName, itemType, itemDescription, itemPrice, itemCategory, itemBrand, itemStore, iPic1, iPic2, iPic3, iPic4);
            is.addItem(item);
            for (int i = 0; i < colors.length; i++) {
                for (int j = 0; j < sizes.length; j++) {
                    Stock stock = new Stock(itemName + " " + itemStore.getName() + " " + colors[i] + " " + sizes[j], colors[i], sizes[j], itemStore.getId(), iStock, item);
                    stockService.addStock(stock);
                }
            }

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
    private void getPic1(ActionEvent event) throws IOException {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Elegir imagen principal");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if(file != null) {
            this.iPic1 = Files.readAllBytes(file.toPath());
        }
    };

    @FXML
    private void getPic2(ActionEvent event) throws IOException {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Elegir imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if(file != null) {
            this.iPic2 = Files.readAllBytes(file.toPath());
        }
    };

    @FXML
    private void getPic3(ActionEvent event) throws IOException {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Elegir imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if(file != null) {
            this.iPic3 = Files.readAllBytes(file.toPath());
        }
    };

    @FXML
    private void getPic4(ActionEvent event) throws IOException {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Elegir imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if(file != null) {
            this.iPic4 = Files.readAllBytes(file.toPath());
        }
    };

    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationAddProductI.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemSize.setText(null);
        itemColor.setText(null);
        itemStock.setText(null);
    }
}
