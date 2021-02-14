package batteryBank;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MainView;

public class MainApp extends Application
{
  public static Stage mainStage;
  public static Scene currentScene;
  
  public void start(Stage primaryStage) throws Exception
  {
    
    mainStage = primaryStage;
    mainStage.setWidth(1200);
    mainStage.setHeight(1000);
    
    mainStage.setScene(new MainView().getScene());
    
    mainStage.show();
  }
  
  public static void main(String[] args)
  {
    launch(args);
    DBConnection.closeConnection();
  }
  
  public enum SceneName
  {
    TestScene1, TestScene2;
  }
}
