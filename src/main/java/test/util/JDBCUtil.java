package test.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JDBCUtil {
    //定义非静态的全局变量data
    private int  data = 1;
    public  static  Properties properties = new Properties();
    static {
        try {
            System.out.println("静态代码块解析Properties数据");
            InputStream inputStream = new FileInputStream(new File("src/test/resources/jdbc.properties"));
            properties.load(inputStream);
        }catch (IOException e) {
            System.out.println("发生了异常");
            e.printStackTrace();
        }
    }
    /*根据sql查询表数据，并以map返回，key为字段名字，value为字段值
     * sql 要执行查询语句
     * Values 条件字段的值
     * */
    public  static Map<String,Object> query(String sql, Object...Values){
        Map<String,Object> columnValues = null;
        try {
//            1.根据链接信息，获取数据库链接(连上数据库):
            Connection connection =getConnection();
//            2.获取PreparedStatement对象(此类型的对象有提供数据库操作方法):
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
//            3.设置条件字段占位符的值:
            for (int i = 0; i < Values.length ; i++) {//数组从0开始
                preparedStatement.setObject(i+1,Values[i]);//设置值从1开始
            }
//            4.调用查询方法,执行查询,返回ResultSet(结果集):
            ResultSet rest = preparedStatement.executeQuery();
            //获取查询相关的信息
            ResultSetMetaData metaData =rest.getMetaData();
            //得到查询字段的数目
            int coulumnCount = metaData.getColumnCount();
//            5.从结果集取查询数据:
            columnValues = new HashMap<String,Object>();
            while (rest.next()){
                //循环取出每个查询字段的数据从1开始
                for (int i = 1; i <coulumnCount ; i++) {
                    String columnLable= metaData.getColumnLabel(i);
                    String columnVaule= rest.getObject(columnLable).toString();
                    columnValues.put(columnLable,columnVaule);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  columnValues;
    }
    /*
     * 获取数据库连接
     * */
    public  static  Connection getConnection() throws SQLException{
        //从properties取url
        String url = properties.getProperty("jdbc.url");
        //从properties取user
        String user = properties.getProperty("jdbc.username");
        //从properties取password
        String password = properties.getProperty("jdbc.password");
        Connection connection =  DriverManager.getConnection(url,user,password);
        return  connection;
    }
}
