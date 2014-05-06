/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.dao;

import common.Connector;
import common.Logger;
import common.ORABaseDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.LT_MO;
import model.bean.LT_MT;

/**
 *
 * @author NONAME
 */
public class LTDAO extends ORABaseDAO {
     private Logger logger = null;

    public LTDAO() {
        logger = new Logger(this.getClass().getName());
    }

    private static final String LT_MO = "TIG_MGAME_LT_MO";
    private static final String LT_MT = "TIG_MGAME_LT_MT";
    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    private String sql = "";

     public List<LT_MO> listLT(){
        List arraylist = new ArrayList();
        LT_MO lt =null;


        try {
            conn = Connector.getConnection();
            sql = "select * from " +LT_MO;
            pstm = conn.prepareCall(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                lt = new LT_MO();
                lt.setId(rs.getInt("id"));
                lt.setTxid(rs.getString("txid"));
                lt.setCusid(rs.getString("cusid"));
                lt.setCuspwd(rs.getString("cuspwd"));
                lt.setMobileno(rs.getString("mobileno"));
                lt.setMsg(rs.getString("msg"));

                arraylist.add(lt);
            }
        } catch (SQLException e) {
            logger.error("select err: " +sql + "" +e.toString());

        }catch (Exception e){
            try {
                Connector.closeConnections(conn, pstm, rs);

            } catch (Exception sqlex) {
                sqlex.printStackTrace();
            }
        }

        return arraylist;
    }

     public LT_MO getLTByID(int id){
        LT_MO lt = null;
         try {
             conn = Connector.getConnection();
             sql = "SELECT * FROM "+LT_MO+" WHERE ID = " +id;
             pstm = conn.prepareStatement(sql);
             rs = pstm.executeQuery();
             if(rs.next()){
                lt = new LT_MO();
                lt.setId(rs.getInt("id"));
                lt.setTxid(rs.getString("txid"));
                lt.setCusid(rs.getString("cusid"));
                lt.setCuspwd(rs.getString("cuspwd"));
                lt.setMobileno(rs.getString("mobileno"));
                lt.setMsg(rs.getString("msg"));

             }
         } catch (SQLException e) {
            logger.error("select err: " +sql + e.toString());
        }catch (Exception ex){
            try {
                Connector.closeConnections(conn, pstm, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return lt;
     }

     public int insertLT(LT_MO lt){
        int rowEF = 0;
         try {
            conn = Connector.getConnection();
            sql = "insert into " +LT_MO+ "(ID, txid, cusid, cuspwd, msg, mobileno,serviceid)"
                    + "values(S_TIG_MGAME_LT_MO.nextval,?,?,?,?,?,?)";

            pstm = conn.prepareStatement(sql);
            pstm.setString(2, lt.getCusid());
            pstm.setString(3, lt.getCuspwd());
            pstm.setString(5, lt.getMobileno());
            pstm.setString(4, lt.getMsg());
            pstm.setString(1, lt.getTxid());
            pstm.setInt(6, lt.getServiceid());

            rowEF = pstm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Select err: " +sql + " " +e.toString());
        } catch (Exception ex){
            try {
                Connector.closeConnections(conn, pstm, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return rowEF;
     }

       public int insertLT_MT(LT_MT lt_mt) throws SQLException{
        int rowFF = 0;
        conn = Connector.getConnection();
        try {
            sql = "insert into " + LT_MT + "(id, cusid,statuscode, statusmsg, contenturl, dnid, fullmsg)"
                    + "values(S_TIG_MGAME_LT_MT.nextval,?,?,?,?,?,?)";

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, lt_mt.getCusid());
            pstm.setString(2, lt_mt.getStatuscode());
            pstm.setString(3, lt_mt.getStatusmsg());
            pstm.setString(4, lt_mt.getContenturl());
            pstm.setString(5, lt_mt.getDnid());
            pstm.setString(6, lt_mt.getFullmsg());
            rowFF = pstm.executeUpdate();
        } catch (SQLException e) {
            logger.error("select err: " + sql + " " + e.toString());
        } catch (Exception ex){
            try {
                Connector.closeConnections(conn, pstm, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return rowFF;
    }

}
