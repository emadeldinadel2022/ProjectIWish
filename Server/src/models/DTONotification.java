/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

public class DTONotification {
    private int itemNumber;
    private String itemName;
    private int itemPrice;
    private String contributors;
    private String contributedAmounts;
   

    public DTONotification(int itemNumber, String itemName, int itemPrice, ArrayList<String> con, ArrayList<Integer> amounts) {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        setContributors(con);
        setContributedAmounts(amounts);
        
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getContributedAmounts() {
        return contributedAmounts;
    }

    public void setContributedAmounts(String contributedAmounts) {
        this.contributedAmounts = contributedAmounts;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getContributors() {
        return contributors;
    }

    public void setContributors(ArrayList<String> con) {
        int arrSize = con.size();
        contributors = "";
        for(String contributor: con){
            contributors += contributor;
            contributors += " , ";
        }
    }
    
    public void setContributedAmounts(ArrayList<Integer> amounts) {
        int arrSize = amounts.size();
        contributedAmounts= "";
        for(Integer amount: amounts){
            contributedAmounts += Integer.toString(amount);
            contributedAmounts += " , ";
        }
    }
}
