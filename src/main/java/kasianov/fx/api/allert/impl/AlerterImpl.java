package kasianov.fx.api.allert.impl;

import feign.RetryableException;
import javafx.scene.control.Alert;
import kasianov.fx.api.allert.Alerter;
import kasianov.fx.exceptions.BaseError;
import org.springframework.stereotype.Component;

import java.lang.reflect.UndeclaredThrowableException;

@Component
public class AlerterImpl implements Alerter {
    @Override
    public void showAlertFromFeign(UndeclaredThrowableException exception) {
        Throwable cause = exception.getCause();
        if(cause.getClass() == BaseError.class){
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error alert");
            alert.setHeaderText("Error");
            alert.setContentText(cause.getMessage());

            alert.showAndWait();
        }else {
            exception.printStackTrace();
        }
    }

    @Override
    public void showAlertNoConnection() {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Warning alert");
            alert.setHeaderText("Warning");
            alert.setContentText("No network connection");

            alert.showAndWait();
    }

    @Override
    public void showWarningAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle("Warning alert");
        alert.setHeaderText("Warning");
        alert.setContentText(message);

        alert.showAndWait();
    }

    @Override
    public void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Success Alert");
        alert.setHeaderText("Success");
        alert.setContentText(message);

        alert.showAndWait();
    }
}
