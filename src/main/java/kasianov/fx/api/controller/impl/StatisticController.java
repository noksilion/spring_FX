package kasianov.fx.api.controller.impl;

import feign.RetryableException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import kasianov.fx.api.allert.Alerter;
import kasianov.fx.api.controller.CustomFXController;
import kasianov.fx.api.sceneChangers.SceneChanger;
import kasianov.fx.dto.impl.*;
import kasianov.fx.exceptions.LoadAnotherSceneException;
import kasianov.fx.feign.HeroClient;
import kasianov.fx.feign.StatisticClient;
import kasianov.fx.feign.UserClient;
import kasianov.fx.fxelements.ComboBoxAutoComplete;
import kasianov.fx.services.autorization.AuthService;
import kasianov.fx.utils.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

@Component
public class StatisticController {


    @FXML
    private ComboBox<HeroDto> heroBox1;
    @FXML
    private ComboBox<UserDtoViewAllUsers> enemyNameBox1;
    @FXML
    private Label gamesPlayed1;
    @FXML
    private Label looses1;
    @FXML
    private Label wictoryPercent1;
    @FXML
    private Label wictories1;


    @FXML
    private ComboBox<UserDtoViewAllUsers> enemyName2;
    @FXML
    private Label gamesPlayed2;
    @FXML
    private Label wictories2;
    @FXML
    private Label looses2;
    @FXML
    private Label wictoryPercent2;


    @FXML
    private ComboBox<HeroDto> heroBox3;
    @FXML
    private Label gamesPlayed3;
    @FXML
    private Label wictories3;
    @FXML
    private Label looses3;
    @FXML
    private Label wictoryPercent3;

    @FXML
    private Label gamesPlayed4;
    @FXML
    private Label wictories4;
    @FXML
    private Label looses4;
    @FXML
    private Label wictoryPercent4;

    @FXML
    private Button logoutButton;
    @FXML
    private Button mainMenu;


    @Value("classpath:/menu.fxml")
    private Resource menuScene;
    @Value("${login.title}")
    private String loginSceneName;
    @Value("classpath:/login.fxml")
    private Resource loginScene;
    @Value("${main_menu.title}")
    private String menuSceneName;


    private SceneChanger sceneChanger;
    private AuthService authService;
    private Alerter alerter;
    private StatisticClient statisticClient;
    private Store tokenStore;
    private UserClient userClient;
    private HeroClient heroClient;

    @Autowired
    public StatisticController(SceneChanger sceneChanger, AuthService authService, Alerter alerter, StatisticClient statisticClient, Store tokenStore, UserClient userClient, HeroClient heroClient) {
        this.sceneChanger = sceneChanger;
        this.authService = authService;
        this.alerter = alerter;
        this.statisticClient = statisticClient;
        this.tokenStore = tokenStore;
        this.userClient = userClient;
        this.heroClient = heroClient;
    }


    @FXML
    void onGet1ButtonClick(ActionEvent event) {
        if (heroBox1.getValue() == null) {
            alerter.showWarningAlert("Hero not selected");
            return;
        }
        HeroDto hero = heroBox1.getValue();

        if (enemyNameBox1.getValue() == null) {
            alerter.showWarningAlert("Enemy not selected");
            return;
        }
        UserDtoViewAllUsers chosenEnemy = enemyNameBox1.getValue();

        GameStatistic statsVsUserOnHero;
        try {
            statsVsUserOnHero = statisticClient.getStatsVsUserOnHero(tokenStore.getToken(), chosenEnemy.getId(), hero.getId());
        } catch (UndeclaredThrowableException undeclared) {
            alerter.showAlertFromFeign(undeclared);
            return;
        } catch (RetryableException connectException) {
            alerter.showAlertNoConnection();
            return;
        }
        gamesPlayed1.setText(statsVsUserOnHero.getGamesCount().toString());
        looses1.setText(statsVsUserOnHero.getLooseCount().toString());
        wictories1.setText(statsVsUserOnHero.getWinsCount().toString());
        float winPercent = statsVsUserOnHero.getWinPercent() * 100;
        wictoryPercent1.setText(Float.toString(winPercent));
    }

    @FXML
    void onGet2ButtonClick(ActionEvent event) {

        if (enemyName2.getValue() == null) {
            alerter.showWarningAlert("Enemy not selected");
            return;
        }
        UserDtoViewAllUsers chosenEnemy = enemyName2.getValue();

        GameStatistic statsVsUser;
        try {
            statsVsUser = statisticClient.getStatsVsUser(tokenStore.getToken(), chosenEnemy.getId());
        } catch (UndeclaredThrowableException undeclared) {
            alerter.showAlertFromFeign(undeclared);
            return;
        } catch (RetryableException connectException) {
            alerter.showAlertNoConnection();
            return;
        }
        gamesPlayed2.setText(statsVsUser.getGamesCount().toString());
        looses2.setText(statsVsUser.getLooseCount().toString());
        wictories2.setText(statsVsUser.getWinsCount().toString());
        float winPercent = statsVsUser.getWinPercent() * 100;
        wictoryPercent2.setText(Float.toString(winPercent));
    }

    @FXML
    void onGet3ButtonClick(ActionEvent event) {
        if (heroBox3.getValue() == null) {
            alerter.showWarningAlert("Hero not selected");
            return;
        }
        HeroDto hero = heroBox3.getValue();

        GameStatistic statsOnHero;
        try {
            statsOnHero = statisticClient.getStatsOnHero(tokenStore.getToken(), hero.getId());
        } catch (UndeclaredThrowableException undeclared) {
            alerter.showAlertFromFeign(undeclared);
            return;
        } catch (RetryableException connectException) {
            alerter.showAlertNoConnection();
            return;
        }
        gamesPlayed3.setText(statsOnHero.getGamesCount().toString());
        looses3.setText(statsOnHero.getLooseCount().toString());
        wictories3.setText(statsOnHero.getWinsCount().toString());
        float winPercent = statsOnHero.getWinPercent() * 100;
        wictoryPercent3.setText(Float.toString(winPercent));
    }

    @FXML
    void onGet4ButtonClick(ActionEvent event) {
        GameStatistic statsOnHero;
        try {
            statsOnHero = statisticClient.getStatsGeneral(tokenStore.getToken());
        } catch (UndeclaredThrowableException undeclared) {
            alerter.showAlertFromFeign(undeclared);
            return;
        } catch (RetryableException connectException) {
            alerter.showAlertNoConnection();
            return;
        }
        gamesPlayed4.setText(statsOnHero.getGamesCount().toString());
        looses4.setText(statsOnHero.getLooseCount().toString());
        wictories4.setText(statsOnHero.getWinsCount().toString());
        float winPercent = statsOnHero.getWinPercent() * 100;
        wictoryPercent4.setText(Float.toString(winPercent));
    }

    @FXML
    void onLogoutButtonClick(ActionEvent event) {
        authService.logout();
        sceneChanger.setNewScene(loginScene, loginSceneName);
    }

    @FXML
    void onMainMenuClick(ActionEvent event) {
        sceneChanger.setNewScene(menuScene, menuSceneName);
    }

    @FXML
    void initialize() {
        String token = tokenStore.getToken();
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

        heroBox1.getItems().addAll(heroDtos);
        heroBox3.getItems().addAll(heroDtos);
        
        new ComboBoxAutoComplete<>(heroBox1);
        new ComboBoxAutoComplete<>(heroBox3);

        enemyName2.getItems().addAll(allEnemyUsers);
        enemyNameBox1.getItems().addAll(allEnemyUsers);
    }

}
