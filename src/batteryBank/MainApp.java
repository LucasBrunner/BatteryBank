package batteryBank;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MainView;

public class MainApp extends Application
{
  public static MainView mainView = new MainView();
  public static Stage mainStage;
  public static Scene currentScene;
  
  public void start(Stage primaryStage) throws Exception
  {
    
    mainStage = primaryStage;
    primaryStage.setWidth(1200);
    primaryStage.setHeight(1000);
    
    primaryStage.setScene(mainView.getScene());

    primaryStage.show();
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
