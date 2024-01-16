package controllers;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOUser;
import dataaccesslayer.DAOUser;
import java.util.ArrayList;
import server.Server;
import server.Server;

public class UserController {

    public static DTOUser loginUser(DTOUser user) {
        DAOUser daoUser = new DAOUser();
        try {
            ArrayList<String> existUsers = daoUser.getUserUniqueNames();
            if (existUsers.contains(user.getUserUniqueName())) {
                if (daoUser.isPasswordCorrect(user.getUserUniqueName(), user.getPassword())) {
                    DTOUser retrievedUser = daoUser.getUserData(user.getUserUniqueName());
                    System.out.println("user login successfully");
                    return retrievedUser;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static boolean register(DTOUser user) {
        boolean flag = false;
        System.out.println("controller serverside: " + user.getUserUniqueName());
        DAOUser daoUser = new DAOUser();
        try {
            ArrayList<String> existUsers = daoUser.getUserUniqueNames();
            if (!existUsers.contains(user.getUserUniqueName())) {
                daoUser.addRegisteredUser(user);
                System.out.println("User added successfully");
                flag = true;
                return flag;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

}
