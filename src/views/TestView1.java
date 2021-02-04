package views;

import batteryBank.MainApp;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TestView1
{
  private static Stage stage;

  static {
    stage = MainApp.mainStage;
  }
  
  public static Scene getScene()
  {
    Button b1 = new Button("Go to scene 2");
    b1.setOnMousePressed((event) -> {
      stage.setScene(TestView2.getScene());
    });

    BorderPane root = new BorderPane();
    
    root.setCenter(b1);
    
    return new Scene(root);
  }
}
