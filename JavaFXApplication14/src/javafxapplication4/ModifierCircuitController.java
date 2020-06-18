/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import entities.Circuit;
import entities.SMSService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceCircuit;
import services.ServiceStation;

/**
 * FXML Controller class
 *
 * @author ezzedine
 */
public class ModifierCircuitController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfDistance;
    @FXML
    private ChoiceBox<String> tfDepart;
    @FXML
    private ChoiceBox<String> tfPause;
    @FXML
    private ChoiceBox<String> tfFin;
    @FXML
    private ChoiceBox<String> tfDifficulte;
    @FXML
    private Button btnModifier;
    private Circuit c1;
ObservableList DifficultyList = FXCollections.observableArrayList("easy", "medium", "hard");
    ObservableList ConditionList = FXCollections.observableArrayList();

    List<String> BeginList;
    List<String> EndList;
    List<String> PauseList;
    @FXML
    private Button btnrRtour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        
         services.ServiceStation SS = new ServiceStation();
           Platform.runLater(()->{  try {
                BeginList = SS.getStations().stream().map(e -> e.getNom()).collect(Collectors.toList());
                EndList = SS.getStations().stream().map(e -> e.getNom()).collect(Collectors.toList());
                PauseList = SS.getStations().stream().map(e -> e.getNom()).collect(Collectors.toList());
                
                
                
               
            } catch (SQLException sQLException) {
                
                
            }
             ObservableList observableBeginList = FXCollections.observableArrayList(BeginList);
                ObservableList observableEndList = FXCollections.observableArrayList(EndList);
                ObservableList observablePauseList = FXCollections.observableArrayList(PauseList);
        tfNom.setText(c1.getNom());
         tfDifficulte.setItems(DifficultyList);
                tfDifficulte.setValue(c1.getDifficulty());
                tfDepart.setItems(observableBeginList);
                tfDepart.setValue(c1.getBegin());
                tfPause.setItems(observablePauseList);
                tfPause.setValue(c1.getPause());
                tfFin.setItems(observableEndList);
                tfFin.setValue(c1.getEnd());
        
        
        tfDifficulte.setValue(c1.getDifficulty());
        tfDistance.setText(String.valueOf(c1.getDistance()));
       
      
           });     
        
    }    

    @FXML
    private void btnModifierAction(ActionEvent event)  {
        try {
            c1.setNom(tfNom.getText());
            c1.setBegin(tfDepart.getValue());
            c1.setPause(tfPause.getValue());
            c1.setEnd(tfFin.getValue());
            c1.setDifficulty(tfDifficulte.getValue());
            c1.setDistance(Float.valueOf(tfDistance.getText()));
            ServiceCircuit SC = new ServiceCircuit();
            SC.updateCircuit(c1);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Circuit enregistré avec succès.");
            alert.setHeaderText(null);
            alert.setContentText("Le Circuit " + c1.getNom() + " a été modifié.");
            alert.showAndWait();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } 
        
    }
    
    public void setCircuit(Circuit c){
        this.c1 = c;
        
    }

    @FXML
    private void btnrRtourAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/MainHome.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
    
    
    
    
}
