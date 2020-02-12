package kasianov.fx.api.listeners;

import kasianov.fx.JavaFxApplication;
import kasianov.fx.api.sceneChangers.SceneChanger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


@Component
public class StageListener implements ApplicationListener<JavaFxApplication.StageReadyEvent> {

    @Value("${app.title}")
    private String applicationTitle;

    @Value("classpath:/signup.fxml")
    private Resource resource;

    private final SceneChanger sceneChanger;

    public StageListener(SceneChanger sceneChanger) {
        this.sceneChanger = sceneChanger;
    }

    @Override
    public void onApplicationEvent(JavaFxApplication.StageReadyEvent stageReadyEvent) {
        sceneChanger.setNewScene(resource,applicationTitle);
    }
}
