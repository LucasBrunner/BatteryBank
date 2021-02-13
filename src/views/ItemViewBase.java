package views;

import batteryBank.MainApp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ItemViewBase extends ViewBase
{
  public ItemViewBase(Pane _body) {
    super(_body);
  }
  
  @Override
  public Scene getScene()
  {
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(10, 10, 10, 10));
    
    Button exitProgram = new Button("Main Menu");
    exitProgram.setOnMousePressed((event) ->
    {
      MainApp.mainStage.setScene(new MainView().getScene());
    });
    
    HBox bottomButtons = new HBox();
    bottomButtons.setAlignment(Pos.CENTER_RIGHT);
    bottomButtons.getChildren().add(exitProgram);
    
    root.setBottom(bottomButtons);
    root.setCenter(body);
    
    return new Scene(root);
  }
}
