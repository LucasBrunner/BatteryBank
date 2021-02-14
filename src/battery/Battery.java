package battery;

import java.time.LocalDate;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Represents a battery in the BatteryBank database.
 * 
 * @author Lucas Brunner 
 */
public class Battery
{
  public int ID;
  public String name;
  public String connector;
  public double voltage;
  public int capacity;
  public int cRating;
  public LocalDate dateOfAquirement;
  public int checkups = 0;  
  public int cycleCount;
  
  public String getViewName()
  {
    String output = name;
    if (name.isEmpty() || name == null || name.trim().isEmpty())
    {
      output = "Battery " + ID;
    }
    return output;
  }
  
  public Battery() {}
  
  public Battery(String _name, String _connector, double _voltage, int _capacity, int _cRating, LocalDate _dateOfAquirement, int _cycleCount)
  {
    name = _name;
    connector = _connector;
    voltage = _voltage;
    capacity = _capacity;
    cRating = _cRating;
    dateOfAquirement = _dateOfAquirement;
    
    cycleCount = _cycleCount;
  }
  
  public Battery(int _ID, String _name, String _connector, double _voltage, int _capacity, int _cRating, LocalDate _dateOfAquirement, int _checkups, int _cycleCount)
  {
    ID = _ID;
    name = _name;
    connector = _connector;
    voltage = _voltage;
    capacity = _capacity;
    cRating = _cRating;
    dateOfAquirement = _dateOfAquirement;
    checkups = _checkups;
    cycleCount = _cycleCount;
  }
  
  public void setBatteryValues(String _name, String _connector, double _voltage, int _capacity, int _cRating, LocalDate _dateOfAquirement)
  {
    name = _name;
    connector = _connector;
    voltage = _voltage;
    capacity = _capacity;
    cRating = _cRating;
    dateOfAquirement = _dateOfAquirement;
  }
  
  public void setBatteryValues(int _ID, String _name, String _connector, double _voltage, int _capacity, int _cRating, LocalDate _dateOfAquirement, int _checkups)
  {
    ID = _ID;
    name = _name;
    connector = _connector;
    voltage = _voltage;
    capacity = _capacity;
    cRating = _cRating;
    dateOfAquirement = _dateOfAquirement;
    checkups = _checkups;
  }

  // Currently not needed. Might reimplement later
  /*
  public void updateBattery(int _ID, String _name, String _connector, double _voltage, int _capacity, int _cRating, LocalDate _dateOfAquirement, int _checkups, int _extraCycles, int _cycleCount)
  {
    ID = _ID;
    name = _name;
    connector = _connector;
    voltage = _voltage;
    capacity = _capacity;
    cRating = _cRating;
    dateOfAquirement = _dateOfAquirement;
    checkups = _checkups;
    extraCycles = _extraCycles;
    
    cycleCount = _cycleCount;
  }
  */
  
  @Override
  public String toString()
  {
    String output;
    output = "ID: " + ID + ", Name: " + name + ", Connector: " + connector + ", Voltage: " + voltage + ", Capacity: " + capacity + ", C-Rating: " + cRating + ", Data Acquired: " + dateOfAquirement + ", Cycles: " + cycleCount;
    return output;
  }
  
  /**
   * Checks to make sure all important values have valid values. If a value does not have a valid value it is reset to the default.
   */
  public void check()
  {
    if (name == null)
      name = "";
    if (connector == null)
      connector = "Default";
    if (voltage < 0)
      voltage = 0;
    if (capacity < 0)
      capacity = 0;
    if (cRating < 0)
      cRating = 0;
    if (dateOfAquirement == null)
      dateOfAquirement = LocalDate.now();
    if (checkups < 0)
      checkups = 0;
  }
  
  /**
   * Creates a pane which displays all of the information about this battery.
   * 
   * @return The {@link Pane} this method created.
   */
  public Pane getView()
  {
    GridPane content = new GridPane();
    content.setHgap(10);
    content.setVgap(10);
    
    // Titles
    Text battName = new Text("Name:");
    Text battConnector = new Text("Connector:");
    Text battVoltage = new Text("Voltage:");
    Text battCapacity = new Text("Capacity (mAh):");
    Text battCRating = new Text("C Rating:");
    Text battDOA = new Text("Date Acquired:");
    Text battCycles = new Text("Current Cycles:");
    Text battCheckups = new Text("Checkups:");
    
    battName.setTextAlignment(TextAlignment.RIGHT);
    battConnector.setTextAlignment(TextAlignment.RIGHT);
    battVoltage.setTextAlignment(TextAlignment.RIGHT);
    battCapacity.setTextAlignment(TextAlignment.RIGHT);
    battCRating.setTextAlignment(TextAlignment.RIGHT);
    battDOA.setTextAlignment(TextAlignment.RIGHT);
    battCycles.setTextAlignment(TextAlignment.RIGHT);
    battCheckups.setTextAlignment(TextAlignment.RIGHT);
    
    content.add(battName, 0, 0);
    content.add(battConnector, 0, 1);
    content.add(battVoltage, 0, 2);
    content.add(battCapacity, 0, 3);
    content.add(battCRating, 0, 4);
    content.add(battDOA, 0, 5);
    // content.add(battCycles, 0, 6);
    // content.add(battCheckups, 0, 7);
    
    // Values
    Text battNameV = new Text(getViewName());
    Text battConnectorV = new Text(connector != null ? connector : "-");
    Text battVoltageV = new Text(voltage != 0 ? String.valueOf(voltage) : "-");
    Text battCapacityV = new Text(capacity != 0 ? String.valueOf(capacity) : "-");
    Text battCRatingV = new Text(cRating != 0 ? String.valueOf(cRating) : "-");
    Text battDOAV = new Text(dateOfAquirement != null ? dateOfAquirement.toString() : "");
    // Text battCyclesV = new Text(String.valueOf(cycleCount));
    // Text battCheckupsV = new Text(String.valueOf(checkups));

    content.add(battNameV, 1, 0);
    content.add(battConnectorV, 1, 1);
    content.add(battVoltageV, 1, 2);
    content.add(battCapacityV, 1, 3);
    content.add(battCRatingV, 1, 4);
    content.add(battDOAV, 1, 5);
    // content.add(battCyclesV, 1, 6);
    // content.add(battCheckupsV, 1, 7);
    
    return content;
  }
}
