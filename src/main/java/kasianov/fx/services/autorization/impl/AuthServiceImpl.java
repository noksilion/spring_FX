package kasianov.fx.services.autorization.impl;

import kasianov.fx.api.sceneChangers.SceneChanger;
import kasianov.fx.dto.impl.AccountCredentials;
import kasianov.fx.dto.impl.TokenDto;
import kasianov.fx.dto.impl.UserDtoForPost;
import kasianov.fx.services.autorization.AuthService;
import kasianov.fx.feign.AuthorizationClient;
import kasianov.fx.utils.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceImpl implements AuthService {

    @Value("${main_menu.title}")
    private String mainMenuSceneName;

    private AuthorizationClient authorizationClient;
    private Store tokenStore;
    private SceneChanger sceneChanger;

    @Value("classpath:/menu.fxml")
    private Resource loginScene;

    @Autowired
    public AuthServiceImpl(AuthorizationClient authorizationClient, Store tokenStore, SceneChanger sceneChanger) {
        this.authorizationClient = authorizationClient;
        this.tokenStore = tokenStore;
        this.sceneChanger = sceneChanger;
    }

    @Override
    public void login(String email, String password) {

        TokenDto tokenDto = authorizationClient.login(AccountCredentials.builder()
                .email(email)
                .password(password)
                .build()
        );
        tokenStore.setToken(tokenDto.getToken());
        sceneChanger.setNewScene(loginScene,mainMenuSceneName);
    }

    @Override
    public void signup(String email, String password, String userName) {
        TokenDto tokenDto = authorizationClient.signup(UserDtoForPost.builder()
                .email(email)
                .password(password)
                .userName(userName)
                .build()
        );
        tokenStore.setToken(tokenDto.getToken());
        sceneChanger.setNewScene(loginScene,mainMenuSceneName);
    }

    @Override
    public void logout() {
        tokenStore.cleanToken();
    }
}
