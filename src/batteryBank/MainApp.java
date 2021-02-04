package batteryBank;

import java.util.HashMap;
import java.util.Map;

import batteryBankDAOs.BatteryDAO;
import items.Battery;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import views.MainView;
import views.TestView1;
import views.ViewBase;

public class MainApp extends Application
{
  public static MainView mainView = new MainView();
  
  public void start(Stage primaryStage) throws Exception
  {
    
    mainStage = primaryStage;
    primaryStage.setWidth(1200);
    primaryStage.setHeight(1000);
    
    primaryStage.setScene(mainView.getScene());

    primaryStage.show();
  }
  
  public static Stage mainStage;
  public static Map<SceneName, Scene> scenes = new HashMap<>();
  
  public static Map<SceneName, Scene> getScenes()
  {
    return scenes;
  }
  
  public static void main(String[] args)
  {
    /*
     * Battery b = new Battery(); b.name = "test1"; BatteryDAO.insertBattery(b);
     */
    launch(args);
  }
  
  public enum SceneName
  {
    TestScene1, TestScene2;
  }
}
