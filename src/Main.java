import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{
   public static void main(String[] args) {
       launch(args);
    }
  
   @Override
   public void start(Stage primaryStage) {
       AnchorPane root = new AnchorPane();

       primaryStage.setTitle("Cipher Creator");
       Button btn = new Button();
       btn.setText("Say 'Hello World'");
       btn.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
           }
       });
       MenuBar menuBar = new MenuBar();
       menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
       menuBar.setPrefHeight(10);
       Menu fileMenu = new Menu("File");
       MenuItem newMenuItem = new MenuItem("Open ");
       MenuItem saveMenuItem = new MenuItem("Save");
       MenuItem exitMenuItem = new MenuItem("Exit");
       exitMenuItem.setOnAction(actionEvent -> Platform.exit());
       fileMenu.getItems().addAll(newMenuItem, saveMenuItem,
               new SeparatorMenuItem(), exitMenuItem);
       menuBar.getMenus().addAll(fileMenu);


       TextField inputField = new TextField();
       inputField.setPrefHeight(250);
       inputField.setPrefWidth(600);
       inputField.setLayoutX(5);
       inputField.setLayoutY(40);

       TextField outputFiled = new TextField();
       outputFiled.setPrefHeight(250);
       outputFiled.setPrefWidth(600);
       outputFiled.setLayoutX(5);
       outputFiled.setLayoutY(320);

       root.getChildren().addAll(menuBar,inputField,outputFiled);
       primaryStage.setScene(new Scene(root, 800, 600));
       primaryStage.show();
    }
}
