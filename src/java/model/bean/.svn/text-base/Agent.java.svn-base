/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Nguyen Dinh Doan
 */
public class Agent {
    private String mobileModel;
    private String mobileNumber;
    private String manufactor;
    private String header;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Agent() {
    }

    public Agent(String mobileName) {
        this.mobileModel = mobileName;
    }

    /**
     * Constructor with mobile name(including model),  and mobile number
     * @param mobileManufact Mobile manufacture
     * @param mobileModel Name and model of user's mobile
     * @param mobileNumber Mobile phone number of user's mobile
     */
    public Agent(String mobileManufact, String mobileModel, String mobileNumber, String header, String ip) {
        this.manufactor = mobileManufact;
        this.mobileModel = mobileModel;
        this.mobileNumber = mobileNumber;
        this.header = header;
        this.ip = ip;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    /**
     * Get Name and model of the mobile
     * e.g: Nokia5300
     * @return 
     */
    public String getMobileModel() {
        return mobileModel;
    }

    public void setMobileModel(String mobileModel) {
        this.mobileModel = mobileModel;
    }

    /**
     * Get mobile number of user. 
     * To do this must use library which was from Viettel, a .jar file
     * @return mobile number
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
    
    
}
