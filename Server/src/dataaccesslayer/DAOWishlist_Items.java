package dataaccesslayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOItem;
import models.DTOWishlist_Items;

public class DAOWishlist_Items {

    public int addItems(DTOWishlist_Items add) {
        int result = 0;
        try (PreparedStatement statement = DAO.getConnection().prepareCall("Insert into wishlist_items () values (?, ?);")) {

            statement.setString(1, add.getUserName());
            statement.setInt(2, add.getItemID());
            result = statement.executeUpdate();
            //DAO.getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DAOWishlist_Items.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int removeItems(DTOWishlist_Items remove) {
        int result = 0;
        try (PreparedStatement statement = DAO.getConnection().prepareCall("delete from wishlist_items where User_name = ? and Item_ID = ?;")) {

            statement.setString(1, remove.getUserName());
            statement.setInt(2, remove.getItemID());
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOWishlist_Items.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<DTOWishlist_Items> showItems(DTOWishlist_Items show) {
        ArrayList<DTOWishlist_Items> arrWishlist = new ArrayList<>();
        try (PreparedStatement statement = DAO.getConnection().prepareCall("Select * from wishlist_items where User_name = ?;")) {

            statement.setString(1, show.getUserName());
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                arrWishlist.add(new DTOWishlist_Items(res.getString(1), res.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOWishlist_Items.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrWishlist;
    }

    public ArrayList<DTOItem> getUserWithlist(String userUniqueName) {
        ArrayList<DTOItem> arrWishlist = new ArrayList<>();
        try (PreparedStatement statement = DAO.getConnection().prepareCall(
                "select item.itemID, item.itemName, item.itemDescription, item.itemPrice, \n"
                + "		COALESCE(itemPrice - cont.total_cont, -1) as reminder\n"
                + "from item\n"
                + "left join wishlist_items\n"
                + "on item.itemID = wishlist_items.Item_ID\n"
                + "left join (\n"
                + "	select owner_name, item_id,SUM(contributed_amount) as total_cont\n"
                + "    from contribution\n"
                + "    group by owner_name, item_id\n"
                + ") cont\n"
                + "on wishlist_items.User_name = cont.owner_name\n"
                + "and item.itemID = cont.item_id\n"
                + "where wishlist_items.User_name = ?;"
        )) {

            statement.setString(1, userUniqueName);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                arrWishlist.add(new DTOItem(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5) == -1 ? res.getInt(4) : res.getInt(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOWishlist_Items.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrWishlist;
    }

    public ArrayList<DTOItem> getCompletedItems(String userUniqueName) {
        ArrayList<DTOItem> completedList = new ArrayList<>();
        try (PreparedStatement statement = DAO.getConnection().prepareCall("select item.itemID, item.itemName, item.itemPrice, \n"
                + "		COALESCE(itemPrice - cont.total_cont, -1) as reminder\n"
                + "from item\n"
                + "left join wishlist_items\n"
                + "on item.itemID = wishlist_items.Item_ID\n"
                + "left join (\n"
                + "	select owner_name, item_id,SUM(contributed_amount) as total_cont\n"
                + "    from contribution\n"
                + "    group by owner_name, item_id\n"
                + ") cont\n"
                + "on wishlist_items.User_name = cont.owner_name\n"
                + "and item.itemID = cont.item_id\n"
                + "where wishlist_items.User_name = ? and itemPrice - cont.total_cont = 0;"
        )) {
            statement.setString(1, userUniqueName);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                completedList.add(new DTOItem(res.getInt(1), res.getString(2), res.getInt(3)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOWishlist_Items.class.getName()).log(Level.SEVERE, null, ex);
        }
        return completedList;
    }
    

}
