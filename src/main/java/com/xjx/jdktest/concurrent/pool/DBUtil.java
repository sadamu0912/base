package com.xjx.jdktest.concurrent.pool;

import java.sql.*;

public class DBUtil {
    //数据库驱动
    public static String driver = "com.mysql.jdbc.Driver";
    //数据库的资源地址
    public static String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8";
    //用户名
    public static String userName = "root";
    //密码
    public static String password = "";

    public static Connection getConnection() {
        try {
            //加载驱动
            Class.forName(driver);
            return DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //连接关闭
    public static void closeConn(ResultSet rs, PreparedStatement ps, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}