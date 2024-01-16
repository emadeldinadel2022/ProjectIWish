/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.models;

import java.util.ArrayList;

public class Contributors {
    private String itemName;
    private int itemPrice;
    private String contributors;
   

    public Contributors(String itemName, int itemPrice,ArrayList<String> con) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        setContributors(con);
        
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
        for (int i = 0; i < arrSize; i++) {
            contributors += con.get(i);
            if (i < arrSize - 1) {
                contributors += " , ";
            }
        }
    }
}
