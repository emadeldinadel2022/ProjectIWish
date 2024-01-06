
package queryhandler;


import java.sql.DriverManager;
import java.util.ArrayList;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOUser;

public class DAO {
    private static DAO instance; // Singleton instance
    private Connection con;
    
    
    private DAO(){//make the constructor private for the enforce just there is one oebject from this class to apply the signleton design pattern
        try {
             DriverManager.registerDriver(new Driver());
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/iwish", "root", "ُ@212بشيس$");
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static DAO getInstance() {//instansation method for signleton object
        if (instance == null) {
            instance = new DAO();
        }
        return instance;
    }
    
    public ArrayList<String> getUserUniqueNames() throws SQLException{
        ArrayList<String> userUniqueNames = new ArrayList<>();
        try (PreparedStatement statement = con.prepareCall("Select userUniqueName from user;")) {
            ResultSet res = statement.executeQuery();
            while(res.next()){
                 userUniqueNames.add(res.getString(1));
            }
        }
        return  userUniqueNames;
    }
    
    public ArrayList<String> getEmails() throws SQLException{
        ArrayList<String> emails = new ArrayList<>();
        try (PreparedStatement statement = con.prepareCall("Select email from user;")) {
            ResultSet res = statement.executeQuery();
            while(res.next()){
                emails.add(res.getString(1));
            }
        }
        return emails;
    }
    
     public int addUser (DTOUser user) throws SQLException{
        int result;
        try(PreparedStatement statement = con.prepareCall("INSERT INTO user(userUniqueName, email, name, password, balance, dob, gender) Values (?,?,?,?,?,?,?)")){
            statement.setString(1, user.getUserUniqueName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getName());
            statement.setString(4, user.getPassword());     
            statement.setInt(5, user.getBalance());
            statement.setDate(6, user.getDob());
            statement.setString(7, user.getGender());
            result = statement.executeUpdate();
        }
      return result;
    }
     
     public boolean isPasswordCorrect(String userUniqueName, String password) throws SQLException{
         try(PreparedStatement statement = con.prepareCall("SELECT password FROM user WHERE userUniqueName = ?;")){
             statement.setString(1, userUniqueName);
             try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("password").equals(password);
                }
            }
         }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;   
     }
     
     public DTOUser getUserData(String userUniqueName) throws SQLException{
         try(PreparedStatement statement = con.prepareCall("SELECT name, email, balance, dob, gender From user WHERE userUniqueName = ?;")){
             statement.setString(1, userUniqueName);
             try(ResultSet resultSet = statement.executeQuery()){
                 if(resultSet.next()){
                    return new DTOUser(userUniqueName, resultSet.getString("email"),resultSet.getString("name"), resultSet.getInt("balance"),
                        resultSet.getDate("dob").toLocalDate(), resultSet.getString("gender"));
                }
             }
         }
         return null;
     }
    
    public void closeConnection() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
    
}
