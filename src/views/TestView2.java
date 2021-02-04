package views;

import batteryBank.MainApp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestView2
{
  private static Stage stage;

  static {
    stage = MainApp.mainStage;
  }
  
  public static Scene getScene()
  {//creating label email 
    Text text1 = new Text("Email");       
    
    //creating label password 
    Text text2 = new Text("Password"); 
  
    //Creating Text Filed for email        
    TextField textField1 = new TextField();       
    
    //Creating Text Filed for password        
    TextField textField2 = new TextField();  
     
    //Creating Buttons 
    Button button1 = new Button("Submit"); 
    Button button2 = new Button("Clear");  
    
    //Creating a Grid Pane 
    GridPane gridPane = new GridPane();    
    
    //Setting size for the pane  
    gridPane.setMinSize(400, 200); 
     
    //Setting the padding  
    gridPane.setPadding(new Insets(10, 10, 10, 10)); 
    
    //Setting the vertical and horizontal gaps between the columns 
    gridPane.setVgap(5); 
    gridPane.setHgap(5);       
    
    //Setting the Grid alignment 
    gridPane.setAlignment(Pos.CENTER); 
     
    //Arranging all the nodes in the grid 
    gridPane.add(text1, 0, 0); 
    gridPane.add(textField1, 1, 0); 
    gridPane.add(text2, 0, 1);       
    gridPane.add(textField2, 1, 1); 
    gridPane.add(button1, 0, 2); 
    gridPane.add(button2, 1, 2); 
    
    gridPane.setGridLinesVisible(true);

    BorderPane root = new BorderPane();
    gridPane.setAlignment(Pos.TOP_LEFT);
    
    root.setCenter(gridPane);

    return new Scene(root);
  }
}