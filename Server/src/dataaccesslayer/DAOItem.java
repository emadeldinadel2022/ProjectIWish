    package dataaccesslayer;

import java.sql.Blob;
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
                    Blob photoBlob = res.getBlob(5);
                    byte[] photoBytes = null;
                    if (photoBlob != null) {
                        photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
                    }
                    arrItem.add(new DTOItem(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), photoBytes));
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
                    Blob photoBlob = res.getBlob(5);
                    byte[] photoBytes = null;
                    if (photoBlob != null) {
                        photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
                    }
                    arrItem.add(new DTOItem(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), photoBytes));
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
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Insert into item(`ItemName`, `ItemDescription`, `ItemPrice`) VALUES ( ?, ?, ?);")) {
                statement.setString(1, item.getItemname());
                statement.setString(2, item.getItemdescription());
                statement.setInt(3, item.getItemprice());
                result = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int addScrapedItem(DTOItem item) {
        int result = 0;
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Insert into item(`ItemName`,`ItemPrice`, `ItemImage`) VALUES (?, ?, ?);")) {
                statement.setString(1, item.getItemname());
                statement.setInt(2, item.getItemprice());
                statement.setBytes(3, item.getItemimage());
                result = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
   
    
    public int addItemWithImage(DTOItem item) {
        int result = 0;
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Insert into item(`ItemID`, `ItemName`, `ItemDescription`, `ItemPrice`, `ItemImage`) VALUES (?, ?, ?, ?, ?);")) {
                statement.setInt(1, item.getItemid());
                statement.setString(2, item.getItemname());
                statement.setString(3, item.getItemdescription());
                statement.setInt(4, item.getItemprice());
                statement.setBytes(5, item.getItemimage());
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
        
        try (PreparedStatement statement = con.prepareCall("DELETE FROM contribution WHERE item_id = ?")) {
            statement.setInt(1, item.getItemid());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try (PreparedStatement statement = con.prepareCall("DELETE FROM cart WHERE item_id = ?")) {
            statement.setInt(1, item.getItemid());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try (PreparedStatement statement = con.prepareCall("DELETE FROM wishlist_items WHERE item_id = ?")) {
            statement.setInt(1, item.getItemid());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Then delete from Item
        try (PreparedStatement statement = con.prepareCall("DELETE FROM Item WHERE itemID = ?")) {
            statement.setInt(1, item.getItemid());
            result = statement.executeUpdate();
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
    
    public ArrayList<DTOItem> searchItems(String search) throws SQLException {
        ArrayList<DTOItem> searchItems = new ArrayList<>();
        try (PreparedStatement statement = DAO.getConnection().prepareCall("Select * from item where itemname like ? or ItemDescription like ?;")) {
            statement.setString(1, "%" + search + "%");
            statement.setString(2, "%" + search + "%");
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                searchItems.add(new DTOItem(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4)));
            }
        }
        return searchItems;
    }
    
    public int getItemPrice(int item_id){
        int price = 0;
        try(PreparedStatement statement = DAO.getConnection().prepareCall("select itemPrice from item where itemID = ?;")){
            statement.setInt(1, item_id);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                price = res.getInt(1);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }
}
