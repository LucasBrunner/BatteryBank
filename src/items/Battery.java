package items;

import java.time.LocalDate;

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
  public int extraCycles = 0;
  
  private int cycleCount;
  public void setCompositeCycles(int _extraCycles) { extraCycles = _extraCycles; }
  public int getTotalCycles() { return cycleCount + extraCycles; }
  
  public Battery() {}
  
  public Battery(int _ID, String _name, String _connector, double _voltage, int _capacity, int _cRating, LocalDate _dateOfAquirement, int _checkups, int _extraCycles, int _cycleCount)
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
    output = "ID: " + ID + ", Name: " + name + ", Connector: " + connector + ", Voltage: " + voltage + ", Capacity: " + capacity + ", C-Rating: " + cRating + ", Data Acquired: " + dateOfAquirement + ", Cycles: " + getTotalCycles();
    return output;
  }
  
  public boolean check()
  {
    boolean output = false;
    
    output = name != null;
    
    if (dateOfAquirement == null)
    {
      dateOfAquirement = LocalDate.now();
    }
    
    return output;
  }
}
