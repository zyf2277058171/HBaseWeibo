package com.atguigu.test;

import com.atguigu.controller.WeiboController;

import java.io.IOException;

public class WeiboTest {
    public static void main(String[] args) throws IOException {
        WeiboController weiboController = new WeiboController();
//        weiboController.init();
        weiboController.publish("1001","content1");
        weiboController.publish("1001","content2");
        weiboController.publish("1001","content3");
        weiboController.publish("1001","content4");
        weiboController.publish("1001","content5");

    }
}

