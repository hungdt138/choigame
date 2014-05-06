/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author noname
 */
public class GrouponTransfers {
    private int id;
    private String idmember;
    private int pricetransfers;
    private String datetime;
    private int price;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdmember() {
        return idmember;
    }

    public void setIdmember(String idmember) {
        this.idmember = idmember;
    }

  

    public int getPricetransfers() {
        return pricetransfers;
    }

    public void setPricetransfers(int pricetransfers) {
        this.pricetransfers = pricetransfers;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
    
}
