package dataaccesslayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOCart;
import models.DTOItem;

public class DAOCart {

    public ArrayList<DTOCart> getCart() {
        ArrayList<DTOCart> arrCart = new ArrayList<>();
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Select * from cart;")) {
                ResultSet res = statement.executeQuery();
                while (res.next()) {
                    arrCart.add(new DTOCart(res.getString(2), res.getInt(1)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrCart;
    }

    public int addToCart(DTOCart add) throws SQLException {
        int result = 0;
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Insert into cart values (?, ?);")) {
                statement.setInt(1, add.getItem_id() );
                statement.setString(2, add.getUser_name());
                result = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int removeFromCart(DTOCart remove) throws SQLException {
        int result = 0;
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Delete from cart where item_id= ? and owner_name=?;")) {
                statement.setInt(1, remove.getItem_id());
                statement.setString(2,  remove.getUser_name());
                result = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<DTOItem> getUserCart(String userUniqueName) {
        ArrayList<DTOItem> arrCart = new ArrayList<>();
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("select itemID, itemName, itemPrice from item, cart\n"
                    + "where cart.owner_name = ? and item.itemID = cart.item_id;")) {
                statement.setString(1, userUniqueName);
                ResultSet res = statement.executeQuery();
                while (res.next()) {
                    arrCart.add(new DTOItem(res.getInt(1), res.getString(2), res.getInt(3)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrCart;
    }
}
