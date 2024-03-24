package pl.dminior8.asteriskfinder.controller;

import com.google.gson.JsonElement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.dminior8.asteriskfinder.service.JsonService;

import java.io.BufferedReader;
import java.net.URL;
import java.util.ResourceBundle;

public class JsonController implements Initializable {
    @FXML
    private Label instructionLabel, statusLabel, resultLabel;
    @FXML
    private Button checkButton;
    @FXML
    private TextField fileNameText;

    JsonService jsonService = new JsonService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        resultLabel.setVisible(false);
        checkButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                JsonElement jsonElement = jsonService.isValidJsonFile(fileNameText.getText());
                if(jsonElement != null) {
                    instructionLabel.setText("Name correct!");
                    resultLabel.setVisible(true);
                    resultLabel.setText("Hello inside JSON!");
                }else{
                    instructionLabel.setText("Wrong name. Try again!");
                }
                String result = new String(jsonService.checkForValue(jsonElement, "Resource", "*"));
                System.out.println("Result: " + result);

                if(result.equals("*")){
                    resultLabel.setText("Asterisk is inside Resource file!");
                }else{
                    resultLabel.setText("No asterisk inside JSON file.");
                }
            }
        });


    }
}