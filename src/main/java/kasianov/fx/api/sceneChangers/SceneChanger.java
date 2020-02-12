package kasianov.fx.api.sceneChangers;


import org.springframework.core.io.Resource;

public interface SceneChanger {
    void setNewScene(Resource resourceLocation, String sceneTitle);
}
