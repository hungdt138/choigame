/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import common.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.bean.Agent;

/**
 *
 * @author Nguyen Dinh Doan
 */
public class PageCounter {

    private Connection conn;
    private PreparedStatement preStmt;
    private ResultSet rs;
    private final String tblGameCat = "TIG_VT_GAME_CAT";
    private final String tblGameData = "TIG_VT_GAME_DATA";
    private final String tblGamePack = "TIG_GAME_PACKAGE";

    /**
     * Get count of pages in Newest page
     * @param agent to check valid Games appropriate with user's mobile
     * @param number number of items want to display on once
     * @return count of pages to loop, use for splitting
     * @throws SQLException when database access error occurs
     */
    public int getPageCountNewest(Agent agent, int number)
            throws SQLException {
        String sql = "";
        if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
            sql = "select count(*) "
                    + " from TIGVIETTEL." + tblGameData
                    + " data, liveinfowap.device d, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v where data.is_publish = 1"
                    + " and data.id = ds.content_instance_id and d.device_id = ds.device_id"
                    + " and upper(d.code) = '" + agent.getMobileModel() + "' and upper(v.code) = '" + agent.getManufactor() + "'";
        } else {
            sql = "select count(*) "
                    + " from " + tblGameData
                    + " where  is_publish = 1";
        }

        int rowCount = 0;
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);
            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        System.out.println("Count: " + rowCount);
        if (rowCount % number == 0) {
            return rowCount / number;
        } else {
            return rowCount / number + 1;
        }
    }

    /**
     * Get count of pages in Hottest page
     * @param number number of items want to display on once
     * @return Page count
     * @throws SQLException when database error occurs
     */
    public int getPageCountHottest(Agent agent, int number)
            throws SQLException {
        String sql = "";
        if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
            sql = "select count(*) "
                    + " from TIGVIETTEL." + tblGameData
                    + " data, liveinfowap.device d, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v where is_hot = 1 and is_publish = 1"
                    + " and data.id = ds.content_instance_id and d.device_id = ds.device_id"
                    + " and upper(d.code) = '" + agent.getMobileModel() + "' and upper(v.code) = '" + agent.getManufactor() + "'";
        } else {
            sql = "select count(*) "
                    + " from " + tblGameData
                    + " where is_hot = 1 and is_publish = 1";
        }


        int rowCount = 0;
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);

            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        System.out.println("Count: " + rowCount);
        if (rowCount % number == 0) {
            return rowCount / number;
        } else {
            return rowCount / number + 1;
        }
    }

    /**
     * Get count of pages when list of all Games in specific Category
     * @param agent a new instance of Agent when get("user_agent") from request
     * @param catId ID of Game Category 
     * @param number number of pages want to display on one page
     * @return count of pages which store all valid Games split by <i>number</i>
     * @throws SQLException 
     */
    public int getPageCountGameInCat(Agent agent, String catId, int number)
            throws SQLException {

        String sql = "";
        if (!"unknown".equalsIgnoreCase(agent.getManufactor()) && !"unknown".equalsIgnoreCase(agent.getMobileModel())) {
            sql =
                    "select count (*) from "
                    + " (select GameCode "
                    + " from TIGVIETTEL." + tblGameData
                    + " data, liveinfowap.device d, liveinfowap.device_support_content ds, LIVEINFOWAP.vendor v where catid = " + catId + " and is_publish = 1 "
                    + " and data.id = ds.content_instance_id and d.device_id = ds.device_id"
                    + " and upper(d.code) = '" + agent.getMobileModel() + "' and upper(v.code) = '" + agent.getManufactor() + "')";
        } else {
            sql =
                    "select count (*) from "
                    + " (select GameCode "
                    + " from " + tblGameData
                    + " where catid = " + catId + " and is_publish = 1 )";
        }


        int rowCount = 0;
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
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

    /**
     * Get count of pages when display all Packages
     * @param agent a new instance of Agent when get("user_agent") from request
     * @param number number of pages want to display on one page
     * @return count of pages which store all valid Games split by <i>number</i>
     */
    public int getPageCountAllPack(Agent agent, int number) throws SQLException {
        String sql = "select count(*) from " + tblGamePack + " where is_publish = 1";
        int rowCount = 0;
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
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

    //huent
    public int getPageCountGameInManufact(Agent agent, String manufact, int number)
            throws SQLException {

        String sql = "";
        sql = "select count (*) from tig_vt_game_data d1 where lower(d1.mobilemanufacture) like lower('%" + manufact + "%')";
        int rowCount = 0;
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
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
}
