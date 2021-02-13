package batteryBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Data Access Object for the database.
 * 
 * @author Lucas Brunner
 */
public class DBConnection
{
  private static Connection con = null;
  
  static
  {
    String mySqlUrl = "jdbc:mysql://localhost:3306/batterybank";
    String user = "batterybank_manager";
    String pass = "lipomaster";
    
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
  
  public static Connection getConnection()
  {
    return con;
  }
  
  /**
   * Gets the last auto_increment value to be used by the database.
   * 
   * @return The last auto_increment value to be used by the database. 
   */
  public static int getLastInsertID()
  {
    PreparedStatement statement = null;
    ResultSet results = null;
    int output = 0;
    
    try
    {
      statement = con.prepareStatement("SELECT LAST_INSERT_ID()");
      results = statement.executeQuery();
      results.next();
      output = results.getInt(1);
    } catch (Exception e)
    {
      // TODO: handle exception
    }
    return output;
  }
}
