package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import dataaccesslayer.DAO;

public class Server {

    private static DAO STATICDBOBJECT_DAO;
    private ServerSocket serverSocket;
    private boolean serverStatus;
    private static Server serverInstance;
    private String IP;
    Thread threadServer;

    private Server() {
        try {
            serverSocket = new ServerSocket(5885);
            //STATICDBOBJECT_DAO = DAO.getInstance();

            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DAO getSTATICDBOBJECT_DAO() {
        return STATICDBOBJECT_DAO;
    }

    public boolean isServerRunning() {
        return serverStatus;
    }

    class ServerThread implements Runnable {

        @Override
        public void run() {
            //while (serverStatus) {
                try {
                    System.out.println("Server Start to accept clients' sockets");
                    Socket clientSocket = serverSocket.accept();
                    if (clientSocket.isConnected()) {
                        IP = String.valueOf(clientSocket.getInetAddress());
                        ClientHandler clientHandler = new ClientHandler(clientSocket);
                        clientHandler.start();
                        System.out.println(IP + " has connected\n");
                    }
                } catch (IOException ex) {
                    if (!serverSocket.isClosed()) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    } else {
                       // break;
                   // }
                }
            }
        }
    }

     public static Server getInstance() {//instansation method for signleton object
        if (serverInstance == null) {
            serverInstance = new Server();
        }
        return serverInstance;
    }
     
    public void startServer() {
        serverStatus = true;
        System.out.println("Server is starting");
        DAO.getInstance();
        System.out.println("Server connected to Database successfully");
        serverInstance.threadServer = new Thread(serverInstance.new ServerThread());
        serverInstance.threadServer.start();
        System.out.println("Server thread is initiated");
        System.out.println("Server is running.");
    }

    public void stopServer() {
        try {
            System.out.println("Server is shuting down");
            serverStatus = false;
            ClientHandler.closeConnections();
            System.out.println("Close all clients connections");
            ClientHandler.clientsVector.clear();
            System.out.println("Clear clients handler vector");
            DAO.closeConnection();
            System.out.println("Database Conncetion Closed");
            //serverInstance.threadServer.stop();
            System.out.println("Shut down the server thread");
            serverSocket.close();
            System.out.println("Server stopped");
            
            //serverInstance = new Server();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Error stopping the server", ex);
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
