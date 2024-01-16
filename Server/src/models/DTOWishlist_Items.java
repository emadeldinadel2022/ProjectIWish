package models;

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