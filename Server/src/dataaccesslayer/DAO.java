
package dataaccesslayer;


import java.sql.DriverManager;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
    private static DAO instance; // Singleton instance
    private static Connection con;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/iwish";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ُ@212بشيس$";
    
    
    private DAO(){//make the constructor private for the enforce just there is one oebject from this class to apply the signleton design pattern
        try {
             DriverManager.registerDriver(new Driver());
            con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, "Error establishing database connection", ex);
            System.exit(1);
        } 
    }

    public static Connection getConnection() {
        return con;
    }
    
    public static DAO getInstance() {//instansation method for signleton object
        if (instance == null) {
            instance = new DAO();
        }
        return instance;
    }
    
    public void getMetaData() throws SQLException{
        DatabaseMetaData metaData = con.getMetaData();

        // Retrieve database information
        System.out.println("Database Product Name: " + metaData.getDatabaseProductName());
        System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());
        System.out.println("Driver Name: " + metaData.getDriverName());
        System.out.println("Driver Version: " + metaData.getDriverVersion());
        System.out.println("schema: " +metaData.getConnection().getCatalog());
    }
     
    public static void closeConnection() throws SQLException {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                con = null;
                instance = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
