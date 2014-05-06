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
import java.util.ArrayList;
import java.util.List;
import model.bean.Advertisement;
import model.bean.Advertisement1;

/**
 *
 * @author Hungdt
 */
public class AdvertisingDAO {
    private Connection conn;
    private PreparedStatement preStmt;
    private ResultSet rs;
    private Logger logger;
    
    public AdvertisingDAO(){
        logger = new Logger(this.getClass().getName());
    }
    
    //Lay thong tin quang cao
    public List<Advertisement> getAd() {
        Advertisement ad = null;
        String sql = "";
        List array = new ArrayList();
        try {
            sql = "Select ad.id, ad.pictures, ad.link, ad.contents, ad.idpos, "
                    + "ad.STATUS from tig_advertising ad, (select * from tig_position) pos "
                    + "where ad.idpos = pos.id and ad.status = 1 and pos.id = 2 and ad.wap = 'VT'";
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
            
            while (rs.next()){
                ad = new Advertisement();
                ad.setId(rs.getInt("id"));
                ad.setPicture(rs.getString("pictures"));
                ad.setLink(rs.getString("link"));
                ad.setContent(rs.getString("contents"));
                ad.setStatus(rs.getInt("status"));
                ad.setIdPos(rs.getInt("idpos"));
                array.add(ad);
            }
        } catch (Exception e) {
            logger.error("[Choigame] Loi phan quang cao: " + e.toString());
        }  finally{
            try {
                Connector.closeConnections(conn, preStmt, rs);
            } catch (SQLException e) {
                logger.error("[Choigame] Loi phan quang cao: " + e.toString());
            }
              
        }
        return array;
    }
    
     public List<Advertisement> getAd3() {
        Advertisement ad1 = null;
        String sql = "";
        List array = new ArrayList();
        try {
            sql = "Select ad.id, ad.pictures, ad.link, ad.contents, ad.idpos, "
                    + "ad.STATUS from tig_advertising ad, (select * from tig_position) pos "
                    + "where ad.idpos = pos.id and ad.status = 1 and pos.id = 1 and ad.wap = 'VT'";
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
            
            while (rs.next()){
                ad1 = new Advertisement();
                ad1.setId(rs.getInt("id"));
                ad1.setPicture(rs.getString("pictures"));
                ad1.setLink(rs.getString("link"));
                ad1.setContent(rs.getString("contents"));
                ad1.setStatus(rs.getInt("status"));
                ad1.setIdPos(rs.getInt("idpos"));
                array.add(ad1);
            }
        } catch (Exception e) {
            logger.error("[Choigame] Loi phan quang cao: " + e.toString());
        }  finally{
            try {
                Connector.closeConnections(conn, preStmt, rs);
            } catch (SQLException e) {
                logger.error("[Choigame] Loi phan quang cao: " + e.toString());
            }
              
        }
        return array;
    }
    
    public Advertisement getAd1() {
        Advertisement ad = null;
        String sql = "";
        List array = new ArrayList();
        try {
            sql = "Select ad.id, ad.pictures, ad.link, ad.contents, ad.idpos, "
                    + "ad.STATUS from tig_advertising ad, (select * from tig_position) pos "
                    + "where pos.id = 2 and ad.idpos = pos.id and ad.status = 1 and ad.wap = 'VT'";
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
            
            while (rs.next()){
                ad = new Advertisement();
                ad.setId(rs.getInt("id"));
                ad.setPicture(rs.getString("pictures"));
                ad.setLink(rs.getString("link"));
                ad.setContent(rs.getString("contents"));
                ad.setStatus(rs.getInt("status"));
                ad.setIdPos(rs.getInt("idpos"));
            }
        } catch (Exception e) {
            logger.error("[Choigame] Loi phan quang cao: " + e.toString());
        }  finally{
            try {
                Connector.closeConnections(conn, preStmt, rs);
            } catch (SQLException e) {
                logger.error("[Choigame] Loi phan quang cao: " + e.toString());
            }
              
        }
        return ad;
    }
    
    public Advertisement1 getAd2() {
        Advertisement1 ad1 = null;
        String sql = "";
        List array = new ArrayList();
        try {
            sql = "Select ad.id, ad.pictures, ad.link, ad.contents, ad.idpos, "
                    + "ad.STATUS from tig_advertising ad, (select * from tig_position) pos "
                    + "where pos.id = 1 and ad.idpos = pos.id and ad.status = 1 and ad.wap = 'VT'";
            conn = Connector.getConnection();
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
            
            while (rs.next()){
                ad1 = new Advertisement1();
                ad1.setId(rs.getInt("id"));
                ad1.setPicture(rs.getString("pictures"));
                ad1.setLink(rs.getString("link"));
                ad1.setContent(rs.getString("contents"));
                ad1.setStatus(rs.getInt("status"));
                ad1.setIdPos(rs.getInt("idpos"));
            }
        } catch (Exception e) {
            logger.error("[Choigame] Loi phan quang cao: " + e.toString());
        }  finally{
            try {
                Connector.closeConnections(conn, preStmt, rs);
            } catch (SQLException e) {
                logger.error("[Choigame] Loi phan quang cao: " + e.toString());
            }
              
        }
        return ad1;
    }
}
