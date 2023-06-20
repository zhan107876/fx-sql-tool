package com.tan.fx.controller;

import com.tan.fx.utils.JsonFormatUtils;
import com.tan.fx.utils.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
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
import java.util.regex.Pattern;

/**
 * @title: Sqlformat
 * @Author Tan
 * @Date: 2022/10/20 22:43
 * @Version 1.0
 */
@Data
public class JsonController implements Initializable {

    /**
     * 输入输出
     */
    public TextArea inputTextArea;
    public TextArea outputTextArea;
    public CheckBox s14;
    public Pane pane;
    public TabPane tabpane;
    public ComboBox font;
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
    public ColorPicker colorPicker;
    public HTMLEditor editor;
    public WebView webvew;
    public ChoiceBox choiceBox;
    public ImageView img;
    public VBox textVox;


    public void clear(ActionEvent mouseEvent) {
        inputTextArea.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * 压缩
     *
     * @param actionEvent
     */
    public void compress(ActionEvent actionEvent) {
        String text = inputTextArea.getText();
        String textA = Pattern.compile("\\s+").matcher(text).replaceAll(" ");
        outputTextArea.setText(textA.trim());
    }

    /**
     * 格式化
     *
     * @param actionEvent
     */
    public void format(ActionEvent actionEvent) {
        JsonController.format(this, actionEvent);
    }

    /**
     * 格式化
     *
     * @param actionEvent
     */
    public static void format(JsonController controller, ActionEvent actionEvent) {

        String text = controller.inputTextArea.getText();
        if (StringUtils.isEmpty(text)) {
            return;
        }
        // 工具先格式化
        String formatStr = JsonFormatUtils.formatJson(text);

        controller.outputTextArea.setText(formatStr);

    }

    /**
     * 复制到粘贴板
     *
     * @param actionEvent
     */
    public void copy(ActionEvent actionEvent) {
        final ClipboardContent content = new ClipboardContent();
        content.putString(outputTextArea.getText());
        Clipboard.getSystemClipboard().setContent(content);

        // 全选
        outputTextArea.selectAll();
        // 得到光标，获取焦点，获取光标
        outputTextArea.requestFocus();

    }

    public void change(ActionEvent actionEvent) {
        StringBuffer in = new StringBuffer(inputTextArea.getText());
        inputTextArea.clear();
        inputTextArea.setText(outputTextArea.getText());
        outputTextArea.setText(in.toString());
    }

    /**
     * 清除并粘贴
     *
     * @param actionEvent
     */
    public void clearPaste(ActionEvent actionEvent) {
        inputTextArea.clear();
        String string = Clipboard.getSystemClipboard().getString();
        inputTextArea.setText(string);
        inputTextArea.selectAll();
        // 得到光标，获取焦点，获取光标
        inputTextArea.requestFocus();
    }

    /**
     * 字体大小
     *
     * @param actionEvent
     */
    public void fontCombox(ActionEvent actionEvent) {
        String selectedItem = (String) font.getSelectionModel().getSelectedItem();
        Font font = new Font("Consolas", Integer.valueOf(selectedItem));
        outputTextArea.setFont(font);
        inputTextArea.setFont(font);
    }

    /**
     * 颜色选择器
     *
     * @param actionEvent
     */
    public void colorPicker(ActionEvent actionEvent) {
        String value = JsonController.toHexString(colorPicker.getValue());
        String style = "-fx-text-fill: " + value.toString();
        inputTextArea.setStyle(style);
        outputTextArea.setStyle(style);

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