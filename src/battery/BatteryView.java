package battery;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import views.ItemViewBase;

public class BatteryView extends ItemViewBase
{
  Battery b = new Battery();
  
  public BatteryView(int batteryID) {
    super(new HBox());
    
    BatteryDAO.getBattery(batteryID, b);
    
    showBattery(super.body);
  }
  
  private void showBattery(Pane body)
  {
    HBox content = (HBox) body;
    
    // Showing batteries
    GridPane batteryShow = new GridPane();
    batteryShow.setVgap(10);
    batteryShow.setHgap(10);
    
    Text battName = new Text("Name:");
    Text battConnector = new Text("Connector:");
    Text battVoltage = new Text("Voltage:");
    Text battCapacity = new Text("Capacity:");
    Text battCRating = new Text("C Rating:");
    Text battDOA = new Text("Date Acquired:");
    Text battCycles = new Text("Current Cycles:");
    
    battName.setTextAlignment(TextAlignment.RIGHT);
    battConnector.setTextAlignment(TextAlignment.RIGHT);
    battVoltage.setTextAlignment(TextAlignment.RIGHT);
    battCapacity.setTextAlignment(TextAlignment.RIGHT);
    battCRating.setTextAlignment(TextAlignment.RIGHT);
    battDOA.setTextAlignment(TextAlignment.RIGHT);
    battCycles.setTextAlignment(TextAlignment.RIGHT);
    
    batteryShow.add(battName, 0, 0);
    batteryShow.add(battConnector, 0, 1);
    batteryShow.add(battVoltage, 0, 2);
    batteryShow.add(battCapacity, 0, 3);
    batteryShow.add(battCRating, 0, 4);
    batteryShow.add(battDOA, 0, 5);
    batteryShow.add(battCycles, 0, 6);
    
    Text battNameV = new Text(b.name != null ? b.name : "-");
    Text battConnectorV = new Text(b.connector != null ? b.connector : "-");
    Text battVoltageV = new Text(b.voltage != 0 ? String.valueOf(b.voltage) : "-");
    Text battCapacityV = new Text(b.capacity != 0 ? String.valueOf(b.capacity) : "-");
    Text battCRatingV = new Text(b.cRating != 0 ? String.valueOf(b.cRating) : "-");
    Text battDOAV = new Text(b.dateOfAquirement != null ? b.dateOfAquirement.toString() : "");
    Text battCyclesV = new Text(String.valueOf(b.getTotalCycles()));

    batteryShow.add(battNameV, 1, 0);
    batteryShow.add(battConnectorV, 1, 1);
    batteryShow.add(battVoltageV, 1, 2);
    batteryShow.add(battCapacityV, 1, 3);
    batteryShow.add(battCRatingV, 1, 4);
    batteryShow.add(battDOAV, 1, 5);
    batteryShow.add(battCyclesV, 1, 6);
    
    // Editing batteries
    GridPane batteryEdit = new GridPane();
    batteryEdit.setVgap(10);
    batteryEdit.setHgap(10);
    
    Text battNameE = new Text("Name:");
    Text battConnectorE = new Text("Connector:");
    Text battVoltageyE = new Text("Capacity:");
    Text battCRatingE = new Text("C Rating:");
    Text battDOAE = new Text("Date Acquired:");
    Text battCyclesE = new Text("Current Cycles:");
    
    Button edit = new Button("Edit Battery");
    edit.setOnMouseClicked((event) -> 
    {
      
    });
    
    content.getChildren().add(batteryShow);
  }
}














