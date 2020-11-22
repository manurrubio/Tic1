package proyecto.tic.ui.admi;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import proyecto.tic.CApplicationFX;
import proyecto.tic.services.BrandService;
import proyecto.tic.services.ItemService;
import proyecto.tic.services.StockService;
import proyecto.tic.services.StoreService;
import proyecto.tic.services.entities.Brand;
import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Stock;
import proyecto.tic.services.entities.Store;
import proyecto.tic.services.exceptions.BrandNotExist;
import proyecto.tic.services.exceptions.InvalidInformation;
import proyecto.tic.services.exceptions.ItemAlreadyExists;
import proyecto.tic.ui.client.ApplicationProductWFilterController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

@Component
@FxmlView("/applicationAddProductII.fxml")
public class AddProductControllerII  {
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
    private Brand itemBrand;
    private Store itemStore;
    private byte[] iPic1;
    private byte[] iPic2;
    private byte[] iPic3;
    private byte[] iPic4;


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
        Long iStock= Long.valueOf(itemStock.getText());

        String[] colors= itemColor.getText().split(",");
        String[] sizes= itemSize.getText().split(",");

        Item item= new Item(itemName,itemType,itemDescription,itemPrice,itemCategory,itemBrand,itemStore,iPic1,iPic2,iPic3,iPic4);
        is.addItem(item);
        for(int i=0; i<colors.length;i++){
            for(int j=0; j<sizes.length;j++){
                Stock stock= new Stock(itemName + itemStore.getName() + colors[i] +sizes[j],colors[i],sizes[j], itemStore.getId(), iStock,item);
                stockService.addStock(stock);
            }
        }


        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationMenuAdmi.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();

    }

    @FXML
    private void getPic1(ActionEvent event) throws IOException {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Elegir logo de la marca");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        this.iPic1= Files.readAllBytes(file.toPath());
    };

    @FXML
    private void getPic2(ActionEvent event) throws IOException {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Elegir logo de la marca");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        this.iPic2= Files.readAllBytes(file.toPath());
    };

    @FXML
    private void getPic3(ActionEvent event) throws IOException {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Elegir logo de la marca");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        this.iPic3= Files.readAllBytes(file.toPath());
    };

    @FXML
    private void getPic4(ActionEvent event) throws IOException {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Elegir logo de la marca");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        this.iPic4= Files.readAllBytes(file.toPath());
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

}
