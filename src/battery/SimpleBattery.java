package battery;

/**
 * A simplified version of the {@link Battery} class. Only contains name and ID variables.
 * 
 * @author Lucas Brunner
 */
public class SimpleBattery
{
  public int ID;
  public String name;

  public String getViewName()
  {
    String output = name;
    if (name.isEmpty() || name == null || name.trim().isEmpty())
    {
      output = "Battery " + ID;
    }
    return output;
  }
  
  public SimpleBattery(int _ID, String _name) 
  {
    ID = _ID;
    name = _name;
  }
}
