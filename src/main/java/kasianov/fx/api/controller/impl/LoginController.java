package kasianov.fx.api.controller.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.net.URL;
import java.util.ResourceBundle;

import feign.RetryableException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import kasianov.fx.api.allert.Alerter;
import kasianov.fx.api.controller.CustomFXController;
import kasianov.fx.api.sceneChangers.SceneChanger;
import kasianov.fx.services.autorization.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class LoginController implements CustomFXController {

    private SceneChanger sceneChanger;
    private AuthService authService;
    private Alerter alerter;

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
    public LoginController(SceneChanger sceneChanger, AuthService authService, Alerter alerter) {
        this.sceneChanger = sceneChanger;
        this.authService = authService;
        this.alerter = alerter;
    }

    @FXML
    void onLoginButtonClick(ActionEvent event) {
        try{
            authService.login(emailField.getText(),passwordField.getText());
        }catch (UndeclaredThrowableException undeclared){
            alerter.showAlertFromFeign(undeclared);
        }catch (RetryableException connectException){
            alerter.showAlertNoConnection();
        }
    }

    @FXML
    void onSignupButtonClick(ActionEvent event) {
        sceneChanger.setNewScene(signupScene,signupSceneName);
    }

    @FXML
    void initialize() {

    }

    @Override
    public void afterInit() {

    }
}

