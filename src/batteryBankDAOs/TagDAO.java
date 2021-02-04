package batteryBankDAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import batteryBank.DBConnection;

public class TagDAO
{
  private static Connection connection;
  
  private static ArrayList<String> tags;
  
  static {
    connection = DBConnection.getConnection();
    refreshTags();
  }
  
  public static int refreshTags()
  {
    int output = 0;
    tags = new ArrayList<String>();
    String skeletion = "SELECT tag_name FROM equipment_tag";
    PreparedStatement statement = null;
    ResultSet tagResults = null;
    try
    {
      statement = connection.prepareStatement(skeletion);
      tagResults = statement.executeQuery();
      
      while (tagResults.next())
      {
        tags.add(tagResults.getString(1));
      }
      
      output = tags.size();
    } catch (Exception e)
    {
      output = -1;
    }
    return output;
  }
  
  public static ArrayList<String> getTags()
  {
    return tags;
  }
}
