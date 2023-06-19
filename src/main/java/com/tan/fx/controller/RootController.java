package com.tan.fx.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import lombok.Data;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @title: Sqlformat
 * @Author Tan
 * @Date: 2022/10/20 22:43
 * @Version 1.0
 */
@Data
public class RootController implements Initializable {

    /**
     * 这里注入的变量名一定等于 sqlformatroot.fxml 里面 x:include 的 fx:id  + Controller
     * 比如 <fx:include fx:id="sql"  source="sqlformatsql.fxml"/>
     * x:id 是 sql  变量名 = qlController
     * 如果不按照以上规则，注入的Controller就是空的
     */
    @FXML
    private SqlController sqlController;
    @FXML
    private JsonController jsonController;

    /**
     * 输入输出
     */
    public ComboBox font;
    public ImageView img;
    public ColorPicker colorPicker;

    public TextArea inputTextArea;
    public TextArea outputTextArea;
    public CheckBox s14;
    public Pane pane;
    public TabPane tabpane;

    public BorderPane main;
    public VBox vbox;

    public BorderPane p1;
    public VBox p2;
    public TabPane p3;
    public Tab p4;
    public BorderPane p5;
    public BorderPane p6;
    public VBox p7;
    public GridPane publicGPane;
    public GridPane G7_1;
    public GridPane G7_2;

    public HTMLEditor editor;
    public WebView webvew;
    public ChoiceBox choiceBox;
    public VBox textVox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 初始化字体
        font.setItems(FXCollections.observableArrayList("10", "11", "12", "13", "14", "15", "16", "20"));
        font.getSelectionModel().select(2);

        try {
            // 设置图标
            String path = "file:" + System.getProperty("user.dir");
            System.out.println("user.dir======" + path);
            img.setImage(new Image(path + "/my.jpg"));

        } catch (Exception e) {

        }
    }

    /**
     * 字体大小
     *
     * @param actionEvent
     */
    public void fontCombox(ActionEvent actionEvent) {
        String selectedItem = (String) font.getSelectionModel().getSelectedItem();
        Font font = new Font("Consolas", Integer.valueOf(selectedItem));

        // 设置字体
        sqlController.outputTextArea.setFont(font);
        sqlController.inputTextArea.setFont(font);

        // 设置字体
        jsonController.outputTextArea.setFont(font);
        jsonController.inputTextArea.setFont(font);
    }

    /**
     * 颜色选择器
     *
     * @param actionEvent
     */
    public void colorPicker(ActionEvent actionEvent) {
        String value = RootController.toHexString(colorPicker.getValue());
        String style = "-fx-text-fill: " + value.toString();
        sqlController.inputTextArea.setStyle(style);
        sqlController.outputTextArea.setStyle(style);

        jsonController.inputTextArea.setStyle(style);
        jsonController.outputTextArea.setStyle(style);
    }

    /**
     * 颜色转换 成十六进制的
     *
     * @param color
     * @return
     */
    private static String toHexString(Color color) {
        int r = ((int) Math.round(color.getRed() * 255)) << 24;
        int g = ((int) Math.round(color.getGreen() * 255)) << 16;
        int b = ((int) Math.round(color.getBlue() * 255)) << 8;
        int a = ((int) Math.round(color.getOpacity() * 255));
        return String.format("#%08X", (r + g + b + a));
    }
}