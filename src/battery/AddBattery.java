package battery;

import batteryBank.DBConnection;
import batteryBank.MainApp;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import views.ItemViewBase;

public class AddBattery extends ItemViewBase
{
  Battery b = new Battery();
  
  public AddBattery() {
    super(new VBox());
    VBox content = (VBox) super.body;
    content.setSpacing(10);
    BatteryGetInfo battInfo = new BatteryGetInfo();
    content.getChildren().add(battInfo.screen());
    
    Button submitBattery = new Button("Add Battery");
    submitBattery.setOnMousePressed((event) ->
    {
      Battery b = new Battery();
      if (battInfo.getBatteryInfo(b))
      {
        if (BatteryDAO.insertBattery(b))
        {
          MainApp.mainStage.setScene(new BatteryView(DBConnection.getLastInsertID()).getScene());
        }
      }
      
    });
    content.getChildren().add(submitBattery);
  }
}
