package kasianov.fx.api.allert;

import feign.RetryableException;

import java.lang.reflect.UndeclaredThrowableException;

public interface Alerter {
    void showAlertFromFeign(UndeclaredThrowableException exception);
    void showAlertNoConnection(RetryableException connectException);
    void showWarningAlert(String message);
}
