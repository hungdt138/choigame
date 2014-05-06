/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

/**
 *
 * @author Do Tien Hung
 */

import common.Connector;
import common.Logger;
import common.ORABaseDAO;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.bean.VTC_MO;
import model.bean.VTC_MT;

public class VTCDao extends ORABaseDAO {

    private Logger log = null;

    public VTCDao() {
        log = new Logger(this.getClass().getName());

    }
    private static final String tblMGAME = "TIG_MGAME_VTC_MO";
    private static final String tblMGAME_MT = "TIG_MGAME_VTC_MT";//tig_mgame_VTC_MT

    public String getReqID() {
        String reqId = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";

        
        try {
            conn = Connector.getConnection();
            sql = "SELECT S_TIG_MGAME_VTC_REQID.NEXTVAL as reqId FROM dual";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                reqId = String.valueOf(rs.getInt("reqId"));
                break;
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
        return reqId;
    }

    /**
     * get all Top
     * @return
     */
    public List<VTC_MO> listVTC() {
        List arrayList = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        VTC_MO vtc = null;
        String sql = "";

       
        try {
            conn = Connector.getConnection();
            sql = "select * from " + tblMGAME;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                vtc = new VTC_MO();
                vtc.setId(rs.getInt("id"));
                vtc.setTxid(rs.getString("txid"));
                vtc.setCusid(rs.getString("cusid"));
                vtc.setCuspwd(rs.getString("cuspwd"));
                vtc.setMobileno(rs.getString("mobileno"));
                vtc.setMsg(rs.getString("msg"));
                arrayList.add(vtc);
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
        return arrayList;
    }

    public VTC_MO getVTCByID(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        VTC_MO vtc = null;
        String sql = "";

        
        try {
            conn = Connector.getConnection();
            sql = "select * from " + tblMGAME + " where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                vtc = new VTC_MO();
                vtc.setId(rs.getInt("id"));
                vtc.setTxid(rs.getString("txid"));
                vtc.setCusid(rs.getString("cusid"));
                vtc.setCuspwd(rs.getString("cuspwd"));
                vtc.setMobileno(rs.getString("mobileno"));
                vtc.setMsg(rs.getString("msg"));
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
        return vtc;
    }

    public int insertVTC_MO(VTC_MO vtc) {
        int rowEF = 0;
        String sql = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        

        try {
            conn = Connector.getConnection();
            sql = "insert into " + tblMGAME + " (ID, txid, cusid, cuspwd, mobileno, msg, serviceid) "
                    + "values (S_TIG_MGAME_VTC_MO.nextval,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, vtc.getTxid());
            pstmt.setString(2, vtc.getCusid());
            pstmt.setString(3, vtc.getCuspwd());
            pstmt.setString(4, vtc.getMobileno());
            pstmt.setString(5, vtc.getMsg());
            pstmt.setInt(6, vtc.getServiceid());

            rowEF = pstmt.executeUpdate();

        } catch (SQLException e) {
            log.error("Error Insert VTC_MO " + sql + " " + e.toString());
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

    public int insertVTC_MT(VTC_MT vtc_mt) {
        int rowEF = 0;
        String sql = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        

        try {
            conn = Connector.getConnection();
            sql = "insert into " + tblMGAME_MT + " (ID, cusid, statuscode, statusmsg, contenturl, dnid, fullmsg) "
                    + "values (S_TIG_MGAME_VTC_MT.nextval,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, vtc_mt.getCusid());
            pstmt.setString(2, vtc_mt.getStatuscode());
            pstmt.setString(3, vtc_mt.getStatusmsg());
            pstmt.setString(4, vtc_mt.getContenturl());
            pstmt.setString(5, vtc_mt.getDnid());
            pstmt.setString(6, vtc_mt.getFullmsg());

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

    public static void main(String args[]) {
        String value = "a c c";
        value = value.toUpperCase();
        String tt = "%' or upper(namenosign) like '%";
        value = value + tt + value.replaceAll(" ", tt);
        value = "(upper(namenosign) like '%" + value + "%')";
        System.out.println(value);
    }
}
