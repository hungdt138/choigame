/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import common.Connector;
import common.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import model.bean.Agent;
import model.bean.Groupon;
import model.bean.GrouponBlock;
import model.bean.GrouponLog;
import model.bean.GrouponTransfers;

/**
 *
 * @author noname
 */
public class GrouponDAO {

    private Connection conn;
    private PreparedStatement preStmt;
    private ResultSet rs;
    private Logger logger;
    private GrouponBlock gBlock;
    private String sql = "";
    private String tableblock = "TIG_GROUPON_BLOCK";
    private String pattern = "dd/MM/yyyy";
    private String pattern1 = "dd-MM-yyyy K:mm a";
    private SimpleDateFormat sdf;

    public GrouponDAO() {
        logger = new Logger(this.getClass().getName());
    }

    public List<GrouponBlock> GetBlockDisplay() throws SQLException {
        List<GrouponBlock> lst = new ArrayList<GrouponBlock>();

        try {
            sql = "SELECT ID, NAMEBLOCK, STATUS FROM " + tableblock;
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                gBlock = new GrouponBlock();
                gBlock.setId(rs.getInt("ID"));
                gBlock.setNameblock(rs.getString("NAMEBLOCK"));
                gBlock.setStatus(rs.getInt("STATUS"));
                lst.add(gBlock);
            }
        } catch (Exception e) {
            logger.error("Error Block Groupon: " + e.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }

        return lst;
    }

