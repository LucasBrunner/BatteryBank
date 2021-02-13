package battery;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import batteryBank.DBConnection;

/**
 * The Data Access Object for {@link Battery} objects.
 * 
 * @author Lucas Brunner
 */
public class BatteryDAO
{
  private static Connection connection;
  
  static
  {
    connection = DBConnection.getConnection();
  }
  
  /**
   * Queries the database for a list of all {@link Battery} names and IDs.
   * 
   * @param _out An {@link ArrayList} of {@link SimpleBattery} objects. All found {@link Battery} names and IDs will be added to this list.
   * @return Whether or not the operation was successful.
   */
  public static boolean getBatteryNames(ArrayList<SimpleBattery> _out)
  {
    boolean output = false;
    _out.clear();
    
    String skeleton = "Select id, name FROM battery";
    PreparedStatement statement = null;
    ResultSet results = null;
    
    try
    {
      statement = connection.prepareStatement(skeleton);
      results = statement.executeQuery();
      
      while (results.next())
      {
        SimpleBattery b = new SimpleBattery(results.getInt(1), results.getString(2));
        if (b.name == "" || b.name == null)
        {
          b.name = "Battery " + b.ID;
        }
        _out.add(b);
      }
      output = true;
      
    } catch (Exception e)
    {
      // TODO: handle exception
    }
    
    return output;
  }
  
  /**
   * Queries the database for a {@link Battery} with a specified ID.
   * 
   * @param _ID The ID of the battery.
   * @param _out An empty {@link Battery} object. The values of this {@link Battery} will be set to the return values of the query.
   * @return Whether or not the operation was successful.
   */
  public static boolean getBattery(int _ID, Battery _out)
  {
    boolean output = false;
    
    String skeleton1 = "SELECT id, name, connector, voltage, capacity, c_rating, date_of_acquirement, checkups FROM battery WHERE id = ?";
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
      }
      
      statement = connection.prepareStatement(skeleton2);
      statement.setInt(1, _ID);
      results = statement.executeQuery();
      
      results.next();
      
      _out.cycleCount = results.getInt(1);
      
      output = true;
    } catch (Exception e)
    {
      e.printStackTrace();
    }
    
    return output;
  }
  
  /**
   * Removes the {@link Battery} with the specified ID from the database.
   * 
   * @param _ID The ID of the {@link Battery} to be deleted
   * @return Whether or not the operation was successful.
   */
  public static boolean deleteBattery(int _ID)
  {
    boolean output = false;
    String skeletion1 = "DELETE FROM battery WHERE id =?";
    PreparedStatement statement = null;
    try
    {
      statement = connection.prepareStatement(skeletion1);
      statement.setInt(1, _ID);
      
      int rows = statement.executeUpdate();
      output = rows == 1;
    } catch (Exception e)
    {
      e.printStackTrace();
      output = false;
    }
    
    return output;
  }

  /**
   * Removes the specified {@link Battery} from the database.
   * 
   * @param _in The {@link Battery} to be deleted
   * @return Whether or not the operation was successful.
   */
  public static boolean deleteBattery(Battery _in)
  {
    return deleteBattery(_in.ID);
  }

  /**
   * Update the values of a {@link Battery} in the database.
   * 
   * @param _in The battery to update. All of it's values will be updated except for checkups.
   * @return  Whether or not the operation was successful.
   */
  public static boolean updateBattery(Battery _in)
  {
    boolean output = false;
    _in.check();
    String skeleton1 = "UPDATE battery SET name = ?, connector = ?, voltage = ?, capacity = ?, c_rating = ?, date_of_acquirement = ? WHERE id = ?";
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
      
      statement.setInt(7, _in.ID);
      
      int rows = statement.executeUpdate();
      output = rows == 1;
    } catch (Exception e)
    {
      e.printStackTrace();
      output = false;
    }
    
    return output;
  }

  /**
   * Update the values of a {@link Battery} in the database.
   * 
   * @param _in The battery to update. All of it's values will be updated including checkups.
   * @return Whether or not the operation was successful.
   */
  public static boolean updateBatteryWithCheckups(Battery _in)
  {
    boolean output = false;
    _in.check();
    String skeleton1 = "UPDATE battery SET name = ?, connector = ?, voltage = ?, capacity = ?, c_rating = ?, date_of_acquirement = ?, checkups = ? WHERE id = ?";
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
      
      statement.setInt(8, _in.ID);
      
      int rows = statement.executeUpdate();
      output = rows == 1;
    } catch (Exception e)
    {
      e.printStackTrace();
      output = false;
    }
    
    return output;
  }
  
  /**
   * Adds a new {@link Battery} to the database.
   * 
   * @param _in The battery to add.
   * @return Whether or not the operation was successful.
   */
  public static boolean insertBattery(Battery _in)
  {
    boolean output = false;
    _in.check();
    String skeletion1 = "INSERT INTO battery (name, connector, voltage, capacity, c_rating, date_of_acquirement, checkups) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
      
      int rows = statement.executeUpdate();
      output = rows == 1;
    } catch (Exception e)
    {
      e.printStackTrace();
      output = false;
    }
    
    return output;
  }
}
