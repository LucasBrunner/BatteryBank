package views.addViews;

import java.time.LocalDate;

import batteryBankDAOs.BatteryDAO;
import batteryBankDAOs.ConnectorDAO;
import items.Battery;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import views.FieldChecks;
import views.ItemViewBase;

public class AddBattery extends ItemViewBase
{
  Battery b = new Battery();
  
  public AddBattery() {
    super(new GridPane());
    GridPane content = (GridPane) super.body;
    content.setVgap(10);
    content.setHgap(10);
    
    Text battName = new Text("Name:");
    Text battConnector = new Text("Connector:");
    Text battVoltage = new Text("Voltage:");
    Text battCapacity = new Text("Capacity:");
    Text battCRating = new Text("C Rating:");
    Text battDOA = new Text("Date Acquired:");
    Text battCycles = new Text("Current Cycles:");
    
    content.add(battName, 0, 0);
    content.add(battConnector, 0, 1);
    content.add(battVoltage, 0, 2);
    content.add(battCapacity, 0, 3);
    content.add(battCRating, 0, 4);
    content.add(battDOA, 0, 5);
    content.add(battCycles, 0, 6);
    
    TextField nameField = new TextField();
    
    HBox connectorFieldBox = new HBox();
    ComboBox<String> connectorField = new ComboBox<>();
    connectorField.getItems().addAll(ConnectorDAO.getConnectorList());
    connectorField.prefWidthProperty().bind(connectorFieldBox.widthProperty());
    connectorFieldBox.getChildren().add(connectorField);
    
    TextField voltageField = new TextField();
    Text voltageError = new Text("Voltage must be a whole or decimal number.");
    voltageField.focusedProperty().addListener(FieldChecks.checkDoubleValid(voltageField, content, voltageError, 2, 2));
    
    TextField capacityField = new TextField();
    Text capacityError = new Text("Capacity must be a whole number.");
    capacityField.focusedProperty().addListener(FieldChecks.checkIntValid(capacityField, content, capacityError, 2, 3));
    
    TextField cRatingField = new TextField();
    Text cRatingError = new Text("C Rating must be a whole number.");
    cRatingField.focusedProperty().addListener(FieldChecks.checkIntValid(cRatingField, content, cRatingError, 2, 4));
    
    TextField dOAField = new TextField();
    Text dOAError = new Text("Date Acquired must be a date formatted as 'YYYY-MM-DD'.");
    dOAField.focusedProperty().addListener(FieldChecks.checkDateValid(dOAField, content, dOAError, 2, 5));
    
    TextField extraCyclesField = new TextField();
    Text extraCyclesError = new Text("Current Cycles must be a whole number.");
    extraCyclesField.focusedProperty()
        .addListener(FieldChecks.checkIntValid(extraCyclesField, content, extraCyclesError, 2, 6));
    
    content.add(nameField, 1, 0);
    content.add(connectorFieldBox, 1, 1);
    content.add(voltageField, 1, 2);
    content.add(capacityField, 1, 3);
    content.add(cRatingField, 1, 4);
    content.add(dOAField, 1, 5);
    content.add(extraCyclesField, 1, 6);
    
    Button addConnector = new Button("Add Connector");
    // open connector view
    
    content.add(addConnector, 2, 1);
    
    Button submitBattery = new Button("Add Battery");
    submitBattery.setOnMousePressed((event) ->
    {
      try
      {
        Battery b = new Battery(
              nameField.getText()
            , connectorField.getValue()
            , voltageField.getText().length() > 0 ? Double.parseDouble(voltageField.getText()) : 0
            , capacityField.getText().length() > 0 ? Integer.parseInt(capacityField.getText()) : 0
            , cRatingField.getText().length() > 0 ? Integer.parseInt(cRatingField.getText()) : 0
            , dOAField.getText().length() > 0 ? LocalDate.parse(dOAField.getText()) : null
            , extraCyclesField.getText().length() > 0 ? Integer.parseInt(extraCyclesField.getText()) : 0
            );
        BatteryDAO.insertBattery(b);
      } catch (Exception e)
      {
        e.printStackTrace();
      }
    });
    
    content.add(submitBattery, 0, 7);
  }
}
