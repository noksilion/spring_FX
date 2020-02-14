package kasianov.fx.api.controller.impl;

import feign.RetryableException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import kasianov.fx.api.allert.Alerter;
import kasianov.fx.api.controller.CustomFXController;
import kasianov.fx.api.sceneChangers.SceneChanger;
import kasianov.fx.dto.impl.GameDto;
import kasianov.fx.dto.impl.UnapprovedGamesDto;
import kasianov.fx.dto.impl.UserGameDto;
import kasianov.fx.exceptions.LoadAnotherSceneException;
import kasianov.fx.feign.GameClient;
import kasianov.fx.services.autorization.AuthService;
import kasianov.fx.utils.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConfirmGameController  {

    @Value("classpath:/menu.fxml")
    private Resource menuScene;
    @Value("${main_menu.title}")
    private String menuSceneName;
    @Value("${login.title}")
    private String loginSceneName;
    @Value("classpath:/login.fxml")
    private Resource loginScene;

    @FXML
    private VBox vBox;
    @FXML
    private Button logoutButton;
    @FXML
    private Button mainMenu;

    private GameClient gameClient;
    private Store tokenStore;
    private Alerter alerter;
    private SceneChanger sceneChanger;
    private AuthService authService;

    @Autowired
    public ConfirmGameController(GameClient gameClient, Store tokenStore, Alerter alerter, SceneChanger sceneChanger, AuthService authService) {
        this.gameClient = gameClient;
        this.tokenStore = tokenStore;
        this.alerter = alerter;
        this.sceneChanger = sceneChanger;
        this.authService = authService;
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
    void initialize(){
        UnapprovedGamesDto unapprovedGames;
        try {
            unapprovedGames = gameClient.getUnapprovedGames(tokenStore.getToken());
        } catch (UndeclaredThrowableException undeclared) {
            alerter.showAlertFromFeign(undeclared);
            throw new LoadAnotherSceneException(menuScene,menuSceneName);
        } catch (RetryableException connectException) {
            alerter.showAlertNoConnection();
            throw new LoadAnotherSceneException(menuScene,menuSceneName);
        }
        List<GameDto> gameDtos = unapprovedGames.getGameDtos();
        if(gameDtos.isEmpty()){
            alerter.showSuccessAlert("No games For approve");
            throw new LoadAnotherSceneException(menuScene,menuSceneName);
        }
        Integer loggedUserId = unapprovedGames.getConfirmingUserId();
        int paneCounter = 1;
        for(GameDto gameDto:gameDtos){
            vBox.getChildren().add(createAndFillPanes(gameDto,loggedUserId,paneCounter));
            paneCounter++;
        }
    }

    private AnchorPane createAndFillPanes(GameDto gameDto,Integer loggedUserId,Integer paneNumber){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId("anchorPane"+paneNumber);
        anchorPane.setMinHeight(123);
        anchorPane.setPrefWidth(580);
        anchorPane.setMaxHeight(Double.MAX_VALUE);
        anchorPane.setStyle( "-fx-border-style: solid inside;"
                + "-fx-border-width: 1;" + "-fx-border-color: black;"
                +"-fx-background-color: #fafafa");


        Button button = new Button();
        button.setFont(new Font("Luminari", 13));
        AnchorPane.setRightAnchor(button,10.0);
        AnchorPane.setBottomAnchor(button, 10.0);
        button.setPrefHeight(32);
        button.setPrefWidth(68);
        button.setId("approve1");
        button.setText("Approve");
        button.setStyle("-fx-background-color:  #F39C63;");
        button.setOnAction(actionEvent -> {
            try{
                gameClient.approveGame(tokenStore.getToken(),gameDto.getId());
            } catch (UndeclaredThrowableException undeclared) {
                alerter.showAlertFromFeign(undeclared);
                throw new LoadAnotherSceneException(menuScene,menuSceneName);
            } catch (RetryableException connectException) {
                alerter.showAlertNoConnection();
                throw new LoadAnotherSceneException(menuScene,menuSceneName);
            }

            AnchorPane parentPane = (AnchorPane) button.getParent();
            vBox.getChildren().remove(parentPane);
        });


        GridPane loggedUserPane = createUserPane(gameDto,loggedUserId);
        GridPane enemiesPane = createEnemiesPane(gameDto, loggedUserId);
        int rowCount = enemiesPane.getRowCount();
        double anchorPainHeight = rowCount * 25.0 + 2;
        anchorPane.setPrefHeight(anchorPainHeight);
        anchorPane.setMinHeight(anchorPainHeight);


        anchorPane.getChildren().add(loggedUserPane);
        anchorPane.getChildren().add(enemiesPane);
        anchorPane.getChildren().add(button);

        return anchorPane;
    }

    private GridPane createEnemiesPane(GameDto gameDto,Integer loggedUserId){
        GridPane gridPane = new GridPane();
        AnchorPane.setLeftAnchor(gridPane,262.0);
        AnchorPane.setTopAnchor(gridPane,0.0);
        AnchorPane.setBottomAnchor(gridPane,0.0);
        Integer rowNumber = 0;

        List<UserGameDto> localUserGameDtos = new ArrayList<>(gameDto.getUserGameList());
        localUserGameDtos.remove(getLoggedUserGameDto(localUserGameDtos,loggedUserId));

        int enemyCounter = 1;
        for(UserGameDto userGameDto:localUserGameDtos){
            rowNumber = addRowToGridPane(gridPane,"Enemy "+ enemyCounter,"",rowNumber);
            rowNumber = addRowToGridPane(gridPane,"Enemy Name",userGameDto.getUserName(),rowNumber);
            rowNumber = addRowToGridPane(gridPane,"Result",userGameDto.getResult().toString(),rowNumber);
            rowNumber = addRowToGridPane(gridPane,"Hero Name",userGameDto.getHeroName(),rowNumber);
            enemyCounter++;
        }
        return gridPane;
    }


    private GridPane createUserPane(GameDto gameDto, Integer loggedUserId){
        GridPane gridPane = new GridPane();
        AnchorPane.setLeftAnchor(gridPane,0.0);
        AnchorPane.setTopAnchor(gridPane,0.0);
        AnchorPane.setBottomAnchor(gridPane,0.0);
        Integer rowNumber = 0;

        String date = gameDto.getDate();
        UserGameDto loggedUserGameDto = getLoggedUserGameDto(gameDto.getUserGameList(),loggedUserId);

        rowNumber = addRowToGridPane(gridPane,"Game Date",date,rowNumber);
        rowNumber = addRowToGridPane(gridPane,"You",loggedUserGameDto.getUserName(),rowNumber);
        rowNumber = addRowToGridPane(gridPane,"Result",loggedUserGameDto.getResult().toString(),rowNumber);
        addRowToGridPane(gridPane,"Hero Name",loggedUserGameDto.getHeroName(),rowNumber);

        return gridPane;
    }

    private Integer addRowToGridPane(GridPane gridPane,String firstText,String secondText,Integer rowNumber){
        gridPane.add(createLabel(firstText),0,rowNumber);
        gridPane.add(createLabel(secondText),1,rowNumber);
        rowNumber = rowNumber +1;
        return rowNumber;
    }

    private Label createLabel(String text){
        Label label = new Label();
        label.setText(text);
        label.setFont(new Font("Luminari", 13));
        label.setStyle("-fx-background-color:  #fafafa;");
        label.setPrefHeight(25);
        label.setMinHeight(25);
        label.setPrefWidth(130);
        return label;
    }

    private UserGameDto getLoggedUserGameDto(List<UserGameDto> userGameDtos, Integer userId){
        UserGameDto userGameDtoForReturn = null;
        for(UserGameDto userGameDto:userGameDtos){
            if(userGameDto.getUserId().equals(userId)){
                userGameDtoForReturn =  userGameDto;
                break;
            }
        }
        return userGameDtoForReturn;
    }



}
