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
import java.util.ArrayList;
import java.util.List;
import model.bean.GameCategory;
import model.bean.GameManufact;

/**
 *
 * @author Nguyen Dinh Doan
 */
public class GameCategoryDAO {

    private Connection conn;
    private PreparedStatement preStmt;
    private ResultSet rs;
    private final String tblGameCat = "TIG_VT_GAME_CAT";
    private final String tblGamemManufact = "TIG_MOBILE_MANUFACT";

    /**
     * Get list of all data in TIG_GAME_CAT table.
     * @return an ArrayList stored all data
     * @throws SQLException if database access error occurs
     */
    public List<GameCategory> getAllGameCat() throws SQLException {
        List<GameCategory> lstGameCat = new ArrayList<GameCategory>();
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement("select CATID, NAME from " + tblGameCat + " order by Name");
            rs = preStmt.executeQuery();
            GameCategory cat;

            while (rs.next()) {
                cat = new GameCategory(rs.getInt(1), rs.getString(2));
                lstGameCat.add(cat);
            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return lstGameCat;
    }

    /**
     * Get Category name by ID
     * @param catId Category ID
     * @return Category name
     * @throws SQLException when database access error occurs
     */
    public String getCatgoryName(String catId) throws SQLException {
        String catName = null;
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(
                    "select name from " + tblGameCat
                    + " where catId = " + catId);
            rs = preStmt.executeQuery();

            while (rs.next()) {
                catName = rs.getString(1);
            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return catName;
    }

    //huent
    public List<GameManufact> getAllGameManufact() throws SQLException {
        List<GameManufact> lstGameManufact = new ArrayList<GameManufact>();
        try {
            String sql = "";
            sql = "select * from tig_mobile_manufact where manufact='Nokia' or manufact='HTC' or manufact='LG' "
                    + "or manufact='SamSung' or manufact='Motorola' or manufact='SonyEricsson' or manufact='BlackBerry'"
                    + "order by MANUFACT";
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
            GameManufact m;
            while (rs.next()) {
                m = new GameManufact(rs.getInt(1), rs.getString(2));
                lstGameManufact.add(m);
            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return lstGameManufact;
    }

    public String getNameManufact(String manufact) throws SQLException {
        //String manufact = null;
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(
                    "select manufact from " + tblGamemManufact
                    + " where manufact = " + manufact);
            rs = preStmt.executeQuery();

            while (rs.next()) {
                manufact = rs.getString(1);
            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return manufact;
    }
}
