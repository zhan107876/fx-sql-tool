package com.tan.fx.service;

import com.tan.fx.controller.RootController;
import com.tan.fx.controller.SqlController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * @title: WinHeightChangeListener
 * @Author Tan
 * @Date: 2022/10/22 19:47
 * @Version 1.0
 */
public class WinHeightChangeListener implements ChangeListener<Number> {

    private RootController rootController;
    private SqlController sqlController;

    public WinHeightChangeListener(RootController rootController, SqlController sqlController) {
        this.rootController = rootController;
        this.sqlController = sqlController;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        System.out.println("当前高度：" + newValue.doubleValue());
//        TextArea outputTextArea = null;
        TextArea outputTextArea = sqlController.getOutputTextArea();
        TextArea inputTextArea = sqlController.getInputTextArea();

        BorderPane p1 = rootController.getP1();
        VBox p2 = rootController.getP2();
        TabPane p3 = rootController.getP3();
        Tab p4 = rootController.getP4();
        VBox p7 = sqlController.getP7();
        GridPane publicGPane = rootController.getPublicGPane();
        GridPane g7_1 = sqlController.getG7_1();
        GridPane g7_2 = sqlController.getG7_2();

        p2.setPrefHeight(newValue.doubleValue());
        publicGPane.setPrefHeight(36);
        p3.setPrefHeight(newValue.doubleValue() - 120);
        p7.setPrefHeight(newValue.doubleValue() - 100);

        double outputTextAreaH = p7.getHeight() - g7_1.getHeight()  - g7_2.getHeight()  - inputTextArea.getHeight() ;
        outputTextArea.setPrefHeight(outputTextAreaH);

        // 输入框跟着变小
        if (outputTextAreaH < 300) {
            inputTextArea.setPrefHeight(outputTextAreaH / 2);
        } else {
            inputTextArea.setPrefHeight(150);
        }
    }
}