package kasianov.fx.api.listeners;

import kasianov.fx.JavaFxApplication;
import kasianov.fx.api.sceneChangers.SceneChanger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


@Component
public class StageListener implements ApplicationListener<JavaFxApplication.StageReadyEvent> {

    @Value("${login.title}")
    private String loginTitle;

    @Value("classpath:/login.fxml")
    private Resource resource;

    private final SceneChanger sceneChanger;

    public StageListener(SceneChanger sceneChanger) {
        this.sceneChanger = sceneChanger;
    }

    @Override
    public void onApplicationEvent(JavaFxApplication.StageReadyEvent stageReadyEvent) {
        sceneChanger.setNewScene(resource, loginTitle);
    }
}
