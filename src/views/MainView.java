package views;

import batteryBank.MainApp;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import views.addViews.AddBattery;

public class MainView extends ViewBase
{
  
  public MainView() {
    super(new HBox());
    HBox content = (HBox) super.body;
    
    VBox addButtons = new VBox();
    addButtons.setSpacing(10);
    addButtons.setPrefWidth(110);
    
    Button newBattery = new Button("New Battery");
    newBattery.prefWidthProperty().bind(addButtons.widthProperty());
    newBattery.setAlignment(Pos.BASELINE_LEFT);
    newBattery.setOnMousePressed((event) ->
    {
      MainApp.mainStage.setScene(new AddBattery().getScene());
    });
    
    Button newEquipment = new Button("New Equipment");
    newEquipment.prefWidthProperty().bind(addButtons.widthProperty());
    newEquipment.setAlignment(Pos.BASELINE_LEFT);
    
    Button newCharger = new Button("New Charger");
    newCharger.prefWidthProperty().bind(addButtons.widthProperty());
    newCharger.setAlignment(Pos.BASELINE_LEFT);
    
    addButtons.getChildren().addAll(newBattery, newEquipment, newCharger);
    
    content.getChildren().add(addButtons);
    
  }
}
