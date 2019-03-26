package com.zx.yuren.jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.*;

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
        String username = "tbj";
        String password = "tbj900900";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);

            conn.setAutoCommit(false);
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


           fetchSchemeInDefaultWay(conn,"storage_tbl");

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

    private static void fetchSchemeInDefaultWay(Connection conn, String tableName) throws SQLException {
        java.sql.Statement stmt = null;
        java.sql.ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            StringBuffer sb = new StringBuffer("SELECT * FROM " + tableName + " LIMIT 1");
            rs = stmt.executeQuery(sb.toString());
            ResultSetMetaData rsmd = rs.getMetaData();
            DatabaseMetaData dbmd = conn.getMetaData();

             resultSetMetaToSchema(rsmd, dbmd);
        } catch (Exception e) {
            if (e instanceof SQLException) {
                throw ((SQLException) e);
            }
            throw new SQLException("Failed to fetch schema of " + tableName, e);

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    private static void resultSetMetaToSchema(ResultSetMetaData rsmd, DatabaseMetaData dbmd) throws SQLException {
        String tableName = rsmd.getTableName(1);
        String schemaName = rsmd.getSchemaName(1);
        String catalogName = rsmd.getCatalogName(1);

        java.sql.ResultSet rs1 = dbmd.getColumns(catalogName, schemaName, tableName, "%");
        while (rs1.next()) {
            System.out.println(rs1.getString("TABLE_CAT"));
            System.out.println(rs1.getString("TABLE_SCHEM"));
            System.out.println(rs1.getString("TABLE_NAME"));
            System.out.println(rs1.getString("COLUMN_NAME").toUpperCase());
            System.out.println(rs1.getInt("DATA_TYPE"));
            System.out.println(rs1.getString("TYPE_NAME"));
            System.out.println(rs1.getInt("COLUMN_SIZE"));
            System.out.println(rs1.getInt("DECIMAL_DIGITS"));
            System.out.println(rs1.getInt("NUM_PREC_RADIX"));
            System.out.println(rs1.getInt("NULLABLE"));
            System.out.println(rs1.getString("REMARKS"));
            System.out.println(rs1.getString("COLUMN_DEF"));
            System.out.println(rs1.getInt("SQL_DATA_TYPE"));
            System.out.println(rs1.getInt("SQL_DATETIME_SUB"));
            System.out.println(rs1.getInt("CHAR_OCTET_LENGTH"));
            System.out.println(rs1.getInt("ORDINAL_POSITION"));
            System.out.println(rs1.getString("IS_NULLABLE"));
            System.out.println(rs1.getString("IS_AUTOINCREMENT"));

        }

        java.sql.ResultSet rs2 = dbmd.getIndexInfo(catalogName, schemaName, tableName, false, true);
        String indexName = "";
        while (rs2.next()) {
            indexName = rs2.getString("INDEX_NAME");
            String colName = rs2.getString("COLUMN_NAME").toUpperCase();
            System.out.println(indexName);
            System.out.println(rs2.getBoolean("NON_UNIQUE"));
            System.out.println(rs2.getString("INDEX_QUALIFIER"));
            System.out.println(rs2.getString("INDEX_NAME"));
            System.out.println(rs2.getShort("TYPE"));
            System.out.println(rs2.getShort("ORDINAL_POSITION"));
            System.out.println(rs2.getString("ASC_OR_DESC"));
            System.out.println(rs2.getInt("CARDINALITY"));
        }
    }

}
