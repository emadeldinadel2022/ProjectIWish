/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Friends
 */
public class DTOContributionList {
    private String ownerName;
    private int itemID;
    private String itemName;
    private int itemPrice;
    private int totalAmountContribution;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DTOContributionList(String ownerName, int itemID, String itemName, int itemPrice, int totalAmountContribution) {
        this.ownerName = ownerName;
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.totalAmountContribution = totalAmountContribution;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getTotalAmountContribution() {
        return totalAmountContribution;
    }

    public void setTotalAmountContribution(int totalAmountContribution) {
        this.totalAmountContribution = totalAmountContribution;
    }
    
    
    
}
