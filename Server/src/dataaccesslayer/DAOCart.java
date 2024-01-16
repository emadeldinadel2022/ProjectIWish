package dataaccesslayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOCart;

public class DAOCart {
    public ArrayList<DTOCart> getCart() {
        ArrayList<DTOCart> arrCart = new ArrayList<>();
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Select * from cart;")) {
                ResultSet res = statement.executeQuery();
                while (res.next()) {
                    arrCart.add(new DTOCart(res.getString(1), res.getInt(2)));
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
                statement.setString(1, add.getUser_name());
                statement.setInt(2, add.getItem_id());
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
                statement.setString(1, remove.getUser_name());
                statement.setInt(2, remove.getItem_id());
                result = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
