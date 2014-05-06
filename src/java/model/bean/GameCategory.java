/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Date;

/**
 *
 * @author Nguyen Dinh Doan
 */
public class GameCategory {
    private int catId;
    private String name;
    private String nameNoSign;
    private String logoURL;
    private String extInfo;
    private Date createDate;

    public GameCategory() {
    }

    public GameCategory(int catId, String name) {
        this.catId = catId;
        this.name = name;
    }

    public GameCategory(int catId, String name, String nameNoSign, String logoURL, String extInfo, Date createDate) {
        this.catId = catId;
        this.name = name;
        this.nameNoSign = nameNoSign;
        this.logoURL = logoURL;
        this.extInfo = extInfo;
        this.createDate = createDate;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameNoSign() {
        return nameNoSign;
    }

    public void setNameNoSign(String nameNoSign) {
        this.nameNoSign = nameNoSign;
    }
}
