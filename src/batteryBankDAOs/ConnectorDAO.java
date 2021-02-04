package batteryBankDAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import batteryBank.DBConnection;

public class ConnectorDAO
{
  private static Connection connection;
  
  private static ArrayList<String> connectors;
  
  static {
    connection = DBConnection.getConnection();
    refreshConnectors();
  }
  
  public static int refreshConnectors()
  {
    int output = 0;
    connectors = new ArrayList<String>();
    String skeletion = "SELECT connector_name FROM connector_type";
    PreparedStatement statement = null;
    ResultSet tagResults = null;
    try
    {
      statement = connection.prepareStatement(skeletion);
      tagResults = statement.executeQuery();
      
      while (tagResults.next())
      {
        connectors.add(tagResults.getString(1));
      }
      
      output = connectors.size();
    } catch (Exception e)
    {
      output = -1;
    }
    
    return output;
  }
  
  public static ArrayList<String> getConnectorList()
  {
    return connectors;
  }
  
  public static boolean updateConnector(String _name, String _newName)
  {
    boolean output = false;
    String skeletion1 = "UPDATE connector_type SET connector_name = ? WHERE connector_name = ?";
    PreparedStatement statement = null;
    try
    {
      statement = connection.prepareStatement(skeletion1);
      statement.setString(1, _newName);
      statement.setString(2, _name);
      
      int rows = statement.executeUpdate();
      
    } catch (Exception e)
    {
      e.printStackTrace();
      output = false;
    }
    
    refreshConnectors();
    return output;
  }
  
  public static boolean deleteConnector(String _name)
  {
    boolean output = false;
    String skeletion1 = "DELETE FROM connector_type WHERE connector_name = ?";
    PreparedStatement statement = null;
    try
    {
      statement = connection.prepareStatement(skeletion1);
      statement.setString(1, _name);
      
      int rows = statement.executeUpdate();
      
    } catch (Exception e)
    {
      e.printStackTrace();
      output = false;
    }
    
    refreshConnectors();
    return output;
  }
  
  public static boolean insertConnector(String _name)
  {
    boolean output = false;
    String skeletion1 = "INSERT INTO connector_type (connector_name) VALUES (?)";
    PreparedStatement statement = null;
    try
    {
      statement = connection.prepareStatement(skeletion1);
      statement.setString(1, _name);
      
      int rows = statement.executeUpdate();
      
    } catch (Exception e)
    {
      e.printStackTrace();
      output = false;
    }
    
    refreshConnectors();
    return output;
  }
}
