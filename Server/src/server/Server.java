
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import dataaccesslayer.DAO;
import dataaccesslayer.DAOUser;

public class Server {
    private static ArrayList<String> emails;
    private static DAO STATICDBOBJECT_DAO;
    private ServerSocket serverSocket;
    private boolean serverStatus;
    private String IP;
    Thread threadServer;

    

    public Server() {
        try {
            serverSocket = new ServerSocket(5885);
            STATICDBOBJECT_DAO = DAO.getInstance();
            serverStatus = true;
            System.out.println("Server connected to Database successfully");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DAO getSTATICDBOBJECT_DAO() {
        return STATICDBOBJECT_DAO;
    }

    class ServerThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    if (clientSocket.isConnected()) {
                        IP = String.valueOf(clientSocket.getInetAddress());
                        ClientHandler clientHandler = new ClientHandler(clientSocket);
                        clientHandler.start();
                        System.out.println(IP + " has connected\n");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
    
    public void stopServer() {
        try {
            serverSocket.close();
            STATICDBOBJECT_DAO.closeConnection();
            // Optionally, close other resources or perform cleanup tasks
            System.out.println("Server stopped");
            System.exit(0);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Error stopping the server", ex);
        }
    }
    /*
    public static void main(String[] args) {
        
        System.out.println("Server is starting");
        Server serverInstance = new Server();

        // Create and start the server thread
        serverInstance.threadServer = new Thread(serverInstance.new ServerThread());
        serverInstance.threadServer.start();
        

        System.out.println("Server is running. Usernames: " + Server.getUserUniqueNames() + ", Emails: " + Server.getEmails());
       

    }
    */
    
    /*
    public static ArrayList<String> getEmails() {
        return emails;
    }
    
    private void setEmails(){
        try {
            DAOUser daoUser = new DAOUser();
            emails = daoUser.getEmails();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */


}
