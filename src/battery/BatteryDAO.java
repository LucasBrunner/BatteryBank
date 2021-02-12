package battery;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import batteryBank.DBConnection;

public class BatteryDAO
{
  private static Connection connection;
  
  static
  {
    connection = DBConnection.getConnection();
  }
  
  public static boolean getBatteryNames(ArrayList<SimpleBattery> _out)
  {
    boolean output = false;
    
    String skeleton = "Select id, name FROM battery";
    PreparedStatement statement = null;
    ResultSet results = null;
    
    try
    {
      statement = connection.prepareStatement(skeleton);
      results = statement.executeQuery();
      
      while (results.next())
      {
        _out.add(new SimpleBattery(results.getInt(1), results.getString(2)));
      }
      output = true;
      
    } catch (Exception e)
    {
      // TODO: handle exception
    }
    
    return output;
  }
  
  public static boolean getBattery(int _ID, Battery _out)
  {
    boolean output = false;
    
    String skeleton1 = "SELECT id, name, connector, voltage, capacity, c_rating, date_of_acquirement, checkups, extra_cycles FROM battery WHERE id = ?";
    String skeleton2 = "SELECT COUNT(*) FROM battery_mission WHERE battery_id = ?";
    PreparedStatement statement = null;
    ResultSet results = null;
    try
    {
      statement = connection.prepareStatement(skeleton1);
      statement.setInt(1, _ID);
      results = statement.executeQuery();
      
      while (results.next())
      {
        _out.ID = results.getInt(1);
        _out.name = results.getString(2);
        _out.connector = results.getString(3);
        _out.voltage = results.getDouble(4);
        _out.capacity = results.getInt(5);
        _out.cRating = results.getInt(6);
        _out.dateOfAquirement = results.getDate(7).toLocalDate();
        _out.checkups = results.getInt(8);
        _out.setCompositeCycles(results.getInt(9));
      }
      
      statement = connection.prepareStatement(skeleton2);
      statement.setInt(1, _ID);
      results = statement.executeQuery();
      
      results.next();
      
      _out.setCompositeCycles(results.getInt(1));
      
      output = true;
    } catch (Exception e)
    {
      e.printStackTrace();
    }
    
    return output;
  }
  
  public static boolean deleteBattery(Battery _in)
  {
    boolean output = false;
    String skeletion1 = "DELETE FROM battery WHERE id =?";
    PreparedStatement statement = null;
    try
    {
      statement = connection.prepareStatement(skeletion1);
      statement.setInt(1, _in.ID);
      
      int rows = statement.executeUpdate();
      output = rows == 1;
    } catch (Exception e)
    {
      e.printStackTrace();
      output = false;
    }
    
    return output;
  }
  
  public static boolean updateBattery(Battery _in)
  {
    boolean output = false;
    if (_in.check())
    {
      String skeleton1 = "UPDATE battery SET name = ?, connector = ?, voltage = ?, capacity = ?, c_rating = ?, date_of_acquirement = ?, checkups = ?, extra_cycles = ? WHERE id = ?";
      PreparedStatement statement = null;
      try
      {
        statement = connection.prepareStatement(skeleton1);
        statement.setString(1, _in.name);
        statement.setString(2, _in.connector);
        statement.setDouble(3, _in.voltage);
        statement.setInt(4, _in.capacity);
        statement.setInt(5, _in.cRating);
        statement.setDate(6, Date.valueOf(_in.dateOfAquirement));
        statement.setInt(7, _in.checkups);
        statement.setInt(8, _in.extraCycles);
        
        statement.setInt(9, _in.ID);
        
        int rows = statement.executeUpdate();
        output = rows == 1;
      } catch (Exception e)
      {
        e.printStackTrace();
        output = false;
      }
    }
    
    return output;
  }
  
  public static boolean insertBattery(Battery _in)
  {
    boolean output = false;
    if (_in.check())
    {
      String skeletion1 = "INSERT INTO battery (name, connector, voltage, capacity, c_rating, date_of_acquirement, checkups, extra_cycles) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement statement = null;
      try
      {
        statement = connection.prepareStatement(skeletion1);
        statement.setString(1, _in.name);
        statement.setString(2, _in.connector);
        statement.setDouble(3, _in.voltage);
        statement.setInt(4, _in.capacity);
        statement.setInt(5, _in.cRating);
        statement.setDate(6, Date.valueOf(_in.dateOfAquirement));
        statement.setInt(7, _in.checkups);
        statement.setInt(8, _in.extraCycles);
        
        int rows = statement.executeUpdate();
        output = rows == 1;
      } catch (Exception e)
      {
        e.printStackTrace();
        output = false;
      }
    }
    
    return output;
  }
}
