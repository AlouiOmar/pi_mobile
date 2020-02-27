/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventsa;

import evenement.Event;
import evenement.EventServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class ModifierEvenementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField nTitre;
    @FXML
    TextField nDescription;    
    @FXML
    ChoiceBox nRegion;
    @FXML
    DatePicker nDate;
    @FXML
    Button Annuler;
    @FXML
    Button Modifier;
    Event e;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(()->{
            System.out.println(e);
               nTitre.setText(e.getTitre());
               nRegion.setValue(e.getRegion());
               nDescription.setText(e.getDescription());
               nDate.getEditor().setText(e.getDate_E());

       
    });    
    
     nRegion.getItems().add("Ariana");
      nRegion.getItems().add("Sidi Bouzid");
      nRegion.getItems().add("Gafsa");
      nRegion.getItems().add("Ben Arous");
      nRegion.getItems().add("Béja");
      nRegion.getItems().add("Bizerte");
      nRegion.getItems().add("Sfax");
      nRegion.getItems().add("Hawaria");
      nRegion.getItems().add("Kirouane");
      nRegion.getItems().add("Ben Guerdene");
      
//      
//      nDate.setValue(cec.dpDateE.getValue());
//        nTitre.setText(cec.tfTitreE.getText());
    }
    @FXML
    private void modifier(ActionEvent event) throws SQLException, IOException {
        EventServices es=new EventServices();
        if(nTitre.getText ()==null || nDescription.getText ()==null ||nDate.getValue ()==null ||  nRegion.getValue ()==null ){
        
           System.out.println("Un ou plusieurs champs non rempli(s)!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Champ(s) obligatoire(s) non rempli(s)!");

            alert.showAndWait();
     }
        else if(nTitre.getText ().equals(es.conditionTitre().getTitre())){
            System.out.println("Titre non disponible!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Titre non disponible!");

            alert.showAndWait();
        }
        else{
        nDate.setPromptText("Date de l'événement");
        nDate.setMaxWidth(300);
        nDate.setStyle("-fx-font-size:20");
        String date_E=nDate.getEditor().getText();
        String titre = nTitre.getText ();
         String Description = nDescription.getText ();
        if (nRegion.getValue().equals("Ariana"))
                {
                    e.setRegion("Ariana");
                }
          if (nRegion.getValue().equals("Soukra"))
                {
                   e.setRegion("Soukra"); 
                }
          e.setDate_E(date_E);
          e.setDescription(Description);
          e.setTitre(titre); 
         es.modifierEvent(e);
         System.out.println("Succés!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Succés!");
            alert.setHeaderText(null);
            alert.setContentText("Modification de l'évenement réalisée!");
            alert.showAndWait();
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/eventsa/Evenement.fxml"));
        try {
            Parent root=loader.load();

                            Modifier.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}


        @FXML
    public void back(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/eventsa/Evenement.fxml"));
        try {
            Parent root = loader.load();
                            Annuler.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
    public void setEvent(Event e1){
        this.e=e1;
    }
}
