package com.exercise.demo.common.utils;

import com.exercise.demo.common.constant.JdbcConstant;
import com.exercise.demo.model.database.ColumnDetail;
import com.exercise.demo.model.database.IndexDetail;
import com.exercise.demo.model.database.TableDetail;

import java.sql.*;
import java.util.*;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/8/25 0:15
 */
public class DatabaseUtil {

    private static String DRIVER = "com.mysql.jdbc.Driver";

    private static String URL = JdbcConstant.URL;

    private static String USERNAME = JdbcConstant.USERNAME;

    private static String PASSWORD = JdbcConstant.PASSWORD;

    /**
     * 数据库操作
     */
    private static final String SQL = "SELECT * FROM ";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LogHelper.error("databaseError", "", "", "can not load jdbc driver", e);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LogHelper.error("databaseError", "", "", "get connection failure", e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LogHelper.error("databaseError", "", "", "close connection failure", e);
            }
        }
    }

    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            LogHelper.error("databaseError", "", "", "getTableNames failure", e);
        } finally {
            try {
                rs.close();
                closeConnection(conn);
            } catch (SQLException e) {
                LogHelper.error("databaseError", "", "", "close ResultSet failure", e);
            }
        }
        return tableNames;
    }

    /**
     * 获取数据库下的所有表名/表注释
     */
    public static List<TableDetail> getTableNamesNew() {
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement ps;
        String sql = "select\n" +
                "TABLE_NAME,\n" +
                "TABLE_COMMENT \n" +
                "from \n" +
                "INFORMATION_SCHEMA.Tables \n" +
                "where \n" +
                "table_schema = 'xdz_db' ";
        List<TableDetail> tableDetailList = new ArrayList<>();
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                TableDetail tableDetail = new TableDetail();
                tableDetail.setName(rs.getString("TABLE_NAME"));
                tableDetail.setComment(rs.getString("TABLE_COMMENT"));
                tableDetailList.add(tableDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LogHelper.error("databaseError", "", "", "getTableNamesNew close ResultSet and connection failure", e);
                }
            }
        }
        return tableDetailList;
    }

    /**
     * 获取表下所有列以及相关属性
     */
    public static List<ColumnDetail> getColunms(String tableName) {
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement ps;
        String sql = "show full columns from " + tableName;
        List<ColumnDetail> columnDetailList = new ArrayList<>();//列名注释集合
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                ColumnDetail columnDetail = new ColumnDetail();
                columnDetail.setField(rs.getString("Field"));
                columnDetail.setType(rs.getString("Type"));
                columnDetail.setCanEmpty(rs.getString("Null"));
                columnDetail.setKey(rs.getString("Key"));
                columnDetail.setDefaultValue(rs.getString("Default"));
                columnDetail.setComment(rs.getString("Comment"));
                columnDetailList.add(columnDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LogHelper.error("databaseError", "", "", "getColunms close ResultSet and connection failure", e);
                }
            }
        }
        return columnDetailList;
    }

    /**
     * 获取表下所有索引以及相关属性
     */
    public static List<IndexDetail> getIndexs(String tableName) {
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement ps;
        String sql = "show index from " + tableName;
        List<IndexDetail> indexDetailList = new ArrayList<>();//列名注释集合
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                IndexDetail indexDetail = new IndexDetail();
                indexDetail.setNonUnique(rs.getString("Non_unique"));
                indexDetail.setKeyName(rs.getString("Key_name"));
                indexDetail.setColumnName(rs.getString("Column_name"));
                indexDetailList.add(indexDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LogHelper.error("databaseError", "", "", "getIndexs close ResultSet and connection failure", e);
                }
            }
        }
        return indexDetailList;
    }

    /**
     * 获取表中所有字段名称
     *
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            LogHelper.error("databaseError", "", "", "getColumnNames failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {

                    LogHelper.error("databaseError", "", "", "getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return columnNames;
    }

    /**
     * 获取表中所有字段类型
     *
     * @param tableName
     * @return
     */
    public static List<String> getColumnTypes(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnTypes.add(rsmd.getColumnTypeName(i + 1));
            }
        } catch (SQLException e) {
            LogHelper.error("databaseError", "", "", "getColumnTypes failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LogHelper.error("databaseError", "", "", "getColumnTypes close pstem and connection failure", e);
                }
            }
        }
        return columnTypes;
    }

    /**
     * 获取表中字段的所有注释
     *
     * @param tableName
     * @return
     */
    public static List<String> getColumnComments(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        List<String> columnComments = new ArrayList<>();//列名注释集合
        ResultSet rs = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                columnComments.add(rs.getString("Comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LogHelper.error("databaseError", "", "", "getColumnComments close ResultSet and connection failure", e);
                }
            }
        }
        return columnComments;
    }


    /**
     * 获取表中字段的所有信息
     *
     * @param tableName
     * @return
     */
    public static LinkedHashMap<String,List<String>> getColumnAllInfo(String tableName) {
        LinkedHashMap<String, List<String>> result = new LinkedHashMap<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt;
        String tableSql = SQL + tableName;
        ResultSet rs = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                if (!result.containsKey(rs.getString("Field"))) {
                    List<String> columnInfo = new ArrayList<>();
                    columnInfo.add(rs.getString("Type"));
                    columnInfo.add(rs.getString("Collation"));
                    columnInfo.add(rs.getString("Null"));
                    columnInfo.add(rs.getString("Key"));
                    columnInfo.add(rs.getString("Default"));
                    columnInfo.add(rs.getString("Comment"));
                    result.put(rs.getString("Field"), columnInfo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LogHelper.error("databaseError", "", "",
                            "getColumnAllInfo close ResultSet and connection failure", e);
                }
            }
        }
        return result;
    }

    /**
     * 获取表中字段的所有索引
     *
     * @param tableName
     * @return
     */
    public static LinkedHashMap<String,List<String>> getIndexInfo(String tableName) {
        LinkedHashMap<String, List<String>> result = new LinkedHashMap<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt;
        String tableSql = SQL + tableName;
        ResultSet rs = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show index from " + tableName);
            while (rs.next()) {
                if (!result.containsKey(rs.getString("Key_name"))) {
                    List<String> columnInfo = new ArrayList<>();
                    columnInfo.add(rs.getString("Non_unique"));
                    columnInfo.add(rs.getString("Column_name"));
                    columnInfo.add(rs.getString("Collation"));
                    columnInfo.add(rs.getString("Index_type"));
                    result.put(rs.getString("Key_name"), columnInfo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LogHelper.error("databaseError", "", "",
                            "getIndexInfo close ResultSet and connection failure", e);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        List<String> tableNames = getTableNames();
        System.out.println("tableNames:" + tableNames);
        for (String tableName : tableNames) {
            System.out.println("ColumnNames:" + getColumnNames(tableName));
            System.out.println("ColumnTypes:" + getColumnTypes(tableName));
            System.out.println("ColumnComments:" + getColumnComments(tableName));
        }
    }
}
