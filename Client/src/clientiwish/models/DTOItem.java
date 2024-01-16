/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.models;


import java.sql.Blob;


public class DTOItem {
    private int itemid;
    private String itemname;
    private String itemdescription;
    private int itemprice;
    private int itemreminder;
   // private Blob img;

    public DTOItem(String itemname, String itemdescription, int itemprice) {
        this.itemname = itemname;
        this.itemdescription = itemdescription;
        this.itemprice = itemprice;
    }

    public DTOItem(int itemid, String itemname, String itemdescription, int itemprice) {
        this.itemid = itemid;
        this.itemname = itemname;
        this.itemdescription = itemdescription;
        this.itemprice = itemprice;
        //this.img = img;
    }
    
    public DTOItem(int itemid, String itemname, String itemdescription, int itemprice, int itemreminder) {
        this.itemid = itemid;
        this.itemname = itemname;
        this.itemdescription = itemdescription;
        this.itemprice = itemprice;
        this.itemreminder = itemreminder;
    }

    public int getItemreminder() {
        return itemreminder;
    }

    public void setItemreminder(int itemreminder) {
        this.itemreminder = itemreminder;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    public int getItemprice() {
        return itemprice;
    }

    public void setItemprice(int itemprice) {
        this.itemprice = itemprice;
    }

   /* 
    public Blob getImg() {
        return img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }
    */
    
    
}
