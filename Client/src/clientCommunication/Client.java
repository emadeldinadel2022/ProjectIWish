package clientCommunication;

import clientiwish.models.DTOCart;
import clientiwish.models.DTOContribution;
import clientiwish.models.DTOContributionList;
import clientiwish.models.DTOItem;
import clientiwish.models.DTONotification;
import clientiwish.models.DTOUser;
import clientiwish.models.DTOWishlist_Items;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;
import sharedlibraries.Response;
import sharedlibraries.Request;

public class Client {

    private static Socket socket;
    private static DataInputStream dis;
    private static PrintStream ps;
    private static Response response;
    private static DTOUser currentUser;

    public static synchronized DTOUser getCurrentUser() {
        return currentUser;
    }

    public static synchronized void setCurrentUser(DTOUser currentUser) {
        Client.currentUser = currentUser;
    }

    static {
        try {
            socket = new Socket("127.0.0.1", 5885);
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
            System.out.println("client conntect to server");
            ClientReciever clientReciever = new ClientReciever();
            clientReciever.start();
        } catch (ConnectException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static <T> void sendRequest(String requestType, T playLoad) {
        JsonObject msg = new JsonObject();
        try {
            Request<T> request = new Request<>(requestType, playLoad);

            Gson gson = new Gson();
            String jsonRequest = gson.toJson(request);
            msg.addProperty("key", requestType);
            msg.addProperty("value", jsonRequest);

            ps.println(msg);
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized void updateResponse(Response<?> receivedResponse) {
        response = receivedResponse;
    }

    public static Response getResponse() {
        return response;
    }

    static class ClientReciever extends Thread {

        @Override
        public void run() {
            try {

                while (true) {

                    String jsonResponse = dis.readLine();
                    Gson gson;
                    JSONObject jmsg = new JSONObject(jsonResponse);
                    String key = jmsg.getString("key");
                    switch (key) {
                        case "LOGIN":
                            gson = new Gson();

                            System.out.println("Login : " + jsonResponse);
                            Response<DTOUser> responseLogin = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<DTOUser>>() {
                            }.getType());

                            Client.updateResponse(responseLogin);
                            if (responseLogin.isSuccess()) {
                                DTOUser receivedUser = (DTOUser) responseLogin.getData();

                                System.out.println("Received User: " + receivedUser.getUserUniqueName());
                                System.out.println("Received Dob: " + receivedUser.getDob());
                                System.out.println("Received Dob: " + receivedUser.getEmail());
                                System.out.println("Received Dob: " + receivedUser.getName());
                                System.out.println("Received Dob: " + receivedUser.getBalance());
                                System.out.println("Received Dob: " + receivedUser.getGender());
                            } else {
                                System.out.println("user name or password is incorrect");
                            }
                            break;
                        case "REGISTER":
                            gson = new Gson();
                            System.out.println("Register: " + jsonResponse);
                            Response<DTOUser> responseRegister = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<DTOUser>>() {
                            }.getType());

                            Client.updateResponse(responseRegister);
                            if (responseRegister.isSuccess()) {
                                System.out.println("user add successfully");
                            } else {
                                System.out.println("user is already exist, please change the user name");
                            }
                            break;
                        case "SHOP":
                            gson = new Gson();
                            System.out.println("Shop : " + jsonResponse);
                            Response<ArrayList<DTOItem>> responseShop = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOItem>>>() {
                            }.getType());
                            ArrayList<DTOItem> itemsShop = responseShop.getData();
                            Client.updateResponse(responseShop);
                            break;
                        case "WISHLIST":
                            gson = new Gson();
                            System.out.println("wishlist : " + jsonResponse);
                            Response<ArrayList<DTOItem>> responseWishlist = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOItem>>>() {
                            }.getType());
                            ArrayList<DTOItem> itemsWishlist = responseWishlist.getData();
                            Client.updateResponse(responseWishlist);
                            break;
                        case "FRIENDLIST":
                            gson = new Gson();
                            System.out.println("friendlist : " + jsonResponse);
                            Response<ArrayList<DTOUser>> responseFriendlist = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOUser>>>() {
                            }.getType());
                            ArrayList<DTOUser> friendsList = responseFriendlist.getData();
                            Client.updateResponse(responseFriendlist);
                            break;
                        case "FRIENDREQUEST":
                            gson = new Gson();
                            System.out.println("friendlist : " + jsonResponse);
                            Response<ArrayList<DTOUser>> responseFriendRequests = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOUser>>>() {
                            }.getType());
                            ArrayList<DTOUser> friendsRequests = responseFriendRequests.getData();
                            Client.updateResponse(responseFriendRequests);
                            break;
                        case "PENDINGLIST":
                            gson = new Gson();
                            System.out.println("friendlist : " + jsonResponse);
                            Response<ArrayList<DTOUser>> responsePendingList = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOUser>>>() {
                            }.getType());
                            ArrayList<DTOUser> pendingListArr = responsePendingList.getData();
                            Client.updateResponse(responsePendingList);
                            break;
                        case "ADDITEM":
                            gson = new Gson();
                            System.out.println("friendlist : " + jsonResponse);
                            Response<ArrayList<DTOItem>> responseAddedItem = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOItem>>>() {
                            }.getType());
                            ArrayList<DTOItem> addedItemArrayList = responseAddedItem.getData();
                            Client.updateResponse(responseAddedItem);
                            break;
                        case "REMOVEITEM":
                            gson = new Gson();
                            Response<DTOWishlist_Items> responseRemoveItem = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<DTOWishlist_Items>>() {
                            }.getType());
                            Client.updateResponse(responseRemoveItem);
                            break;
                        case "SECURITYQUESTION":
                            gson = new Gson();
                            Response<DTOUser> secQuesResponse = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<DTOUser>>(){}.getType());
                            Client.updateResponse(secQuesResponse);
                            break;
                        case "RESETPASSWORD":
                            gson = new Gson();
                            Response<DTOUser> resetResponse = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<DTOUser>>(){}.getType());
                            Client.updateResponse(resetResponse);
                            break;
                        case "REMOVINGPENDINGREQUEST":
                            gson = new Gson();
                            Response<ArrayList<DTOUser>> responseRemoveRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOUser>>>() {
                            }.getType());
                            ArrayList<DTOUser> updatedpendinglist = responseRemoveRequest.getData();
                            Client.updateResponse(responseRemoveRequest);
                            break;
                        case "DELETEFRIEND":
                            gson = new Gson();
                            System.out.println("friendlist : " + jsonResponse);
                            Response<ArrayList<DTOUser>> responsedeleteFriendlist = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOUser>>>() {
                            }.getType());
                            ArrayList<DTOUser> updatedfriendsList = responsedeleteFriendlist.getData();
                            Client.updateResponse(responsedeleteFriendlist);
                            break;
                        case "FRIENDWISHLIST":
                            gson = new Gson();
                            System.out.println("Friend Wishlist: "+ jsonResponse);
                            Response<ArrayList<DTOItem>> responseWishlistFriend = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOItem>>>() {
                            }.getType());
                            Client.updateResponse(responseWishlistFriend);
                            break;
                        case "Edit_name":
                            gson = new Gson();
                            Response<DTOUser> responseEditName = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<DTOUser>>() {
                            }.getType());
                            Client.updateResponse(responseEditName);
                            if (responseEditName.isSuccess()){
                                DTOUser receivedUser = (DTOUser) responseEditName.getData();
                            }
                            break;
                        case "Edit_passward":
                            gson = new Gson();
                            Response<ArrayList<DTOUser>> responseEditPassword = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOUser>>>() {
                            }.getType());
                            Client.updateResponse(responseEditPassword);
                            break;
                        case "Edit_user":
                            gson = new Gson();
                            Response<DTOUser> responseEditUser = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<DTOUser>>() {
                            }.getType());
                            Client.updateResponse(responseEditUser);
                            if (responseEditUser.isSuccess()){
                                DTOUser receivedUser = (DTOUser) responseEditUser.getData();
                            }
                            break;
                        case "Add_balance":
                            gson = new Gson();
                            Response<DTOUser> responseAddedBalance = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<DTOUser>>() {
                            }.getType());
                            Client.updateResponse(responseAddedBalance);
                            if (responseAddedBalance.isSuccess()) {
                                DTOUser receivedUser = (DTOUser) responseAddedBalance.getData();
                                System.out.println("Balance Added successfully");
                            } else {
                                System.out.println("Error");
                            }
                            break;
                        case "ACCEPTFRIEND":
                            gson = new Gson();
                            //System.out.println("friendlist : " + jsonResponse);
                            Response<ArrayList<DTOUser>> responseAcceptFriend = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOItem>>>() {
                            }.getType());
                            //ArrayList<DTOItem> addedItemArrayList = responseAddedItem.getData();
                            Client.updateResponse(responseAcceptFriend);
                            break;
                        case "DECLINEFRIEND":
                            gson = new Gson();
                            //System.out.println("friendlist : " + jsonResponse);
                            Response<ArrayList<DTOUser>> responsedeclineFriend = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOItem>>>() {
                            }.getType());
                            //ArrayList<DTOItem> addedItemArrayList = responseAddedItem.getData();
                            Client.updateResponse(responsedeclineFriend);
                            break;
                        case "USERSEARCHED":
                            gson = new Gson();
                            System.out.println("user searched : " + jsonResponse);
                            Response<ArrayList<DTOUser>> responseUserSearched = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOUser>>>() {
                            }.getType());
                            ArrayList<DTOUser> searchedUser = responseUserSearched.getData();
                            Client.updateResponse(responseUserSearched);
                            break;
                        case "SENDFRIENDREQUEST":
                            gson = new Gson();
                            //System.out.println("friendlist : " + jsonResponse);
                            Response<ArrayList<DTOUser>> responseFriendRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOItem>>>() {
                            }.getType());
                            //ArrayList<DTOItem> addedItemArrayList = responseAddedItem.getData();
                            Client.updateResponse(responseFriendRequest);
                            break;
                        case "SEARCHLIST":
                            gson = new Gson();
                            Response<ArrayList<String>> responseSearchlist = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<String>>>() {
                            }.getType());
                            ArrayList<String> searchList = responseSearchlist.getData();
                            Client.updateResponse(responseSearchlist);
                            break;
                        case "CONTRIBUTION":
                            gson = new Gson();
                            Response<ArrayList<DTOItem>> responseContribution = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOItem>>>(){}.getType());
                            Client.updateResponse(responseContribution);
                            if (responseContribution.isSuccess()){
                                ArrayList<DTOItem> updatedWishList = responseContribution.getData();
                            }
                            break;
                        case "NOTIFICATION":
                            gson = new Gson();
                            Response<ArrayList<DTONotification>> responseNotification = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTONotification>>>()
                            {}.getType());
                            Client.updateResponse(responseNotification);
                            break;
                        case "CONTRIBUTIONLIST":
                            gson = new Gson();
                            Response<ArrayList<DTOContributionList>> responseContributionList = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOContributionList>>>()
                            {}.getType());
                            Client.updateResponse(responseContributionList);
                            break;
                        case "CART":
                            gson = new Gson();
                            Response<ArrayList<DTOItem>> responseCart = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOItem>>>(){}.getType());
                            Client.updateResponse(responseCart);
                             break;
                        case "COLLECTITEM":
                            gson = new Gson();
                            Response<DTOCart> responseItemCart = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<DTOItem>>(){}.getType());
                            Client.updateResponse(responseItemCart);
                            break;
                        case "UPLOAD_IMAGE":
                            gson = new Gson();
                            Response<ArrayList<DTOUser>> responseUploadImage = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<ArrayList<DTOItem>>>() {
                            }.getType());
                            Client.updateResponse(responseUploadImage);
                            break;
                        case "CANCELCONTRIBUTION":
                            gson = new Gson();
                            Response<DTOUser> responseReturnBalance = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<DTOUser>>(){}.getType());
                            Client.updateResponse(responseReturnBalance);
                            break;
                         case "CARTITEMREMOVE":
                            gson = new Gson();
                            Response<DTOCart> removeCartItemResponse = gson.fromJson(jmsg.getString("value"), new TypeToken<Response<DTOCart>>(){}.getType());
                            Client.updateResponse(removeCartItemResponse);
                            break;

                        default:
                            System.out.println("hi");
                            break;
                    }

                }
            } catch (SocketException ex) {
                try {
                    dis.close();
                    ps.close();
                    socket.close();
                    //Platform.exit();
                    JOptionPane.showMessageDialog(null, "Server is disconnected");

                } catch (IOException ex1) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } 

        }

    }
}
