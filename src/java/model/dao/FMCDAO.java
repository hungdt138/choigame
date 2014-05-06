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
public class FMCDAO extends common.ORABaseDAO {
    private Logger logger = null;

    public FMCDAO(){
        logger = new Logger(this.getClass().getName());
    }

    private static final String FMC_MO = "TIG_MGAME_FMC_MO";
    private static final String FMC_MT = "TIG_MGAME_FMC_MT";
    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    private String sql = "";
    /*
     * Tra ve tat ca gia tri MO
     */

    public List<FMC_MO> listFMC(){
        List arraylist = new ArrayList();
        FMC_MO fmc =null;

        
        try {
            conn = Connector.getConnection();
            sql = "select * from " +FMC_MO;
            pstm = conn.prepareCall(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                fmc = new FMC_MO();
                fmc.setId(rs.getInt("id"));
                fmc.setTxid(rs.getString("txid"));
                fmc.setCusid(rs.getString("cusid"));
                fmc.setCuspwd(rs.getString("cuspwd"));
                fmc.setMobileno(rs.getString("mobileno"));
                fmc.setMsg(rs.getString("msg"));

                arraylist.add(fmc);
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

    /*
     * Lay gia tri FMC_MO theo id
     */
    public FMC_MO getFMCByID(int id){
        
        FMC_MO fmc = null;
        try {
            conn = Connector.getConnection();
           sql = "select * from " +FMC_MO + "where ID = " + id;
           pstm = conn.prepareStatement(sql);
           rs = pstm.executeQuery();
           while(rs.next()){
               fmc = new FMC_MO();
               fmc.setId(rs.getInt("id"));
               fmc.setCusid(rs.getString("cusid"));
               fmc.setCuspwd(rs.getString("cuspwd"));
               fmc.setMobileno(rs.getString("mobileno"));
               fmc.setMsg(rs.getString("msg"));
               fmc.setTxid(rs.getString("txtd"));
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
        return fmc;
    }

    /*
     * insert du lieu vao bang FMC_MO
     */
    public int insertFMC(FMC_MO fmc){
        int rowFF = 0;
        
        try {
            conn = Connector.getConnection();
            sql = "insert into " +FMC_MO+ "(ID, txid, cusid, cuspwd, msg, mobileno,serviceid)"
                    + "values(S_TIG_MGAME_FMC_MO.nextval,?,?,?,?,?,?)";

            pstm = conn.prepareStatement(sql);
            pstm.setString(2, fmc.getCusid());
            pstm.setString(3, fmc.getCuspwd());
            pstm.setString(5, fmc.getMobileno());
            pstm.setString(4, fmc.getMsg());
            pstm.setString(1, fmc.getTxid());
            pstm.setInt(6, fmc.getServiceid());

            rowFF = pstm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Select err: " +sql + " " +e.toString());
        } catch (Exception ex){
            try {
                Connector.closeConnections(conn, pstm, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return rowFF;
    }

    /*
     * insert du lieu vao bang FMC_MT
     */

    public int insertFMC_MT(FMC_MT fmc_mt) throws SQLException{
        int rowFF = 0;
        conn = Connector.getConnection();
        try {
            sql = "insert into " + FMC_MT + "(id, cusid,statuscode, statusmsg, contenturl, dnid, fullmsg)"
                    + "values(S_TIG_MGAME_FMC_MT.nextval,?,?,?,?,?,?)";
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, fmc_mt.getCusid());
            pstm.setString(2, fmc_mt.getStatuscode());
            pstm.setString(3, fmc_mt.getStatusmsg());
            pstm.setString(4, fmc_mt.getContenturl());
            pstm.setString(5, fmc_mt.getDnid());
            pstm.setString(6, fmc_mt.getFullmsg());
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
