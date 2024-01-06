package server.controllers;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOUser;
import server.Server;

public class LoginController {

    public static String loginUser(Server serverObject,String userUniqueName, String password) {
        try {
            if (serverObject.getUserUniqueNames().contains(userUniqueName)) {
                if (Server.getSTATICDBOBJECT_DAO().isPasswordCorrect(userUniqueName, password)) {
                    DTOUser retrievedUser = Server.getSTATICDBOBJECT_DAO().getUserData(userUniqueName);
                    return displayUserInfo(retrievedUser);
                } else {
                    return "Incorrect password";
                }
            } else {
                return "User does not exist";
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            return "Error occurred during login";
        }
    }

    private static String displayUserInfo(DTOUser retrievedUser) {
        StringBuilder result = new StringBuilder();
        result.append("Password is correct\n");
        result.append(retrievedUser.getUserUniqueName()).append("\n");
        result.append(retrievedUser.getName()).append("\n");
        result.append(retrievedUser.getEmail()).append("\n");
        result.append(retrievedUser.getGender()).append("\n");
        result.append(retrievedUser.getDob()).append("\n");
        return result.toString();
    }
}
