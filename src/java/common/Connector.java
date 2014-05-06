/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import oracle.jdbc.pool.OracleDataSource;

/**
 * This class use for creating or closing connections such as
 * Connection, PreparedStatement, ResultSet ...
 * @author Nguyen Dinh Doan
 */
public class Connector {

    private static OracleDataSource ods;

    static {
        try {
            setDataSourceInfo();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /** 
     * Get connection.
     * @return object to connect to database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
//        if (ods == null) {
//            return ORADBconnect.getConnection();
//        }
       return ORADBconnect.getConnection();
       
    }

    /**
     * This method will check each object whether is null, then close it.
     * @param conn The Connection object
     * @param preStmt The PreparedStatement object
     * @param rs The ResultSet object
     * @throws SQLException if database access occurs
     */
     public static void closeConnections(Connection conn, PreparedStatement preStmt) {
        common.ORADBconnect.putConnection(conn);
        try {
            if (preStmt != null) {
                preStmt.close();
            }
        } catch (SQLException e) {}
    }
    public static void closeConnections(Connection conn, PreparedStatement preStmt, ResultSet rs)
            throws SQLException {
        closeConnections(conn, preStmt);
        if (rs != null) {
            rs.close();
        }
    }
    
    public static void closeConnections(Connection conn, Statement st, ResultSet rs) throws SQLException{
        closeConnections(conn, null, rs);
        if(st != null){
            st.close();
        }
    }
    

    /**
     * Set parameters for DataSource object
     * @throws SQLException 
     */
    public static void setDataSourceInfo() throws SQLException {
        ods = new OracleDataSource();
        ods.setURL(Config.getString("jdbc.url"));
        ods.setUser(Config.getString("jdbc.username"));
        ods.setPassword(Config.getString("jdbc.password"));
        ods.setConnectionCachingEnabled(true);

        Properties cacheProps = new Properties();
        cacheProps.setProperty("MinLimit", "1");
        cacheProps.setProperty("MaxLimit", "10");
        cacheProps.setProperty("InitialLimit", "2");
        cacheProps.setProperty("ConnectionWaitTimeout", "5");
        cacheProps.setProperty("ValidateConnection", "true");
        ods.setConnectionCacheProperties(cacheProps);
    }

    public static void main(String[] args) throws Exception {
        System.gc();
        Connection connection = getConnection();
        connection.close();

        long start = System.currentTimeMillis();
        connection = getConnection();
        connection.close();
        connection = getConnection();
        connection.close();
        connection = getConnection();
        connection.close();

        System.out.println("Time for 4 connections = " + (System.currentTimeMillis() - start));
        System.out.println("OK - Null? " + (connection == null));
    }
}
