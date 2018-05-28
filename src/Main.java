import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();

        primaryStage.setTitle("Cipher Creator");

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


        TextArea inputField = new TextArea();
        inputField.setPrefHeight(200);
        inputField.setPrefWidth(600);
        inputField.setLayoutX(5);
        inputField.setLayoutY(40);

        TextArea outputFiled = new TextArea();
        outputFiled.setPrefHeight(200);
        outputFiled.setPrefWidth(600);
        outputFiled.setLayoutX(5);
        outputFiled.setLayoutY(270);

        CheckBox checkEncode = new CheckBox();
        checkEncode.setLayoutX(610);
        checkEncode.setLayoutY(40);
        Label labelEncode = new Label("Encrypted");
        labelEncode.setLayoutX(635);
        labelEncode.setLayoutY(40);

        CheckBox checkDecode = new CheckBox();
        checkDecode.setLayoutX(610);
        checkDecode.setLayoutY(70);
        Label labelDecode = new Label("Decrypted");
        labelDecode.setLayoutX(635);
        labelDecode.setLayoutY(70);

        Label labelKey = new Label("key number");
        labelKey.setLayoutX(620);
        labelKey.setLayoutY(110);

        TextField inputKey = new TextField();
        inputKey.setLayoutX(610);
        inputKey.setLayoutY(140);
        inputKey.setPrefWidth(90);

        Button startBtn = new Button("Start");
        startBtn.setLayoutX(610);
        startBtn.setLayoutY(180);
        startBtn.setPrefWidth(90);
        Button crackBtn = new Button("Crack");
        crackBtn.setLayoutX(610);
        crackBtn.setLayoutY(220);
        crackBtn.setPrefWidth(90);
        Button clearBtn = new Button("Clear");
        clearBtn.setLayoutX(610);
        clearBtn.setLayoutY(260);
        clearBtn.setPrefWidth(90);

        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "Caesar Ciphar", "Vigenere Ciphar")
        );
        cb.getSelectionModel().select(0);
        cb.setLayoutX(610);
        cb.setLayoutY(300);
        cb.setPrefWidth(90);

        startEncode(cb, inputField, outputFiled, startBtn, inputKey, checkEncode, checkDecode);
        clearAll(inputField, outputFiled, clearBtn, inputKey, checkEncode, checkDecode);
        openFile(newMenuItem, primaryStage);
        saveFile(saveMenuItem, primaryStage, outputFiled);
        crack(cb, inputField, outputFiled, crackBtn);

        root.getChildren().addAll(menuBar, inputField, outputFiled, checkEncode, checkDecode
                , labelEncode, labelDecode, labelKey, inputKey,
                startBtn, clearBtn, crackBtn, cb);
        primaryStage.setScene(new Scene(root, 720, 500));
        primaryStage.show();
    }


    private void openFile(MenuItem openItem, Stage primaryStage) {
        openItem.setOnAction((e) -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(primaryStage);
            FileUtils.fileReader(file);
//            System.out.println();
        });
    }

    private void saveFile(MenuItem saveItem, Stage primaryStage, TextArea output) {
        saveItem.setOnAction((e) -> {
            FileChooser fileChooser1 = new FileChooser();
            fileChooser1.setTitle("Save");
//            System.out.println(pic.getId());
            File file = fileChooser1.showSaveDialog(primaryStage);
            FileUtils.fileWriter(output, file);
//            System.out.println(file);
        });
    }

    private void clearAll(TextArea input, TextArea output, Button clear, TextField key, CheckBox encode, CheckBox decode) {
        clear.setOnAction((e) -> {
            input.setText("");
            output.setText("");
            key.setText("");
            encode.setSelected(false);
            decode.setSelected(false);
            FileUtils.in = "";
        });
    }

    private void crack(ChoiceBox cb, TextArea input, TextArea output, Button crack) {
//        if (cb.getSelectionModel().getSelectedIndex() == 0) {
            crack.setOnAction((e) -> {
                if (cb.getSelectionModel().getSelectedIndex() == 0) {
                    String str = input.getText();
                    if (str == null || "".equals(str)) {
                        str = FileUtils.in;
                    }
                    output.setText(CaesarCiphar.crack(str));
                }
            });
    }

    private void startEncode(ChoiceBox cb, TextArea input, TextArea output, Button start, TextField key, CheckBox encode, CheckBox decode) {
        start.setOnAction((event) -> {
            if (cb.getSelectionModel().getSelectedIndex() == 0) {

                String strNum = key.getText();
                int num = 0;
                try {
                    num = Integer.valueOf(strNum);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Warning!");
                    alert.setContentText("Please enter a valid Key Number");
                    alert.showAndWait();
                    return;
                }
                String str = input.getText();
                if (str == null || "".equals(str)) {
                    str = FileUtils.in;
                }
                Boolean isEncode = encode.isSelected();
                Boolean isDecode = decode.isSelected();
                String result = "";
                if (isEncode) {
                    result = CaesarCiphar.encode(str, num);
                } else if (isDecode) {
                    result = CaesarCiphar.decode(str, num);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Warning!");
                    alert.setContentText("Please select Encrypted or Decrypted");
                    alert.showAndWait();
                    return;
                }
                output.setText(result);
            } else {
                String strNum = key.getText();
//                try {
//                    num = Integer.valueOf(strNum);
//                } catch (Exception e) {
//                    Alert alert = new Alert(Alert.AlertType.WARNING);
//                    alert.setTitle("Warning Dialog");
//                    alert.setHeaderText("Warning!");
//                    alert.setContentText("Please enter a valid Key Number");
//                    alert.showAndWait();
//                    return;
//                }
                Pattern pattern = Pattern.compile("[a-zA-Z]+");
                Matcher m = pattern.matcher(strNum);
                boolean ismatch =  m.matches();
                if(strNum=="" || !ismatch){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Warning!");
                    alert.setContentText("Please enter a valid Key Number");
                    alert.showAndWait();
                    return;
                }
                String str = input.getText();
                if (str == null || "".equals(str)) {
                    str = FileUtils.in;
                }
                Boolean isEncode = encode.isSelected();
                Boolean isDecode = decode.isSelected();
                String result = "";
                if (isEncode) {
                    result = VigenereCiphar.encode(str, strNum);
                } else if (isDecode) {
                    result = VigenereCiphar.decode(str, strNum);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Warning!");
                    alert.setContentText("Please select Encrypted or Decrypted");
                    alert.showAndWait();
                    return;
                }
                output.setText(result);
            }

        });

    }
}
