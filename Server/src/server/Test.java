/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dataaccesslayer.DAOItem;
import dataaccesslayer.DAOUser;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Friends
 */
public class Test {
    
    public static void main(String []args){
        
        DAOItem daoItem = new DAOItem();
        System.out.println(daoItem.getItems());
        
    }
    
}
