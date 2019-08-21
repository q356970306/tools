package com.xiahuaxian;

import com.jinshuai.DelMongo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sun.font.TextLabel;

public class Controller {

    //定义fxml中的控件；fxml文件中VBox节点手动加入fx:controller
    @FXML
    private Button button1;
    @FXML
    private TextArea input1;
    @FXML
    private TextArea input2;
    @FXML
    private CheckBox checkBox1;

    @FXML
    public void change(ActionEvent event) throws Exception {
        System.out.println("操作开始");

        String content = input1.getText();
        boolean ccrdel = checkBox1.isSelected();
        System.out.println("是否下划线转驼峰为："+ccrdel);


        String aa = downLine.downlineToHall(content, ccrdel);

        input2.setText(aa);

        }

    }
