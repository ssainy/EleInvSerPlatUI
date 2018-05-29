package seleniumutils;

import java.sql.*;

/**
 * Created by cch on 2017/5/24.
 */
public class JDBC {
    public static String connJdbc(String colname,String sql){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //加载MySQL的驱动类
            Class.forName("com.mysql.jdbc.Driver");
            connection =  DriverManager.getConnection("jdbc:mysql://10.1.20.8:3306/vPiaoBao?characterEncoding=utf-8", "root", "root");
            statement = connection.createStatement();
            resultSet =  statement.executeQuery(sql);
            while(resultSet.next()){
                String result=resultSet.getString(colname);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public static void update(String sql) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");//加载驱动
        String jdbc="jdbc:mysql://10.1.20.8:3306/elephant3-q?characterEncoding=GBK";
        Connection conn=DriverManager.getConnection(jdbc, "root", "root");//链接到数据库
        Statement state=conn.createStatement();   //容器
        // String sql="update xs set xuexiao='淄博汉企' where xuehao='1101' ";   //SQL语句
        state.executeUpdate(sql);         //将sql语句上传至数据库执行

        conn.close();//关闭通道

    }

}