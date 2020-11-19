package proyecto.tic.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.tic.ApplicationFX;
import proyecto.tic.services.BrandService;
import proyecto.tic.services.ItemService;
import proyecto.tic.services.entities.Brand;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;




@Component
@FxmlView("/applicationMenu.fxml")
public class ApplicationMenuController implements Initializable {

    @Autowired
    private BrandService bs;
    @Autowired
    private ApplicationProductWFilterController ap;
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
    private JFXButton signInButtonAdmiButton;
    @FXML
    private JFXButton addProductButton;
    @FXML
    private JFXButton addBrandButton;
    @FXML
    private GridPane grid;
    @FXML
    private ImageView toAdd;
    @FXML
    private JFXButton modaH;
    @FXML
    private JFXButton modaM;
    @FXML
    private JFXButton modaN;

    private Image[] images = new Image[150];

    private Image[] addImagesToArray() {
        //tengo una lista de fotos en forma
        Image[] toReturn = new Image[150];
        ObservableList<Brand> allBrands = getAllBrands();
        for (int i = 0; i < allBrands.size(); i++) {
            byte[] img = allBrands.get(i).getBrandImage();
            ByteArrayInputStream bis = new ByteArrayInputStream(img);
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();

            }
            Image toAdd = SwingFXUtils.toFXImage(bImage, null);
            toReturn[i] = toAdd;
        }
        return toReturn;
    }

    private ObservableList<Brand> getAllBrands() {

        ObservableList<Brand> allBrands = FXCollections.observableArrayList();

        List<Brand> brandList = bs.getRepository().findAll();

        for (int i = 0; i < brandList.size(); i++) {
            boolean esta = false;
            Brand temp = bs.getRepository().findOneByName(brandList.get(i).getName());
            if (allBrands.isEmpty()) {
                allBrands.add(temp);
            }
            for (Brand j : allBrands) {
                if (j.getId() == temp.getId()) {
                    esta = true;
                }
            }
            if (!esta) {
                allBrands.add(temp);
            }
        }

        return allBrands;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
           --CODIGO PARA TABLA--
        c1.setCellValueFactory(new PropertyValueFactory<Brand, ImageView>("brandImage"));
        c2.setCellValueFactory(new PropertyValueFactory<Brand, String>("name"));

        table.setItems(getAllBrands());
        */
        //CODIGO PARA UN GRID!!!
        grid.getChildren().clear();
        grid.setPadding(new Insets(90, 7, 80, 7));
        grid.setHgap(150);
        grid.setVgap(100);
        ObservableList<Brand> brands = getAllBrands();
        Image[] images = addImagesToArray();
        boolean complete = false;
        int i = 0;
        int m = 0; // m es la columna
        while (!complete) {
            for (int n = 0; n < 4 && !complete; n++) { //n es la fila
                toAdd = new ImageView();
                toAdd.setFitWidth(150);
                toAdd.setFitHeight(150);
                toAdd.setImage(images[i]);
                grid.add(toAdd, n, m);
                /* / boton :)
                int finalI = i;
                toAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        Brand brand = brands.get(finalI);

                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setControllerFactory(ApplicationFX.getContext()::getBean);
                        Parent inicioSesion = null;
                        try {
                            ap.setAtributo(brand.getName());
                            inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationProductWFilter.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(paginaInicio);
                        window.show();

                    }
                });
                / */
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
    private void addProduct(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationAddProductI.fxml"));
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }

    @FXML
    void addBrand(ActionEvent event) {
    }

    @FXML
    void signInAdmi(ActionEvent event) {

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






