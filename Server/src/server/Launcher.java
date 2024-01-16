package server;

import dataaccesslayer.DAOFriends;
import dataaccesslayer.DAOItem;
import dataaccesslayer.DAOWishlist_Items;
import java.util.ArrayList;
import models.DTOItem;
import models.DTOUser;

public class Launcher {
    public static void main(String[] args) {
        // Launch Admin Dashboard application
        Thread runinigThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Server is starting");
                Server serverInstance = new Server();

                // Create and start the server thread
                serverInstance.threadServer = new Thread(serverInstance.new ServerThread());
                serverInstance.threadServer.start();
        

                System.out.println("Server is running. ");
                DAOFriends dAOFriends = new DAOFriends();
                ArrayList<DTOUser> items = dAOFriends.getUserPendingList("emad");
                System.out.println(items.get(0).getUserUniqueName());
                System.out.println(items.get(0).getEmail());
                serveriwish.MainView.main(args);
                
            }
        });
        runinigThread.start();

       
    }
}

