//package com.atguigu.dao;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.*;
//import org.apache.hadoop.hbase.client.*;
//import org.apache.hadoop.hbase.util.Bytes;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class WeiboDao {
//
//    private static Connection connection = null;
//
//    /**
//     * 0.创建连接
//     */
//    static {
//        try {
//            Configuration configuration = HBaseConfiguration.create();
//            configuration.set("hbase.zookeeper.quorum","hadoop107,hadoop108,hadoop109");
//            connection = ConnectionFactory.createConnection(configuration);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 1.创建命名空间
//     * @param nameSpace
//     * @throws IOException
//     */
//    public void createNameSpace(String nameSpace) throws IOException {
//        //1.要创建命名空间，需要admin，就要先获取连接，在静态代码块中
//        Admin admin = connection.getAdmin();
//
//        try {
//            //2.如果没有此命名空间，则抓异常，并创建。
//            admin.getNamespaceDescriptor(nameSpace);
//        }catch (NamespaceNotFoundException e){
//            NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(nameSpace).build();
//            admin.createNamespace(namespaceDescriptor);
//        }finally {
//            admin.close();
//        }
//    }
//
//    /**
//     * 2.创建weibo，relation表
//     * @param tableName
//     * @param families
//     * @throws IOException
//     */
//    public void createTable(String tableName, String... families) throws IOException {
//        //1.获取admin
//        Admin admin = connection.getAdmin();
//
//        //判断一下
//        if (admin.tableExists(TableName.valueOf(tableName))){
//
//        } else {
//            HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
//            for (String family : families) {
//                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(family);
//                HTableDescriptor hTableDescriptor1 = hTableDescriptor.addFamily(hColumnDescriptor);
//                //2.创建表【创建表描述器-添加列族-创建列描述器】
//                admin.createTable(hTableDescriptor);
//            }
//        }
//
//        //3.关闭admin
//        admin.close();
//    }
//
//    /**
//     * 3.创建inbox表
//     * @param tableName
//     * @param versions
//     * @param families
//     * @throws IOException
//     */
//    public void createTable(String tableName, int versions, String... families) throws IOException {
//        //1.
//        Admin admin = connection.getAdmin();
//
//        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
//        for (String family : families) {
//            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(family);
//            hColumnDescriptor.setMaxVersions(versions);
//            HTableDescriptor hTableDescriptor1 = hTableDescriptor.addFamily(hColumnDescriptor);
//
//        }
//
//        //2.
//        admin.createTable(hTableDescriptor);
//
//        //3.
//        admin.close();
//
//    }
//
//    /**
//     * 4.插入一条数据
//     * @param tableName
//     * @param rowkey
//     * @param family
//     * @param column
//     * @param value
//     * @throws IOException
//     */
//    public void putCell(String tableName, String rowkey, String family, String column, String value) throws IOException {
//        //1.
//        Table table = connection.getTable(TableName.valueOf(tableName));
//
//        //2.
//        Put put = new Put(Bytes.toBytes(rowkey));
//        put.addColumn(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes(value));
//
//        table.put(put);
//
//        //3.
//        table.close();
//    }
//
//    /**
//     * 5.获取到指定starId的所有fansId
//     * @param tableName
//     * @param prefix
//     * @return
//     * @throws IOException
//     */
//    public List<String> getRowkeysByPrefix(String tableName, String prefix) throws IOException {
//        //1.
//        Table table = connection.getTable(TableName.valueOf(tableName));
//
//        //2.查询
//        //2.2
//        Scan scan = new Scan();
//        //2.3设置为按前缀过滤
//        scan.setRowPrefixFilter(Bytes.toBytes(prefix));
//        //2.1
//        ResultScanner scanner = table.getScanner(scan);
//
//        //2.4
//        List<String> rowkeys = new ArrayList<>();//将获取到的所有fansId放进集合
//        //遍历结果集，每一条数据就是一行记录
//        for (Result result : scanner) {
//            //可以直接通过此方法获取到这个记录的rowkey
//            byte[] row = result.getRow();
//            rowkeys.add(Bytes.toString(row));
//        }
//
//        //3.
//        table.close();
//        scanner.close();
//
//        return rowkeys;
//    }
//
//    /**
//     * 5.向所有fansId的starId列插入数据
//     * @param tableName
//     * @param rowkeys
//     * @param family
//     * @param column
//     * @throws IOException
//     */
//    public void putCells(String tableName, List<String> rowkeys, String family, String column) throws IOException {
//        //1.
//        Table table = connection.getTable(TableName.valueOf(tableName));
//
//        //2.
//        List<Put> puts = new ArrayList<>();
//        for (String rowkey : rowkeys) {
//            Put put = new Put(Bytes.toBytes(rowkey));
//            //向put中传入数据
//            //put.addColumn();
//            puts.add(put);
//        }
//        table.put(puts);
//
//        //3.
//        table.close();
//    }
//}
//
//
//
