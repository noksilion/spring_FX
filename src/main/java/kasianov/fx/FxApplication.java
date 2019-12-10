package kasianov.fx;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FxApplication {

	public static void main(String[] args) {
//		SpringApplication.run(FxApplication.class, args);
		Application.launch(JavaFxApplication.class,args);
	}

}
