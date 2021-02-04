package views;

import batteryBank.MainApp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewBase
{
  protected static Stage stage;
  
  protected Pane body = null;
  
  static
  {
    stage = MainApp.mainStage;
  }
  
  public ViewBase(Pane _body) 
  {
    body = _body;
  }
  
  public Scene getScene()
  {
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(10, 10, 10, 10));
    
    Button exitProgram = new Button("Exit");
    exitProgram.setOnMousePressed((event) ->
    {
      javafx.application.Platform.exit();
      System.exit(0);
    });
    
    HBox bottomButtons = new HBox();
    bottomButtons.setAlignment(Pos.CENTER_RIGHT);
    bottomButtons.getChildren().add(exitProgram);
    
    root.setBottom(bottomButtons);
    root.setCenter(body);
    
    return new Scene(root);
  }
}
