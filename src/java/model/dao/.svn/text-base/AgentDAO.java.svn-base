/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.viettel.xsd.ResultResponse;
import common.Connector;
import common.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import model.bean.Agent;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author Nguyen Dinh Doan
 */
public class AgentDAO {

    private Connection conn;
    private PreparedStatement preStmt;
    private ResultSet rs;
    private final String tblManufact = "TIG_MOBILE_MANUFACT";
    private final String tblMobileType = "TIG_MOBILE_TYPE";
    private final String tblGameData = "TIG_VT_GAME_DATA";
    public static final String USER_LOG_TABLE = "TIG_VT_USER_LOG";
    //Bang cua LifeInfo
    private final String tblDevice = "DEVICE";
    private final String tblVendor = "VENDOR";
    private Logger log;

    public AgentDAO() {
        log = new Logger(this.getClass().getName());
    }

    /**
     * Get mobile number from user's request. Currently, this project can only 
     * get mobile number from VIETTEL's customers
     * @param header Header from user's mobile 
     * @return mobile name and mobile model
     * @throws SQLException when database access error occurs
     */
    public String[] getMobileManufactAndModel(String header) throws SQLException {
        String[] manufactAndModel = {"unknown", "unknown"};
        //String[] manufactAndModel = {"NOKIA", "C3"};
        String sql = "";
        try {
            conn = Connector.getConnection();
            sql = "select upper(m.manufact), upper(t.name), upper(t.os)"
                    + " from " + tblMobileType + " t, " + tblManufact + " m"
                    + " where t.catid = m.id";

            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();

            String manufact;
            String model;
            String os;
            while (rs.next()) {
                manufact = rs.getString(1);
                model = rs.getString(2);
                os = rs.getString(3);
                if (header.contains("ANDROID")) {
                    if (header.contains(model) && header.contains(os.toUpperCase())) {
                        manufactAndModel[0] = manufact;
                        manufactAndModel[1] = model;
                        break;
                    }
                } else if (header.contains(manufact) && header.contains(model)) {
                    manufactAndModel[0] = manufact;
                    if (model.equalsIgnoreCase("X2-01")) {
                        manufactAndModel[1] = "C3";
                    } else {
                        manufactAndModel[1] = model;
                    }
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("[Choigame] Error getMobileManufactAndModel" + e.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }

        return manufactAndModel;
    }

    /**
     * Get mobile number from user's request.
     * @param request
     * @return mobile number
     */
    public String getMobileNumber(HttpServletRequest request) {
        String msisdn = request.getHeader("msisdn");
        String ip = "";
        String header = request.getHeader("user-agent");

        if (header.contains("Opera")) {
            ip = request.getHeader("X-FORWARDED-FOR");
        }
        if ("".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String furl = request.getRequestURL().toString().toLowerCase();
        call_ws_viettel.Main ws_vt = new call_ws_viettel.Main();
        if (msisdn == null || msisdn.length() == 0) {
            msisdn = request.getHeader("X-WAP-MSISDN");
        }
        if (msisdn == null || msisdn.length() == 0) {
            if (furl.indexOf("v.mobix") != -1 || furl.indexOf("viettelmobile") != -1) {
              //  String ip = request.getRemoteAddr();
                ResultResponse rp = ws_vt.getMSISDN("datacp", "datacp%$#12", ip);
                if (rp == null) {
                    return "anonymous";
                }

                if (rp.getCode() == 0) {
                    msisdn = rp.getDesc();
                } else {
                    msisdn = "anonymous";
                }
            }
        }
        return msisdn;
    }

    public Boolean checkMobileByGameId(int id, Agent agent) throws SQLException {
        int count = 0;
        String sql = "select count(*) from TIGVIETTEL." + tblGameData + " d, liveinfowap.device de, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v  where "
                + "d.id = ds.content_instance_id and de.device_id = ds.device_id and "
                + "upper(de.code) = '" + agent.getMobileModel() + "' and upper(v.code) = '" + agent.getManufactor() + "' and "
                + "id = " + id;
        try {
            conn = Connector.getConnection();
            Statement st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
                if (count != 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            log.error("error: " + sql + "" + e.toString());
        } catch (Exception ex) {
            log.error("error" + ex.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return false;
    }

    public int countGame(Agent agent) throws SQLException {
        int count = 0;
        String sql = "";
        try {
            sql = "select count(g.id) from TIGVIETTEL." + tblGameData + " g, liveinfowap.device d, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v"
                    + " where g.id = ds.content_instance_id and d.device_id = ds.device_id and v.vendor_id = d.vendor_id"
                    + " and upper(d.code) = '" + agent.getMobileModel() + "' and upper(v.code) = '" + agent.getManufactor() + "'";

            conn = Connector.getConnection();
            Statement st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            log.error("Error get count mobile: " + sql + "" + e.toString());
        } catch (Exception ex) {
            log.error("error" + ex.toString());
        } finally {
            conn.close();
            rs.close();
        }

        return count;
    }

    public void addUserInfoLog(String phonenumber, String Manufact, String Model, String user_agent) {
        Statement stmt = null;
        String sql = null;
        try {
            conn = Connector.getConnection();
            conn.setAutoCommit(false);
            sql =
                    "INSERT INTO " + USER_LOG_TABLE + " (ID, title, phonenumber, manufacture, "
                    + "phonename, user_agent) "
                    + "VALUES (TIG_VT_USER_LOG_SEQ.nextval, ?, ?, ?, ?, ?)";
            preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, "CHOIGAME");
            preStmt.setString(2, phonenumber);
            preStmt.setString(3, Manufact);
            preStmt.setString(4, Model);
            preStmt.setString(5, user_agent);

            if (preStmt.executeUpdate() == 1) {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("Select TIG_VT_USER_LOG_SEQ.currval from dual");
                if (rs.next()) {
                    //id = rs.getInt(1);
                    conn.commit();
                } else {
                    conn.rollback();
                }
            }

            System.out.println("[Choigame] Insert log truy cap OK!");
        } catch (SQLException ex) {
            log.error("insertRow: Error executing SQL " + sql + ">>>" + ex.toString());

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

    public static void main(String[] args) throws SQLException {
        String header = "NokiaX2-01/5.0 (08.63) Profile/MIDP-2.1 Configuration/CLDC-1.1 Mozilla/5.0 AppleWebKit/420+ (KHTML, like Gecko) Safari/420+";
        AgentDAO a = new AgentDAO();
        String[] mobile = a.getMobileManufactAndModel(header.toUpperCase());
        System.out.println("Manufact: " + mobile[0]);
        System.out.println("Model: " + mobile[1]);
        // a.addUserInfoLog("0946467668","SamSung", "GT-S5570", "Mozilla/5.0 (Linux; U; Android 2.3.3; en-gb; GT-S5570 Build/GINGERBREAD) AppleWe");

    }
}
