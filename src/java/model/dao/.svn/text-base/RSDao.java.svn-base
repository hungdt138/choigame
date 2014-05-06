package model.dao;

import common.*;
import common.wsclient.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import model.bean.*;

public class RSDao extends ORABaseDAO {

    private Logger log = null;

    public RSDao() {
        log = new Logger(this.getClass().getName());

    }
    private static final String tblMGAME_MT = "TIG_MGAME_RS_MT";
    private static final String tblMGAME = "TIG_MGAME_RS_MO";

    public int insertRS_MO(RS mo) {
        int rowEF = 0;
        String sql = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = ORADBconnect.getConnection();

        try {
            sql = "insert into " + tblMGAME + " (ID, txid, cusid, cuspwd, mobileno, msg,serviceid) "
                    + "values (S_TIG_MGAME_RS_MO.nextval,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, mo.getId());
            pstmt.setString(2, mo.getTxid());
            pstmt.setString(3, mo.getCusid());
            pstmt.setString(4, mo.getCuspwd());
            pstmt.setString(5, mo.getMobileno());
            pstmt.setString(6, mo.getMsg());

            rowEF = pstmt.executeUpdate();

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
        return rowEF;
    }
    
    public int insertRS_MT(RS_MT mt) {
        int rowEF = 0;
        String sql = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = ORADBconnect.getConnection();

        try {
            sql = "insert into " + tblMGAME_MT + " (ID, cusid, statuscode, statusmsg, contenturl, dnid, fullmsg) "
                    + "values (S_TIG_MGAME_RS_MT.nextval,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, mt.getCusid());
            pstmt.setString(2, mt.getStatuscode());
            pstmt.setString(3, mt.getStatusmsg());
            pstmt.setString(4, mt.getContenturl());
            pstmt.setString(5, mt.getDnid());
            pstmt.setString(6, mt.getFullmsg());

            rowEF = pstmt.executeUpdate();

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
        return rowEF;
    }
    
    public String getRSURL(String msisdn, String gameid) {
        RSDao dao = new RSDao();
        
        String strURL = common.Config.getString("rsUrl");
        String strServiceNumber = "8298";
        java.sql.Timestamp ts = new Timestamp(System.currentTimeMillis());
        java.lang.String reqTime = Stringutils.getYYYYMMDDHHMMSSString(ts);
        
        RS mo = new RS();
        mo.setTxid(reqTime);
        mo.setMobileno(msisdn);
        mo.setMsg(gameid);
        dao.insertRS_MO(mo);
        
        gameid = gameid.substring(3);
        String strRequestCode = gameid.replaceAll("_", " ");
        String[] arr = strRequestCode.split(" ");

        if (arr.length < 2) {
            return null;
        }
        String strResponseMessage = null;
        String strPreCode = arr[0];
        try {
            SmsGatewayBindingStub receiver = (SmsGatewayBindingStub) (new SmsGatewayLocator()).getsmsGatewayPort(new URL(strURL));
            strResponseMessage = receiver.smsGateway(msisdn, strServiceNumber, strPreCode, strRequestCode, reqTime);
            RS_MT mt = new RS_MT();
            mt.setCusid("NOTSET");
            mt.setStatuscode("NOTSET");
            mt.setStatusmsg(strRequestCode);
            if(strResponseMessage!= null && !"".equals(strResponseMessage))
                mt.setContenturl(strResponseMessage);
            mt.setDnid("NOTSET");
            mt.setFullmsg(strResponseMessage);
            dao.insertRS_MT(mt);
        } catch (Exception ex) {
            ex.printStackTrace();
            strResponseMessage = null;
        } finally {
            return strResponseMessage;
        }
    }
}
