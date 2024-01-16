    package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOItem;

public class DAOItem {
    public ArrayList<DTOItem> getItems() {
        ArrayList<DTOItem> arrItem = new ArrayList<>();
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Select * from item;")) {
                ResultSet res = statement.executeQuery();
                while (res.next()) {
                    arrItem.add(new DTOItem(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrItem;
    }
    
    public ArrayList<DTOItem> getShop(String userUniqueName){
         ArrayList<DTOItem> arrItem = new ArrayList<>();
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("select * from item where itemID not in(select item_ID from wishlist_items where User_name = ?);")) {
                statement.setString(1, userUniqueName);
                ResultSet res = statement.executeQuery();
                while (res.next()) {
                    arrItem.add(new DTOItem(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrItem;   
    }

    public int addItem(DTOItem item) {
        int result = 0;
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Insert into item(`ItemID`, `ItemName`, `ItemDescription`, `ItemPrice`) VALUES (?, ?, ?, ?);")) {
                statement.setInt(1, item.getItemid());
                statement.setString(2, item.getItemname());
                statement.setString(3, item.getItemdescription());
                statement.setInt(4, item.getItemprice());
                result = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int delItem(DTOItem item){
        int result =0;
        Connection con = DAO.getConnection();
        try {
            con.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (PreparedStatement statement = con.prepareCall("Delete from wishlist_items where item_id =?;")) {
                statement.setInt(1, item.getItemid());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        try {
            try (PreparedStatement statement = con.prepareCall("Delete from Item where itemID=?")) {
                statement.setInt(1, item.getItemid());
                result = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        try {
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
            return result;
    }
    
    
    public int countItem() {
        int countItems = 0;
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Select count(*) from item;")) {
                ResultSet res = statement.executeQuery();
                if (res.next()) {
                countItems = res.getInt(1);
                System.out.println(countItems);
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countItems;
    }
}
