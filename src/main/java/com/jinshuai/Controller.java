package com.jinshuai;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    //定义fxml中的控件；fxml文件中VBox节点手动加入fx:controller
    @FXML
    private Button btn;
    @FXML
    private Label title1;
    @FXML
    private Label title2;
    @FXML
    private TextArea input;
    @FXML
    private RadioButton ifdelccr;
    @FXML
    private RadioButton taobaoradiobutton;
    @FXML
    private Button taobaobutton;
    @FXML
    private Button uidbutton;
    @FXML
    private TextField taobaotextfield;
    @FXML
    private TextField uidtextfield;

    @FXML
    public void del(ActionEvent event) throws Exception {
        System.out.println("操作开始");

        String phone = input.getText();
        boolean ccrdel = ifdelccr.isSelected();
        System.out.println("是否删除carrier_crawl_record为："+ccrdel);

        DelMongo.delcarrierdata(phone,ccrdel);

        }

    @FXML
    public void deltaobao(ActionEvent event) throws Exception {
        System.out.println("操作开始");

        String uid = taobaotextfield.getText();
        boolean isdeltaobao = taobaoradiobutton.isSelected();
        System.out.println("是否删除taobao_crawl_record为："+isdeltaobao);

        DelMongo.deltaobaodata(uid,isdeltaobao);

    }


    @FXML
    public void gettaobaouid(ActionEvent event) throws Exception {
        System.out.println("操作开始");

        String token = uidtextfield.getText();

        String uid = DelMongo.findtaobaouid(token);

        if (uid!=null&&!uid.isEmpty()) {
            taobaotextfield.setText(DelMongo.findtaobaouid(token));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setContentText("获取成功");
            alert.show();
        }else {
            taobaotextfield.setText("");
        }
    }

    }
