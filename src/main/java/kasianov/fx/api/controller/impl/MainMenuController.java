package kasianov.fx.api.controller.impl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import kasianov.fx.api.controller.CustomFXController;
import kasianov.fx.api.sceneChangers.SceneChanger;
import kasianov.fx.services.autorization.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class MainMenuController {
    @Value("classpath:/login.fxml")
    private Resource loginScene;

    @Value("classpath:/statistic.fxml")
    private Resource statisticsScene;

    @Value("classpath:/confirmGames.fxml")
    private Resource confirmGamesScene;

    @Value("classpath:/add_battle.fxml")
    private Resource addBattleScene;

    @Value("${login.title}")
    private String loginSceneName;

    @Value("${confirmGames.title}")
    private String confirmGamesSceneName;

    @Value("${statistics.title}")
    private String statisticsSceneName;

    @Value("${add_battle.title}")
    private String addBattleSceneName;

    private SceneChanger sceneChanger;
    private AuthService authService;


    @FXML
    private Button confirmGameButton;
    @FXML
    private Button statisticButton;
    @FXML
    private Button addBattleButton;
    @FXML
    private Button logoutButton;

    public MainMenuController(SceneChanger sceneChanger, AuthService authService) {
        this.sceneChanger = sceneChanger;
        this.authService = authService;
    }

    @FXML
    void onAddBattleButtonClick(ActionEvent event) {
        sceneChanger.setNewScene(addBattleScene,addBattleSceneName);
    }

    @FXML
    void onConfirmButtonClick(ActionEvent event) {
        sceneChanger.setNewScene(confirmGamesScene,confirmGamesSceneName);
    }

    @FXML
    void onStatisticButtonClick(ActionEvent event) {
        sceneChanger.setNewScene(statisticsScene,statisticsSceneName);
    }

    @FXML
    void onLogoutButtonClick(ActionEvent event) {
        authService.logout();
        sceneChanger.setNewScene(loginScene,loginSceneName);
    }


}
