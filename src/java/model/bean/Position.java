/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Hungdt
 */
public class Position {
    private int Id;
    private int Position;
    private String Description;

    public Position(int Id, int Position, String Description) {
        this.Id = Id;
        this.Position = Position;
        this.Description = Description;
    }

    public Position() {
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int Position) {
        this.Position = Position;
    }
    
    
}
