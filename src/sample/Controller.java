package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;

    @FXML
    private ListView listview;
    private Label myMessage;

    public void pressButtonSelect(ActionEvent event){
        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XML and TXT files","*.xml","*.txt")); //DOSYA TÜRÜ FİLTRELEME

        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null){
            listview.getItems().add(selectedFile.getName());
        }
        else{
            System.out.println("File is not valid");
        }
        fileSelected(selectedFile);

    }
    public void fileSelected(File file){
        Parser parser = new Parser();
        parser.ReadFile(file);
    }
    public void pressButtonBar(ActionEvent event) throws IOException { //BARCHART SENARYOSU
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("barchart.fxml"));
        Parent root2 = loader1.load();
        Scene scene2 = new Scene(root2);
        Stage stage2 = new Stage(StageStyle.DECORATED);
        stage2.setTitle("Bar Chart");
        stage2.setScene(scene2);
        stage2.initModality(Modality.APPLICATION_MODAL);
        Screen screen1 = Screen.getPrimary();
        stage2.show();
    }
    public void pressButtonLine(ActionEvent event) throws IOException { //LINECHART SENARYOSU
        FXMLLoader loader = new FXMLLoader(getClass().getResource("linechart.fxml"));
        Parent root1 = loader.load();
        Scene scene = new Scene(root1);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Line Chart");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        Screen screen = Screen.getPrimary();
        stage.show();
    }
    public void pressButtonExit(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
