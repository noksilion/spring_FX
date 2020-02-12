package kasianov.fx.api.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import kasianov.fx.api.sceneChangers.SceneChanger;
import kasianov.fx.services.autorization.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    private final SceneChanger sceneChanger;

    private final AuthService authService;

    @Value("classpath:/signup.fxml")
    private Resource signupScene;

    @Value("${signup.title}")
    private String signupSceneName;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signupButton;

    @Autowired
    public LoginController(SceneChanger sceneChanger, AuthService authService) {
        this.sceneChanger = sceneChanger;
        this.authService = authService;
    }

    @FXML
    void onLoginButtonClick(ActionEvent event) {
        authService.login(emailField.getText(),passwordField.getText());
    }

    @FXML
    void onSignupButtonClick(ActionEvent event) {
        sceneChanger.setNewScene(signupScene,signupSceneName);
    }

    @FXML
    void initialize() {

    }
}

