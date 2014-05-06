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
public class Game {
    private int dataId;
    private int pId;    // Goi game
    private int catId;
    private String catName; // trong Table Tig_Game_Data khong co Field nay, 
                            //nhưng thêm vào để hiện thị được cả tên Catgory
    private String gameCode;
    private String name;
    private String nameNoSign;
    private String createBy;
    private String UpdateBy;
    private Date createDate;
    private Date updateDate;
    private Date expireDate;
    private String logoUrl;
    private int gamePrice;
    private int isPublic;
    private int isHome;
    private int downloaded;
    private String isHot;
    private String downloadUrl;
    private String mobileManufact;
    private String mobileTypes;
    private String gameInfo;
    private String gameInfoNosign;
    private String supplier;
    private int is2G;
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    /**
     * Default constructor without parameters
     */
    public Game() {
    }

    /**
     * Game with GameCode, Name, GameLogo, GameInfo
     * @param gameCode Code of the Game
     * @param name Name of the Game
     * @param logoUrl URL to Game Image
     * @param gameInfo Informations of the game
     */
    public Game(int id, String name, String logoUrl, String gameInfo, int downloaded) {
        this.dataId = id;
        this.name = name;
        this.logoUrl = logoUrl;
        this.gameInfo = gameInfo;
        this.downloaded = downloaded;
    }

    /**
     * Game with CategoryId, GameCode, Name, GameLogo, GameInfo
     * @param catId ID of the category which store the Game
     * @param gameCode Code of the Game
     * @param name Name of the Game
     * @param logoUrl URL to Game Image
     * @param gameInfo Informations of the game
     */
    public Game(int catId, String gameCode, String name, String logoUrl, String gameInfo) {
        this.catId = catId;
        this.gameCode = gameCode;
        this.name = name;
        this.logoUrl = logoUrl;
        this.gameInfo = gameInfo;
    }
    
    /**
     * Game with CategoryId, CategoryName, Game ID, Name, GameLogo, GameInfo
     * @param catName Name of GameCategory
     * @param catId ID of the category which store the Game
     * @param id ID of the Game
     * @param name Name of the Game
     * @param logoUrl URL to Game Image
     * @param gameInfo Informations of the game
     */
    public Game(int catId, String catName, int id, String name, 
            String logoUrl, String gameInfo) {
        this.catId = catId;
        this.dataId = id;
        this.name = name;
        this.logoUrl = logoUrl;
        this.gameInfo = gameInfo;
        this.catName = catName;
    }    
    
    /**
     * Game with CategoryId, CategoryName, GameCode, Name, GameLogo, GameInfo, MobileTypes
     * @param catName Name of GameCategory
     * @param catId ID of the category which store the Game
     * @param gameCode Code of the Game
     * @param name Name of the Game
     * @param logoUrl URL to Game Image
     * @param gameInfo Informations of the game
     * @param mobileTypes List of all mobile types support the game
     */
    public Game(int catId, String catName, String gameCode, String name, 
            String logoUrl, String gameInfo, String mobileTypes) {
        this.catId = catId;
        this.gameCode = gameCode;
        this.name = name;
        this.logoUrl = logoUrl;
        this.gameInfo = gameInfo;
        this.catName = catName;
        this.mobileTypes = mobileTypes;
    }
    
    /*
     * Gettor and Settor
     */
    
    public String getUpdateBy() {
        return UpdateBy;
    }

    public void setUpdateBy(String UpdateBy) {
        this.UpdateBy = UpdateBy;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public int getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(int downloaded) {
        this.downloaded = downloaded;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(String gameInfo) {
        this.gameInfo = gameInfo;
    }

    public String getGameInfoNosign() {
        return gameInfoNosign;
    }

    public void setGameInfoNosign(String gameInfoNosign) {
        this.gameInfoNosign = gameInfoNosign;
    }

    public int getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(int gamePrice) {
        this.gamePrice = gamePrice;
    }

    public int getIs2G() {
        return is2G;
    }

    public void setIs2G(int is2G) {
        this.is2G = is2G;
    }

    public int getIsHome() {
        return isHome;
    }

    public void setIsHome(int isHome) {
        this.isHome = isHome;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = common.Config.getString("imgUrl") + logoUrl;
    }

    public String getMobileManufact() {
        return mobileManufact;
    }

    public void setMobileManufact(String mobileManufact) {
        this.mobileManufact = mobileManufact;
    }

    public String getMobileType() {
        return mobileTypes;
    }

    public void setMobileType(String mobileType) {
        this.mobileTypes = mobileType;
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

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
