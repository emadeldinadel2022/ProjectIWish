/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import queryhandler.DAO;

/**
 *
 * @author Friends
 */
public class Server {
    private static ArrayList<String> emails;
    private static ArrayList<String> userUniqueNames;
    private final static DAO STATICDBOBJECT_DAO = DAO.getInstance();
    private ServerSocket serverSocket;
    private boolean serverStop;
    private String IP;
    Thread threadServer;
    
    
    static {
        try {
            emails = STATICDBOBJECT_DAO.getEmails();
            userUniqueNames = STATICDBOBJECT_DAO.getUserUniqueNames();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Server(){
        try {
            serverSocket = new ServerSocket(5885);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> getUserUniqueNames() {
        return userUniqueNames;
    }

    public static DAO getSTATICDBOBJECT_DAO() {
        return STATICDBOBJECT_DAO;
    }
    
    public ArrayList<String> getEmails() {
        return emails;
    }
    
    class ServerListener implements Runnable{

        @Override
        public void run() {
            while(true){
                try{
                    Socket clientSocket = serverSocket.accept();
                    if(clientSocket.isConnected()){
                        IP = String.valueOf(clientSocket.getInetAddress());
                        ClientHandler clientHandler = new ClientHandler(clientSocket);
                    }
                }catch(IOException ex){
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    /*
    public void setUserNames(ArrayList<String> userNames){
        Server.userNames = userNames;
    }
    
    public void setEmails(ArrayList<String> emails){
        Server.emails = emails;
    }
    */
    
}
