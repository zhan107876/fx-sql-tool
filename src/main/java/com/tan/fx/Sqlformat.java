package com.tan.fx;

import com.alibaba.druid.sql.SQLUtils;
import com.tan.fx.utils.StringUtils;
import com.tan.fx.utils.SymbolUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
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
public class Sqlformat implements Initializable {

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

    public static String NotSeparation = "通用";
    public static String FieldSeparation = "字段分离";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 初始化字体
        font.setItems(FXCollections.observableArrayList("10", "11", "12", "13", "14", "15", "16", "20"));
        font.getSelectionModel().select(2);

        choiceBox.setItems(FXCollections.observableArrayList(NotSeparation, FieldSeparation));
        choiceBox.getSelectionModel().select(1);
        try {
            // 设置图标
            String path = "file:" + System.getProperty("user.dir");
            System.out.println("user.dir======" + path);
            img.setImage(new Image(path + "/my.jpg"));

        } catch (Exception e) {

        }
        inputTextArea.setWrapText(true);

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
        Sqlformat.format(this, actionEvent);
    }

    /**
     * 格式化
     *
     * @param actionEvent
     */
    public static void format(Sqlformat controller, ActionEvent actionEvent) {

        String text = controller.inputTextArea.getText();
        if (StringUtils.isEmpty(text)) {
            return;
        }
        // 工具先格式化
        String formatStr = SQLUtils.formatMySql(text);
        controller.outputTextArea.setText(formatStr);
        choiceBoxChange(controller, null);
    }

    /**
     * and 格式化
     *
     * @param actionEvent
     */
    public void formatAnd(ActionEvent actionEvent) {
        format(this, actionEvent);
        String text = outputTextArea.getText();
        text = text.replaceAll("(and|AND)", "\r\nAND");
        outputTextArea.setText(text.trim());

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
        String value = Sqlformat.toHexString(colorPicker.getValue());
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


    public void choiceBox(MouseEvent mouseEvent) {

        ObservableList items = choiceBox.getItems();
        for (Object observable : items) {
            String selectedItem = (String) observable;
            if (FieldSeparation.equals(selectedItem)) {
                String text = outputTextArea.getText();
                int from = text.indexOf("FROM");
                String selectstr = text.substring(0, from);

                String[] split = text.split(",");

                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(split[0]);
                for (int i = 1; i < split.length - 1; i++) {
                    stringBuffer.append("    " + split[i] + ",");
                }
                // 最后一个没有逗号
                stringBuffer.append("    " + split[split.length - 1]);
                stringBuffer.append(text.substring(from));

                outputTextArea.setText(stringBuffer.toString());
            }
        }

    }

    /**
     * 格式化
     */
    public static void choiceBoxChange(Sqlformat controller, String value) {
        if (value == null) {
            value = (String) controller.getChoiceBox().getSelectionModel().getSelectedItem();
        }

        if (value.equals(Sqlformat.FieldSeparation)) {
            String text = controller.getOutputTextArea().getText();
            // 工具先格式化
            String formatStr = SQLUtils.formatMySql(text);
            // 字段分离
            int from = text.indexOf("\n");
            String fromBefor = formatStr.substring(0, from); //第一行

            // 暂时 select 开头才字段分离
            if (fromBefor.toLowerCase().contains("select")) {
                StringBuffer stringBuffer = new StringBuffer();
                String[] firstStr = fromBefor.split(SymbolUtils.COMMA);

                int firstFieldInx = firstStr[0].lastIndexOf(SymbolUtils.SPACE);
                String firstField = firstStr[0].substring(firstFieldInx + 1);
                String sqlKey = firstStr[0].substring(0, firstFieldInx);

                stringBuffer.append(sqlKey + SymbolUtils.LINE_FEED);
                stringBuffer.append(SymbolUtils.INDENTATION + firstField + SymbolUtils.COMMA + SymbolUtils.LINE_FEED);

                // 中间字段 逗号换行
                for (int i = 1; i < firstStr.length - 1; i++) {
                    stringBuffer.append(SymbolUtils.INDENTATION + firstStr[i].trim() + SymbolUtils.COMMA + SymbolUtils.LINE_FEED);
                }

                // 最后一个没有逗号
                stringBuffer.append(SymbolUtils.INDENTATION + firstStr[firstStr.length - 1].trim());

                // 其余部分
                stringBuffer.append(text.substring(from));

                controller.getOutputTextArea().setText(stringBuffer.toString());
            } else {
                controller.getOutputTextArea().setText(formatStr);
            }

        } else if (value.equals(Sqlformat.FieldSeparation)) {

        }
    }
}