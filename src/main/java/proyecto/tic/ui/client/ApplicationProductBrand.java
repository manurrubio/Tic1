package proyecto.tic.ui.client;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import proyecto.tic.CApplicationFX;
import proyecto.tic.services.BrandService;
import proyecto.tic.services.ItemService;
import proyecto.tic.services.entities.Brand;
import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.ItemSpecification;
import proyecto.tic.services.entities.Usuario;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

@Component
@FxmlView("/applicationProductBrand.fxml")
public class ApplicationProductBrand implements Initializable {
    @Autowired
    private ItemService is;

    @Autowired
    private ApplicationProductWFilterController ap;

    @Autowired
    private ApplicationProductController pc;

    @Autowired
    private BrandService bs;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane grid;

    @FXML
    private JFXComboBox<String> comboMarcaBox;

    @FXML
    private JFXComboBox<String> comboTipoBox;

    @FXML
    private JFXButton buttonFiltrar;

    @FXML
    private Button modaH;

    @FXML
    private Button modaM;

    @FXML
    private Button modaN;

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
    @Autowired
    private ApplicationMenuController am;


    private ImageView toAdd;

    private Brand brand;

    private Usuario usuario;

    private Image[] images = new Image[150];

    void inicioSesion(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setAtributo(Brand brand) {
        this.brand = brand;
    }
    public List<Item> findProducts (
            @RequestParam(name = "brand", required = false) Brand brand,
            @RequestParam(name = "type", required = false) String type) {
        if (brand != null || type != null){
            List<Item>  result = is.getRepository().findAll(new ItemSpecification(brand, type));
            return result;
        } else {
            List<Item> result = new ArrayList<>();
            Iterator<Item> studentIterator = is.getRepository().findAll().iterator();
            while (studentIterator.hasNext())
                result.add(studentIterator.next());
            return result;
        }
    }

    @FXML
    void applyFilter(ActionEvent event) {
        String tipo = comboTipoBox.getValue();
        String marca = comboMarcaBox.getValue();
        Brand brand = bs.getRepository().findOneByName(marca);
        List<Item> listaFiltrada = findProducts(brand,tipo);
        grid.getChildren().clear();
        grid.setPadding(new Insets(90, 7, 80, 7));
        grid.setHgap(150);
        grid.setVgap(100);
        boolean complete = false;
        images = filterAddImagesToArray(listaFiltrada);
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
                        List<Item> allItems = new ArrayList<>();
                        allItems = (List<Item>) listaFiltrada;
                        Item item = allItems.get(finalI);
                        pc.setItem(item);
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
        ap.setAtributo("hombre");
        ap.inicioSesion(usuario);
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
        ap.inicioSesion(usuario);
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
        ap.inicioSesion(usuario);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
        Parent inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationProductWFilter.fxml"));
        ApplicationProductWFilterController controller = fxmlLoader.getController();
        Scene paginaInicio = new Scene(inicioSesion, 780, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(paginaInicio);
        window.show();
    }

    private Image[] filterAddImagesToArray(Iterable<Item> filtrado) {
        Image[] toReturn = new Image[150];
        Iterator<Item> lista = filtrado.iterator();
        int i = 0;
        while (lista.hasNext()) {
            byte[] img = lista.next().getImagenPrincipal();
            ByteArrayInputStream bis = new ByteArrayInputStream(img);
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();

            }
            Image toAdd = SwingFXUtils.toFXImage(bImage, null);
            if (toAdd != null) {
                toReturn[i] = toAdd;
            }
            i=i+1;
        }
        return toReturn;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carritoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(CApplicationFX.getContext()::getBean);
                Parent inicioSesion = null;
                am.inicioSesion(usuario);
                try {
                    inicioSesion = fxmlLoader.load(getClass().getResourceAsStream("/applicationMenu.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene paginaInicio = new Scene(inicioSesion, 780, 450);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(paginaInicio);
                window.show();

            }
        });
        if (usuario != null) {
            loginButton.setText("Cerrar sesión");
        }
        List<Item> allItems = findProducts(brand,null);
        List<String> allMarcas = new ArrayList<>();
        List<String> allTipos = new ArrayList<>();
        for (Item item : allItems) {
            if (!allMarcas.contains(item.getBrand().getName())) {
                allMarcas.add(item.getBrand().getName());
            }
            if (!allTipos.contains(item.getType())) {
                allTipos.add(item.getType());
            }
        }
        ObservableList<String> listBrands = FXCollections.observableArrayList(allMarcas);
        comboMarcaBox.setItems(listBrands);

        ObservableList<String> listTipos = FXCollections.observableArrayList(allTipos);
        comboTipoBox.setItems(listTipos);
        List<Item> listaFiltrada = findProducts(brand,null);
        grid.getChildren().clear();
        grid.setPadding(new Insets(90, 7, 80, 7));
        grid.setHgap(150);
        grid.setVgap(100);
        boolean complete = false;
        images = filterAddImagesToArray(listaFiltrada);
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
                        List<Item> allItems = new ArrayList<>();
                        allItems = (List<Item>) listaFiltrada;
                        Item item = allItems.get(finalI);
                        pc.setItem(item);
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
    }



