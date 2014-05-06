package model.dao;

import common.Config;
import common.Connector;
import common.Logger;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.bean.Agent;
import model.bean.Game;
import model.bean.GameManufact;
import model.bean.GamePackage;
import unicode.Unicode;

/**
 *
 * @author Nguyen Dinh Doan
 */
public class GameDAO {

    private Connection conn;
    private PreparedStatement preStmt;
    private ResultSet rs;
    private Game game;
    private Logger logger;
    private String supplier;
    private final String tblGameCat = "TIG_VT_GAME_CAT";
    private final String tblGameData = "TIG_VT_GAME_DATA";
    private final String tblGamePack = "TIG_GAME_PACKAGE";
    private final String tblPackageData = "TIG_GAME_PACKAGE_DATA";
    private final String tblManuFact = "TIG_MOBILE_MANUFACT";
    private int shortNum;
    private Unicode u;

    /**
     * Default constructor with initialize for <i>shorNum</i> (this is a property 
     * saved in .properties file, use for showing length of Game content 
     * or Package details...). You can change its property by accessing to the 
     * .properties file and change the value which has key is <i>shorNum</i>
     */
    public GameDAO() {
        shortNum = 40;
        try {
            shortNum = Integer.parseInt(Config.getString("shortNum"));
        } catch (Exception e) {
            System.out.println(e);
        }
        logger = new Logger(this.getClass().getName());
        u = new Unicode();
    }

    /**
     * Get list of all Games in TIG_GAME_DATA table with CatID from parameter.
     * @return an ArrayList stored all valid Games
     * @throws SQLException if database access error occurs
     */
    public List<Game> getGamesByCatId(String catId, Agent agent, int page, int num)
            throws SQLException {

        int startIndex;
        if (page == 1) {
            startIndex = 0;
        } else {
            startIndex = page - 1;
        }
        String sql = "";
        if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
            sql =
                    "select * from"
                    + " (select data.id, data.Name,  data.logourl,  substr(data.GameInfo, 0, ?) || '...',data.downloaded, row_number() over (order by  dbms_random.value) r, d.createdate "
                    + " from TIGVIETTEL." + tblGameData + " data, liveinfowap.device d, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v"
                    + " where data.is_publish = 1 and data.catId = " + catId
                    + " and data.id = ds.content_instance_id and d.device_id = ds.device_id"
                    + " and upper(d.code) = '" + agent.getMobileModel() + "' and upper(v.code) = '" + agent.getManufactor() + "'"
                    + " order by dbms_random.value)"
                    + " where r > " + (startIndex * num) + " and r <= " + (page * num)+" order by createdate desc";
        } else {
            sql =
                    "select * from"
                    + " (select id, Name, logourl,  substr(GameInfo, 0, ?) || '...',downloaded, row_number() over (order by  dbms_random.value) r, d.createdate "
                    + " from " + tblGameData + " d"
                    + " where is_publish = 1 and catId = " + catId
                    + " order by dbms_random.value )"
                    + "where r > " + (startIndex * num) + " and r <= " + (page * num)+" order by createdate desc";
        }
        conn = Connector.getConnection();
        preStmt = conn.prepareStatement(sql);
        preStmt.setInt(1, shortNum);
        rs = preStmt.executeQuery();

        List<Game> lstGame = new ArrayList<Game>();
        while (rs.next()) {
            game = new Game(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
            lstGame.add(game);
        }
        Connector.closeConnections(conn, preStmt, rs);
        return lstGame;
    }

