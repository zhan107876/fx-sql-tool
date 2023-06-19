package com.tan.fx.service;

import com.tan.fx.controller.SqlController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * @title: WinHeightChangeListener
 * @Author Tan
 * @Date: 2022/10/22 19:47
 * @Version 1.0
 */
public class ChoiceChangeListener implements ChangeListener<String> {

    private SqlController controller;
    public ChoiceChangeListener(SqlController controller) {
        this.controller = controller;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        String value = observable.getValue();
        if (value.equals(SqlController.FieldSeparation)) {
            SqlController.choiceBoxChange(controller, value);
        }else if(value.equals(SqlController.NotSeparation)){
            SqlController.format(controller, null);
        }
    }
}