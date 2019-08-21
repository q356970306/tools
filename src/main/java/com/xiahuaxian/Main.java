package com.xiahuaxian;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Thread.currentThread().getContextClassLoader().getResource("changeWord.fxml"));
        stage.setTitle("下划线驼峰转换");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(false);

        //stage.getIcons().add(new Image(getResource("image/icon.png")));
    }

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        launch(args);

    }

    private InputStream getResource(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
    }
}
