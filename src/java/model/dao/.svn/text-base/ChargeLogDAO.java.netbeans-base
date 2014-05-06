/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;


import common.Connector;
import common.Logger;
import common.ORADBconnect;
import java.sql.Statement;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.bean.ChargePDU;



/**
 *
 * @author Do Tien Hung
 */
public class ChargeLogDAO extends common.ORABaseDAO {
     private Logger log = null;
    //ORADBTask db = new ORADBTask();

    public ChargeLogDAO() {
        log = new Logger(this.getClass().getName());
        try {
            m_operator.OperatorConfig.loadProperties();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertRow(ChargePDU chargePDU) {

        BigDecimal id = null;
        java.sql.Connection conn = null;
        PreparedStatement preStmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        String strSQL = null;
        try {
            conn = Connector.getConnection();
            conn.setAutoCommit(false);
           strSQL =
                    "INSERT INTO WAP_CHARGE_LOG (ID, msisdn, charging, "
                    + "username, password, reqTime, providerid, "
                    + "serviceId, cmd, contents, requestId, MOBILE_OPERATOR, RETURN_VALUE,PCODE,CONTENTID) "
                    + "VALUES (S_WAP_CHARGE_LOG.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preStmt = conn.prepareStatement(strSQL);

            preStmt.setString(1, chargePDU.getMsisdn());
            String pr = chargePDU.getCharging();
            if (pr == null || pr.length() == 0) {
                pr = "0";
            }
            preStmt.setString(2, pr);
            preStmt.setString(3, chargePDU.getUsername());
            preStmt.setString(4, chargePDU.getPassword());
            preStmt.setTimestamp(5, chargePDU.getRegTime());
            preStmt.setString(6, chargePDU.getProviderid());
            preStmt.setString(7, chargePDU.getServiceid());
            preStmt.setString(8, chargePDU.getCmd());
            preStmt.setString(9, chargePDU.getContents());
            preStmt.setInt(10, chargePDU.getRequesid());
            preStmt.setString(11, m_operator.OperatorConfig.getMobileOperator(chargePDU.getMsisdn()));
            preStmt.setString(12, chargePDU.getReturn_values());;
            preStmt.setString(13, chargePDU.getPcode());
            preStmt.setInt(14, chargePDU.getContentid());

            if (preStmt.executeUpdate() == 1) {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("Select S_WAP_CHARGE_LOG.currval from dual");
                if (rs.next()) {
                    id = rs.getBigDecimal(1);
                    conn.commit();
                } else {
                    conn.rollback();
                }
            }
        } catch (SQLException ex) {
            log.error("insertRow: Error executing SQL " + strSQL + ">>>" + ex.toString());

        } catch (Exception e) {
            log.error("insertRow: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
               Connector.closeConnections(conn, preStmt, rs);
//                if (rs != null) {
//                    rs.close();
//                }
//                if (preStmt != null) {
//                    preStmt.close();
//                }
//
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (Exception sqlex) {
                sqlex.printStackTrace();
            }
        }

    }

    public void insertRow_Pre(ChargePDU chargePDU) {

        BigDecimal id = null;
        java.sql.Connection conn = null;
        PreparedStatement preStmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        String strSQL = null;
        try {
            conn = Connector.getConnection();
            conn.setAutoCommit(false);
            strSQL =
                    "INSERT INTO WAP_CHARGE_LOG_PRE (ID, msisdn, charging, "
                    + "username, password, reqTime, providerid, "
                    + "serviceId, cmd, contents, requestId, MOBILE_OPERATOR, RETURN_VALUE, PCODE,CONTENTID) "
                    + "VALUES (S_WAP_CHARGE_LOG.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preStmt = conn.prepareStatement(strSQL);


            preStmt.setString(1, chargePDU.getMsisdn());
            String pr = chargePDU.getCharging();
            if (pr == null || pr.length() == 0) {
                pr = "0";
            }
            preStmt.setString(2, pr);
            preStmt.setString(3, chargePDU.getUsername());
            preStmt.setString(4, chargePDU.getPassword());
            preStmt.setTimestamp(5, chargePDU.getRegTime());
            preStmt.setString(6, chargePDU.getProviderid());
            preStmt.setString(7, chargePDU.getServiceid());
            preStmt.setString(8, chargePDU.getCmd());
            preStmt.setString(9, chargePDU.getContents());
            preStmt.setInt(10, chargePDU.getRequesid());
            preStmt.setString(11, m_operator.OperatorConfig.getMobileOperator(chargePDU.getMsisdn()));
            preStmt.setString(12, chargePDU.getReturn_values());
            preStmt.setString(13, chargePDU.getPcode());
            preStmt.setInt(14, chargePDU.getContentid());
            if (preStmt.executeUpdate() == 1) {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("Select S_WAP_CHARGE_LOG.currval from dual");
                if (rs.next()) {
                    id = rs.getBigDecimal(1);
                    conn.commit();
                } else {
                    conn.rollback();
                }
            }
        } catch (SQLException ex) {
            log.error("insertRow: Error executing SQL " + strSQL + ">>>" + ex.toString());

        } catch (Exception e) {
            log.error("insertRow: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                Connector.closeConnections(conn, preStmt, rs);

            } catch (Exception sqlex) {
                sqlex.printStackTrace();
            }
        }

    }


    public String getContentsFrTables(String tableName, int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String rvl = "MS:"+id+";Ten:";

        String sql = "select * from "+tableName+" where id= ? ";
        try {
            conn = Connector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rvl = rvl + rs.getString("namenosign").trim();
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
        return rvl;
    }

   public String getContents(String cat, String id) {
        String rvl = "";

        //Truong hop mac dinh
        if (id == null || "".equals(id) || cat == null || "".equals(cat)){
            id = "2000";
            cat = "1";
        }
        String tn = getTable(cat);
        try{
            rvl = getContentsFrTable(tn, Integer.parseInt(id));
        }catch(NumberFormatException e){
            e.printStackTrace();
            rvl = "";
        }
        return rvl.replace("\n", " ").replace("|", " ");
    }

    public int checkMSISDNByTime(String msisdn,  String pcode) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int rval = 0;
        String sql = "";
//        if (cat.equals("7")) {
//            sql = "select * from ( "
//                    + "select msisdn, pcode, cmd, return_value, to_number(substr((sdate - udate),instr((sdate - udate),' ')+7,2)) seconds, "
//                    + "to_number(substr((sdate - udate),instr((sdate - udate),' ')+4,2)) minutes, "
//                    + "to_number(substr((sdate - udate),instr((sdate - udate),' ')+1,2)) hours, "
//                    + "trunc(to_number(substr((sdate-udate),1,instr(sdate-udate,' ')))) days "
//                    + "from (select msisdn, pcode, cmd, return_value, cast(sysdate as timestamp) sdate, cast(gen_date as timestamp) udate from wap_charge_log)) "
//                    + "where days <= 1 "
//                    + "and msisdn = '" + msisdn + "' "
//                    + "and cmd = 'DOWNLOAD' "
//                    + "and return_value = '0' and pcode = '" + pcode + "'";
//        } else {
            sql = "select * from ( "
                    + "select msisdn, pcode, cmd, RETURN_VALUE, to_number(substr((sdate - udate),instr((sdate - udate),' ')+7,2)) seconds, "
                    + "to_number(substr((sdate - udate),instr((sdate - udate),' ')+4,2)) minutes, "
                    + "to_number(substr((sdate - udate),instr((sdate - udate),' ')+1,2)) hours, "
                    + "trunc(to_number(substr((sdate-udate),1,instr(sdate-udate,' ')))) days "
                    + "from (select msisdn, pcode, cmd, RETURN_VALUE, cast(sysdate as timestamp) sdate, cast(gen_date as timestamp) udate from wap_charge_log)) "
                    + "where days=0 and hours=0 and minutes <= 60 "
                    + "and msisdn = '" + msisdn + "' "
                    + "and cmd = 'DOWNLOAD' "
                    + "and RETURN_VALUE = '0' and pcode = '" + pcode + "'";
//        }
        try {
            conn = Connector.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rval = 1;
            } else {
                rval = 0;
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
        return rval;
    }

    private String getTable(String msgType) {
        /*
         * 1 NHAC CHUONG - TIG_VT_RINGTONES_DATA
         * 2 ANH - TIG_VT_PICTURE_DATA
         * 3 GAME - TIG_VT_GAME_DATA
         * 4 UNG UNG - TIG_VT_APP_DATA
         * 5 THEME - TIG_VT_THEME_DATA
         * 6 CLIP - TIG_VT_CLIP_DATA
         *
         */

        if (msgType.equals("1")) {
            return "TIG_VT_GAME_DATA";
        } else if (msgType.equals("2")) {
            return "TIG_GAME_PACKAGE";
        } else {
            return "TIG_VT_GAME_DATA";//Mac dinh, game
        }


    }

        public String getContentsFrTable(String tableName, int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String rvl = "";
        String sql = "";
        if("TIG_GAME_PACKAGE".equals(tableName)){
            rvl = "MS:"+id+";TenGoi:";
        sql = "select * from "+tableName+" where pid= ? ";
        }else{
            rvl="MS:"+id+";Ten:";
        sql = "select * from "+tableName+" where id= ? ";
         }
        try {
            conn = Connector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rvl = rvl + rs.getString("namenosign").trim();
            }
        } catch (SQLException e) {
            log.error("select error " + sql + " " + e.toString());
        } catch (Exception e) {
            log.error("exception error " + e.toString());
            System.out.println("sql:" + sql);
        } finally {
            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//
//                if (conn != null) {
//                    conn.close();
//                }
               Connector.closeConnections(conn, pstmt, rs);
            } catch (Exception sqlex) {
                sqlex.printStackTrace();
            }
        }
        return rvl;
    }

    public static void main(String[] args) {
        ChargeLogDAO log = new ChargeLogDAO();
        System.out.println("test\ntest 2".replace("\n", " "));
        String ct = log.getContents("2","2");
        System.out.println(ct);


        try {
            //log.insertRow(ch);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



   
}
