/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.models;

/**
 *
 * @author Mostafa Mohamed
 */
public class DTOWishlist_Items {
    private String userName; 
    private int itemID;

    public DTOWishlist_Items(String userName, int itemID) {
        this.userName = userName;
        this.itemID = itemID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
    
    
}
