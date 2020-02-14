package kasianov.fx.api.allert;

import feign.RetryableException;

import java.lang.reflect.UndeclaredThrowableException;

public interface Alerter {
    void showAlertFromFeign(UndeclaredThrowableException exception);
    void showAlertNoConnection();
    void showWarningAlert(String message);
    void showSuccessAlert(String message);
}
