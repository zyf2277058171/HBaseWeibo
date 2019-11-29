//package com.atguigu.service;
//
//import com.atguigu.dao.WeiboDao;
//import com.atguigu.name.Names;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class WeiboService {
//
//    private WeiboDao dao = new WeiboDao();
//
//    //1.这些创建表的方法，真正的实现是在dao层中的。
//    //2.所以要创建dao对象，并在dao中创建对应的方法
//
//    /**
//     * 1.
//     * @throws IOException
//     */
//    public void init() throws IOException {
//        //1) 创建命名空间以及表名的定义
//        dao.createNameSpace(Names.NAMESPACE_WEIBO);
//
//        //2) 创建微博内容表
//        dao.createTable(Names.TABLE_WEIBO,Names.WEIBO_FAMILY);
//
//        //3) 创建用户关系表
//        dao.createTable(Names.TABLE_RELATION,Names.RELATION_FAMILY);
//
//        //4) 创建用户微博内容接收邮件表
//        dao.createTable(Names.TABLE_INBOX,Names.VERSIONS,Names.INBOX_FAMILY);
//
//    }
//
//    /**
//     * 2.发布微博
//     * @param starId
//     * @param content
//     * @throws IOException
//     */
//    public void publish(String starId, String content) throws IOException {
//        //1.向微博表中插入数据
//        String rowkey = starId + "-" + System.currentTimeMillis();
//        dao.putCell(Names.TABLE_WEIBO,rowkey,Names.WEIBO_FAMILY,Names.WEIBO_COLUMN,content);
//
//        //2.根据starId，获取到其所有fansId。有返回值
//        String prefix = starId + ":followedby:";
//        List<String> rowkeys = dao.getRowkeysByPrefix(Names.TABLE_RELATION,prefix);
//        if (rowkeys.size() <= 0) //没粉丝的情况
//            return;
//        List<String> fansIds = new ArrayList<>();
//        for (String row : rowkeys) {
//            String[] split = row.split(":");
//            fansIds.add(split[2]);
//        }
//
//        //3.向fansId对应的列中插入weibo表对应的rowkey
//        dao.putCells(Names.TABLE_INBOX,fansIds,Names.INBOX_FAMILY,starId);
//
//
//    }
//}
