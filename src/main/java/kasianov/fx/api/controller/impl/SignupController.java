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
public class SignupController implements CustomFXController {

    private final SceneChanger sceneChanger;
    private final AuthService authService;
    private final Alerter alerter;

    @Value("classpath:/login.fxml")
    private Resource loginScene;

    @Value("${signup.title}")
    private String signupSceneName;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signupButton;
    @FXML
    private Button loginButton;

    @Autowired
    public SignupController(SceneChanger sceneChanger, AuthService authService, Alerter alerter) {
        this.sceneChanger = sceneChanger;
        this.authService = authService;
        this.alerter = alerter;
    }

    @FXML
    void onLoginButtonClick(ActionEvent event) {
        sceneChanger.setNewScene(loginScene,signupSceneName);
    }

    @FXML
    void onSignupButtonClick(ActionEvent event) {
        try{
            authService.signup( emailField.getText(),passwordField.getText(),nameField.getText());
        }catch (UndeclaredThrowableException undeclared){
            alerter.showAlertFromFeign(undeclared);
        }catch (RetryableException connectException){
            alerter.showAlertNoConnection();
        }
    }

    @FXML
    void initialize() {
    }

    @Override
    public void afterInit() {

    }
}

