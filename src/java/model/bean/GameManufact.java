/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Nguyen thi Hue
 */
public class GameManufact {
    private int id;
    private String manufact;
    private String ExInfo;

    public String getExInfo() {
        return ExInfo;
    }

    public void setExInfo(String ExInfo) {
        this.ExInfo = ExInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufact() {
        return manufact;
    }

    public void setManufact(String manufact) {
        this.manufact = manufact;
    }

    public GameManufact(int id, String manufact, String ExInfo) {
        this.id = id;
        this.manufact = manufact;
        this.ExInfo = ExInfo;
    }

    public GameManufact(int id, String manufact) {
        this.id = id;
        this.manufact = manufact;
    }
    
    

    public GameManufact() {
    }
    
}
