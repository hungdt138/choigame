/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import common.Config;
import common.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Agent;
import model.bean.GamePackage;

/**
 *
 * @author Nguyen Dinh Doan
 */
public class PackageDAO {

    private Connection conn;
    private PreparedStatement preStmt;
    private ResultSet rs;
    private int shortNum;
    private final String tblGamePackage = "TIG_GAME_PACKAGE";

    /**
     * Default constructor with initialize for <i>shorNum</i> (this is a property 
     * saved in <i>.properties</i> file, use for showing length of Game content 
     * or Package details...). You can change its property by accessing to the 
     * <i>.properties</i> file and change the value which has key is <i>shorNum</i>
     */
    public PackageDAO() {
        shortNum = 40;
        try {
            shortNum = Integer.parseInt(Config.getString("shortNum"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Get some Packages in TIG_VT_GAME_PACKAGE table by randomizing 
     * in query statement
     * @return some Packages. Depend on splitNum in common.properties
     * @throws SQLException when database access error occurs
     */
    public List<GamePackage> getPackageInHome() throws SQLException {
        List<GamePackage> lstPackage = new ArrayList<GamePackage>();
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement("select * from "
                    + " (select pid, name, packagePrice, logoUrl, substr(details, 0, ?) || '...' details "
                    + " from " + tblGamePackage
                    + " where is_publish = 1"
                    + " order by dbms_random.value)"
                    + " where rownum < 7");
            preStmt.setInt(1, shortNum);
            rs = preStmt.executeQuery();

            while (rs.next()) {
                lstPackage.add(new GamePackage(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return lstPackage;
    }

    /**
     * Get list of packages each page with splitting. Results depend on the 
     * current page number, the number items want to display each page and 
     * the count of all package which is valid without splitting and the Agent.
     * @param agent Agent object which was get by get("user-agent")
     * @param page page to jump to
     * @param num number of Packages want to display on once
     * @param count total of split pages
     * @return List of Packages need to display each page
     * @throws SQLException when database access error occurs
     */
    public List<GamePackage> getPackagesEachPage(Agent agent, int page, int num, int count)
            throws SQLException {
        int startIndex;
        if (page == 1) {
            startIndex = 0;
        } else {
            startIndex = page - 1;
        }

        List<GamePackage> lstPackage = new ArrayList<GamePackage>();
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement("select * from "
                    + "(select pid, name, packagePrice, logoUrl, substr(details, 0, ?) || '...' details, rownum r"
                    + " from " + tblGamePackage
                    + " where is_publish = 1"
                    + " order by EditDate)"
                    + " where r > " + (startIndex * num)
                    + "     and r <= " + (page * num));
            preStmt.setInt(1, shortNum);
            rs = preStmt.executeQuery();

            while (rs.next()) {
                lstPackage.add(new GamePackage(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return lstPackage;
    }

    /**
     * Get specific package by id passed from parameter
     * @param id ID of the package
     * @return the Package has the ID
     * @throws SQLException when database access error occurs
     */
    public GamePackage getPackageById(int id) throws SQLException {
        GamePackage pkg = null;
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(
                    "select name, packageprice, details "
                    + " from " + tblGamePackage
                    + " where pid = " + id);
            rs = preStmt.executeQuery();

            while (rs.next()) {
                pkg = new GamePackage(
                        id,
                        rs.getString(1),
                        rs.getInt(2),
                        null,
                        rs.getString(3));
            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return pkg;
    }

    /**
     * Get related packages. This means get some packages (randomizing) which each
     * of them all have ID differences the ID of the current Package.
     * @param id ID of the current package 
     * @return List of related Packages
     * @throws SQLException when database error occurs
     */
    public List<GamePackage> getRelatedPackages(int id) throws SQLException {
        List<GamePackage> lstRelatedPkg = new ArrayList<GamePackage>();
        try {
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(
                    "select pid, name, packagePrice, logoUrl, substr(details, 0, ?) || '...' details "
                    + " from " + tblGamePackage
                    + " where pid <> " + id + " and rownum < 7"
                    + " order by dbms_random.value");
            preStmt.setInt(1, shortNum);
            rs = preStmt.executeQuery();

            while (rs.next()) {
                lstRelatedPkg.add(
                        new GamePackage(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        } finally {
            Connector.closeConnections(conn, preStmt, rs);
        }
        return lstRelatedPkg;
    }
}
