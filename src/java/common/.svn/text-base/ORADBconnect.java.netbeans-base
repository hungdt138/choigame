/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.*;
import java.sql.*;
/**
 *
 * @author Do Tien Hung
 * Connection pooling
 * Connect to database Oracle
 */
public class ORADBconnect {
    private static Logger logger = new Logger("ORADBconnect");
    private static LinkedList pool = new LinkedList();
    public final static int MAX_CONNECTION = 10;
    public final static int INIT_CONNECTION = 2;
    public static String db_driver_Service = Config.getString("jdbc.driverClassName");
    public static String db_url_Service = Config.getString("jdbc.url");
    public static String db_user_Service = Config.getString("jdbc.username");
    public static String db_pass_Service = Config.getString("jdbc.password");
    //tao connection
    static { 
        built(INIT_CONNECTION);
    }
    
    public static void built(int number){
        try {
            System.out.println("Loading DB...........");
        } catch (Exception e) { 
            System.out.println("Khong ket noi duoc toi DB");
            return;
        }
        
        logger.log("Create " + number + "connections..");
        Connection conn = null;
        for(int i= 0;i< INIT_CONNECTION;i++){
            try {
                conn = makeDBConnection();
            }catch(SQLException e) {
                logger.log("Error: " +e.getMessage());
                logger.log("Khong ket noi duoc toi DB");
                System.exit(1);
            }
            if(conn != null){
                pool.addLast(conn);
            }
        }
        
    }
    
    public static Connection getConnection(){
        Connection conn = null;
        try {
            while(conn == null || conn.isClosed()){
                try {
                    synchronized (pool){
                        if(pool.size() > 0){
                            conn = (Connection) pool.removeFirst();
                        }
                    }
                    
                    if(conn == null || conn.isClosed()){
                        conn = makeDBConnection();
                    }
                    
                    if(conn != null){
                       conn.setAutoCommit(true);
                    }else{
                        Thread.sleep(30000);
                    }
                } catch (Exception e) {
                    logger.error("getConnection: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void putConnection(Connection conn){
        try {
            if(conn == null || conn.isClosed()){
                logger.log("putConnection: conn is null or closed" + conn);
                return;
            }
            if(pool.size() >= MAX_CONNECTION){
                conn.close();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        synchronized (pool){
            pool.addLast(conn);
            pool.notify();
        }
    }
    
    
    /*
     *Remove and close connection 
     * Tra lai connection khi da su dung xong
     */
    public static void release(){
        logger.log("Closing connection in pool");
        synchronized (pool){
            for(Iterator it = pool.iterator(); it.hasNext();){
                Connection conn = (Connection) it.next();
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.error("release: Cannot close connection !");
                }
            }
            pool.clear();
        }
        logger.log("OK");
    }
    
    public static int size(){
        synchronized (pool){
            return pool.size();
        }
    }
    
    public static boolean isEmpty(){
        synchronized (pool){
            return pool.isEmpty();
        }
    }
    
    public void finalize(){
        release();
    }
    /**
     * Make connection to Oracle Database
     */
    public static Connection makeDBConnection() throws SQLException {
        Connection conn = null;
        
        try {
            Class.forName(db_driver_Service);
            conn = DriverManager.getConnection(db_url_Service, db_user_Service, db_pass_Service);
        } catch (ClassNotFoundException e) {
            System.out.println("Khong ket noi duoc voi DB" + e.toString());
        }
        return conn;
    }
    
    //main test
    
    public static void main(String[] args) {
        ORADBconnect ora = new ORADBconnect();
        try {
            
            System.out.println(ora.getConnection());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
