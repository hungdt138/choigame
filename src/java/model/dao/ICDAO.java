/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.dao;

import common.Connector;
import model.bean.IC;
import model.bean.IC_MT;

import common.Logger;
import common.ORABaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Do Tien Hung
 */
public class ICDAO extends ORABaseDAO {
    private Logger log = null;

    public ICDAO() {
        log = new Logger(this.getClass().getName());
    }

    private static final String tbl_ICMO = "TIG_MGAME_IC_MO";
    private static final String tbl_ICMT = "TIG_MGAME_IC_MT";

    public List<IC> listIC() throws SQLException{
        List arrayList = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        IC ic = null;
        String sql = "";

        conn = Connector.getConnection();
        try {
            sql = "select * from " + tbl_ICMO;

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                ic = new IC();

                ic.setID(rs.getInt("id"));
                ic.setTxid(rs.getString("txid"));
                ic.setCusid(rs.getString("cusid"));
                ic.setCuspwd(rs.getString("cuspwd"));
                ic.setMobileno(rs.getString("mobileno"));
                ic.setMsg(rs.getString("msg"));

                arrayList.add(ic);
            }
        } catch (SQLException e) {
            log.error("Error ICDAO: " + sql + e.toString());
        } catch (Exception ex){
            log.error("Error ICDAO: " + ex.toString());
        } finally{
            Connector.closeConnections(conn, pstmt, rs);
        }

        return arrayList;
    }

    public IC getICBYID(int id){
       Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        IC ic = null;
        String sql = "";

        
        try {
            conn = Connector.getConnection();
            sql = "select * from " + tbl_ICMO + " where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                ic = new IC();

                ic.setID(rs.getInt("id"));
                ic.setTxid(rs.getString("txid"));
                ic.setCusid(rs.getString("cusid"));
                ic.setCuspwd(rs.getString("cuspwd"));
                ic.setMobileno(rs.getString("mobileno"));
                ic.setMsg(rs.getString("msg"));
            }

        } catch (SQLException e) {
            log.error("select error " + sql + " " + e.toString());
        } catch (Exception e) {
            log.error("exception error " + e.toString());
            System.out.println("sql:" + sql);
        } finally {
            try {
                Connector.closeConnections(conn, pstmt, rs);
            } catch (Exception sqlex) {
                sqlex.printStackTrace();
            }
        }
        return ic;

    }

    public int insertICMO(IC ic) throws SQLException{
        int rowEF = 0;
        String sql = "";
        Connection conn = null;
        PreparedStatement pstms = null;
        ResultSet rs = null;


        try {
            sql = "insert into "+tbl_ICMO+" (ID, txid, cusid, cuspwd, mobileno, msg,serviceid) "
                    + "values (?,?,?,?,?,?,?)";
            conn = Connector.getConnection();
            pstms = conn.prepareStatement(sql);

            pstms.setInt(1, ic.getID());
            pstms.setString(2, ic.getTxid());
            pstms.setString(3, ic.getCusid());
            pstms.setString(4, ic.getCuspwd());
            pstms.setString(5, ic.getMobileno());
            pstms.setString(6, ic.getMsg());
            pstms.setInt(7, ic.getServiceid());

            rowEF = pstms.executeUpdate();
        } catch (SQLException e) {
            log.error("Insert error: " + sql + e.toString());
        } catch (Exception ex){
            log.error("Error: " + ex.toString());
        } finally{
            Connector.closeConnections(conn, pstms, rs);
        }

        return rowEF;

    }

    public int getICMO_ID(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int moID = 0;
        String sql = "";

        try {
            sql = "select max(id) as id from " + tbl_ICMO;
            conn = Connector.getConnection();

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
                moID = rs.getInt("id");
            } else {
                moID = 0;
            }
        } catch (SQLException e) {
            log.error("select error " + sql + " " + e.toString());
        } catch (Exception e) {
            log.error("exception error " + e.toString());
            System.out.println("sql:" + sql);
        } finally {
            try {
                Connector.closeConnections(conn, pstmt, rs);
            } catch (Exception sqlex) {
                sqlex.printStackTrace();
            }
        }
        return moID;
    }

    public int inserIC_MT(IC_MT icmt){
        int rowEF = 0;
        String sql = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try {
            sql = "insert into "+tbl_ICMT+" (id, cusid, statuscode, statusmsg, contenturl, dnid, fullmsg) "
                    + " values(S_TIG_MGAME_IC_MT.nextval,?,?,?,?,?,?)";
            conn = Connector.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, icmt.getCusid());
            pstmt.setString(2, icmt.getStatuscode());
            pstmt.setString(3, icmt.getStatusmsg());
            pstmt.setString(4, icmt.getContenturl());
            pstmt.setString(5, icmt.getDnid());
            pstmt.setString(6, icmt.getFullmsg());

            rowEF = pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("select error " + sql + " " + e.toString());
        } catch (Exception e) {
            log.error("exception error " + e.toString());
            System.out.println("sql:" + sql);
        } finally {
            try {
                Connector.closeConnections(conn, pstmt, rs);
            } catch (Exception sqlex) {
                sqlex.printStackTrace();
            }
        }
        return rowEF;
    }

    
}
