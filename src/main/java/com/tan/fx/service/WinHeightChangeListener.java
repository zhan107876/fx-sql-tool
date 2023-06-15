package com.tan.fx.service;

import com.tan.fx.Sqlformat;
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

    private Sqlformat controller;
    public WinHeightChangeListener(Sqlformat controller) {
        this.controller = controller;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        System.out.println("当前高度：" + newValue.doubleValue());
        TextArea outputTextArea = null;
//        TextArea outputTextArea = controller.getOutputTextArea();
        TextArea inputTextArea = controller.getInputTextArea();

        BorderPane p1 = controller.getP1();
        VBox p2 = controller.getP2();
        TabPane p3 = controller.getP3();
        Tab p4 = controller.getP4();
        BorderPane p5 = controller.getP5();
        BorderPane p6 = controller.getP6();
        VBox p7 = controller.getP7();
        GridPane publicGPane = controller.getPublicGPane();
        GridPane g7_1 = controller.getG7_1();
        GridPane g7_2 = controller.getG7_2();

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