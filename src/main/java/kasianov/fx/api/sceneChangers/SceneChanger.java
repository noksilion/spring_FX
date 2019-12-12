package kasianov.fx.api.sceneChangers;


import org.springframework.core.io.Resource;

public interface SceneChanger {
    public void setNewScene(Resource resourceLocation, String sceneTitle);

}
