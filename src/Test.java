import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author zjb
 * @date 2018/5/26.
 */
public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Anchor Pane Example!");

        AnchorPane anchorPane = new AnchorPane();

        Button topBtn = new Button("Top Button");
        Button bottomBtn = new Button("Bottom Button");
        Button leftBtn = new Button("Left Button");
        Button rightBtn = new Button("Right Button");
        topBtn.setLayoutX(10);
//        topBtn.setPrefHeight(10);
        bottomBtn.setLayoutY(100);
        anchorPane.getChildren().addAll(topBtn,bottomBtn);

        TextField inputField = new TextField();

//        anchorPane.getChildren().addAll(topBtn,bottomBtn,leftBtn,rightBtn);

//        AnchorPane.setBottomAnchor(bottomBtn, 8.0);
//        AnchorPane.setRightAnchor(rightBtn, 5.0);
//        AnchorPane.setTopAnchor(topBtn, 20.0);
//        AnchorPane.setLeftAnchor(leftBtn, 40.0);
//        AnchorPane.setBottomAnchor(leftBtn, 40.0);

        primaryStage.setScene(new Scene(anchorPane, 400, 250));
        primaryStage.show();
    }
}
