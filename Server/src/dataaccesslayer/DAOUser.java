package dataaccesslayer;

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
        try (PreparedStatement statement = DAO.getConnection().prepareCall("SELECT name, email, balance, dob, gender From user WHERE user_unique_name = ?;")) {
            statement.setString(1, userUniqueName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new DTOUser(userUniqueName, resultSet.getString("email"), resultSet.getString("name"), resultSet.getInt("balance"),
                            resultSet.getDate("dob").toLocalDate(), resultSet.getString("gender"));
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
    
    public int delUser(DTOUser user){
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
}
