package proyecto.tic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import proyecto.tic.ui.admi.ApplicationMenuAdmiController;
import proyecto.tic.ui.client.ApplicationMenuController;


@SpringBootApplication
public class AApplicationFX extends Application {


    private static ConfigurableApplicationContext applicationContext;

    public static ConfigurableApplicationContext getContext() {
        return applicationContext;
    }


    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(ClientApp.class)
                .run(args);
    }

    @Override
    public void start(Stage stage) {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ApplicationMenuAdmiController.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
}