package kasianov.fx.api.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import kasianov.fx.api.sceneChangers.SceneChanger;
import kasianov.fx.services.autorization.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class MainMenuController {
    @Value("classpath:/login.fxml")
    private Resource loginScene;

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

    }

    @FXML
    void onConfirmButtonClick(ActionEvent event) {

    }

    @FXML
    void onStatisticButtonClick(ActionEvent event) {

    }

    @FXML
    void onLogoutButtonClick(ActionEvent event) {
        authService.logout();
        sceneChanger.setNewScene(loginScene,"Login Page");
    }


}