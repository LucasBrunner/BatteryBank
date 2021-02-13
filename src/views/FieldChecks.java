package views;
import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class FieldChecks
{
  public static ChangeListener<? super Boolean> checkIntValid(TextField checkField, GridPane content, Text message, int row, int collumn)
  {
    return (arg0, arg1, arg2) ->
    {
      if (arg1)
      {
        try
        {
          Integer.parseInt(checkField.getText());
          content.getChildren().remove(message);
        } catch (Exception e)
        {
          if (checkField.getText().length() > 0)
          {
            message.setFill(Color.RED);
            content.getChildren().remove(message);
            content.add(message, row, collumn);
          }
        }
      }
    };
  }

  public static ChangeListener<? super Boolean> checkDoubleValid(TextField checkField, GridPane content, Text message, int row, int collumn)
  {
    return (arg0, arg1, arg2) ->
    {
      if (arg1)
      {
        try
        {
          Double.parseDouble(checkField.getText());
          content.getChildren().remove(message);
        } catch (Exception e)
        {
          if (checkField.getText().length() > 0)
          {
            message.setFill(Color.RED);
            content.getChildren().remove(message);
            content.add(message, row, collumn);
          }
        }
      }
    };
  }
  
  public static ChangeListener<? super Boolean> checkDateValid(TextField checkField, GridPane content, Text message, int row, int collumn)
  {
    return (arg0, arg1, arg2) ->
    {
      if (arg1)
      {
        try
        {
          LocalDate.parse((CharSequence) checkField.getText());
          content.getChildren().remove(message);
        } catch (Exception e)
        {
          if (checkField.getText().length() > 0)
          {
            message.setFill(Color.RED);
            content.getChildren().remove(message);
            content.add(message, row, collumn);
          }
        }
      }
    };
  }
}
