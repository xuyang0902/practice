package com.zx.yuren.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * jdbc规范
 *
 *
 * connection
 *
 * statement
 *
 *
 *
 *
 *
 * @author xu.qiang
 * @date 19/1/17
 */
public class JdbcMain {

    public static void main(String[] args) {


        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.1.13:3306/fescar";
        String username = "XXX";
        String password = "XXX";
        ConnectionWrapper conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = new ConnectionWrapper(DriverManager.getConnection(url, username, password));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        PreparedStatement preparedStatement = null;

        String sql = " delete from storage_tbl where commodity_code = ? ";

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "C00321");

            int i = preparedStatement.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null) {

                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
