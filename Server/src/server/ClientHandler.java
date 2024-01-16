
package server;

import controllers.UserController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dataaccesslayer.DAOFriends;
import dataaccesslayer.DAOItem;
import dataaccesslayer.DAOWishlist_Items;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOItem;
import models.DTOUser;
import org.json.JSONException;
import org.json.JSONObject;
import sharedlibraries.Request;
import sharedlibraries.Response;


public class ClientHandler extends Thread{
    DataInputStream dis;
    PrintStream ps;
    Socket clientSocket;
    static Vector<ClientHandler> clientsVector = new Vector<ClientHandler>();
    
    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
        System.out.println("I'm here");
        try {
            dis = new DataInputStream(clientSocket.getInputStream());
             ps = new PrintStream(clientSocket.getOutputStream());
            ClientHandler.clientsVector.add(this);
            System.out.println("I'm here now");
            
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
    @Override
    public void run(){
         while(true){
             try {
                String jsonRequest = dis.readLine();
                Gson gson;
                DAOItem daoItem;
                DAOFriends dAOFriends;
                String userUniqueName;
                JSONObject jmsg = new JSONObject(jsonRequest);
                String key = jmsg.getString("key");
                String jsonResponse;
                switch(key){
                    case "LOGIN":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<DTOUser> requestLogin = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOUser>>(){}.getType());// Read JSON string from the client
                        DTOUser user = requestLogin.getRequestObject();
                        String login = requestLogin.getRequestType();
            
                        System.out.println(user.getUserUniqueName());
                        System.out.println(user.getPassword());
                        DTOUser userLogin = UserController.loginUser(user);
                        if(userLogin != null){
                            Response<DTOUser> responseLogin = new Response<>(true,"LOGIN" , userLogin);
                            jsonResponse = gson.toJson(responseLogin);
                        }else{
                            Response<DTOUser> response = new Response<>(false,"LOGIN" , null);
                            jsonResponse = gson.toJson(response);
                        }
                        jmsg = new JSONObject();
                        jmsg.put("key", "LOGIN");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "REGISTER":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<DTOUser> requestRegister = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOUser>>(){}.getType());
                        DTOUser userRegister = requestRegister.getRequestObject();
                        String register = requestRegister.getRequestType();
                        
                        System.out.println(userRegister.getUserUniqueName());
                        System.out.println(userRegister.getName());
                        System.out.println(userRegister.getDob());
                        System.out.println(userRegister.getEmail());
                        System.out.println(userRegister.getPassword());
                        System.out.println(userRegister.getGender());
                        boolean usrRegisterStatus = UserController.register(userRegister);
                        if(usrRegisterStatus){
                            Response<DTOUser> responseRegister = new Response<>(true,"REGISTER" , null);
                            jsonResponse = gson.toJson(responseRegister);
                        }else{
                            Response<DTOUser> responseRegister = new Response<>(false,"REGISTER" , null);
                            jsonResponse = gson.toJson(responseRegister);
                        }
                        jmsg = new JSONObject();
                        jmsg.put("key", "REGISTER");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "SHOP":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<String> shopRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<String>>(){}.getType());
                        userUniqueName = shopRequest.getRequestObject();
                        String shop = shopRequest.getRequestType();
                        System.out.println(userUniqueName);
                        System.out.println(shop);
                        daoItem = new DAOItem();
                        ArrayList<DTOItem> itemsShop = daoItem.getShop(userUniqueName);
                        Response<ArrayList<DTOItem>> responseShop = new Response<>(true,"SHOP" , itemsShop);
                        jsonResponse = gson.toJson(responseShop);
                        jmsg = new JSONObject();
                        jmsg.put("key", "SHOP");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "WISHLIST":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<String> wishlistRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<String>>(){}.getType());
                        userUniqueName = wishlistRequest.getRequestObject();
                        String wishlist = wishlistRequest.getRequestType();
                        System.out.println(userUniqueName);
                        System.out.println(wishlist);
                        DAOWishlist_Items dAOWishlist_Items = new DAOWishlist_Items();
                        ArrayList<DTOItem> itemsWishlist = dAOWishlist_Items.getUserWithlist(userUniqueName);
                        Response<ArrayList<DTOItem>> responseWishlist = new Response<>(true,"WISHLIST" , itemsWishlist);
                        jsonResponse = gson.toJson(responseWishlist);
                        jmsg = new JSONObject();
                        jmsg.put("key", "WISHLIST");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "FRIENDLIST":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<String> friendListRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<String>>(){}.getType());
                        userUniqueName = friendListRequest.getRequestObject();
                        String friendlist = friendListRequest.getRequestType();
                        System.out.println(userUniqueName);
                        System.out.println(friendlist);
                        dAOFriends = new DAOFriends();
                        ArrayList<DTOUser> friends = dAOFriends.getUserFriendList(userUniqueName);
                        Response<ArrayList<DTOUser>> responseFriendList = new Response<>(true,"FRIENDLIST" , friends);
                        jsonResponse = gson.toJson(responseFriendList);
                        jmsg = new JSONObject();
                        jmsg.put("key", "FRIENDLIST");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "FRIENDREQUEST":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<String> friendRequestsRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<String>>(){}.getType());
                        userUniqueName = friendRequestsRequest.getRequestObject();
                        String friendrequests = friendRequestsRequest.getRequestType();
                        System.out.println(userUniqueName);
                        System.out.println(friendrequests);
                        dAOFriends = new DAOFriends();
                        ArrayList<DTOUser> friendRequests = dAOFriends.getUserFriendRequestList(userUniqueName);
                        Response<ArrayList<DTOUser>> responseFriendRequets = new Response<>(true,"FRIENDREQUEST" , friendRequests);
                        jsonResponse = gson.toJson(responseFriendRequets);
                        jmsg = new JSONObject();
                        jmsg.put("key", "FRIENDREQUEST");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "PENDINGLIST":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<String> pendingListRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<String>>(){}.getType());
                        userUniqueName = pendingListRequest.getRequestObject();
                        String pendingList = pendingListRequest.getRequestType();
                        System.out.println(userUniqueName);
                        System.out.println(pendingList);
                        dAOFriends = new DAOFriends();
                        ArrayList<DTOUser> pendingListArr = dAOFriends.getUserPendingList(userUniqueName);
                        Response<ArrayList<DTOUser>> responsePendingList = new Response<>(true, "PENDINGLIST" ,  pendingListArr);
                        jsonResponse = gson.toJson(responsePendingList);
                        jmsg = new JSONObject();
                        jmsg.put("key",  "PENDINGLIST");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                        
                    default:
                        System.out.println("hello");
                        break;
                }   
                
             }catch (SocketException ex) {
                try {
                    dis.close();
                    ps.close();
                    clientSocket.close();
                    clientsVector.remove(this);
                 } catch (IOException ex1) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex1);
                }
             }catch (JSONException | IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
                  
         }
        
    }
    
}
