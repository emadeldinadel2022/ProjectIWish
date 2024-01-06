/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.views;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Friends
 */
public class Main extends Application{
    
     @Override
    public void start(Stage stage) throws Exception {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/clientiwish/views/loginview/Login.fxml"));
            Scene scene = new Scene(root);
            
            

            stage.resizableProperty().setValue(false);
            stage.setScene(scene);
            stage.show();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
 
        
        
        
    }
    
}



