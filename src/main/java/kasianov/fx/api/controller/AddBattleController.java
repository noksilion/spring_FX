package kasianov.fx.api.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import kasianov.fx.api.sceneChangers.SceneChanger;
import kasianov.fx.dto.impl.HeroDto;
import kasianov.fx.dto.impl.Result;
import kasianov.fx.dto.impl.UserDtoViewAllUsers;
import kasianov.fx.feign.HeroClient;
import kasianov.fx.feign.UserClient;
import kasianov.fx.services.autorization.AuthService;
import kasianov.fx.utils.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddBattleController {

    @Value("classpath:/login.fxml")
    private Resource loginScene;

    @Value("classpath:/menu.fxml")
    private Resource menuScene;

    @Value("${login.title}")
    private String loginSceneName;

    @Value("${main_menu.title}")
    private String menuSceneName;

    private SceneChanger sceneChanger;
    private AuthService authService;
    private UserClient userClient;
    private Store store;
    private HeroClient heroClient;

    @FXML
    private Button logoutButton;
    @FXML
    private Button submitButton;
    @FXML
    private Button addEnemyButton;
    @FXML
    private AnchorPane mePane;
    @FXML
    private ChoiceBox<HeroDto> heroBox;
    @FXML
    private ChoiceBox<UserDtoViewAllUsers> userBox;
    @FXML
    private ChoiceBox<Result> resultBox;
    @FXML
    private AnchorPane enemy1Pane;
    @FXML
    private Button mainMenu;

    @Autowired
    public AddBattleController(SceneChanger sceneChanger, AuthService authService, UserClient userClient, Store store, HeroClient heroClient) {
        this.sceneChanger = sceneChanger;
        this.authService = authService;
        this.userClient = userClient;
        this.store = store;
        this.heroClient = heroClient;
    }

    @FXML
    void onAddEnemyButtonClick(ActionEvent event) {

    }

    @FXML
    void onMainMenuClick(ActionEvent event) {
        sceneChanger.setNewScene(menuScene,menuSceneName);
    }

    @FXML
    void onSubmitButtonClick(ActionEvent event) {
        HeroDto value = heroBox.getValue();
    }

    @FXML
    void onLogoutButtonClick(ActionEvent event) {
        authService.logout();
        sceneChanger.setNewScene(loginScene,loginSceneName);
    }

    @FXML
    void initialize() {
        String token = store.getToken();
        List<UserDtoViewAllUsers> allEnemyUsers = userClient.getAllEnemyUsers(token);
        List<HeroDto> heroDtos = heroClient.getAllHeroes(token);
        heroBox.getItems().addAll(heroDtos);
        userBox.getItems().addAll(allEnemyUsers);
    }

}