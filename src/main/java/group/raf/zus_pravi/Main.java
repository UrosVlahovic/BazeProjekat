package group.raf.zus_pravi;

import group.raf.zus_pravi.view.LoginForm;
import group.raf.zus_pravi.view.MainScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new LoginForm();
        primaryStage = stage;
        stage.setTitle("ZUS");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}