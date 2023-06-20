package com.tan.fx;

import com.tan.fx.controller.JsonController;
import com.tan.fx.controller.RootController;
import com.tan.fx.controller.SqlController;
import com.tan.fx.service.ChoiceChangeListener;
import com.tan.fx.service.WinHeightChangeListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

//        URL location = getClass().getResource("/sqlformat_old.fxml");
        URL location = getClass().getResource("/sqlformatroot.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load();
        RootController rootController = fxmlLoader.getController();

        // 设置图标
        setIcons(primaryStage);
        primaryStage.setTitle("格式化工具CYZZ");

        Scene scene = new Scene(root);
        //加载css样式

        primaryStage.setScene(scene);

        //Controller中写的初始化方法
        primaryStage.show();

        /**
         * 高度改变的事件
         * 动态改变窗口高度
         */
        scene.getWindow().heightProperty().addListener(new WinHeightChangeListener(rootController ));
    }

    private void setIcons(Stage primaryStage) {
        try {
            // 设置图标
            String path = "file:" + System.getProperty("user.dir");
            System.out.println("user.dir======" + path);
            primaryStage.getIcons().add(new Image(path + "/tan1.bmp"));
        } catch (Exception e) {

        }
        try {
            // 设置图标
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            String path1 = loader.getResource("").getPath();
            System.out.println("getContextClassLoader======" + path1);
            primaryStage.getIcons().add(new Image("file:" + path1 + "/tan1.bmp"));
        } catch (Exception e) {
            System.out.println("getContextClassLoader======Exception");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

}
