package clientCommunication;

import clientiwish.models.DTOItem;
import clientiwish.models.DTOUser;
import clientiwish.views.userprofileview.shop.ShopController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
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
                        default:
                            System.out.println("hi");
                            break;
                    }

                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);

            }

        }

    }
}
