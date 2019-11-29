//package com.atguigu.controller;
//
//import com.atguigu.service.WeiboService;
//
//import java.io.IOException;
//
//public class WeiboController {
//
//    private WeiboService service = new WeiboService();
//
//    //1) 创建命名空间以及表名的定义
//    //2) 创建微博内容表
//    //3) 创建用户关系表
//    //4) 创建用户微博内容接收邮件表
//
//    /**
//     * 初始化方法，创建一些必要的表.[完成的是前四步的功能]
//     */
//    public void init() throws IOException {
//        //1.这里不会真正写方法，而是调用service中的方法
//        //2.要想调用service的方法，就先要创建service的对象
//        service.init();
//
//    }
//
//    //5) 发布微博内容
//    public void publish(String starId,String content) throws IOException {
//        service.publish(starId,content);
//    }
//
//    //6) 添加关注用户
//
//    //7) 移除（取关）用户
//
//    //8) 获取关注的人的微博内容
//
//    //9) 测试
//
//}