    public Groupon GetGrouponGame(Agent agent) throws SQLException {
        Groupon gr = null;

        try {
//            if ("unknown".equalsIgnoreCase(agent.getManufactor()) && "unknown".equalsIgnoreCase(agent.getMobileModel())) {
                sql = "select tg.id, tg.startdate, tg.enddate, tg.NUMBER_FACT, tg.NUMBER_VIRTUAL,tg.NUMBER_BUY, tg.NAME as namegroupon, tg.DESCRIPTION, tg.STATUSGROUPON, td.name as gamename, "
                        + "td.id as idgame, td.LOGOURL, td.GAMEPRICE, tg.PRICEGROUPON from tig_game_groupon tg, tig_vt_game_data td "
                        + "where "
                        + "tg.idgame = td.id and tg.statusgroupon = 0 and tg.enddate >= sysdate";
//            } else {
//                sql = "select tg.id, tg.startdate, tg.enddate, tg.NUMBER_FACT, tg.NUMBER_VIRTUAL,tg.NUMBER_BUY, tg.NAME as namegroupon, tg.DESCRIPTION, tg.STATUSGROUPON, td.name as gamename, "
//                        + "td.id as idgame, td.LOGOURL, td.GAMEPRICE, tg.PRICEGROUPON from tig_game_groupon tg, tig_vt_game_data td, liveinfowap.device d, "
//                        + "liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v "
//                        + "where td.id = ds.content_instance_id and d.device_id = ds.device_id and upper(d.code) = '" + agent.getMobileModel() + "' and upper(v.code) = '" + agent.getManufactor() + "' "
//                        + "and tg.idgame = td.id and tg.statusgroupon = 0 and tg.enddate >= sysdate";
//            }
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
//            if (rs.next()){
//                sql = "select tg.id, tg.startdate, tg.enddate, tg.NUMBER_FACT, tg.NUMBER_VIRTUAL,tg.NUMBER_BUY, tg.NAME as namegroupon, tg.DESCRIPTION, tg.STATUSGROUPON, td.name as gamename, "
//                        + "td.id as idgame, td.LOGOURL, td.GAMEPRICE, tg.PRICEGROUPON from tig_game_groupon tg, tig_vt_game_data td "
//                        + "where "
//                        + "tg.idgame = td.id and tg.statusgroupon = 0 and tg.enddate >= sysdate";
//                preStmt = conn.prepareStatement(sql);
//                rs = preStmt.executeQuery();
//            }
            if (rs.next()) {
                gr = new Groupon();
                gr.setId(rs.getInt("id"));
//                gr.setStartdate("startdate");
                gr.setEnddate1(rs.getTimestamp("enddate"));
                gr.setNumber_fact(rs.getInt("NUMBER_FACT"));
                gr.setNumber_virtual(rs.getInt("NUMBER_VIRTUAL"));
                gr.setName(rs.getString("namegroupon"));
                gr.setDescription(rs.getString("DESCRIPTION"));
                gr.setStatusgroupon(rs.getInt("STATUSGROUPON"));
                gr.setNamegame(rs.getString("gamename"));
                gr.setIdgame(rs.getInt("idgame"));
                gr.setLogurl(rs.getString("LOGOURL"));
                gr.setNumber_buy(rs.getInt("NUMBER_BUY"));
                gr.setPricegroupon(rs.getInt("PRICEGROUPON"));
                gr.setPricegame(rs.getInt("GAMEPRICE"));
                double pr = (double) rs.getInt("PRICEGROUPON");
                double pr1 = (double) rs.getInt("GAMEPRICE");
                double percent = ((pr1 - pr) / pr1) * 100;
                gr.setPercent((int) percent);
            }
        } catch (Exception e) {
            System.out.println("[CHOIGAME] Error groupon in homepage: " + e.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }

        return gr;
    }

    public Groupon GetGrouponGameById(Agent agent, int id) throws SQLException {
        Groupon gr = null;

        try {
//            if ("unknown".equalsIgnoreCase(agent.getManufactor()) && "unknown".equalsIgnoreCase(agent.getMobileModel())) {
                sql = "select tg.id, tg.startdate, tg.enddate, tg.NUMBER_FACT, tg.NUMBER_VIRTUAL,tg.NUMBER_BUY, tg.NAME as namegroupon, tg.DESCRIPTION, tg.STATUSGROUPON, td.name as gamename, "
                        + "td.id as idgame, td.LOGOURL, td.GAMEPRICE, tg.PRICEGROUPON, td.GAMEINFO from tig_game_groupon tg, tig_vt_game_data td "
                        + "where "
                        + "tg.idgame = td.id and tg.statusgroupon = 0 and tg.enddate >= sysdate and tg.id = ?";
//            } else {
//                sql = "select tg.id, tg.startdate, tg.enddate, tg.NUMBER_FACT, tg.NUMBER_VIRTUAL,tg.NUMBER_BUY, tg.NAME as namegroupon, tg.DESCRIPTION, tg.STATUSGROUPON, td.name as gamename, "
//                        + "td.id as idgame, td.LOGOURL, td.GAMEPRICE, tg.PRICEGROUPON, td.GAMEINFO from tig_game_groupon tg, tig_vt_game_data td, liveinfowap.device d, "
//                        + "liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v "
//                        + "where td.id = ds.content_instance_id and d.device_id = ds.device_id and upper(d.code) = '" + agent.getMobileModel() + "' and upper(v.code) = '" + agent.getManufactor() + "' "
//                        + "and tg.idgame = td.id and tg.statusgroupon = 0 and tg.enddate >= sysdate and tg.id = ?";
//            }
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, id);
            rs = preStmt.executeQuery();
//              if (!rs.next()){
//                sql = "select tg.id, tg.startdate, tg.enddate, tg.NUMBER_FACT, tg.NUMBER_VIRTUAL,tg.NUMBER_BUY, tg.NAME as namegroupon, tg.DESCRIPTION, tg.STATUSGROUPON, td.name as gamename, "
//                        + "td.id as idgame, td.LOGOURL, td.GAMEPRICE, tg.PRICEGROUPON, td.GAMEINFO from tig_game_groupon tg, tig_vt_game_data td "
//                        + "where "
//                        + "tg.idgame = td.id and tg.statusgroupon = 0 and tg.enddate >= sysdate and tg.id = ?";
//                preStmt = conn.prepareStatement(sql);
//                preStmt.setInt(1, id);
//                rs = preStmt.executeQuery();
//            }
            sdf = new SimpleDateFormat();
            if (rs.next()) {
                gr = new Groupon();
                gr.setId(rs.getInt("id"));
                Date sd = rs.getTimestamp("startdate");
                gr.setEnddate1(rs.getTimestamp("enddate"));
                Date ed = rs.getTimestamp("enddate");
                String startdate = sdf.format(sd);
                String enddate = sdf.format(ed);
                gr.setStartdate(startdate);
                gr.setEnddate(enddate);
                gr.setNumber_fact(rs.getInt("NUMBER_FACT"));
                gr.setNumber_virtual(rs.getInt("NUMBER_VIRTUAL"));
                gr.setName(rs.getString("namegroupon"));
                gr.setDescription(rs.getString("DESCRIPTION"));
                gr.setStatusgroupon(rs.getInt("STATUSGROUPON"));
                gr.setNamegame(rs.getString("gamename"));
                gr.setIdgame(rs.getInt("idgame"));
                gr.setLogurl(rs.getString("LOGOURL"));
                gr.setNumber_buy(rs.getInt("NUMBER_BUY"));
                gr.setPricegroupon(rs.getInt("PRICEGROUPON"));
                gr.setPricegame(rs.getInt("GAMEPRICE"));
                double pr = (double) rs.getInt("PRICEGROUPON");
                double pr1 = (double) rs.getInt("GAMEPRICE");
                double percent = ((pr1 - pr) / pr1) * 100;
                gr.setPercent((int) percent);
                gr.setGameinfo(rs.getString("GAMEINFO"));
            }
        } catch (Exception e) {
            System.out.println("[CHOIGAME] Error groupon in homepage: " + e.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }

        return gr;
    }

    public String UpdateNumberBuy(int id) throws SQLException {
       
        String status = "";
        try {
            sql = "update tig_game_groupon set NUMBER_BUY = ((select NUMBER_BUY from tig_game_groupon where id = " + id + ") + 1) where id = " + id + "";
            conn = Connector.getConnection();
            java.sql.Statement st = conn.createStatement();
            rs = st.executeQuery(sql);
            status = "[Choigame-Groupon] Update Number Buy Success!";
        } catch (Exception e) {
            System.out.println("[Choigame] error downloaded" + e.toString());
            status = "[Choigame-Groupon] Update Number Buy Error";
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }

        return status;
    }

    public int CountListMsisdnBuy(int number, int id) throws SQLException {
        sql = "select count(id) from tig_groupon_log where idgroupon = ? and statuscharging = 0";
        int rowCount = 0;
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, id);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);
            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }

        if (rowCount % number == 0) {
            return rowCount / number;
        } else {
            return rowCount / number + 1;
        }
    }

    public List<GrouponLog> GetListMsisdnBuy(int id, int page, int num) throws SQLException {
        int startIndex;
        if (page == 1) {
            startIndex = 0;
        } else {
            startIndex = page - 1;
        }
        List<GrouponLog> lst = new ArrayList<GrouponLog>();
        GrouponLog log = null;
        try {
            sql = "select * from (select row_number() over(order by  datebuy desc) r, id, msisdn, datebuy, code from tig_groupon_log "
                    + "where idgroupon = ? and statuscharging = 0 order by  datebuy desc) "
                    + "where r > ? and r <= ?";
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, id);
            preStmt.setInt(2, startIndex * num);
            preStmt.setInt(3, page * num);
            rs = preStmt.executeQuery();
            sdf = new SimpleDateFormat(pattern1);
            while (rs.next()) {
                log = new GrouponLog();
                log.setId(rs.getInt("id"));
                String msisdn = rs.getString("msisdn");
                     String phone = "";
                     
                String phone1= "";
                if(msisdn.startsWith("84")){
                    phone = "0"+msisdn.substring(2);
                }
                if(phone.length() == 10){
                    phone1 = phone.substring(0, 7) + "xxx";
                } else if (phone.length() == 11){
                    phone1 = phone.substring(0, 8) + "xxx";
                }
                log.setMsisdn(phone1);
                Timestamp datebuy = rs.getTimestamp("datebuy");
                log.setDatebuy(sdf.format(datebuy));
                log.setRow(rs.getInt("r"));
                lst.add(log);
            }
        } catch (Exception e) {
            System.out.println("[Choigame - mua chung] Error get customer buy groupon" + e.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return lst;
    }

    public int InsertLogGroupon(GrouponLog log) throws SQLException {
        int t = 0;
        try {
            sql = "insert into tig_groupon_log(id, msisdn, DATEBUY, CHARGING, CONTENT, IDGROUPON, STATUSCHARGING, CODE) "
                    + "values (TIG_GROUPON_LOG_SEQUENCE.nextval,?,sysdate,?,?,?,?,?)";
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, log.getMsisdn());
            preStmt.setInt(2, log.getCharging());
            preStmt.setString(3, log.getContent());
            preStmt.setInt(4, log.getIdgroupon());
            preStmt.setInt(5, log.getStatuscharging());
            preStmt.setString(6, log.getCode());
            rs = preStmt.executeQuery();
            t = 1;
        } catch (Exception e) {
            System.out.println("[Choigame - Muachung] Error insert log" + e.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }

        return t;
    }

    public String GenerateCode(int id) throws SQLException {
        String code = "000";
        List<String> lstcode = new ArrayList<String>();
        try {
            sql = "select code from tig_groupon_log where idgroupon = ?";
            conn = Connector.getConnection(); 
            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, id);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                lstcode.add(rs.getString("code"));
            }
            
            Random rand = new Random();
            int numNoRange = rand.nextInt();
            int n = 0;
            while (numNoRange < 0 && n < 5) {
                numNoRange = rand.nextInt();
                if (numNoRange > 0) {
                    break;
                }
            }
            boolean a = lstcode.contains(String.valueOf(numNoRange));
            int n1 =0;
            while(a && n1 < 5){
                numNoRange = rand.nextInt();
                a = lstcode.contains(String.valueOf(numNoRange));
                if(!a){
                    code += numNoRange;
                    break;
                }
            }
            code += numNoRange;
        } catch (Exception e) {
            System.out.println("[Choigame - Muachung] Error generate code" + e.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }

        return code;
    }
    
    public int CountNumberBuyFromLog(int id) throws SQLException {
        int count = 0;
          sql = "select count(id) from tig_groupon_log where IDGROUPON = ? and statuscharging = 0";
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, id);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch(Exception e) {
            System.out.println("[Choigame - groupon] Error CountNumberBuyFromLog" + e.toString());
        
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return count;
    }
    
     public String UpdateNumberBuyFromLog(int id, int number) throws SQLException {
       
        String status = "";
        try {
            sql = "update tig_game_groupon set NUMBER_BUY = "+number+" where id = " + id + "";
            conn = Connector.getConnection();
            java.sql.Statement st = conn.createStatement();
            rs = st.executeQuery(sql);
            status = "[Choigame-Groupon] UpdateNumberBuyFromLog Success!";
        } catch (Exception e) {
            System.out.println("[Choigame] error downloaded" + e.toString());
            status = "[Choigame-Groupon] UpdateNumberBuyFromLog Error";
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }

        return status;
    }
     
    public int GetPriceByMsisdn(String msisdn) throws SQLException{
        int price = 0;
        String phone = "";
        if(msisdn.startsWith("0")){
            phone = "84" + msisdn.substring(1);
        }
        sql = "select pricetotal from tig_groupon_member where msisdn = ?";
         try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, phone);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                price = rs.getInt(1);
            }
        } catch(Exception e) {
            System.out.println("[Choigame - groupon] Error GetPriceByMsisdn" + e.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        
        return price;
    }
    
    //Gianh cho thue bao con tien tren he thong mua chung
    public int InserHistoryTransfers(GrouponTransfers gt) throws SQLException{
         int t = 0;
        try {
            sql = "insert into tig_groupon_transfer_history(id, idmember,datetime1, pricetransfers) values (TIG_GP_TRANSFERS_HISTORY_SEQ.nextval, ?, sysdate, ?)";
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            String msisdn = gt.getIdmember() ;
            if(msisdn.startsWith("0")){
                msisdn = "84"+ gt.getIdmember().substring(1);
            }
            preStmt.setLong(1, Long.parseLong(msisdn));
            preStmt.setInt(2, gt.getPricetransfers());
            rs = preStmt.executeQuery();
            String a = UpdatePrice(msisdn, gt.getPrice());
            System.out.println(a);
            t = 1;
        } catch (Exception e) {
            System.out.println("[Choigame - Muachung] Error InserHistoryTransfers" + e.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }

        return t;
    }
    
    public String UpdatePrice(String msisdn, int price) throws SQLException{
         String status = "";
        String phone = msisdn;
         if (phone.startsWith("0")){
             phone = "84" + msisdn.substring(1);
         }
        try {
            sql = "update tig_groupon_member set pricetotal = "+price+" where id = " + phone;
            conn = Connector.getConnection();
            java.sql.Statement st = conn.createStatement();
            rs = st.executeQuery(sql);
            status = "[Choigame-Groupon] UpdatePrice Success!" + price + msisdn;
        } catch (Exception e) {
            System.out.println("[Choigame] error downloaded" + e.toString());
            status = "[Choigame-Groupon] UpdatePrice Error";
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }

        return status;
    }
    
    public  void UpdateBlock(int status) throws SQLException{
        int a = 0;
        if(status == 1){
            a = 0;
        } else if (status == 0){
            a = 1;
        }
          try {
            sql = "update tig_groupon_block set status = "+status+" where ID = 1";
            String sql1 = "update tig_groupon_block set status = "+a+" where ID = 2";
            conn = Connector.getConnection();
            Statement st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs = st.executeQuery(sql1);
            //status = "[Choigame-Groupon] UpdatePrice Success!" + price + msisdn;
            System.out.println("====UPDATE BLOCK AUTOMATIC SUCCESS====");
            if(status == 1){
                System.out.println("GAME ACTIVE");
            } else {
                System.out.println("GROUPON ACTIVE");
            }
            System.out.println("=======================================");
        } catch (Exception e) {
            System.out.println("[Choigame] error UpdateBlock" + e.toString());
            //status = "[Choigame-Groupon] UpdatePrice Error";
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }

    }
    
   

    public static void main(String[] args) throws SQLException {
        GrouponDAO gDAO = new GrouponDAO();

//        List<GrouponBlock> lst = gDAO.GetBlockDisplay();
//        for (GrouponBlock g : lst) {
//            System.out.println(g.getNameblock());
//        }
        
        gDAO.UpdateBlock(0);
        
        String a = gDAO.GenerateCode(8);
        System.out.println(a);

    }
}
