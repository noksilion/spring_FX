package kasianov.fx.api.controller.impl;

import feign.RetryableException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import kasianov.fx.api.allert.Alerter;
import kasianov.fx.api.controller.CustomFXController;
import kasianov.fx.api.sceneChangers.SceneChanger;
import kasianov.fx.dto.impl.*;
import kasianov.fx.exceptions.LoadAnotherSceneException;
import kasianov.fx.feign.HeroClient;
import kasianov.fx.feign.UserClient;
import kasianov.fx.services.autorization.AuthService;
import kasianov.fx.services.game.GameServices;
import kasianov.fx.utils.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddBattleController {

    @Value("classpath:/login.fxml")
    private Resource loginScene;

    @Value("classpath:/menu.fxml")
    private Resource menuScene;

    @Value("classpath:/add_battle.fxml")
    private Resource addBattleScene;

    @Value("${login.title}")
    private String loginSceneName;

    @Value("${main_menu.title}")
    private String menuSceneName;

    @Value("${add_battle.title}")
    private String addBattleSceneName;

    private SceneChanger sceneChanger;
    private AuthService authService;
    private UserClient userClient;
    private Store store;
    private HeroClient heroClient;
    private GameServices gameServices;
    private Alerter alerter;


    @FXML
    private Button submitButton;
    @FXML
    private ComboBox<HeroDto> heroBox;
    @FXML
    private ComboBox<Result> resultBox;
    @FXML
    private ComboBox<UserDtoViewAllUsers> enemyName1;
    @FXML
    private ComboBox<HeroDto> heroBox1;
    @FXML
    private ComboBox<Result> resultBox1;
    @FXML
    private Button logoutButton;
    @FXML
    private Button mainMenu;


    @Autowired
    public AddBattleController(SceneChanger sceneChanger, AuthService authService, UserClient userClient, Store store, HeroClient heroClient, GameServices gameServices, Alerter alerter) {
        this.sceneChanger = sceneChanger;
        this.authService = authService;
        this.userClient = userClient;
        this.store = store;
        this.heroClient = heroClient;
        this.gameServices = gameServices;
        this.alerter = alerter;
    }

    @FXML
    void onMainMenuClick(ActionEvent event) {
        sceneChanger.setNewScene(menuScene,menuSceneName);
    }

    @FXML
    void onSubmitButtonClick(ActionEvent event) {
        if(heroBox.getValue() == null){
            alerter.showWarningAlert("Your hero not selected");
            return;
        }
        HeroDto userHero = heroBox.getValue();

        if(resultBox.getValue() == null){
            alerter.showWarningAlert("Your result not selected");
            return;
        }
        Result userResult = resultBox.getValue();

        if(enemyName1.getValue() == null){
            alerter.showWarningAlert("Enemy 1 name not selected");
            return;
        }
        UserDtoViewAllUsers enemy1 = enemyName1.getValue();

        if(heroBox1.getValue() == null){
            alerter.showWarningAlert("Enemy 1 hero not selected");
            return;
        }
        HeroDto enemy1Hero = heroBox1.getValue();

        if(resultBox1.getValue() == null){
            alerter.showWarningAlert("Enemy 1 result not selected");
            return;
        }
        Result enemy1Result = resultBox1.getValue();

        List<UserGameDtoForPost> enemyList = new ArrayList<>();
        enemyList.add(UserGameDtoForPost.builder()
                .result(enemy1Result)
                .userId(enemy1.getId())
                .heroId(enemy1Hero.getId())
                .build());
        try {
            gameServices.saveGame(userHero.getId(), userResult, enemyList);
            alerter.showSuccessAlert("Game successfully added");
        }catch (UndeclaredThrowableException undeclared){
            alerter.showAlertFromFeign(undeclared);
        }catch (RetryableException connectException){
            alerter.showAlertNoConnection();
        }
    }

    @FXML
    void onLogoutButtonClick(ActionEvent event) {
        authService.logout();
        sceneChanger.setNewScene(loginScene,loginSceneName);
    }

    @FXML
    void initialize() {
        String token = store.getToken();

        List<UserDtoViewAllUsers> allEnemyUsers;
        try{
            allEnemyUsers = userClient.getAllEnemyUsers(token);
        } catch (RetryableException connectException) {
            alerter.showAlertNoConnection();
            throw new LoadAnotherSceneException(menuScene,menuSceneName);
        }

        List<HeroDto> heroDtos;
        try{
            heroDtos = heroClient.getAllHeroes(token);
        } catch (RetryableException connectException) {
            alerter.showAlertNoConnection();
            throw new LoadAnotherSceneException(menuScene,menuSceneName);
        }

        heroBox.getItems().addAll(heroDtos);
        heroBox1.getItems().addAll(heroDtos);

        enemyName1.getItems().addAll(allEnemyUsers);

        resultBox.getItems().addAll(Result.LOOSE,Result.VICTORY);
        resultBox1.getItems().addAll(Result.LOOSE,Result.VICTORY);
    }
}