package batteryBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
  private static Connection con = null;
  
  // Initialize database connection
  static
  {
    String mySqlUrl = "jdbc:mysql://localhost:3306/batterybank";
    String user = "sampleUser";
    String pass = "1234567890";
    
    try
    {
      con = DriverManager.getConnection(mySqlUrl, user, pass);
    } catch (SQLException e)
    {
      System.out.println("************************");
      System.out.println("Connection failed using credentials of " + user + "/" + pass);
      System.out.println("************************");
      e.printStackTrace();
    }
  }
  
  // Get database connection
  public static Connection getConnection()
  {
    return con;
  }
}
