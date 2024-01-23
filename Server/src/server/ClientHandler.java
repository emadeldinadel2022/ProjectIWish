
package server;

import controllers.UserController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controllers.ResetPasswordController;
import dataaccesslayer.DAOCart;
import dataaccesslayer.DAOContribution;
import dataaccesslayer.DAOFriends;
import dataaccesslayer.DAOItem;
import dataaccesslayer.DAOUser;
import dataaccesslayer.DAOWishlist_Items;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOCart;
import models.DTOContribution;
import models.DTOContributionList;
import models.DTOFriendlist;
import models.DTOFriendrequest;
import models.DTOItem;
import models.DTONotification;
import models.DTOUser;
import models.DTOWishlist_Items;
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
                DAOFriends DAOPendingRequest;
                DAOWishlist_Items daoWishlist_Items;
                DAOContribution daoContribution;
                DAOCart daoCart;
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
                        daoWishlist_Items = new DAOWishlist_Items();
                        ArrayList<DTOItem> itemsWishlist = daoWishlist_Items.getUserWithlist(userUniqueName);
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
                    case "ADDITEM":
                        gson = new Gson();
                        Request<DTOWishlist_Items> addItemRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOWishlist_Items>>(){}.getType());
                        DTOWishlist_Items dTOWishlist_Items = addItemRequest.getRequestObject();
                        daoWishlist_Items = new DAOWishlist_Items();
                        System.out.println(dTOWishlist_Items.getUserName() + dTOWishlist_Items.getItemID());
                        int returning = daoWishlist_Items.addItems(dTOWishlist_Items);
                        System.out.println(returning + "is the return of inserting new wishlist item");
                        //ArrayList<DTOItem> itemsWishlist1 = daOWishlist_Items.getUserWithlist(dTOWishlist_Items.getUserName());
                        Response<ArrayList<DTOItem>> responseWishlist1 = new Response<>(true,"ADDITEM" , null);
                        jsonResponse = gson.toJson(responseWishlist1);
                        jmsg = new JSONObject();
                        jmsg.put("key", "ADDITEM");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "REMOVEITEM":
                          gson = new Gson();
                          Request<DTOWishlist_Items> removeItemRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOWishlist_Items>>(){}.getType());
                          DTOWishlist_Items dTOWishlist_ItemsRemove = removeItemRequest.getRequestObject();
                          System.out.println(dTOWishlist_ItemsRemove.getUserName() + dTOWishlist_ItemsRemove.getItemID());
                          daoWishlist_Items = new DAOWishlist_Items();
                          int removeOperationResult = daoWishlist_Items.removeItems(dTOWishlist_ItemsRemove);
                          Response<DTOWishlist_Items> responseWishlistItemRemove;
                          if(removeOperationResult > 0){
                                responseWishlistItemRemove = new Response<>(true, "REMOVEITEM", null);
                          }else{
                                responseWishlistItemRemove = new Response<>(false, "REMOVEITEM", null);
                          }
                          jsonResponse = gson.toJson(responseWishlistItemRemove);
                          jmsg = new JSONObject();
                          jmsg.put("key", "REMOVEITEM");
                          jmsg.put("value", jsonResponse);
                          ps.println(jmsg);
                          break;
                    case "SECURITYQUESTION":
                        gson = new Gson();
                        Request<DTOUser> secQuesRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOUser>>(){}.getType());
                        DTOUser checkUser = secQuesRequest.getRequestObject();
                        Response<DTOUser> secQuesResponse;
                        if(ResetPasswordController.checkSecurityQuestionData(checkUser)){
                            secQuesResponse = new Response<>(true, "SECURITYQUESTION", null);
                        }else{
                            secQuesResponse = new Response<>(false, "SECURITYQUESTION", null);
                        }
                        jsonResponse = gson.toJson(secQuesResponse);
                        jmsg = new JSONObject();
                        jmsg.put("key", "SECURITYQUESTION");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "RESETPASSWORD":
                        gson = new Gson();
                        Request<DTOUser> resetPassswordRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOUser>>(){}.getType());
                        DTOUser resetPassUser = resetPassswordRequest.getRequestObject();
                        System.out.println(resetPassUser.getUserUniqueName());
                        System.out.println(resetPassUser.getPassword());
                        Response<DTOUser> resetResponse;
                        if(ResetPasswordController.updateUserPassword(resetPassUser)){
                            resetResponse = new Response<>(true, "RESETPASSWORD", null);
                        }else{
                            resetResponse = new Response<>(false, "RESETPASSWORD", null);
                        }
                        jsonResponse = gson.toJson(resetResponse);
                        jmsg = new JSONObject();
                        jmsg.put("key", "RESETPASSWORD");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                   case "REMOVINGPENDINGREQUEST":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<DTOFriendrequest> removePendingRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOFriendrequest>>(){}.getType());
                        DTOFriendrequest dtoFriendRequest = removePendingRequest.getRequestObject();
                        String removeRequest = removePendingRequest.getRequestType();
                        DAOPendingRequest = new DAOFriends();
                        int res = DAOPendingRequest.declineRequest(dtoFriendRequest);
                        ArrayList<DTOUser> updatePending = DAOPendingRequest.getUserFriendRequestList(dtoFriendRequest.getSender());
                        Response<ArrayList<DTOUser>> responseRemoveRequest = new Response<>(true,"REMOVINGPENDINGREQUEST" , updatePending);
                        jsonResponse = gson.toJson(responseRemoveRequest);
                        jmsg = new JSONObject();
                        jmsg.put("key", "REMOVINGPENDINGREQUEST");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "DELETEFRIEND":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<DTOFriendlist> deleteFriendRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOFriendlist>>(){}.getType());
                        DTOFriendlist dtoFriendList = deleteFriendRequest.getRequestObject();
                        String deleteFriend = deleteFriendRequest.getRequestType();
                        System.out.println(dtoFriendList.getFriend1());
                        System.out.println(dtoFriendList.getFriend2());
                        System.out.println(deleteFriend);
                        dAOFriends = new DAOFriends();
                        int result = dAOFriends.removeFriend(dtoFriendList);
                        ArrayList<DTOUser> updatefriends = dAOFriends.getUserFriendList(dtoFriendList.getFriend1());
                        Response<ArrayList<DTOUser>> responsedeleteFriendList = new Response<>(true,"DELETEFRIEND" , updatefriends);
                        jsonResponse = gson.toJson(responsedeleteFriendList);
                        jmsg = new JSONObject();
                        jmsg.put("key", "DELETEFRIEND");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break; 
                        
                    case "FRIENDWISHLIST":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<String> wishlistFriendRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<String>>(){}.getType());
                        userUniqueName = wishlistFriendRequest.getRequestObject();
                        String wishlistFriend = wishlistFriendRequest.getRequestType();
                        System.out.println(userUniqueName);
                        System.out.println(wishlistFriend);
                        daoWishlist_Items = new DAOWishlist_Items();
                        ArrayList<DTOItem> itemsWishlistFriend = daoWishlist_Items.getUserWithlist(userUniqueName);
                        Response<ArrayList<DTOItem>> responseWishlistFriend = new Response<>(true,"FRIENDWISHLIST" , itemsWishlistFriend);
                        jsonResponse = gson.toJson(responseWishlistFriend);
                        jmsg = new JSONObject();
                        jmsg.put("key", "FRIENDWISHLIST");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "Edit_name": 
                        gson = new Gson();
                        Request<DTOUser> editName = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOUser>>(){}.getType());
                        DTOUser dTOUserN = editName.getRequestObject();
                        DAOUser dAOUserN = new DAOUser();
                        DTOUser updatedUserName = dAOUserN.editUserName(dTOUserN);
                        Response<DTOUser> responseEditName = new Response<>(true, "Edit_name", updatedUserName);
                        jsonResponse = gson.toJson(responseEditName);
                        jmsg = new JSONObject();
                        jmsg.put("key", "Edit_name");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "Edit_passward":
                        gson = new Gson();
                        Request<DTOUser> editPassword = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOUser>>(){}.getType());
                        DTOUser dTOUserP = editPassword.getRequestObject();
                        DAOUser dAOUserP = new DAOUser();
                        returning = dAOUserP.editUserPassword(dTOUserP);
                        System.out.println(returning + " is the return of editing user password");
                        Response<ArrayList<DTOUser>> responseEditPassword = new Response<>(true, "Edit_passward", null);
                        jsonResponse = gson.toJson(responseEditPassword);
                        jmsg = new JSONObject();
                        jmsg.put("key", "Edit_passward");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "Edit_user":
                        gson = new Gson();
                        Request<DTOUser> editUser = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOUser>>(){}.getType());
                        DTOUser dTOUser = editUser.getRequestObject();
                        DAOUser dAOUser = new DAOUser();
                        DTOUser updatedUser = dAOUser.editUser(dTOUser);
                        Response <DTOUser> responseEditUser = new Response<>(true, "Edit_user", updatedUser);
                        jsonResponse = gson.toJson(responseEditUser);
                        jmsg = new JSONObject();
                        jmsg.put("key", "Edit_user");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "Add_balance":
                        gson = new Gson();
                        Request<DTOUser> addBalance = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOUser>>(){}.getType());
                        DTOUser dTOUserBalance = addBalance.getRequestObject();
                        DAOUser dAOUserBalance = new DAOUser();
                        DTOUser updatedUserBalance = dAOUserBalance.addBalance(dTOUserBalance);
                        Response<DTOUser> responseAddBalance = new Response<>(true, "Add_balance", updatedUserBalance);
                        jsonResponse = gson.toJson(responseAddBalance);
                        jmsg = new JSONObject();
                        jmsg.put("key", "Add_balance");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "ACCEPTFRIEND":
                        gson = new Gson();
                        Request<DTOFriendrequest> acceptedFriendRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOFriendrequest>>(){}.getType());
                        DTOFriendrequest acceptedFriend = acceptedFriendRequest.getRequestObject();
                        DAOFriends dAOAcceptFriend = new DAOFriends();
                        System.out.println(acceptedFriend.getSender() + acceptedFriend.getReceiver());
                        int acceptRequestReturn = dAOAcceptFriend.acceptRequest(acceptedFriend);
                        System.out.println(acceptRequestReturn + "is the return of inserting new wishlist item");
                        Response<ArrayList<DTOUser>> responseAcceptFriend = new Response<>(true,"ACCEPTFRIEND" , null);
                        jsonResponse = gson.toJson(responseAcceptFriend);
                        jmsg = new JSONObject();
                        jmsg.put("key", "ACCEPTFRIEND");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "DECLINEFRIEND":
                        gson = new Gson();
                        Request<DTOFriendrequest> declinedFriendRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOFriendrequest>>(){}.getType());
                        DTOFriendrequest declinedFriend = declinedFriendRequest.getRequestObject();
                        DAOFriends dAODeclinedFriend = new DAOFriends();
                        System.out.println(declinedFriend.getSender() + declinedFriend.getReceiver());
                        int declineRequestReturn = dAODeclinedFriend.declineRequest(declinedFriend);
                        System.out.println(declineRequestReturn + "is the return of inserting new wishlist item");
                        Response<ArrayList<DTOUser>> responseDeclineFriend = new Response<>(true,"DECLINEFRIEND" , null);
                        jsonResponse = gson.toJson(responseDeclineFriend);
                        jmsg = new JSONObject();
                        jmsg.put("key", "DECLINEFRIEND");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "USERSEARCHED":
                        gson = new Gson();
                        Request<DTOUser> userSearchedRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOUser>>(){}.getType());
                        DTOUser userSearched = userSearchedRequest.getRequestObject();
                        DAOUser dAOUserSearched = new DAOUser();
                        System.out.println("requested search user is " + userSearched.getUserUniqueName());
                        String searchedUserUniqueName = userSearched.getUserUniqueName();
                       
                        ArrayList<DTOUser> userSearchedReturn = dAOUserSearched.getSearchedUserData(searchedUserUniqueName);
                        System.out.println("directly after database " + userSearchedReturn.get(0));
                        Response<ArrayList<DTOUser>> responseSearchedUser = new Response<ArrayList<DTOUser>>(true, "USERSEARCHED", userSearchedReturn);

                        jsonResponse = gson.toJson(responseSearchedUser);
                        jmsg = new JSONObject();
                        jmsg.put("key", "USERSEARCHED");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "SENDFRIENDREQUEST":
                        gson = new Gson();
                        Request<DTOFriendrequest> sentFriendRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOFriendrequest>>(){}.getType());
                        DTOFriendrequest friendRequest = sentFriendRequest.getRequestObject();
                        DAOFriends dAOFriendRequest = new DAOFriends();
                        System.out.println(friendRequest.getSender() + friendRequest.getReceiver());
                        int friendRequestReturn = dAOFriendRequest.sendRequest(friendRequest);
                        System.out.println(friendRequestReturn + "is the return of inserting new friend request");
                        if (friendRequestReturn != 0) {
                            Response<ArrayList<DTOUser>> responseFriendRequest = new Response<>(true,"SENDFRIENDREQUEST" , null);
                            jsonResponse = gson.toJson(responseFriendRequest);
                            jmsg = new JSONObject();
                            jmsg.put("key", "SENDFRIENDREQUEST");
                            jmsg.put("value", jsonResponse);
                            ps.println(jmsg);
                        } else {
                            Response<ArrayList<DTOUser>> responseFriendRequest = new Response<>(false,"SENDFRIENDREQUEST" , null);
                            jsonResponse = gson.toJson(responseFriendRequest);
                            jmsg = new JSONObject();
                            jmsg.put("key", "SENDFRIENDREQUEST");
                            jmsg.put("value", jsonResponse);
                            ps.println(jmsg);
                        }
                        break;
                    case "SEARCHLIST":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<String> searchListRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<String>>(){}.getType());
                        userUniqueName = searchListRequest.getRequestObject();
                        System.out.println(userUniqueName);
                        dAOUser = new DAOUser();
                        ArrayList<String> searchArrayList = dAOUser.getStrangerUsers(userUniqueName);
                        Response<ArrayList<String>> responseSearchList = new Response<>(true,"SEARCHLIST" , searchArrayList);
                        jsonResponse = gson.toJson(responseSearchList);
                        jmsg = new JSONObject();
                        jmsg.put("key", "SEARCHLIST");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "CONTRIBUTION":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<DTOContribution> contributionRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOContribution>>() {
                        }.getType());
                        daoContribution = new DAOContribution();
                        DTOContribution dTOContribution = contributionRequest.getRequestObject();
                        ArrayList<Integer> amounts = daoContribution.getContributionAmount(dTOContribution.getOwner_name(), dTOContribution.getItem_id());
                        daoItem = new DAOItem();
                        int itemPrice = daoItem.getItemPrice(dTOContribution.getItem_id());
                        Response<ArrayList<DTOItem>> responseContribution;
                        int sumAmounts = 0;
                        for (int num : amounts) {
                            sumAmounts += num;
                        }
                        if (sumAmounts < itemPrice) {
                            daoContribution.makeContribution(dTOContribution);
                            dAOUser = new DAOUser();
                            dAOUser.decreaseBalance(dTOContribution.getContributor_name(), dTOContribution.getContributed_amount());
                            daoWishlist_Items = new DAOWishlist_Items();
                            ArrayList<DTOItem> friendItems = daoWishlist_Items.getUserWithlist(dTOContribution.getOwner_name());
                            responseContribution = new Response<>(true, "CONTRIBUTION", friendItems);
                        } else {
                            responseContribution = new Response<>(false, "CONTRIBUTION", null);
                        }
                        jsonResponse = gson.toJson(responseContribution);
                        jmsg = new JSONObject();
                        jmsg.put("key", "CONTRIBUTION");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "NOTIFICATION":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<String> requestNotification = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<String>>(){}.getType());
                        String currentUserUniqueName = requestNotification.getRequestObject();
                        daoWishlist_Items = new DAOWishlist_Items();
                        ArrayList<DTOItem> completedList = daoWishlist_Items.getCompletedItems(currentUserUniqueName);
                        ArrayList<String> contributers = new ArrayList<>();
                        ArrayList<Integer> contributedAmounts = new ArrayList<>();
                        daoContribution = new DAOContribution();
                        ArrayList<DTONotification> notificationItems = new ArrayList<>();
                        for(DTOItem item: completedList){
                            contributers = daoContribution.getContributors(currentUserUniqueName, item.getItemid());
                            contributedAmounts = daoContribution.getContributionAmount(currentUserUniqueName, item.getItemid());
                            notificationItems.add(new DTONotification(item.getItemid(), item.getItemname(), item.getItemprice(), contributers, contributedAmounts));
                        }
                        System.out.println(notificationItems);
                        Response<ArrayList<DTONotification>> responseNotifications = new Response<>(true, "NOTIFICATION", notificationItems);
                        jsonResponse = gson.toJson(responseNotifications);
                        jmsg = new JSONObject();
                        jmsg.put("key", "NOTIFICATION");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "CONTRIBUTIONLIST":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<String> requestContributionList = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<String>>(){}.getType());
                        String user_unique_name = requestContributionList.getRequestObject();
                        daoContribution = new DAOContribution();
                        ArrayList<DTOContributionList> contributionList = daoContribution.getAllContribution(user_unique_name);
                        ArrayList<Integer> itemsCompleted = new ArrayList<>();
                        for(DTOContributionList dTOContributionList: contributionList){
                            itemsCompleted = daoContribution.getCompleteContributionItemID(dTOContributionList.getOwnerName());
                            if(itemsCompleted.contains(dTOContributionList.getItemID())){
                                dTOContributionList.setStatus("Complete");
                            }else{
                                dTOContributionList.setStatus("Incomplete");
                            }
                        }
                        Response<ArrayList<DTOContributionList>> responseContributionList = new Response<>(true, "CONTRIBUTIONLIST", contributionList);
                        jsonResponse = gson.toJson(responseContributionList);
                        jmsg = new JSONObject();
                        jmsg.put("key", "CONTRIBUTIONLIST");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "CART":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<String> requestCart = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<String>>(){}.getType());
                        String cartUserUniqueName = requestCart.getRequestObject();
                        daoCart = new DAOCart();
                        ArrayList<DTOItem> cartItems = daoCart.getUserCart(cartUserUniqueName);
                        Response<ArrayList<DTOItem>> responseCart = new Response<>(true, "CART", cartItems);
                        jsonResponse = gson.toJson(responseCart);
                        jmsg = new JSONObject();
                        jmsg.put("key", "CART");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "COLLECTITEM":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<DTOCart> itemCartRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOCart>>(){}.getType());
                        DTOCart itemCartCollect = itemCartRequest.getRequestObject();
                        daoCart = new DAOCart();
                        ArrayList<DTOCart> allCartItems = daoCart.getCart();
                        ArrayList<Integer> itemsIDs = new ArrayList<>();
                        daoWishlist_Items = new DAOWishlist_Items();
                        for (DTOCart item: allCartItems){
                            itemsIDs.add(item.getItem_id());
                        }
                        Response<DTOCart> responseItemCart;
                        
                        if(!itemsIDs.contains(itemCartCollect.getItem_id())){
                            daoCart.addToCart(itemCartCollect);
                            daoWishlist_Items.removeItems(new DTOWishlist_Items(itemCartCollect.getUser_name(), itemCartCollect.getItem_id()));
                            responseItemCart = new Response<>(true, "COLLECTITEM", null);
                        }else{
                            responseItemCart = new Response<>(false, "COLLECTITEM", null);
                        }
                        jsonResponse = gson.toJson(responseItemCart);
                        jmsg = new JSONObject();
                        jmsg.put("key", "COLLECTITEM");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                    case "UPLOAD_IMAGE":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<DTOUser> requestUpload_img = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOUser>>(){}.getType());
                        DTOUser userupload_img = requestUpload_img.getRequestObject();
                        String upload_img = requestUpload_img.getRequestType();
                        
                        dAOUser = new DAOUser();
                        int imageSetReturn = dAOUser.addUserPhoto(userupload_img);
                        if (imageSetReturn != 0) {
                            Response<ArrayList<DTOUser>> responseUploadImage = new Response<>(true,"UPLOAD_IMAGE" , null);
                            jsonResponse = gson.toJson(responseUploadImage);
                            jmsg = new JSONObject();
                            jmsg.put("key", "UPLOAD_IMAGE");
                            jmsg.put("value", jsonResponse);
                            ps.println(jmsg);
                        } else {
                            Response<ArrayList<DTOUser>> responseUploadImage = new Response<>(false,"UPLOAD_IMAGE" , null);
                            jsonResponse = gson.toJson(responseUploadImage);
                            jmsg = new JSONObject();
                            jmsg.put("key", "UPLOAD_IMAGE");
                            jmsg.put("value", jsonResponse);
                            ps.println(jmsg);
                        }
                        break;
                        
                    case "CANCELCONTRIBUTION":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<DTOContribution> cancelContributionRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOContribution>>(){}.getType());
                        daoContribution = new DAOContribution();
                        daoContribution.cancelContribution(cancelContributionRequest.getRequestObject());
                        dAOUser = new DAOUser();
                        int balance = cancelContributionRequest.getRequestObject().getContributed_amount();
                        String name = cancelContributionRequest.getRequestObject().getContributor_name();
                        DTOUser updatedBalance = dAOUser.addBalance(new DTOUser(name, balance));
                        
                        Response<DTOUser> cancelContribution = new Response<>(true, "CANCELCONTRIBUTION", updatedBalance);
                        jsonResponse = gson.toJson(cancelContribution);
                        jmsg = new JSONObject();
                        jmsg.put("key", "CANCELCONTRIBUTION");
                        jmsg.put("value", jsonResponse);
                        ps.println(jmsg);
                        break;
                     case "CARTITEMREMOVE":
                        gson = new Gson();
                        System.out.println(jmsg.getString("value"));
                        Request<DTOCart> removeCartItemRequest = gson.fromJson(jmsg.getString("value"), new TypeToken<Request<DTOCart>>(){}.getType());
                        daoCart = new DAOCart();
                        daoCart.removeFromCart(removeCartItemRequest.getRequestObject());
                        Response<DTOCart> removeCartItemResponse = new Response<>(true, "CARTITEMREMOVE", null);
                        jsonResponse = gson.toJson(removeCartItemRequest);
                        jmsg = new JSONObject();
                        jmsg.put("key", "CARTITEMREMOVE");
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
            } catch (SQLException ex) {
                 Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
             }
                  
         }
        
    }
    
     public static void closeConnections() throws IOException {
        for (int i = 0; i < clientsVector.size(); i++) {
            clientsVector.get(i).dis.close();
            clientsVector.get(i).ps.close();
            clientsVector.get(i).clientSocket.close();
            clientsVector.get(i).stop();
        }
    }
    
}
