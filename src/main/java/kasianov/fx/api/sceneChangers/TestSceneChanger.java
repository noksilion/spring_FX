package kasianov.fx.api.sceneChangers;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@Qualifier ("testSceneChanger")
public class TestSceneChanger implements SceneChanger {
    private final GenericApplicationContext genericApplicationContext;
    private final ApplicationContext applicationContext;

    public TestSceneChanger(GenericApplicationContext genericApplicationContext, ApplicationContext applicationContext) {
        this.genericApplicationContext = genericApplicationContext;
        this.applicationContext = applicationContext;
    }

    @Override
    public void setNewScene(Resource resourceLocation, String sceneTitle) {
        HBox hbox = new HBox();

        Button b = new Button("add");
        b.setOnAction(ev -> hbox.getChildren().add(new Label("Test")));

        ScrollPane scrollPane = new ScrollPane(hbox);
        scrollPane.setFitToHeight(true);

        BorderPane root = new BorderPane(scrollPane);
        root.setPadding(new Insets(15));
        root.setTop(b);

        Scene scene = new Scene(root, 400, 400);
        Stage primaryStage = getStageFromContext();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Stage getStageFromContext(){
        return (Stage) genericApplicationContext.getBean("stageBean");
    }
}
