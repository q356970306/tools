package com.xiahuaxian;

public class downLine {


//    public static void main(String[] args) {
//
//        downLine.downlineToHall("call_time_in_1_min_1m", true);
//
//
//    }


    public static String downlineToHall(String text, boolean hall) {

        String xx = "";
        String yy = "";
        String[] textarry = text.split(",");
        if (hall) {

            for (int i = 0; i < textarry.length; i++) {
                xx = "";
                char[] a = textarry[i].toCharArray();

                for (int j = 0; j < textarry[i].length(); j++) {


                    //call_time_in_1_min_1m
                    if (a[j] != '_') {
//                        if (j == 0) {
//                            xx += a[j];
//                        }
//
//                        if (j > 0 && a[j - 1] != '_') {
//                            xx += a[j];
//
//                        }
                        if (j > 0 && a[j - 1] == '_' &&(a[j] >= 'a' && a[j] <= 'z') ) {
                            a[j] -= 32;
                            xx += a[j];
                        }else {
                       xx += a[j];
                        }
                    }
                }
                System.out.println("下划线转驼峰：" + textarry[i] + " 转化为 " + xx);
                yy+=","+xx;
            }

        } else {


            for (int i = 0; i < textarry.length; i++) {
                xx = "";
                char[] a = textarry[i].toCharArray();

                for (int j = 0; j < textarry[i].length(); j++) {

                    if (a[j] >= 'A' && a[j] <= 'Z') {
                        xx += "_" + (a[j]+=32);
                    }else {
                        xx +=a[j];
                    }

                }
                System.out.println("驼峰转下划线：" + textarry[i] + " 转化为 " + xx);
                yy+=","+xx;


            }


        }

        return yy.substring(1);

    }
}
