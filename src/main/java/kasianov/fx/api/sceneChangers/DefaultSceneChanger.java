package kasianov.fx.api.sceneChangers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kasianov.fx.api.allert.Alerter;
import kasianov.fx.exceptions.LoadAnotherSceneException;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final Alerter alerter;

    @Autowired
    public DefaultSceneChanger(GenericApplicationContext genericApplicationContext, ApplicationContext applicationContext, Alerter alerter) {
        this.genericApplicationContext = genericApplicationContext;
        this.applicationContext = applicationContext;
        this.alerter = alerter;
    }


    @Override
    public void setNewScene(Resource resourceLocation, String sceneTitle) {
        Stage stage = getStageFromContext();

        try {
            Parent root;
            try {
                URL url = resourceLocation.getURL();
                FXMLLoader fxmlLoader = new FXMLLoader(url);
                fxmlLoader.setControllerFactory(applicationContext::getBean);
                stage.setTitle(sceneTitle);
                root = fxmlLoader.load();
            }catch (LoadAnotherSceneException e){
                URL url = e.getLoginScene().getURL();
                FXMLLoader fxmlLoader = new FXMLLoader(url);
                fxmlLoader.setControllerFactory(applicationContext::getBean);
                stage.setTitle(e.getSceneName());
                root = fxmlLoader.load();
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            double w = 601.0;
            double h = 400.0;

            stage.setMaxHeight(h);
            stage.setMaxWidth(w);
            stage.setMinHeight(h);
            stage.setMinWidth(w);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showStage(){

    }

    private Stage getStageFromContext(){
        return (Stage) genericApplicationContext.getBean("stageBean");
    }
}
