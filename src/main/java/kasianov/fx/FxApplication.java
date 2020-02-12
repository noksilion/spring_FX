package kasianov.fx;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FxApplication {

	public static void main(String[] args) {
		Application.launch(JavaFxApplication.class,args);
	}

}
