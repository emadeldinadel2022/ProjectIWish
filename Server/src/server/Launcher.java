package server;

import dataaccesslayer.DAOContribution;
import dataaccesslayer.DAOItem;

public class Launcher {
    public static void main(String[] args) {
        Thread runinigThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Server is starting");
                Server serverInstance = Server.getInstance(); 

                serverInstance.threadServer = new Thread(serverInstance.new ServerThread());
                serverInstance.threadServer.start();
                DAOContribution dAOContribution = new DAOContribution();
                DAOItem dAOItem = new DAOItem();
                System.out.println(dAOContribution.getContributors("emad", 4));
                System.out.println(dAOContribution.getContributionAmount("emad", 4));
                System.out.println(dAOItem.getItemPrice(4));
        

                System.out.println("Server is running. ");
                
                serveriwish.MainView.main(args);
                
            }
        });
        runinigThread.start(); 
    }
}