    /**
     * Get the Candidate Game to show at the top of home page
     * @return Candidate Game with id, Name, LogoURL, GameInfo
     * @throws SQLException when database access error occurs
     */
    public Game getCandidateGame(Agent agent) throws SQLException {
        //int catid = 25;
        Game candidate = null;
        String sql = "";
        try {
            //catid = Integer.parseInt(Config.getString("catid"));
            if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
                sql = "select * from (select d.id, d.name, d.logourl, substr(d.GameInfo, 0, 170) || '...', d.downloaded from TIGVIETTEL.TIG_VT_GAME_DATA d, TIGVIETTEL.TIG_VT_GAME_CAT c, "
                        + "liveinfowap.device de, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v"
                        + " where d.catid = c.catid and IS_PROMOTE = 1 and upper(de.code) = '" + agent.getMobileModel() + "' and upper(v.code) = '" + agent.getManufactor() + "' and d.id = ds.content_instance_id and de.device_id = ds.device_id and"
                        + " d.is_publish = 1 order by dbms_random.value) where rownum < 2";
            } else {
                sql = "select * from (select d.id, d.name, d.logourl, substr(d.GameInfo, 0, 170) || '...', d.downloaded from TIG_VT_GAME_DATA d, TIG_VT_GAME_CAT c "
                        + "where d.catid = c.catid and  IS_PROMOTE = 1  and d.is_publish = 1 order by dbms_random.value) where rownum < 2";
            }
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            //  preStmt.setInt(1, catid);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                candidate = new Game(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
            }
        } catch (Exception e) {
            System.out.println("[Choigame] error getCandidateGame" + e.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return candidate;
    }

    public Game getCandidateGameNotSupport(Agent agent) throws SQLException {
        int catid = 25;
        Game candidate = null;
        String sql = "";
        try {
            catid = Integer.parseInt(Config.getString("catid"));

            sql = "select * from (select d.id, d.name, d.logourl, substr(d.GameInfo, 0, 170) || '...', d.downloaded from TIG_VT_GAME_DATA d, TIG_VT_GAME_CAT c "
                    + "where d.catid = c.catid and  IS_PROMOTE = 1 and d.is_publish = 1 order by dbms_random.value) where rownum < 2";

            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            // preStmt.setInt(1, catid);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                candidate = new Game(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
            }
        } catch (Exception e) {
            System.out.println("[Choigame] error getCandidateGame" + e.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return candidate;
    }

    /**
     * Iterate each Game in TIG_VT_GAME_DATA table then get the newest 
     * game for each category. If in home page then select 3 Games, 
     * otherwise select all games. The query is really long. Because of this,
     * you must optimize it in future by converting to a <b>store procedure</b> 
     * then use CallableStatement to get results.
     * 
     * @param agent Agent object which was get by get("user-agent")
     * @param inHomePage  whether is in home page or not
     * @param page page to jump to
     * @param num number of Games want to display on once
     * @param count total of split pages
     * @return List of newest game in each Game Category 
     * @throws SQLException when database access error occurs
     */
    public List<Game> getNewestGameEachCat(Agent agent, boolean inHomePage,
            int page, int num, int count)
            throws SQLException {
        List<Game> lstNewest = new ArrayList<Game>();
        String sql;

        int startIndex;
        if (page == 1) {
            startIndex = 0;
        } else {
            startIndex = page - 1;
        }

        if (inHomePage) {
            if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
                sql = "select * from "
                        + " (select d.catid, "
                        + " (select c.name from TIGVIETTEL." + tblGameCat + " c where c.catid = d.catid) Catgory_Name, "
                        + " d.id, d.name, d.Logourl, substr(d.GameInfo, 0, ?) || '...' "
                        + " from TIGVIETTEL." + tblGameData + " d, "
                        + "         liveinfowap.device de, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v  "
                        + "     where  de.device_id = ds.device_id and d.id = ds.content_instance_id "
                        + " and upper(de.code) = '" + agent.getMobileModel() + "' and upper(v.code) = '" + agent.getManufactor() + "'"
                        + "     order by dbms_random.value  "
                        + " )"
                        + " where rownum < 7  ";
            } else {
                sql = "select * from "
                        + " (select d.catid, "
                        + "     (select c.name from " + tblGameCat + " c where c.catid = d.catid) Catgory_Name, "
                        + "     d.id, d.name, d.Logourl, substr(d.GameInfo, 0, ?) || '...' "
                        + "     from " + tblGameData + " d, "
                        + "         (select catid, max(updateDate) UpdateDate "
                        + "         from " + tblGameData + " temp "
                        + "         where temp.is_publish = 1"
                        + "         group by temp.catid "
                        + "         ) newest "
                        + "     where d.catid = newest.catid and d.UpdateDate = newest.UpdateDate "
                        + "     order by dbms_random.value "
                        + " )"
                        + " where rownum < 7";
            }
            // System.out.println("SQL: " + sql);

        } else {
            if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
                sql = " select * from "
                        + "     (select catid, "
                        + "             (select c.name from " + tblGameCat + " c where c.catid = data.catid) Catgory_Name, "
                        + "             data.id, data.name, data.Logourl, substr(data.GameInfo, 0, ?) || '...', data.is_hot, data.is_publish, rownum r"
                        + "      from TIGVIETTEL." + tblGameData + " data, liveinfowap.device d, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v "
                        + "      where  is_publish = 1"
                        + "          and data.id = ds.content_instance_id and d.device_id = ds.device_id and upper(d.code) = '" + agent.getMobileModel() + "' and upper(v.code) = '" + agent.getManufactor() + "'"
                        + "      order by createdate desc"
                        + "     )"
                        + " where r > " + (startIndex * num)
                        + " and r <= " + (page * num);

                //System.out.println("HOT......." + sql);
            } else {
                sql = " select * from "
                        + "     (select catid, "
                        + "             (select c.name from " + tblGameCat + " c where c.catid = d.catid) Catgory_Name, "
                        + "             id, name, Logourl, substr(GameInfo, 0, ?) || '...', is_hot, is_publish, rownum r"
                        + "      from " + tblGameData + " d "
                        + "      where  is_publish = 1"
                        + "      order by createdate desc"
                        + "     )"
                        + " where r > " + (startIndex * num)
                        + " and r <= " + (page * num);
            }
        }
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, shortNum);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                game = new Game(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
                lstNewest.add(game);
            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return lstNewest;
    }

    /**
     * Iterate each Game in TIG_GAME_DATA table then get the hottest
     * games for each category. If in home page then select 3 Games, 
     * otherwise select all games. The query is really long. Because of this,
     * you must optimize it in future by converting to a <b>store procedure</b> 
     * then use CallableStatement to get results.
     *
     * @param inHomePage  whether is in home page
     * @param page page to jump to
     * @param num number of Games want to display on once
     * @return List of hottest games in each Game Category 
     * @throws SQLException when database access error occurs
     */
    public List<Game> getHottestGameEachCat(Agent agent, boolean inHomePage,
            int page, int num, int count)
            throws SQLException {
        List<Game> lstHottest = new ArrayList<Game>();
        String sql;

        int startIndex;
        if (page == 1) {
            startIndex = 0;
        } else {
            startIndex = page - 1;
        }

        if (inHomePage) {
            if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
                sql = "select * from "
                        + "(select catid, "
                        + "(select c.name from TIGVIETTEL." + tblGameCat + " c where c.catid = data.catid) CatgoryName, "
                        + "data.id, data.name, data.Logourl, substr(data.GameInfo, 0, ?) || '...', data.is_hot, data.is_publish, rownum r"
                        + " from TIGVIETTEL." + tblGameData + " data, liveinfowap.device d, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v  "
                        + " where is_hot = 1 and is_publish = 1"
                        + " and data.id = ds.content_instance_id and d.device_id = ds.device_id"
                        + " and upper(d.code) = '" + agent.getMobileModel() + "' and upper(v.code) = '" + agent.getManufactor() + "'"
                        + " order by dbms_random.value "
                        + ")"
                        + " where rownum < 7";
            } else {
                sql = "select * from "
                        + "(select catid, "
                        + "     (select c.name from " + tblGameCat + " c where c.catid = d.catid) CatgoryName, "
                        + "     id, name, Logourl, substr(GameInfo, 0, ?) || '...', is_hot, is_publish, rownum r"
                        + " from " + tblGameData + " d "
                        + " where is_hot = 1 and is_publish = 1"
                        + " order by dbms_random.value "
                        + ")"
                        + " where rownum < 7";
            }
        } else {
            if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
                sql = "select * from "
                        + "(select data.catid, "
                        + "(select c.name from TIGVIETTEL." + tblGameCat + " c where c.catid = data.catid) Catgory_Name, "
                        + "data.id, data.name, data.Logourl, substr(data.GameInfo, 0, ?) || '...', data.is_hot, data.is_publish, data.downloaded, rownum r "
                        + "from TIGVIETTEL." + tblGameData + " data, liveinfowap.device d, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v "
                        + "where is_hot = 1 and is_publish = 1 "
                        + "and data.id = ds.content_instance_id and d.device_id = ds.device_id "
                        + "and upper(d.code) = '" + agent.getMobileModel() + "' and upper(v.code) = '" + agent.getManufactor() + "' "
                        + "order by data.downloaded desc) "
                        + "where r > " + (startIndex * num)
                        + " and r <= " + (page * num);

                //System.out.println("HOT......." + sql);
            } else {
                sql = " select * from "
                        + "     (select catid, "
                        + "             (select c.name from " + tblGameCat + " c where c.catid = d.catid) Catgory_Name, "
                        + "             id, name, Logourl, substr(GameInfo, 0, ?) || '...', is_hot, is_publish, rownum r"
                        + "      from " + tblGameData + " d "
                        + "      where is_hot = 1 and is_publish = 1"
                        + "      order by name"
                        + "     )"
                        + " where r > " + (startIndex * num)
                        + " and r <= " + (page * num);
            }
        }
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, shortNum);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                game = new Game(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
                lstHottest.add(game);
            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return lstHottest;
    }

    /**
     * Get results for searching. This method will split the user's request string
     * to tokens. After splitting, it will convert to lower case or to upper case, 
     * then it will use <i>like</i> statement for each token to get results.
     * @param agent get("user-agent") from user's mobile
     * @param content keywords for searching
     * @param catId check the user whether is current in any a Package
     * @return list of Games which appropriate with user's request keywords
     * @throws SQLException when database access error occurs
     */
    public List<Game> getSearchResults(Agent agent, String content, String catId) throws SQLException {
        //String[] keywords = content.trim().replaceAll("\\s+", " ").split(" ");
        String keywords = u.convertUnicode(content).toLowerCase().trim().replaceAll("\\s+", " ").replace("-", "");
        String sql = analyseSearchQuery(agent, content, catId);

        List<Game> lstSearchResults = new ArrayList<Game>();
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
//            for (int i = 0; i < keywords.length; i++) {
//                preStmt.setString(i + 1, "%" + keywords[i] + "%");
//            }
            preStmt.setString(1, "%" + keywords + "%");
            rs = preStmt.executeQuery();
            while (rs.next()) {
                lstSearchResults.add(new Game(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return lstSearchResults;
    }

    //huent
    public List<Game> getSearchByManufact(Agent agent, String content, String catId) throws SQLException {
        String[] keywords = content.trim().replaceAll("\\s+", " ").split(" ");
        //String keywords = u.convertUnicode(content).toLowerCase().trim().replaceAll("\\s+", " ").replace("-", "");
        String sql = searchByManufat(agent, content, catId);

        List<Game> lstSearchResults = new ArrayList<Game>();
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            for (int i = 0; i < keywords.length; i++) {
                preStmt.setString(i + 1, "%" + keywords[i] + "%");
            }

            rs = preStmt.executeQuery();
            while (rs.next()) {
                lstSearchResults.add(new Game(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return lstSearchResults;
    }

    /**
     * Analyst the search query from user. Split query 
     * then append with "like" statement
     * @param agent Get from get("user-agent") to check mobile.
     * @param content query from user
     * @param catId Id of category, use it to check user currently is in any Package
     * @return Query analysis
     */
    public String analyseSearchQuery(Agent agent, String content, String catId) {
        //String[] keywords = content.trim().replaceAll("\\s+", " ").split(" ");   
        String keywords = u.convertUnicode(content).toLowerCase().trim().replaceAll("\\s+", " ").replace("-", "");
        StringBuilder sql = null;
        if (catId == null || catId.equals("")) {
            sql = new StringBuilder(
                    "SELECT catid, "
                    + "(SELECT c.name from TIGVIETTEL." + tblGameCat + " c WHERE c.catid = d.catid) CatgoryName, "
                    + "d.id, "
                    + "d.name, d.logourl, (substr(d.gameinfo, 0, " + shortNum + ") || '...') GameInfo "
                    + "from TIGVIETTEL." + tblGameData + " d");
            if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
                sql.append(", liveinfowap.device de, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v where is_publish = 1 and (");
            } else {
                sql.append(" where is_publish = 1 and(");
            }
        } else {
            int catIdInt;
            try {
                catIdInt = Integer.parseInt(catId);
            } catch (Exception e) {
                return null;
            }
            sql = new StringBuilder(
                    "SELECT catid, "
                    + "(SELECT c.name from TIGVIETTEL." + tblGameCat + " c WHERE c.catid = d.catid) CatgoryName, "
                    + " d.id, "
                    + " d.name, d.logourl, (substr(d.gameinfo, 0, " + shortNum + ") || '...') GameInfo, d.MobileTypes"
                    + " from TIGVIETTEL." + tblGameData + " d");
            if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
                sql.append(", liveinfowap.device de, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v where is_publish = 1 and catId=").append(catIdInt).append(" and (");
            } else {
                sql.append(" where is_publish = 1 and catId=").append(catIdInt).append(" and (");
            }
        }

        //for (String key : keywords) {
        sql.append(" lower (TIGVIETTEL.fuCutUnicode1(d.name)) like ? or");
        //    sql.append(" lower (TIGVIETTEL.fuCutUnicode1(d.name)) like lower (TIGVIETTEL.fuCutUnicode1(?) or");
        // }
        sql.delete(sql.lastIndexOf("or"), sql.length());
        sql.append(")");
        if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
            sql.append(" and d.id = ds.content_instance_id and de.device_id = ds.device_id" + " and upper(de.code) = '").append(agent.getMobileModel()).append("' and upper(v.code) = '").append(agent.getManufactor()).append("'");
        }
        return sql.toString();
    }

    //huent
    public String searchByManufat(Agent agent, String content, String catId) {
        String[] keywords = content.trim().replaceAll("\\s+", " ").split(" ");
        StringBuilder sql = null;
        if (catId == null || catId.equals("")) {
            sql = new StringBuilder(
                    "SELECT catid, "
                    + "(SELECT c.name from TIGVIETTEL." + tblGameCat + " c WHERE c.catid = d.catid) CatgoryName, "
                    + "d.id, "
                    + "d.name, d.logourl, (substr(d.gameinfo, 0, " + shortNum + ") || '...') GameInfo "
                    + "from TIGVIETTEL." + tblGameData + " d");
            if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
                sql.append(", liveinfowap.device de, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v where is_publish = 1 and (");
            } else {
                sql.append(" where is_publish = 1 and (");
            }
        } else {
            int catIdInt;
            try {
                catIdInt = Integer.parseInt(catId);
            } catch (Exception e) {
                return null;
            }
            sql = new StringBuilder(
                    "SELECT catid, "
                    + "(SELECT c.name from TIGVIETTEL." + tblGameCat + " c WHERE c.catid = d.catid) CatgoryName, "
                    + " d.id, "
                    + " d.name, d.logourl, (substr(d.gameinfo, 0, " + shortNum + ") || '...') GameInfo, d.MobileTypes"
                    + " from TIGVIETTEL." + tblGameData + " d");
            if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
                sql.append(", liveinfowap.device de, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v where is_publish = 1 and catId=").append(catIdInt).append(" and (");
            } else {
                sql.append(" where is_publish = 1 and catId=").append(catIdInt).append(" and (");
            }
        }

        for (String key : keywords) {
            sql.append(" lower(d.mobilemanufacture) like ? or");
        }
        sql.delete(sql.lastIndexOf("or"), sql.length());
        sql.append(")");
        if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
            sql.append(" and d.id = ds.content_instance_id and de.device_id = ds.device_id" + " and upper(de.code) = '").append(agent.getMobileModel()).append("' and upper(v.code) = '").append(agent.getManufactor()).append("'");
        }
        return sql.toString();
    }

    /**
     * Game all games in a package
     * @param pId ID of the package
     * @return List of all games in the package
     * @throws SQLException when database error occurs
     */
    public List<Game> getGamesInPack(Agent agent, int pId) throws SQLException {
        String sql = "";
        if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
            sql = "SELECT catid, "
                    + " (SELECT c.name from TIGVIETTEL." + tblGameCat + " c WHERE c.catid = d.catid) CatgoryName, "
                    + " d.id, d.name, d.logourl, substr(gameinfo, 0, " + shortNum + ") || '...'"
                    + " from TIGVIETTEL." + tblGameData + " d, liveinfowap.device de, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v"
                    + " where d.id in (select p.dataid from TIGVIETTEL." + tblPackageData + " p where p.pid = ?)"
                    + " and d.id = ds.content_instance_id and de.device_id = ds.device_id"
                    + " and upper(de.code) = '" + agent.getMobileModel() + "' and upper(v.code) = '" + agent.getManufactor() + "'";
        } else {
            sql = "SELECT catid, "
                    + " (SELECT c.name from " + tblGameCat + " c WHERE c.catid = d.catid) CatgoryName, "
                    + " d.id, d.name, d.logourl, substr(gameinfo, 0, " + shortNum + ") || '...'"
                    + " from " + tblGameData + " d"
                    + " where d.id in (select p.dataid from " + tblPackageData + " p where p.pid = ?)";
        }
        List<Game> lstGamesInPack = new ArrayList<Game>();
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, pId);
            rs = preStmt.executeQuery();

            while (rs.next()) {
                lstGamesInPack.add(new Game(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return lstGamesInPack;
    }

    /*
     * HungDT 
     * 
     */
    public String getCatName(int catid) {
        ResultSet rs1 = null;
        String sql = "select name from " + tblGameCat + " where catid= " + catid;
        String name = null;
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            rs1 = preStmt.executeQuery();
            while (rs1.next()) {
                name = rs1.getString(1);
                break;
            }
        } catch (SQLException e) {
            logger.error("select error " + sql + " " + e.toString());
        } catch (Exception e) {
            logger.error("exception error " + e.toString());
            System.out.println("sql:" + sql);
        } finally {
            try {
                Connector.closeConnections(conn, preStmt, rs1);
            } catch (Exception sqlex) {
                System.out.println("Error");
                sqlex.printStackTrace();
            }
        }
        return name;
    }
    //hungdt get chi tiet game

    public Game getDetail(int id) throws SQLException {

        Game gamecontent = null;

        int catid = 0;
        String sql = "Select catid, gamecode, name, namenosign, logourl, gameprice,is_publish, is_home, is_hot, downloadurl, downloaded, gameinfo, gameinfonosign, supplier from "
                + tblGameData + " where id =" + id;
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                gamecontent = new Game();
                gamecontent.setDataId(id);
                catid = rs.getInt("catid");
                gamecontent.setCatId(catid);
                gamecontent.setGameCode(rs.getString(2));
                gamecontent.setName(rs.getString(3));
                gamecontent.setNameNoSign(rs.getString(4));
                gamecontent.setLogoUrl(rs.getString(5));
                gamecontent.setGamePrice(rs.getInt(6));
                gamecontent.setIsPublic(rs.getInt("is_publish"));
                gamecontent.setIsHome(rs.getInt("is_home"));
                gamecontent.setIsHot(rs.getString("is_hot"));
                gamecontent.setDownloadUrl(common.Config.getString("dataurl") + rs.getString("downloadurl"));
                gamecontent.setDownloaded(rs.getInt("downloaded"));
                gamecontent.setGameInfo(rs.getString("gameinfo"));
                gamecontent.setGameInfoNosign(rs.getString("gameinfonosign"));
                supplier = rs.getString("supplier");
                gamecontent.setSupplier(supplier);
                if (supplier != null && supplier.length() > 0) {
                    if (supplier.equalsIgnoreCase("FMC")) {
                        gamecontent.setDownloadUrl("FMC:" + rs.getString("gamecode"));
                    }
                    if (supplier.equalsIgnoreCase("VNM")) {
                        gamecontent.setDownloadUrl("VNM:" + rs.getString("gamecode"));
                    }
                    if (supplier.equalsIgnoreCase("MV")) {
                        gamecontent.setDownloadUrl("MV:" + rs.getString("gamecode"));
                    }
                    if (supplier.equalsIgnoreCase("VTC")) {
                        gamecontent.setDownloadUrl("VTC:" + rs.getString("gamecode"));
                    }
                    if (supplier.equalsIgnoreCase("IC")) {
                        gamecontent.setDownloadUrl("IC:" + rs.getString("gamecode"));
                    }
                    if (supplier.equalsIgnoreCase("LT")) {
                        gamecontent.setDownloadUrl("LT:" + rs.getString("gamecode"));
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error: " + e.toString());
        } catch (Exception ex) {
            logger.error("error" + ex.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
            if (id != 0) {
                gamecontent.setCatName(getCatName(catid));
            } else {
                Connector.closeConnections(conn, preStmt, rs);
            }


        }
        return gamecontent;
    }
    //hungdt: get game cung chu de

    public List<Game> getGameRelatedCat(int id, Agent agent) {
        Game gamecontent = null;
        String sql = "";
        //select 5 ban ghi ngau nhien cung the loai voi id game do
        if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
            sql = "Select * from"
                    + "(Select d.id, d.name"
                    + " from TIGVIETTEL." + tblGameData + " d, liveinfowap.device de, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v "
                    + "where d.id != " + id
                    + " and d.id = ds.content_instance_id and de.device_id = ds.device_id and "
                    + " upper(de.code) = '" + agent.getMobileModel() + "' and upper(v.code) = '" + agent.getManufactor() + "' and"
                    + " catid= (select catid from " + tblGameData + " where id = " + id
                    + ") order by dbms_random.value) where rownum <= 5";
        } else {
            sql = "Select * from"
                    + "(Select id, name"
                    + " from " + tblGameData + " where id != " + id
                    + " and "
                    + " catid= (select catid from " + tblGameData + " where id = " + id
                    + ") order by dbms_random.value) where rownum <= 5";
        }


        List arraylist = new ArrayList();
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                gamecontent = new Game();
                gamecontent.setDataId(rs.getInt("id"));
                gamecontent.setName(rs.getString("name"));
                arraylist.add(gamecontent);
            }
        } catch (SQLException e) {
            logger.error("select error " + sql + " " + e.toString());
        } catch (Exception e) {
            logger.error("exception error " + e.toString());
        } finally {
            try {
                Connector.closeConnections(conn, preStmt, rs);
            } catch (Exception sqlex) {
                sqlex.printStackTrace();
            }
        }
        return arraylist;
    }

    /*
     * HungDT: get game download trong package(goi khuyen mai)
     */
    public List<Game> getGameDownloadInPackage(int pid) throws SQLException {
        String sql = "Select catid, (Select c.name from " + tblGameCat + " c where c.catid = d.catid ) CategoryName,"
                + " d.id, d.name, d.logourl,d.gamecode, d.downloadurl, d.supplier, pk.packageprice"
                + " from " + tblGameData + " d, " + tblPackageData + " pk where"
                + " d.id in (select p.dataid from " + tblPackageData + " p where p.pid = ?) and"
                + " pk.pid in (select p.pid from " + tblPackageData + " p where p.pid = ?)";
        Game gamecontent = null;
        //GamePackage gamepackage = null;
        List arraylist = new ArrayList();
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                gamecontent.setDataId(rs.getInt("id"));
                gamecontent.setGameCode(rs.getString("gamecode"));
                gamecontent.setDownloadUrl(rs.getString("downloadurl"));
                supplier = rs.getString("supplier");
                gamecontent.setSupplier(supplier);
                if (supplier != null && supplier.length() > 0) {
                    if (supplier.equalsIgnoreCase("FMC")) {
                        gamecontent.setDownloadUrl("FMC:" + rs.getString("gamecode"));
                    }
                    if (supplier.equalsIgnoreCase("VNM")) {
                        gamecontent.setDownloadUrl("VNM:" + rs.getString("gamecode"));
                    }
                    if (supplier.equalsIgnoreCase("MV")) {
                        gamecontent.setDownloadUrl("MV:" + rs.getString("gamecode"));
                    }
                    if (supplier.equalsIgnoreCase("VTC")) {
                        gamecontent.setDownloadUrl("VTC:" + rs.getString("gamecode"));
                    }
                    if (supplier.equalsIgnoreCase("IC")) {
                        gamecontent.setDownloadUrl("IC:" + rs.getString("gamecode"));
                    }
                    if (supplier.equalsIgnoreCase("LT")) {
                        gamecontent.setDownloadUrl("LT:" + rs.getString("gamecode"));
                    }
                }
                arraylist.add(gamecontent);
            }
        } catch (SQLException e) {
            System.out.println("error: " + e.toString());
        } catch (Exception ex) {
            System.out.println("error: " + ex.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }

        return arraylist;
    }

    /*
     * <p>HungDT: Lay gia tri package</p>
     */
    public GamePackage getPackage(int pid) throws SQLException {
        GamePackage gamepkg = null;
        String sql = "select p.packageprice, p.name from " + tblGamePack + " p where p.pid = " + pid;
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
            gamepkg = new GamePackage();
            while (rs.next()) {
                gamepkg.setName(rs.getString("name"));
                gamepkg.setPackagePrice(rs.getInt("packageprice"));
            }
        } catch (SQLException e) {
            System.out.println("error: " + e.toString());
        } catch (Exception ex) {
            System.out.println("error: " + ex.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return gamepkg;
    }

    /*
     * Get pid(package) from dataid(gameid)
     */
    public int getpid(int dataid) throws SQLException {
        String sql = "select pid from " + tblPackageData + " where dataid = " + dataid;
        int pid = 0;
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                pid = rs.getInt("pid");
            }
        } catch (SQLException e) {
            System.out.println("error getpid: " + e.toString());
        } catch (Exception ex) {
            System.out.println("error getpid: " + ex.toString());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return pid;
    }

    //Insert luot download
    public String insertDownloaded(int id) throws SQLException {
        String sql = "";
        String status = "";
        try {
            sql = "update " + tblGameData + " set DOWNLOADED = ((select downloaded from tig_vt_game_data where id = " + id + ") + 1) where id = " + id + "";
            conn = Connector.getConnection();
            java.sql.Statement st = conn.createStatement();
            rs = st.executeQuery(sql);
            status = "[Choigame] update downloaded thanh cong";
        } catch (Exception e) {
            System.out.println("[Choigame] error downloaded" + e.toString());
            status = "[Choigame] co loi khi update downloaded";
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }

        return status;
    }

    /**
     * Get all mobile types and its models which support the game
     * @return A HasMap(<String>, List<String>). 
     * 1st para is name of Manufacture. 
     * 2nd is list of models of each Manufacture.
     * <p>See the code demo:</p>
     * <pre>
     * <code>
     *  Set<String> keySet = mobileAndModel.keySet();
    Iterator<String> iterator = keySet.iterator();
    while (iterator.hasNext()) {
    String manuFact = iterator.next();
    List<String> lstModelResults = mobileAndModel.get(manuFact);
    if (lstModelResults.isEmpty()) {
    continue;
    }
    
    System.out.println("\n***" + manuFact + ":");
    System.out.print("\t");
    for (String item : lstModelResults) {
    System.out.print(item + ", ");
    }
    System.out.println();
    }
     * </code>
     * </pre>
     * @throws SQLException when database access error occurs
    
    public Map<String, List<String>> getSupportedMobiles(int gameId) throws SQLException {
    String sql = "select lower(MobileTypes) from " + tblGameData + " where id = " + gameId;
    
    Map<String, List<String>> mobileAndModel = new HashMap<String, List<String>>();
    try {
    conn = Connector.getConnection();
    preStmt = conn.prepareStatement(sql);
    //preStmt.setInt(1, gameId);
    rs = preStmt.executeQuery();
    
    String temp = "";
    while (rs.next()) {
    temp = rs.getString(1);
    }
    String strTypes = temp.trim().replaceAll("\\s+", "").replaceAll(";", ",");
    
    // Get list of Mobiles
    String sqlMobiles = "select lower(ManuFact) from " + tblManuFact;
    preStmt = conn.prepareStatement(sqlMobiles);
    rs = preStmt.executeQuery();
    int a = rs.getRow();
    System.out.println(a);
    List<String> lstManufacts = new ArrayList<String>();
    while (rs.next()) {
    lstManufacts.add(rs.getString(1));
    }
    
    // Spliting
    String[] tokens = strTypes.split(",");
    for (String manuFact : lstManufacts) {
    List<String> lstModels = new ArrayList<String>();
    for (String token : tokens) {
    if (token.startsWith(manuFact)) {
    String model = token.substring(manuFact.length());
    lstModels.add(model);
    }
    }
    mobileAndModel.put(manuFact, lstModels);
    }
    } catch (SQLException ex) {
    System.out.println("[Choigame]Err Mobile support: " + ex.getMessage());
    } catch (NullPointerException e) {
    System.out.println("[Choigame]Err Mobile support" + e.getMessage());
    } finally {
    Connector.closeConnections(conn, preStmt, rs);
    }
    return mobileAndModel;
    }
    
     */
    public Map<String, List<String>> getMobileSupport(int id) throws SQLException {
        List<Agent> list = new ArrayList<Agent>();
        Map<String, List<String>> mobileAndModel = new HashMap<String, List<String>>();

        String sql = "";
        Agent agent1 = null;
        try {
            conn = Connector.getConnection();
            sql = "select v.code Manufact, d.code Model from tig_vt_game_data g, device d, device_support_content ds, vendor v "
                    + "where g.id = ds.content_instance_id and d.device_id =ds.device_id and v.vendor_id = d.vendor_id and g.id = " + id + "";
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                agent1 = new Agent();
                agent1.setManufactor(rs.getString("Manufact"));
                agent1.setMobileModel(rs.getString("Model"));
                list.add(agent1);
            }

            // Get list of Mobiles
            String sqlMobiles = "select upper(ManuFact) from " + tblManuFact;
            preStmt = conn.prepareStatement(sqlMobiles);
            rs = preStmt.executeQuery();
            List<String> lstManufacts = new ArrayList<String>();
            while (rs.next()) {
                lstManufacts.add(rs.getString(1));
            }
            // put to map
            for (String manufact : lstManufacts) {
                List<String> lstModels = new ArrayList<String>();

                for (Agent agent2 : list) {
                    if (manufact.equalsIgnoreCase(agent2.getManufactor())) {

                        lstModels.add(agent2.getMobileModel());
                        mobileAndModel.put(manufact, lstModels);

                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("[Choigame]Err Mobile support: " + ex.getMessage());
        } catch (NullPointerException e) {
            System.out.println("[Choigame]Err Mobile support" + e.getMessage());
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }

        return mobileAndModel;
    }

    //huent
    public List<Game> getGamesByManufact(String manufact, Agent agent, int page, int num)
            throws SQLException {

        int startIndex;
        if (page == 1) {
            startIndex = 0;
        } else {
            startIndex = page - 1;
        }
        String sql = "";


        List<Game> lstGame = new ArrayList<Game>();
        try {
            sql = "select * from"
                    + "(select rownum r, d1.id, d1.gamecode,d1.name, d1.mobilemanufacture, d1.logourl, substr(d1.GameInfo, 0, ?) || '...', d1.downloaded "
                    + "from tig_vt_game_data d1 "
                    + "where lower(d1.mobilemanufacture) like lower('%" + manufact + "%') "
                    + "order by d1.id asc)"
                    + "where r > " + (startIndex * num) + " and r <= " + (page * num);
            conn = Connector.getConnection();

            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, shortNum);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                game = new Game(rs.getInt("id"), rs.getString("name"), rs.getString("logourl"), rs.getString(7), rs.getInt("downloaded"));
                lstGame.add(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return lstGame;
    }

    public static void main(String[] args) throws SQLException {
        GameDAO game = new GameDAO();
        //List<Agent> list = game.getMobileSupport(144);
        Map<String, List<String>> mobileAndModel = game.getMobileSupport(144);

        Set<String> keySet = mobileAndModel.keySet();
        Iterator<String> iterator = keySet.iterator();
        List<String> temps = new ArrayList<String>();

        while (iterator.hasNext()) {
            String next = iterator.next();
            List<String> lstModelResults = mobileAndModel.get(next);
            if (mobileAndModel.get(next).isEmpty()) {
                temps.add(next);
            }
            System.out.println("\n***" + next + ":");
            System.out.print("\t");
            for (String item : lstModelResults) {
                System.out.print(item + ", ");
            }
        }
        for (String temp : temps) {
            mobileAndModel.remove(temp);
        }
    }
}
