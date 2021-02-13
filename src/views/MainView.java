package views;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import battery.AddBattery;
import battery.BatteryDAO;
import battery.BatteryView;
import battery.SimpleBattery;
import batteryBank.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The home screen of the app.
 * 
 * @author Lucas Brunner
 */
public class MainView extends ViewBase
{
  private int selectedBatt = -1;
  
  public MainView() {
    super(new HBox());
    HBox content = (HBox) super.body;
    content.setSpacing(10);
    
    // Add item buttons
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
    
    // Show batteries
    ArrayList<SimpleBattery> batteryNames = new ArrayList<>();
    ListView<String> batteryList = new ListView<>();
    Dictionary<Integer, Integer> batteryListIDs = new Hashtable<>();
    
    BatteryDAO.getBatteryNames(batteryNames);
    for (int i = 0; i < batteryNames.size(); i++)
    {
      batteryList.getItems().add(i, batteryNames.get(i).name);
      batteryListIDs.put(i, batteryNames.get(i).ID);
    }
    
    batteryList.setMaxHeight(400);
    MainApp.mainStage.heightProperty().addListener(new ChangeListener<Number>()
    {
      @Override
      public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight)
      {
        double h = (double) newSceneHeight * 0.4;
        batteryList.setMaxHeight(h > 400 ? 400 : h);
      }
    });
    
    batteryList.setOnMouseClicked((event) ->
    {
      int sb = batteryList.getSelectionModel().getSelectedIndex();
      if (selectedBatt == sb)
      {
        MainApp.mainStage.setScene(new BatteryView(batteryListIDs.get(sb)).getScene());
      } else {
        selectedBatt = sb;
      }
    });
    
    content.getChildren().add(batteryList);
  }
}










