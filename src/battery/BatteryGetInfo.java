package battery;

import java.time.LocalDate;

import batteryBank.DBConnection;
import batteryBank.MainApp;
import batteryBankDAOs.ConnectorDAO;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import views.FieldChecks;

public class BatteryGetInfo
{
  GridPane content = new GridPane();
  
  TextField nameField = new TextField();
  ComboBox<String> connectorField = new ComboBox<>();
  TextField voltageField = new TextField();
  TextField capacityField = new TextField();
  TextField cRatingField = new TextField();
  TextField dOAField = new TextField();
  TextField extraCyclesField = new TextField();

  Text voltageError = new Text("Voltage must be a whole or decimal number.");
  Text capacityError = new Text("Capacity must be a whole number.");
  Text cRatingError = new Text("C Rating must be a whole number.");
  Text dOAError = new Text("Date Acquired must be a date formatted as 'YYYY-MM-DD'.");
  Text extraCyclesError = new Text("Current Cycles must be a whole number.");
  
  public Pane screen()
  {
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
    
    content.add(battName, 0, 0);
    content.add(battConnector, 0, 1);
    content.add(battVoltage, 0, 2);
    content.add(battCapacity, 0, 3);
    content.add(battCRating, 0, 4);
    content.add(battDOA, 0, 5);
    content.add(battCycles, 0, 6);
    
    HBox connectorFieldBox = new HBox();
    connectorField.getItems().addAll(ConnectorDAO.getConnectorList());
    connectorField.prefWidthProperty().bind(connectorFieldBox.widthProperty());
    connectorFieldBox.getChildren().add(connectorField);
    
    voltageField.focusedProperty().addListener(FieldChecks.checkDoubleValid(voltageField, content, voltageError, 2, 2));
    capacityField.focusedProperty().addListener(FieldChecks.checkIntValid(capacityField, content, capacityError, 2, 3));
    cRatingField.focusedProperty().addListener(FieldChecks.checkIntValid(cRatingField, content, cRatingError, 2, 4));
    dOAField.focusedProperty().addListener(FieldChecks.checkDateValid(dOAField, content, dOAError, 2, 5));
    extraCyclesField.focusedProperty().addListener(FieldChecks.checkIntValid(extraCyclesField, content, extraCyclesError, 2, 6));
    
    content.add(nameField, 1, 0);
    content.add(connectorFieldBox, 1, 1);
    content.add(voltageField, 1, 2);
    content.add(capacityField, 1, 3);
    content.add(cRatingField, 1, 4);
    content.add(dOAField, 1, 5);
    content.add(extraCyclesField, 1, 6);
    
    return content;
  }
  
  public boolean getBatteryInfo(Battery b)
  {
    boolean output = false;
    
    FieldChecks.checkDoubleValid(voltageField, content, voltageError, 2, 2);
    FieldChecks.checkIntValid(capacityField, content, capacityError, 2, 3);
    FieldChecks.checkIntValid(cRatingField, content, cRatingError, 2, 4);
    FieldChecks.checkDateValid(dOAField, content, dOAError, 2, 5);
    FieldChecks.checkIntValid(extraCyclesField, content, extraCyclesError, 2, 6);
    try
    {
      b = new Battery(
            nameField.getText()
          , connectorField.getValue()
          , voltageField.getText().length() > 0 ? Double.parseDouble(voltageField.getText()) : 0
          , capacityField.getText().length() > 0 ? Integer.parseInt(capacityField.getText()) : 0
          , cRatingField.getText().length() > 0 ? Integer.parseInt(cRatingField.getText()) : 0
          , dOAField.getText().length() > 0 ? LocalDate.parse(dOAField.getText()) : null
          , extraCyclesField.getText().length() > 0 ? Integer.parseInt(extraCyclesField.getText()) : 0
          );
      output = true;
    } catch (Exception e)
    {
      e.printStackTrace();
    }
    return output;
  }
}






