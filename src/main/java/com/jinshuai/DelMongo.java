package com.jinshuai;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.*;
import com.mongodb.client.model.geojson.GeoJsonObjectType;
import com.mongodb.util.JSON;
import com.mongodb.util.JSONParseException;
import javafx.scene.control.Alert;
import jdk.nashorn.internal.parser.JSONParser;

import java.text.SimpleDateFormat;
import java.util.*;


public class DelMongo {
//    public static void main(String[] args) {
//
//        delcarrierdata("");
//    }

    public static MongoClient getconnect() {

        ServerAddress serverAddress = new ServerAddress("10.0.221.99", 27017);
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
        addrs.add(serverAddress);
        MongoCredential credential = MongoCredential.createScramSha1Credential("root", "admin", "baofoo@64".toCharArray());
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(credential);
        MongoClient mongoClient = new MongoClient(addrs, credentials);

        return mongoClient;
    }


    public static void delcarrierdata(String phone, boolean ccrdel) {

        System.out.println("输入字符长度:" + phone.trim().length());
        if (phone.trim().length() > 10) {
            String[] phonearray = phone.split(",");
            //String phonestr1 = phonearray[0];
            //String phonestr2 = phonearray[1];

            //System.out.println("111111111111111:"+phonestr1+"      "+phonestr2);

            MongoClient mongoClient = DelMongo.getconnect();

            for (int j = 0; j < phonearray.length; j++) {

                String carriercrawlrecord = "carrier_crawl_record";
                BasicDBObject mobileObject = new BasicDBObject("mobile", phonearray[j]);

                for (int i = 0; i < 6; i++) {
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

                    Date newDate = stepMonth(date, -i);
                    String dateNowStr = sdf.format(newDate);

                    String calltable = "carrier_call_" + dateNowStr;
                    String smstable = "carrier_sms_" + dateNowStr;
                    String nettable = "carrier_net_" + dateNowStr;
                    String billtable = "carrier_bill_" + dateNowStr;
                    String packagestable = "carrier_packages_" + dateNowStr;
                    String rechargetable = "carrier_recharge_" + dateNowStr;

                    BasicDBObject queryObject = new BasicDBObject("phone", phonearray[j]);


                    DB dbcall = mongoClient.getDB(calltable);
                    DBCollection dbcallCollection = dbcall.getCollection("data");
                    System.out.println("删除哪张表的数据：" + phonearray[j] + "  ;  " + calltable);
                    dbcallCollection.remove(queryObject);


                    DB dbsms = mongoClient.getDB(smstable);
                    DBCollection dbsmsCollection = dbsms.getCollection("data");
                    System.out.println("删除哪张表的数据：" + phonearray[j] + "  ;  " + smstable);
                    dbsmsCollection.remove(queryObject);

                    DB dbnet = mongoClient.getDB(nettable);
                    DBCollection dbnetCollection = dbnet.getCollection("data");
                    System.out.println("删除哪张表的数据：" + phonearray[j] + "  ;  " + nettable);
                    dbnetCollection.remove(queryObject);

                    DB dbbill = mongoClient.getDB(billtable);
                    DBCollection dbbillCollection = dbbill.getCollection("data");
                    System.out.println("删除哪张表的数据：" + phonearray[j] + "  ;  " + billtable);
                    dbbillCollection.remove(queryObject);

                    DB dbpackages = mongoClient.getDB(packagestable);
                    DBCollection dbpackagesCollection = dbpackages.getCollection("data");
                    System.out.println("删除哪张表的数据：" + phonearray[j] + "  ;  " + packagestable);
                    dbpackagesCollection.remove(queryObject);

                    DB dbrecharge = mongoClient.getDB(rechargetable);
                    DBCollection dbrechargeCollection = dbrecharge.getCollection("data");
                    System.out.println("删除哪张表的数据：" + phonearray[j] + "  ;  " + rechargetable);
                    dbrechargeCollection.remove(queryObject);


//            BasicDBObject queryObject = new BasicDBObject("phone", phone);
//            DBObject obj = dbCollection.findOne(queryObject);
//            if (!obj.toString().isEmpty()) {
//                System.out.println(obj.toString());
//            }
                }
                if (ccrdel) {
                    DB dbcarriercrawlrecord = mongoClient.getDB(carriercrawlrecord);
                    DBCollection dbcarriercrawlrecordCollection = dbcarriercrawlrecord.getCollection("carrier_crawl_record");
                    System.out.println("删除哪张表的数据：" + phonearray[j] + "  ;  " + carriercrawlrecord);
                    dbcarriercrawlrecordCollection.remove(mobileObject);
                }

            }
            mongoClient.close();

            //弹框
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setContentText("删除成功！");
            alert.show();

        } else {
            System.out.println("输入手机号码有误，请重新输入！");

            //弹框
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setContentText("输入手机号码有误，请重新输入！");
            alert.show();
        }
    }


