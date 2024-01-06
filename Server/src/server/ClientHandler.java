
package server;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;


public class ClientHandler extends Thread{
    DataInputStream dis;
    PrintStream ps;
    Socket clientSocket;
    String IP;
    static Vector<ClientHandler> clientsVector = new Vector<ClientHandler>();
    
    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
        IP = String.valueOf(clientSocket.getInetAddress());
        try {
            dis = new DataInputStream(clientSocket.getInputStream());
             ps = new PrintStream(clientSocket.getOutputStream());
            ClientHandler.clientsVector.add(this);
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    @Override
    public void run(){
        try{
            String msg = dis.readLine(); 
            JSONObject jmsg = new JSONObject(msg); 
            String key = jmsg.getString("Key"); 
            String value;
            Gson gson;
            
            
        }catch (SocketException ex) {
                try {
                    dis.close();
                    ps.close();
                    clientSocket.close();
                    clientsVector.remove(this);
                    this.stop();
                } catch (IOException ex1) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            } 
        
    }
    
}
