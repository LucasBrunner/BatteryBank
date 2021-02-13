package battery;

import batteryBank.MainApp;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import views.ItemViewBase;
import views.MainView;

public class BatteryView extends ItemViewBase
{
  Battery b = new Battery();
  
  BatteryGetInfo bgi = new BatteryGetInfo();
  
  public BatteryView(int batteryID) {
    super(new HBox());
    
    BatteryDAO.getBattery(batteryID, b);
    
    showBattery(super.body);
  }
  
  private void showBattery(Pane body)
  {
    HBox content = (HBox) body;

    VBox batteryShow = new VBox();
    batteryShow.setSpacing(10);
    VBox batteryEdit = new VBox();
    batteryEdit.setSpacing(10);
    
    // Showing batteries
    batteryShow.getChildren().add(b.getView());
    
    Button edit = new Button("Edit Battery");
    edit.setOnMouseClicked((event) -> 
    {
      bgi.setDefaultText(b);
      content.getChildren().set(0, batteryEdit);
    });
    batteryShow.getChildren().add(edit);
    
    // Editing batteries
    HBox editButtons = new HBox();
    editButtons.setSpacing(10);
    batteryEdit.getChildren().add(bgi.screen());
    batteryEdit.getChildren().add(editButtons);
    
    Button apply = new Button("Apply Changes");
    apply.setOnMouseClicked((event) -> 
    {
      bgi.getBatteryInfo(b);
      BatteryDAO.updateBattery(b);
      
      batteryShow.getChildren().set(0, b.getView());
      content.getChildren().set(0, batteryShow);
    });
    editButtons.getChildren().add(apply);
    
    Button delete = new Button("Delete Battery");
    delete.setOnMouseClicked((event) ->
    {
      BatteryDAO.deleteBattery(b);
      
      MainApp.mainStage.setScene(new MainView().getScene());
    });
    editButtons.getChildren().add(delete);
    
    content.getChildren().add(batteryShow);
  }
}














