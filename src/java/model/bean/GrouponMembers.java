/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author noname
 */
public class GrouponMembers {
   private int id;
   private String msisdn;
   private String datelogin;
   private int number;

    public String getDatelogin() {
        return datelogin;
    }

    public void setDatelogin(String datelogin) {
        this.datelogin = datelogin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
   
   
   
    
}



