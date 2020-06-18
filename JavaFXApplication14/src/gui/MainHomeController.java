/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ezzedine
 */
public class MainHomeController implements Initializable {

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Pane navBarPane;
    @FXML
    private Button btnGestionCircuit;
    @FXML
    private AnchorPane primarAnchorPane;
    @FXML
    private Button btnGestionStation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGestionCircuitAction(ActionEvent event) throws IOException {
        
        Parent root=FXMLLoader.load(getClass().getResource("/javafxapplication4/circuit.fxml"));
        primarAnchorPane.getChildren().clear();
        primarAnchorPane.getChildren().add(root);
    }

    @FXML
    private void btnGestionStationAction(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("/javafxapplication4/FXMLDocument.fxml"));
        primarAnchorPane.getChildren().clear();
        primarAnchorPane.getChildren().add(root);
    }
    
}
