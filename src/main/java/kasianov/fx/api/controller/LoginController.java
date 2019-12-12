package kasianov.fx.api.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import kasianov.fx.api.sceneChangers.SceneChanger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    private final SceneChanger sceneChanger;

    @Value("classpath:/add.fxml")
    private Resource resource;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button submitButton;

    @FXML
    private Button loginSignupButton;

    public LoginController(SceneChanger sceneChanger) {
        this.sceneChanger = sceneChanger;
    }


//    @FXML
//    void initialize() {
//        submitButton.setOnAction(actionEvent -> {
//            System.out.println("Button Clicked");
//            sceneChanger.setNewScene(resource,"Hello Page");
//        });
//    }




}

