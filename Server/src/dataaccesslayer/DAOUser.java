package dataaccesslayer;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOUser;

public class DAOUser {

    public ArrayList<String> getUserUniqueNames() throws SQLException {
        ArrayList<String> userUniqueNames = new ArrayList<>();
        try (PreparedStatement statement = DAO.getConnection().prepareCall("Select user_unique_name from user;")) {
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                userUniqueNames.add(res.getString(1));
            }
        }
        return userUniqueNames;
    }

    public ArrayList<String> getEmails() throws SQLException {
        ArrayList<String> emails = new ArrayList<>();
        try (PreparedStatement statement = DAO.getConnection().prepareCall("Select email from user;")) {
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                emails.add(res.getString(1));
            }
        }
        return emails;
    }

    public int addUser(DTOUser user) throws SQLException {
        int result;
        try (PreparedStatement statement = DAO.getConnection().prepareCall("INSERT INTO user(user_unique_name, email, name, password, balance, dob, gender) Values (?,?,?,?,?,?,?)")) {
            statement.setString(1, user.getUserUniqueName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getName());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getBalance());
            statement.setDate(6, user.getDob());
            statement.setString(7, user.getGender());
            result = statement.executeUpdate();
        }
        return result;
    }

    public int addRegisteredUser(DTOUser user) throws SQLException {
        int result;
        try (PreparedStatement statement = DAO.getConnection().prepareCall("INSERT INTO user(user_unique_name, email, name, password, dob, gender) Values (?,?,?,?,?,?)")) {
            statement.setString(1, user.getUserUniqueName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getName());
            statement.setString(4, user.getPassword());
            statement.setDate(5, user.getDob());
            statement.setString(6, user.getGender());
            result = statement.executeUpdate();
        }
        return result;
    }
    
    public int addUserPhoto(DTOUser user) throws SQLException {
        int result;
        try (PreparedStatement statement = DAO.getConnection().prepareCall("update user set photo = ? where user_unique_name = ?")) {
            statement.setBytes(1, user.getImage());
            statement.setString(2, user.getUserUniqueName());
            result = statement.executeUpdate();
        }
        return result;
    }

    public boolean isPasswordCorrect(String userUniqueName, String password) throws SQLException {
        try (PreparedStatement statement = DAO.getConnection().prepareCall("SELECT password FROM user WHERE user_unique_name = ?;")) {
            statement.setString(1, userUniqueName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("password").equals(password);
                }
            }
        }
        return false;
    }

    public DTOUser getUserData(String userUniqueName) throws SQLException {
        try (PreparedStatement statement = DAO.getConnection().prepareCall("SELECT name, email, balance, dob, gender, photo From user WHERE user_unique_name = ?;")) {
            statement.setString(1, userUniqueName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Blob photoBlob = resultSet.getBlob("photo");
                    byte[] photoBytes = null;
                    if (photoBlob != null) {
                        photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
                    }
                    return new DTOUser(
                            userUniqueName,
                            resultSet.getString("email"),
                            resultSet.getString("name"),
                            resultSet.getInt("balance"),
                            resultSet.getDate("dob").toLocalDate(),
                            resultSet.getString("gender"),
                            photoBytes
                    );
                }
            }
        }
        return null;
    }

    public ArrayList<DTOUser> getUsers() throws SQLException {
        ArrayList<DTOUser> users = new ArrayList<>();
        try (PreparedStatement statement = DAO.getConnection().prepareCall("Select * from user;")) {
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                users.add(new DTOUser(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getDate(6).toLocalDate(), res.getString(7)));
            }
        }
        return users;
    }

    public int delUser(DTOUser user) {
        int result = 0;
        Connection connection = null;
        try {
            connection = DAO.getConnection();
            connection.setAutoCommit(false); // Set autocommit to false for transaction
            // Delete friendships first
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM friends_list WHERE friend1_name = ? OR friend2_name = ?")) {
                statement.setString(1, user.getUserUniqueName());
                statement.setString(2, user.getUserUniqueName());
                result = statement.executeUpdate();
            }
            // Delete the user after deleting friendships
            
            
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM wishlist_items WHERE User_name = ?")) {
                statement.setString(1, user.getUserUniqueName());
                result = statement.executeUpdate();
            }
            
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM contribution WHERE Owner_name = ?")) {
                statement.setString(1, user.getUserUniqueName());
                result = statement.executeUpdate();
            }
            
            try (PreparedStatement statement = connection.prepareStatement("update contribution set idcontributor_name = null WHERE idcontributor_name = ?")) {
                statement.setString(1, user.getUserUniqueName());
                result = statement.executeUpdate();
            }
            
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM friend_request WHERE sender_name = ? OR receiver_name = ?")) {
                statement.setString(1, user.getUserUniqueName());
                statement.setString(2, user.getUserUniqueName());
                result = statement.executeUpdate();
            }
            
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE user_unique_name = ?")) {
                statement.setString(1, user.getUserUniqueName());
                result = statement.executeUpdate();
            }
            
            connection.commit(); // Commit the transaction
        } catch (SQLException ex) {
            try {
                if (connection != null) {
                    connection.rollback(); // Rollback the transaction in case of exception
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true); // Set autocommit back to true
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
        return result;
    }
    
    public int countUsers() {
        int countUsers = 0;
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Select count(*) from user;")) {
                ResultSet res = statement.executeQuery();
                if (res.next()) {
                    countUsers = res.getInt(1);
                    System.out.println(countUsers);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countUsers;
    }

    public String getUserBirthdate(String userUniqueName) {
        String dob = "";
        try (PreparedStatement statement = DAO.getConnection().prepareCall("select dob from user where user_unique_name = ?")) {
            statement.setString(1, userUniqueName);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                dob = res.getString(1);
                System.out.println(dob);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dob;
    }

    public int getUserBalance(String userUniqueName) {
        int balance = 0;
        try (PreparedStatement statement = DAO.getConnection().prepareCall("select balance from user where user_unique_name = ?")) {
            statement.setString(1, userUniqueName);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                balance = res.getInt(1);
                System.out.println(balance);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return balance;
    }

    public int updatePassword(String userUniqueName, String password) {
        int updateRes = 0;
        try (PreparedStatement statement = DAO.getConnection().prepareCall("update user set password = ? where user_unique_name = ?;")) {
            statement.setString(1, password);
            statement.setString(2, userUniqueName);
            updateRes = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateRes;
    }
    
     public ArrayList<DTOUser> searchUsers(String search) throws SQLException {
        ArrayList<DTOUser> searchUsers = new ArrayList<>();
        try (PreparedStatement statement = DAO.getConnection().prepareCall("Select * from user where user_unique_name like ? or name like ?;")) {
            statement.setString(1, "%" + search + "%");
            statement.setString(2, "%" + search + "%");
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                searchUsers.add(new DTOUser(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getDate(6).toLocalDate(), res.getString(7)));
            }
        }
        return searchUsers;
    }
     
      public DTOUser editUser(DTOUser user) throws SQLException {
        int result;
        try (PreparedStatement statement = DAO.getConnection().prepareCall("update user set name=?, password= ? where user_unique_name =?;")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUserUniqueName());
            result = statement.executeUpdate();
            statement.close();
        }
        PreparedStatement statement = DAO.getConnection().prepareCall("SELECT name, email, balance, dob, gender From user WHERE user_unique_name = ?;");
        statement.setString(1, user.getUserUniqueName());
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                System.out.println("success");
                return new DTOUser(user.getUserUniqueName(), resultSet.getString("email"), resultSet.getString("name"), resultSet.getInt("balance"),
                        resultSet.getDate("dob").toLocalDate(), resultSet.getString("gender"));
            }
        }
        return null;
    }

    public DTOUser editUserName(DTOUser user) throws SQLException {
        int result;
        try (PreparedStatement statement = DAO.getConnection().prepareCall("update user set name=? where user_unique_name =?;")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getUserUniqueName());
            result = statement.executeUpdate();
        }
        PreparedStatement statement = DAO.getConnection().prepareCall("SELECT name, email, balance, dob, gender From user WHERE user_unique_name = ?;");
        statement.setString(1, user.getUserUniqueName());
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                System.out.println("success");
                return new DTOUser(user.getUserUniqueName(), resultSet.getString("email"), resultSet.getString("name"), resultSet.getInt("balance"),
                        resultSet.getDate("dob").toLocalDate(), resultSet.getString("gender"));
            }
        }
        return null;
    }
    

    public int editUserPassword(DTOUser user) throws SQLException {
        int result;
        try (PreparedStatement statement = DAO.getConnection().prepareCall("update user set password= ? where user_unique_name =?;")) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getUserUniqueName());
            result = statement.executeUpdate();
        }
        return result;
    }

    public DTOUser addBalance(DTOUser user) throws SQLException {
        int result;
        try (PreparedStatement statement = DAO.getConnection().prepareCall("update user set balance = COALESCE(balance, 0) + ? where user_unique_name =?;")) {
            statement.setInt(1, user.getBalance());
            statement.setString(2, user.getUserUniqueName());
            result = statement.executeUpdate();
        }

        PreparedStatement statement2 = DAO.getConnection().prepareCall("Select balance from user where user_unique_name =?;");
        statement2.setString(1, user.getUserUniqueName());
        ResultSet res = statement2.executeQuery();
        while (res.next()) {
            user.setBalance(res.getInt(1));
        }
        return user;
    }
    
   
    
    public int decreaseBalance(String userUniqueName, int amount) throws SQLException {
        int result = 0;
        try (PreparedStatement statement = DAO.getConnection().prepareCall("update user set balance = balance - ? where user_unique_name =?;")) {
            statement.setInt(1, amount);
            statement.setString(2, userUniqueName);
            result = statement.executeUpdate();
        }

        return result;
    }
    
     public ArrayList<DTOUser> getSearchedUserData(String userUniqueName) throws SQLException {
        ArrayList<DTOUser> arrSearchedUser = new ArrayList<>();
        try (PreparedStatement statement = DAO.getConnection().prepareCall("SELECT user_unique_name, email, dob From user WHERE user_unique_name = ?;")) {
            statement.setString(1, userUniqueName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    arrSearchedUser.add(new DTOUser(userUniqueName, resultSet.getString("email"),resultSet.getDate("dob")));

                    return arrSearchedUser;
                }
            }
        }
        return null;
    }
     
      public ArrayList<String> getStrangerUsers(String user_unique_name) throws SQLException {
        ArrayList<String> users = new ArrayList<>();
        try (PreparedStatement statement = DAO.getConnection().prepareCall("select user_unique_name from user where user_unique_name not in \n" +
            "(select friend1_name from friends_list where friend2_name = ?)\n" +
            "and user_unique_name not in (select friend2_name from friends_list where friend1_name = ?)\n" +
            "and user_unique_name not in (select sender_name from friend_request where receiver_name = ?)\n" +
            "and user_unique_name not in  (select receiver_name from friend_request where sender_name = ?)\n" +
            "and user_unique_name != ?")) {
            statement.setString(1, user_unique_name);
            statement.setString(2, user_unique_name);
            statement.setString(3, user_unique_name);
            statement.setString(4, user_unique_name);
            statement.setString(5, user_unique_name);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                users.add(res.getString(1));
            }
        }
        return users;
    }

}
