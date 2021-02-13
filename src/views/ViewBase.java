package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * The base for all screens in the app.
 * 
 * @author Lucas Brunner
 */
public class ViewBase
{
  protected Pane body = null;
  
  public ViewBase(Pane _body) 
  {
    body = _body;
  }
  
  /**
   * Creates a up to date version of this class's {@link Scene}.
   * @return The {@link Scene} created.
   */
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
