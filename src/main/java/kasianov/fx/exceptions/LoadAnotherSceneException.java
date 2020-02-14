package kasianov.fx.exceptions;

import org.springframework.core.io.Resource;

public class LoadAnotherSceneException extends RuntimeException {
    private Resource loginScene;
    private String sceneName;

    public LoadAnotherSceneException(Resource loginScene,String sceneName) {
        this.sceneName = sceneName;
        this.loginScene = loginScene;
    }

    public Resource getLoginScene() {
        return loginScene;
    }

    public String getSceneName() {
        return sceneName;
    }
}
