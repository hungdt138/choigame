/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;
import common.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.bean.*;

/**
 *
 * @author Do Tien Hung
 */
public class VNMDAO  {
    private Logger logger = null;
    public VNMDAO(){
        logger = new Logger(this.getClass().getName());
    }
    
    private static final String VNM_MO = "TIG_MGAME_VNM_MO";
    private static final String VNM_MT = "TIG_MGAME_VNM_MT";
    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    private String sql = "";
    
    //get all
    public List<VNM_MO> listVNM(){
        List arraylist = new ArrayList();
        VNM_MO vnm = null;
        
        
        
        try {
            conn = Connector.getConnection();
            sql = "select * from " +VNM_MO;
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                vnm = new VNM_MO();
                vnm.setId(rs.getInt("id"));
                vnm.setCusid(rs.getString("cusid"));
                vnm.setCuspwd(rs.getString("cuspwd"));
                vnm.setMobileno(rs.getString("mobileno"));
                vnm.setMsg(rs.getString("msg"));
                vnm.setTxid(rs.getString("txid"));
            }
        } catch (SQLException e) {
            logger.error("select error: " +sql+ "" + e.toString());
        } catch (Exception ex){
            logger.error("Exeption error: " + ex.toString());
            System.out.println("sql: " +sql);
        } finally {
            try {
                Connector.closeConnections(conn, pstm, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return arraylist;
    }
    
    //get by id
    public VNM_MO getVNMMOByID(int id){
        VNM_MO vnm = null;
        
        try {
            conn = Connector.getConnection();
            sql = "select * from " +VNM_MO+ "where id= ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            
            rs = pstm.executeQuery();
            
            if(rs.next()){
                vnm = new VNM_MO();
                
                vnm.setId(rs.getInt("id"));
                vnm.setCusid(rs.getString("cusid"));
                vnm.setCuspwd(rs.getString("cuspwd"));
                vnm.setMobileno(rs.getString("mobileno"));
                vnm.setMsg(rs.getString("msg"));
                vnm.setTxid(rs.getString("txid"));
                
            }
            
        } catch (SQLException e) {
            logger.error("select error: " + sql + "" + e.toString());
        } catch (Exception ex) {
            logger.error("exeption error: " + ex.toString());
            System.out.println("Sql: " +sql);
        } finally {
            try {
                Connector.closeConnections(conn, pstm, rs);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        return vnm;
    }
    
    //insert VNM_MO
    
    public int insertVNM_MO(VNM_MO vnm){
        int rowEF = 0;
        
        try {
            conn = Connector.getConnection();
            sql = "insert into "+VNM_MO+ " (ID, txid, cusid, cuspwd, mobileno, msg, serviceid)"
                    + "values(?,?,?,?,?,?,?)";
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, vnm.getId());
            pstm.setString(2, vnm.getCusid());
            pstm.setString(3, vnm.getCuspwd());
            pstm.setString(4, vnm.getMobileno());
            pstm.setString(5, vnm.getMsg());
            pstm.setString(6, vnm.getTxid());
            pstm.setInt(7, vnm.getServiceid());
            
            rowEF = pstm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error" + sql + e.toString());
        } catch (Exception ex ) {
            logger.error("Exeption Error " +ex.toString());
            System.out.println("Error:" + sql);
        } finally {
            try {
               Connector.closeConnections(conn, pstm, rs);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return rowEF;
    }
    
    public int getVNMMO_ID(){
       int MoID = 0;
       
        try {
            conn = Connector.getConnection();
            sql = "select max(id) as id from " + VNM_MO;
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if(rs.next()){
                MoID = rs.getInt("id");
            } else {
                MoID = 0;
            }
        } catch (SQLException e) {
            logger.error("select error " + sql + " " + e.toString());
        } catch (Exception e) {
            logger.error("exception error " + e.toString());
            System.out.println("sql:" + sql);
        } finally {
            try {
              Connector.closeConnections(conn, pstm, rs);
            } catch (Exception sqlex) {
                sqlex.printStackTrace();
            }
        }
        
        return MoID;
    }
    
    
    public int insertVNM_MT(VNM_MT vnm_mt){
        int rowEF = 0;
        
        try {
            conn = Connector.getConnection();
            sql = "insert into " +VNM_MT+ "(ID, cusid, statuscode, statusmsg, contenturl, dnid, fullmsg)"
                    + "values (S_TIG_MGAME_VNM_MT.nextval,?,?,?,?,?,?)";
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, vnm_mt.getCusid());
            pstm.setString(2, vnm_mt.getStatuscode());
            pstm.setString(3, vnm_mt.getStatusmsg());
            pstm.setString(4, vnm_mt.getContenturl());
            pstm.setString(5, vnm_mt.getDnid());
            pstm.setString(6, vnm_mt.getFullmsg());
            
            rowEF = pstm.executeUpdate();
        }  catch (SQLException e) {
            logger.error("select error " + sql + " " + e.toString());
        } catch (Exception e) {
            logger.error("exception error " + e.toString());
            System.out.println("sql:" + sql);
        } finally {
            try {
                Connector.closeConnections(conn, pstm, rs);
            } catch (Exception sqlex) {
                sqlex.printStackTrace();
            }
        }
        return rowEF;
    }
    
    public static void main(String args[]) {
        
        
    }
    
}
