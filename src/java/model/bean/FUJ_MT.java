/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Do Tien Hung
 * MT stands for Mobile Terminated 
 * Bat dau tu nha mang va ket thuc la nguoi su dung
 */
public class FUJ_MT {
      int id = 0;
    String cusid = "";
    String statuscode = "";
    String statusmsg="";
    String contenturl ="";
    String dnid = "";
    String fullmsg = "";
    
    public FUJ_MT(){
    }
    public FUJ_MT(int id, String cusid, String statuscode, String statusmsg, String contenturl, String dnid, String fullmsg){
        this.id = id;
        this.cusid = cusid;
        this.statuscode = statuscode;
        this.statusmsg = statusmsg;
        this.contenturl = contenturl;
        this.dnid = dnid;
        this.fullmsg = fullmsg;
    }

    public String getContenturl() {
        return contenturl;
    }

    public void setContenturl(String contenturl) {
        this.contenturl = contenturl;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getDnid() {
        return dnid;
    }

    public void setDnid(String dnid) {
        this.dnid = dnid;
    }

    public String getFullmsg() {
        return fullmsg;
    }

    public void setFullmsg(String fullmsg) {
        this.fullmsg = fullmsg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public String getStatusmsg() {
        return statusmsg;
    }

    public void setStatusmsg(String statusmsg) {
        this.statusmsg = statusmsg;
    }
    
    
}