    public static void deltaobaodata(String uid, boolean collectiondel) {


        System.out.println("输入字符长度:" + uid.length());
        if (uid.trim().length() > 0) {
            MongoClient mongoClient = DelMongo.getconnect();


            for (int i = 0; i < 6; i++) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

                Calendar date = Calendar.getInstance();
                date.add(Calendar.YEAR, -i);
                Date year = date.getTime();
                String year2 = sdf.format(year);


                String calltable = "taobao_finish_" + year2;

                BasicDBObject queryObject = new BasicDBObject("uid", uid);
                DB dbcall = mongoClient.getDB(calltable);
                DBCollection dbcallCollection = dbcall.getCollection("taobao_finish");
                System.out.println("删除哪个库哪个表的数据：" + uid + "[taobao_finish]" + "  " + calltable);
                dbcallCollection.remove(queryObject);

            }
            if (collectiondel) {

                BasicDBObject queryObject = new BasicDBObject("uid", uid);
                DB dbcall = mongoClient.getDB("taobao_crawl_record");
                DBCollection dbcallCollection = dbcall.getCollection("taobao_crawl_record");
                System.out.println("删除哪个库哪个表的数据：" + uid + "[taobao_crawl_record]" + "  " + "taobao_crawl_record");
                dbcallCollection.remove(queryObject);
            }


            mongoClient.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setContentText("删除成功！");
            alert.show();

        } else {
            System.out.println("输入淘宝id有误，请重新输入！");

            //弹框
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setContentText("输入淘宝id有误，请重新输入！");
            alert.show();
        }
    }


//    public static void main(String[] args) {
//        deltaobaodata("4251940983",true);
//    }

    //根据token查询淘宝uid
    public static String findtaobaouid(String token) {
        System.out.println("输入字符长度:" + token.length());
        if (token.trim().length() > 0) {


        String table = "task_param_taobao";
        MongoClient mongoClient = DelMongo.getconnect();
        BasicDBObject queryObject = new BasicDBObject("token", token);


        DB dbcall = mongoClient.getDB("task_param");
        DBCollection dbfindCollectionCollection = dbcall.getCollection(table);

        DBObject obj = dbfindCollectionCollection.findOne(queryObject);
//        String data = obj.toString();
        if (obj != null) {
            Map data = obj.toMap();

            JSONObject uidObj = new JSONObject(data);
            String taobaoUid = uidObj.getString("taobaoUid");


            if (taobaoUid != null && !taobaoUid.isEmpty()) {

                mongoClient.close();
                System.out.println("查询的哪张表：" + table);
                System.out.println("uid为" + taobaoUid);
                return taobaoUid;


            } else {
                System.out.println("查询的哪张表：" + table);
                System.out.println("该token查不到淘宝id");
                mongoClient.close();
                //弹框
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setContentText("该token查不到淘宝id！");
                alert.show();
                return null;
            }
        } else {
            table = "task_param_taobaopay";
            DBCollection dbfindCollectionCollection2 = dbcall.getCollection(table);
            DBObject obj2 = dbfindCollectionCollection2.findOne(queryObject);

            if (obj2 != null) {

                Map data2 = obj2.toMap();
                JSONObject uidObj2 = new JSONObject(data2);
                String taobaoUid2 = uidObj2.getString("taobaoUid");

                if (taobaoUid2 != null && !taobaoUid2.isEmpty()) {

                    mongoClient.close();
                    System.out.println("查询的哪张表：" + table);
                    System.out.println("uid为" + taobaoUid2);
                    return taobaoUid2;

                } else {

                    mongoClient.close();
                    System.out.println("查询的哪张表：" + table);
                    System.out.println("该token查不到淘宝id");
                    //弹框
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setContentText("该token查不到淘宝id！");
                    alert.show();
                    return null;

                }
            } else {
                mongoClient.close();
                System.out.println("传入的token查不到记录");
                //弹框
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setContentText("传入的token查不到记录！");
                alert.show();
                return null;
            }

        }
        } else {

            System.out.println("输入token有误，请重新输入！");

            //弹框
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setContentText("输入token有误，请重新输入！");
            alert.show();
            return null;
        }

    }

    public static void main(String[] args) {
        findtaobaouid("0065979178883264512");
    }


    public static String findcarrierdata(String token) {

        MongoClient mongoClient = DelMongo.getconnect();
        BasicDBObject queryObject = new BasicDBObject("token", token);


        DB dbcall = mongoClient.getDB("task_success_record");
        DBCollection dbfindCollectionCollection = dbcall.getCollection("carrier");
        System.out.println("查询的哪张表：" + "task_success_record");
        DBObject obj = dbfindCollectionCollection.findOne(queryObject);
        String data = obj.toString();
        if (!obj.toString().isEmpty()) {
            System.out.println(data);
        }
        return data;
    }


    /**
     * 在给定的日期加上或减去指定月份后的日期
     *
     * @param sourceDate 原始时间
     * @param month      要调整的月份，向前为负数，向后为正数
     * @return
     */
    public static Date stepMonth(Date sourceDate, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, month);

        return c.getTime();
    }
}
