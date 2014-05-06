/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Tig
 */
public class RS_MT {
    private int id=0;
    private String cusid="";
    private String statuscode="";
    private String statusmsg="";
    private String contenturl="";
    private String dnid="";
    private String fullmsg="";

    public RS_MT() {
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
