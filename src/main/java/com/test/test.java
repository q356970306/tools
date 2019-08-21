package com.test;

import java.util.Random;
import java.math.BigDecimal;

public class test {

    public static void main(String[] args) {


        BigDecimal xianZhiScore = new BigDecimal(2802);


        if (xianZhiScore.compareTo(new BigDecimal(200)) < 0) {
            xianZhiScore = new BigDecimal(new Random().nextInt(200 - 100) + 100);

            System.out.println("小于200：：："+xianZhiScore);

        }
        if (xianZhiScore.compareTo(new BigDecimal(800)) > 0) {
            xianZhiScore = new BigDecimal(new Random().nextInt(900 - 800) + 801);
            System.out.println("大于800：：："+xianZhiScore);

        }

        System.out.println(xianZhiScore);
    }

}
