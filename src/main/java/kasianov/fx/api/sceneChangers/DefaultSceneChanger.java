package kasianov.fx.api.sceneChangers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
@Primary
public class DefaultSceneChanger implements SceneChanger {
    private final GenericApplicationContext genericApplicationContext;
    private final ApplicationContext applicationContext;

    public DefaultSceneChanger(GenericApplicationContext genericApplicationContext, ApplicationContext applicationContext) {
        this.genericApplicationContext = genericApplicationContext;
        this.applicationContext = applicationContext;
    }


    @Override
    public void setNewScene(Resource resourceLocation, String sceneTitle) {
        Stage stage = getStageFromContext();

        try {
            URL url = resourceLocation.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(applicationContext::getBean);

            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            double w = 601.0;
            double h = 400.0;

            stage.setMaxHeight(h);
            stage.setMaxWidth(w);
            stage.setMinHeight(h);
            stage.setMinWidth(w);

            stage.setTitle(sceneTitle);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Stage getStageFromContext(){
        return (Stage) genericApplicationContext.getBean("stageBean");
    }
}
