package com.jinshuai;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;
import java.nio.charset.Charset;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Thread.currentThread().getContextClassLoader().getResource("del.fxml"));
        stage.setTitle("删除淘宝已完成订单及其统计、运营商原始数据");
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
