package controllers;

import dataaccesslayer.DAOUser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOUser;

public class ResetPasswordController {

    public static boolean checkSecurityQuestionData(DTOUser checkUser)  {
        try {
            DAOUser daoUser = new DAOUser();
            ArrayList<String> users = daoUser.getUserUniqueNames();
            if(users.contains(checkUser.getUserUniqueName())){
                return daoUser.getUserBalance(checkUser.getUserUniqueName()) > 0 && (daoUser.getUserBirthdate(checkUser.getUserUniqueName()).equals(checkUser.getDob().toString()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false;
    }

    public static boolean updateUserPassword(DTOUser resetUser) {
        DAOUser daoUser = new DAOUser();
        return daoUser.updatePassword(resetUser.getUserUniqueName(), resetUser.getPassword()) > 0;
    }
}
