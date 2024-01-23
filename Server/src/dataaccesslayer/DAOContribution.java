package dataaccesslayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOContribution;
import models.DTOContributionList;

public class DAOContribution {

    public int makeContribution(DTOContribution dTOContribution) {
        int result = 0;
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Insert into contribution values (?, ?, ?, ?, now());")) {
                statement.setString(1, dTOContribution.getContributor_name());
                statement.setString(2, dTOContribution.getOwner_name());
                statement.setInt(3, dTOContribution.getItem_id());
                statement.setInt(4, dTOContribution.getContributed_amount());
                result = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<String> getContributors(String owner_name, int item_id) {
        ArrayList<String> contributors = new ArrayList<>();
        try (PreparedStatement statement = DAO.getConnection().prepareCall("select idcontributor_name from contribution where Owner_name = ? and Item_id = ? order by idcontributor_name;")) {
            statement.setString(1, owner_name);
            statement.setInt(2, item_id);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                contributors.add(res.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOContribution.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contributors;
    }

    public ArrayList<Integer> getContributionAmount(String owner_name, int item_id) {
        ArrayList<Integer> amounts = new ArrayList<>();
        try (PreparedStatement statement = DAO.getConnection().prepareCall("select contributed_amount from contribution where Owner_name = ? and Item_id = ? order by idcontributor_name;")) {
            statement.setString(1, owner_name);
            statement.setInt(2, item_id);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                amounts.add(res.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOContribution.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amounts;

    }

    public ArrayList<DTOContributionList> getAllContribution(String userUniqueName) {
        ArrayList<DTOContributionList> contributionList = new ArrayList<>();
        try (PreparedStatement statement = DAO.getConnection().prepareCall("select cont.Owner_name, cont.item_id, item.itemName, item.itemPrice, cont.total_amount\n"
                + "from item\n"
                + "join (\n"
                + "select Owner_name, item_id, sum(contributed_amount) as total_amount\n"
                + "from contribution\n"
                + "where idcontributor_name = ?\n"
                + "and item_id \n"
                + "group by Owner_name, Item_id) cont\n"
                + "on item.itemID = cont.item_id;")) {
            statement.setString(1, userUniqueName);

            ResultSet res = statement.executeQuery();
            while (res.next()) {
                contributionList.add(new DTOContributionList(res.getString(1), res.getInt(2), res.getString(3), res.getInt(4), res.getInt(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOContribution.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contributionList;

    }

    public ArrayList<Integer> getCompleteContributionItemID(String userUniqueName) {
        ArrayList<Integer> completedItems = new ArrayList<>();
        try (PreparedStatement statement = DAO.getConnection().prepareCall("select item.itemID\n"
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
                + "where wishlist_items.User_name = ? and COALESCE(itemPrice - cont.total_cont, -1) = 0;"
        )) {
            statement.setString(1, userUniqueName);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                completedItems.add(res.getInt(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOWishlist_Items.class.getName()).log(Level.SEVERE, null, ex);
        }
        return completedItems ;

    }
    
    public int cancelContribution(DTOContribution dTOContribution) {
        int result = 0;
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("delete from contribution\n"
                    + "where idcontributor_name = ? and Owner_name = ? and item_id = ?;")) {
                statement.setString(1, dTOContribution.getContributor_name());
                statement.setString(2, dTOContribution.getOwner_name());
                statement.setInt(3, dTOContribution.getItem_id());
                result = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
