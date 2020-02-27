/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventsa;
import connection.Connection;
import evenement.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class CreerEvenementController implements Initializable {

    @FXML
     Button btAjouterE;
    @FXML
     TextField tfTitreE;
    @FXML
     TextField tfDescriptionE;
    @FXML
     Label title;
    @FXML
     DatePicker dpDateE;
    @FXML
     ChoiceBox<String> cbRegionE;
    @FXML
     Button back;
        final ObservableList circuits=FXCollections.observableArrayList();
    @FXML 
    ChoiceBox<String> cbCircuits; 
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        EventServices es=new EventServices();
        cbRegionE.setValue("<Region>");
        cbRegionE.getItems().add("Ariana");
      cbRegionE.getItems().add("Sidi Bouzid");
      cbRegionE.getItems().add("Gafsa");
      cbRegionE.getItems().add("Ben Arous");
      cbRegionE.getItems().add("Béja");
      cbRegionE.getItems().add("Bizerte");
      cbRegionE.getItems().add("Sfax");
      cbRegionE.getItems().add("Hawaria");
      cbRegionE.getItems().add("Kirouane");
      cbRegionE.getItems().add("Ben Guerdene");
      try {
            String req="SELECT name_C FROM circuit";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            cbCircuits.getItems().add(rs.getString("name_C"));
            }
//            e.setPoint_depart(rs.getString("Point_Depart"));
//            e.setPoint_arrivee(rs.getString("Point_Arrivee"));
            
           
            
            
            s.close();
            rs.close();
            }
         catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

        
    
    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        EventServices es=new EventServices();
        if(tfTitreE.getText ()==null || tfDescriptionE.getText ()==null ||dpDateE.getValue ()==null || cbCircuits.getValue ()==null || cbRegionE.getValue ()==null ){
        
           System.out.println("Un ou plusieurs champs non rempli(s)!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Champ(s) obligatoire(s) non rempli(s)!");

            alert.showAndWait();
     }
        else if(tfTitreE.getText ().equals(es.conditionTitre().getTitre())){
            System.out.println("Titre non disponible!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Titre non disponible!");

            alert.showAndWait();
        }
        else{
        Event e=new Event();
        dpDateE.setPromptText("Date de l'événement");
        dpDateE.setMaxWidth(300);
        dpDateE.setStyle("-fx-font-size:20");
        String date_E=dpDateE.getEditor().getText();
        String titre = tfTitreE.getText ();
         String Description = tfDescriptionE.getText ();
         
        e.setRegion(cbRegionE.getValue());
          e.setDate_E(date_E);
          e.setDescription(Description);
          e.setTitre(titre); 
          e.setName_C(cbCircuits.getValue());
         es.AjouterEvent(e);
         System.out.println("Succés!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Succés!");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez créé l'événement "+titre);
            alert.showAndWait();
        }
    }
    @FXML
    public void back(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/eventsa/Evenement.fxml"));
        try {
            Parent root = loader.load();
                            back.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
    
}
