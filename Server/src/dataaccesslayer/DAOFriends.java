package dataaccesslayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOFriendlist;
import models.DTOFriendrequest;
import models.DTOUser;

public class DAOFriends {

    public int sendRequest(DTOFriendrequest request) {
        int result = 0;
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Insert into friend_request values(?, ?);")) {
                statement.setString(1, request.getSender());
                statement.setString(2, request.getReceiver());
                result = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFriends.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<DTOFriendrequest> showFriendRequests() {
        ArrayList<DTOFriendrequest> arrFriendrequest = new ArrayList<>();
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Select * from friend_request;")) {
                ResultSet res = statement.executeQuery();
                while (res.next()) {
                    arrFriendrequest.add(new DTOFriendrequest(res.getString(1), res.getString(2)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFriends.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrFriendrequest;
    }

    public ArrayList<DTOFriendlist> showFriendlist() {
        ArrayList<DTOFriendlist> arrFriendlist = new ArrayList<>();
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Select * from friends_list;")) {
                ResultSet res = statement.executeQuery();
                while (res.next()) {
                    arrFriendlist.add(new DTOFriendlist(res.getString(1), res.getString(2)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFriends.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrFriendlist;
    }

    public int acceptRequest(DTOFriendrequest accept) {
        int result1 = 0;
        int result2 = 0;
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Insert into friends_list values (?,?, now());")) {
                statement.setString(1, accept.getSender());
                statement.setString(2, accept.getReceiver());
                result1 = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFriends.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("delete from friend_request where sender_name=? AND receiver_name=?;")) {
                statement.setString(1, accept.getSender());
                statement.setString(2, accept.getReceiver());
                result2 = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFriends.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result1 + result2;
    }

    public int removeFriend(DTOFriendlist remove) {
        int result = 0;
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Delete from friends_list where friend1_name = ? and friend2_name = ?;")) {
                statement.setString(1, remove.getFriend1());
                statement.setString(2, remove.getFriend2());
                result = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFriends.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int declineRequest(DTOFriendrequest decline) {
        int result = 0;
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("Delete from friend_request where sender_name = ? and  receiver_name= ?;")) {
                statement.setString(1, decline.getSender());
                statement.setString(2, decline.getReceiver());
                result = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFriends.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<DTOUser> getUserFriendList(String userUniqueName) {
        ArrayList<DTOUser> arrFriendlist = new ArrayList<>();
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("select user_unique_name, email, dob\n"
                    + "from user\n"
                    + "join friends_list\n"
                    + "on (user.user_unique_name = friends_list.friend1_name\n"
                    + "or user.user_unique_name = friends_list.friend2_name)\n"
                    + "where (friends_list.friend1_name = ?\n"
                    + "or friends_list.friend2_name = ?)\n"
                    + "and user.user_unique_name != ?;")) {

                for (int i = 1; i < 4; i++) {
                    statement.setString(i, userUniqueName);
                }
                ResultSet res = statement.executeQuery();
                while (res.next()) {
                    arrFriendlist.add(new DTOUser(res.getString(1), res.getString(2), res.getDate(3)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFriends.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrFriendlist;
    }

    public ArrayList<DTOUser> getUserFriendRequestList(String userUniqueName) {
        ArrayList<DTOUser> arrFriendlist = new ArrayList<>();
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("select user_unique_name, email, dob\n"
                    + "from user\n"
                    + "join friend_request\n"
                    + "on friend_request.sender_name  = user.user_unique_name\n"
                    + "where friend_request.receiver_name = ?;")) {

                statement.setString(1, userUniqueName);
                ResultSet res = statement.executeQuery();
                while (res.next()) {
                    arrFriendlist.add(new DTOUser(res.getString(1), res.getString(2), res.getDate(3)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFriends.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrFriendlist;
    }

    public ArrayList<DTOUser> getUserPendingList(String userUniqueName) {
        ArrayList<DTOUser> arrFriendlist = new ArrayList<>();
        try {
            try (PreparedStatement statement = DAO.getConnection().prepareCall("select user_unique_name, email, dob\n"
                    + "from user\n"
                    + "join friend_request\n"
                    + "on friend_request.receiver_name  = user.user_unique_name\n"
                    + "where friend_request.sender_name = ?;")) {

                statement.setString(1, userUniqueName);
                ResultSet res = statement.executeQuery();
                while (res.next()) {
                    arrFriendlist.add(new DTOUser(res.getString(1), res.getString(2), res.getDate(3)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFriends.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrFriendlist;
    }

}
