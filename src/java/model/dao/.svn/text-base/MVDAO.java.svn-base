/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import common.Connector;
import common.ORABaseDAO;
import common.Logger;
import common.ORADBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.MV_MO;
import model.bean.MV_MT;

/**
 *
 * @author Do Tien Hung
 */
public class MVDAO extends ORABaseDAO {
      private Logger log = null;

    public MVDAO() {
        log = new Logger(this.getClass().getName());

    }
    private static final String tblMGAME = "TIG_MGAME_MV_MO";
    private static final String tblMGAME_MT = "TIG_MGAME_MV_MT";//tig_mgame_MV_mt

    /**
     * @return Lay ra mot danh sach trong bang MV_MO
     * @param no param
     * @author Do Tien Hung
     * @since tig_vms_v2
     */
    public List<MV_MO> listMV() {
        List arrayList = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MV_MO mv = null;
        String sql = "";

        
        try {
            conn = Connector.getConnection();
            sql = "select * from " + tblMGAME;

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                mv = new MV_MO();

                mv.setId(rs.getInt("id"));
                mv.setTxid(rs.getString("txid"));
                mv.setCusid(rs.getString("cusid"));
                mv.setCuspwd(rs.getString("cuspwd"));
                mv.setMobileno(rs.getString("mobileno"));
                mv.setMsg(rs.getString("msg"));

                arrayList.add(mv);
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

    /**
     *
     * @param id, truyn id cua MV
     * @return Gia tri trong MV_MO theo ID
     */
    public MV_MO getMVByID(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MV_MO mv = null;
        String sql = "";

        
        try {
            conn = Connector.getConnection();
            sql = "select * from " + tblMGAME + " where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                mv = new MV_MO();

                mv.setId(rs.getInt("id"));
                mv.setTxid(rs.getString("txid"));
                mv.setCusid(rs.getString("cusid"));
                mv.setCuspwd(rs.getString("cuspwd"));
                mv.setMobileno(rs.getString("mobileno"));
                mv.setMsg(rs.getString("msg"));
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
        return mv;
    }

    /**
     *
     * @param mv
     * @return insert to MV_MO
     */
    public int insertMV_MO(MV_MO mv) {
        int rowEF = 0;
        String sql = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
       

        try {
             conn = Connector.getConnection();
            sql = "insert into " + tblMGAME + " (ID, txid, cusid, cuspwd, mobileno, msg, serviceid) "
                    + "values (?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, mv.getId());
            pstmt.setString(2, mv.getTxid());
            pstmt.setString(3, mv.getCusid());
            pstmt.setString(4, mv.getCuspwd());
            pstmt.setString(5, mv.getMobileno());
            pstmt.setString(6, mv.getMsg());
            pstmt.setInt(7, mv.getServiceid());

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

    public int getMV_MO_ID() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int moID = 0;
        String sql = "";

      
        try {
              conn = Connector.getConnection();
            sql = "select max(id) as id from " + tblMGAME;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
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
                releaseConnection(conn, pstmt, rs);
            } catch (Exception sqlex) {
                sqlex.printStackTrace();
            }
        }
        return moID;
    }

    public int insertMV_MT(MV_MT mv_mt) {
        int rowEF = 0;
        String sql = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
       

        try {
             conn = Connector.getConnection();
            sql = "insert into " + tblMGAME_MT + " (ID, cusid, statuscode, statusmsg, contenturl, dnid, fullmsg) "
                    + "values (S_TIG_MGAME_MV_MT.nextval,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, mv_mt.getCusid());
            pstmt.setString(2, mv_mt.getStatuscode());
            pstmt.setString(3, mv_mt.getStatusmsg());
            pstmt.setString(4, mv_mt.getContenturl());
            pstmt.setString(5, mv_mt.getDnid());
            pstmt.setString(6, mv_mt.getFullmsg());

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
