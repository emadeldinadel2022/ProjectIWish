/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.models;

/**
 *
 * @author Software
 */
public class DTOItemView {
    
    private String name;
    private String description;
    private int price;
    private int reminder;
    

    public DTOItemView(String name, String description, int price, int reminder) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.reminder = reminder;
    }
    
    public DTOItemView(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getReminder() {
        return reminder;
    }

    public void setReminder(int reminder) {
        this.reminder = reminder;
    }
    
    
    
}
