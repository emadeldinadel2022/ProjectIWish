
package server;


import server.controllers.LoginController;


public class Main {
    
    public static void main(String []args){
        Server serverInstance = new Server();
        
        System.out.println("Usernames: " + serverInstance.getUserUniqueNames());
        System.out.println("Emails: " + serverInstance.getEmails());
        
        String userUniqueName = "emad";
        String password = "12345678";
        
        String loginResult = LoginController.loginUser(serverInstance, userUniqueName, password);
        System.out.println(loginResult);
       
    }
}
