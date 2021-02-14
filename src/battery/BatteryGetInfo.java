package battery;

import java.time.LocalDate;

import batteryBankDAOs.ConnectorDAO;
// import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import views.FieldChecks;

/**
 * Creates screens for editing {@link Battery} objects.
 * 
 * @author Lucas Brunner
 */
public class BatteryGetInfo
{
  private GridPane content = new GridPane();
  
  // Value fields
  private TextField nameField = new TextField();
  private ComboBox<String> connectorField = new ComboBox<>();
  private TextField voltageField = new TextField();
  private TextField capacityField = new TextField();
  private TextField cRatingField = new TextField();
  private TextField dOAField = new TextField();

  // Error messages
  private Text voltageError = new Text("Voltage must be a whole or decimal number.");
  private Text capacityError = new Text("Capacity must be a whole number.");
  private Text cRatingError = new Text("C Rating must be a whole number.");
  private Text dOAError = new Text("Date Acquired must be a date formatted as 'YYYY-MM-DD'.");
  
  public BatteryGetInfo() 
  {
    content.setVgap(10);
    content.setHgap(10);
  }
  
  /**
   * Sets the values shown by default in the edit boxes.
   * @param b The battery who's values to display.
   */
  public void setDefaultText(Battery b)
  {
    nameField.setText(b.name);
    connectorField.getSelectionModel().select(ConnectorDAO.getConnectorList().indexOf(b.connector));
    voltageField.setText(String.valueOf(b.voltage));
    capacityField.setText(String.valueOf(b.capacity));
    cRatingField.setText(String.valueOf(b.cRating));
    dOAField.setText(b.dateOfAquirement != null ? b.dateOfAquirement.toString() : "");
  }
  
  /**
   * Creates a screen with input fields for a battery.
   * @return The created screen.
   */
  public Pane screen()
  {
    Text battName = new Text("Name:");
    Text battConnector = new Text("Connector:");
    Text battVoltage = new Text("Voltage:");
    Text battCapacity = new Text("Capacity (mAh):");
    Text battCRating = new Text("C Rating:");
    Text battDOA = new Text("Date Acquired:");
    
    battName.setTextAlignment(TextAlignment.RIGHT);
    battConnector.setTextAlignment(TextAlignment.RIGHT);
    battVoltage.setTextAlignment(TextAlignment.RIGHT);
    battCapacity.setTextAlignment(TextAlignment.RIGHT);
    battCRating.setTextAlignment(TextAlignment.RIGHT);
    battDOA.setTextAlignment(TextAlignment.RIGHT);
    
    content.add(battName, 0, 0);
    content.add(battConnector, 0, 1);
    content.add(battVoltage, 0, 2);
    content.add(battCapacity, 0, 3);
    content.add(battCRating, 0, 4);
    content.add(battDOA, 0, 5);
    
    HBox connectorFieldBox = new HBox();
    connectorField.getItems().addAll(ConnectorDAO.getConnectorList());
    connectorField.prefWidthProperty().bind(connectorFieldBox.widthProperty());
    connectorFieldBox.getChildren().add(connectorField);
    
    voltageField.focusedProperty().addListener(FieldChecks.checkDoubleValid(voltageField, content, voltageError, 2, 2));
    capacityField.focusedProperty().addListener(FieldChecks.checkIntValid(capacityField, content, capacityError, 2, 3));
    cRatingField.focusedProperty().addListener(FieldChecks.checkIntValid(cRatingField, content, cRatingError, 2, 4));
    dOAField.focusedProperty().addListener(FieldChecks.checkDateValid(dOAField, content, dOAError, 2, 5));
    
    content.add(nameField, 1, 0);
    content.add(connectorFieldBox, 1, 1);
    content.add(voltageField, 1, 2);
    content.add(capacityField, 1, 3);
    content.add(cRatingField, 1, 4);
    content.add(dOAField, 1, 5);

    // Button addConnector = new Button("Add Connector");
    // open connector view
    // content.add(addConnector, 2, 1);
    
    return content;
  }
  
  /**
   * Exports the values held in the screen's fields to a {@link Battery} object. 
   * @param b The battery to place the values in. Must be an empty {@link Battery} object.
   * @return Whether or not the export was successful.
   */
  public boolean getBatteryInfo(Battery b)
  {
    boolean output = false;
    
    FieldChecks.checkDoubleValid(voltageField, content, voltageError, 2, 2);
    FieldChecks.checkIntValid(capacityField, content, capacityError, 2, 3);
    FieldChecks.checkIntValid(cRatingField, content, cRatingError, 2, 4);
    FieldChecks.checkDateValid(dOAField, content, dOAError, 2, 5);
    try
    {
      b.setBatteryValues(
            nameField.getText()
          , connectorField.getValue()
          , voltageField.getText().length() > 0 ? Double.parseDouble(voltageField.getText()) : 0
          , capacityField.getText().length() > 0 ? Integer.parseInt(capacityField.getText()) : 0
          , cRatingField.getText().length() > 0 ? Integer.parseInt(cRatingField.getText()) : 0
          , dOAField.getText().length() > 0 ? LocalDate.parse(dOAField.getText()) : null
          );
      output = true;
    } catch (Exception e)
    {
      e.printStackTrace();
    }
    return output;
  }
}






