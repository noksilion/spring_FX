package kasianov.fx;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class JavaFxApplication extends Application {
    private ConfigurableApplicationContext context;
    @Override
    public void init() throws Exception {
        ApplicationContextInitializer<GenericApplicationContext> initializer =
                (GenericApplicationContext ac) -> {
                    ac.registerBean(Application.class,()-> JavaFxApplication.this);
                    ac.registerBean(Parameters.class, this::getParameters);
                    ac.registerBean(HostServices.class, this::getHostServices);
                };
        this.context = new SpringApplicationBuilder()
                .sources(FxApplication.class)
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(new String[0]));

    }

    @Override
    public void start(Stage stage) throws Exception {
        context.getBeanFactory().registerSingleton("stageBean",stage);
//        genericApplicationContext.registerBean("stageBean",Stage.class,stage);
        this.context.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }

    public class  StageReadyEvent extends ApplicationEvent{

        public Stage getStage(){
            return (Stage) getSource();
        }

        public StageReadyEvent(Object source) {
            super(source);
        }
    }
}
