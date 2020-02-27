/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventsa;

import connection.Connection;
import evenement.Circuit;
import evenement.Event;
import evenement.EventServices;
import evenement.Participation;
import evenement.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class EvenementController implements Initializable {
    @FXML
    private AnchorPane weather;
    @FXML
    private TableView<Participation> tvParticipants;
    @FXML
    private TableColumn<Participation, String> cParticipants;
    @FXML
    private Label LabEvent;
    @FXML
    private Button addEvent;
    @FXML
    private Button suppEvent;
    @FXML
    private Button upEvent;
    @FXML
    private Button btnParticiper;
    @FXML
    private Button voirP;
    @FXML
    private TableView<Event> tvEvent;
    @FXML
     private TableColumn<Event, String> tcTitreEvent;
    @FXML
    private TableColumn<Event, String> tcDateEvent;
    @FXML
    private TableColumn<Event, String> tcRegionEvent;
    @FXML
    private TableColumn<Event, String> tcDescriptionEvent;
    @FXML
    private TableColumn<Event, String> tcCircuitEvent;
    @FXML
    private Label nbParticipants;
    @FXML
    private Label nbEvents;
    @FXML
    private TextField tfSearch;
    @FXML
     private ChoiceBox<String> cbSearch;
    @FXML
    private Button btnHome;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AnchorPane pane1;
        
        try {

            pane1 = FXMLLoader.load(getClass().getResource("/eventsa/WxView.fxml"));
                                weather.getChildren().setAll(pane1);

        } catch (IOException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
//            e.setPoint_depart(rs.getString("Point_Depart"));
//            e.setPoint_arrivee(rs.getString("Point_Arrivee"));
            
           
            
            
   EventServices es = new EventServices();
        
        List<Event> le = new ArrayList<>();
        
        
        try {
            le = (ArrayList<Event>) es.afficherEvents();


            ObservableList<Event> data = FXCollections.observableArrayList(le);
                    FilteredList<Event> fle = new FilteredList(data, e -> true);//Pass the data to a filtered list

            tcTitreEvent.setCellValueFactory(new PropertyValueFactory<>("titre"));
            tcDateEvent.setCellValueFactory(new PropertyValueFactory<>("Date_E"));
            tcRegionEvent.setCellValueFactory(new PropertyValueFactory<>("region"));
            tcDescriptionEvent.setCellValueFactory(new PropertyValueFactory<>("Description"));
            tcCircuitEvent.setCellValueFactory(new PropertyValueFactory<>("name_C"));

            tvEvent.setItems(fle);
            int nbe=tvEvent.getItems().size();
        nbEvents.setText(nbe+" événements en cours");
cbSearch.getItems().addAll("Titre", "Date", "Region","Circuit");
        cbSearch.setValue("Titre");

        tfSearch.setPromptText("Search here!");
        tfSearch.setOnKeyReleased(keyEvent ->
        {
            switch (cbSearch.getValue())//Switch on choiceBox value
            {
                case "Titre":
                    fle.setPredicate(e-> e.getTitre().toLowerCase().contains(tfSearch.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "Date":
                    fle.setPredicate(e -> e.getDate_E().toLowerCase().contains(tfSearch.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "Region":
                    fle.setPredicate(e -> e.getRegion().toLowerCase().contains(tfSearch.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "Circuit":
                    fle.setPredicate(e -> e.getName_C().toLowerCase().contains(tfSearch.getText().toLowerCase().trim()));//filter table by first name
                    break;
                 
            }
        });

        cbSearch.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {//reset table and textfield when new choice is selected
            if (newVal != null)
            {
                tfSearch.setText("");
                fle.setPredicate(null);//This is same as saying flPerson.setPredicate(p->true);
            }
        });
              } catch (SQLException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    @FXML
    private void HomeAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/eventsa/Home.fxml"));
        try {
            Parent root = loader.load();
                            btnHome.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void add(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/eventsa/CreerEvenement.fxml"));
        try {
            Parent root = loader.load();
                            addEvent.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        }
        @FXML
        private void TableEvent(ActionEvent event) {
        
        
        tvEvent.getSelectionModel().getSelectedItem();
        
    
}
        @FXML
        public void modify(ActionEvent event) throws Exception {
                     Event e=tvEvent.getSelectionModel().getSelectedItem();

if(e==null){
        
           System.out.println("Aucun événement séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");

            alert.showAndWait();
     
        }
else {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/eventsa/ModifierEvenement.fxml"));
        Scene scene=new Scene(loader.load());
        

        ModifierEvenementController mc= loader.getController();
        mc.setEvent(e);
        Stage stageAff=new Stage();
        stageAff.setScene(scene);
        stageAff.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
//        try {
//            Parent root = loader.load();
//                            upEvent.getScene().setRoot(root);
//
//        } catch (IOException ex) {
//            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
//        }
}

        }
         void refresh() throws SQLException {
         try {
           EventServices es = new EventServices();
        

           ArrayList<Event> le;
       
            le = (ArrayList<Event>) es.afficherEvents();
            ObservableList<Event> data = FXCollections.observableArrayList(le);
            tcTitreEvent.setCellValueFactory(new PropertyValueFactory<>("titre"));
            tcDateEvent.setCellValueFactory(new PropertyValueFactory<>("Date_E"));
            tcRegionEvent.setCellValueFactory(new PropertyValueFactory<>("region"));
            tcDescriptionEvent.setCellValueFactory(new PropertyValueFactory<>("Description"));
            tcCircuitEvent.setCellValueFactory(new PropertyValueFactory<>("name_C"));

            tvEvent.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @FXML
        private void Supprimer(ActionEvent event) throws SQLException {
        
        
         Event e=tvEvent.getSelectionModel().getSelectedItem();
        
        if(e==null){
        
           System.out.println("Aucun événement séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");

            alert.showAndWait();
     
        }else {
        
        EventServices es=new EventServices();
        String nom_P=e.getTitre();
        
         try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Supprimer Evenement");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sur de vouloir supprimer l'évenement " + e.getTitre());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    // System.out.println("sup1");
                    es.supprimerEvent(e);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Succés!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Evenement supprimé!");

                    alert1.showAndWait();
                }
            } catch (Exception ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
         loadData();
         refresh();
        }
        
         
        }
        public void loadData() throws SQLException{
    ObservableList<Event> dataa = null;

    dataa = FXCollections.observableArrayList(new EventServices().afficherEvents());
    }
        @FXML
        public void participer(ActionEvent event) throws Exception {
                     Event e=tvEvent.getSelectionModel().getSelectedItem();
                     EventServices es=new EventServices();
                     User u=new User(1,"mahmoud");

if(e==null){
        
           System.out.println("Aucun événement séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");

            alert.showAndWait();
     
        }else {
            es.Participer(e,u);
            System.out.println("Participation validée!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Participation validée!");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez participé à l'événement "+e.getTitre());
            alert.showAndWait();
//        try {
//            Parent root = loader.load();
//                            upEvent.getScene().setRoot(root);
//
//        } catch (IOException ex) {
//            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
//        }
}
        }
        @FXML
        public void AfficherParticipants(ActionEvent event) throws Exception {
                     Event e=tvEvent.getSelectionModel().getSelectedItem();

if(e==null){
        
           System.out.println("Aucun événement séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");

            alert.showAndWait();
     
        }else {
                EventServices es = new EventServices();
        
        List<Participation> lp = new ArrayList<>();
        
        
        try {
            lp = (ArrayList<Participation>) es.afficherParticipation(e.getTitre());


            ObservableList<Participation> data = FXCollections.observableArrayList(lp);
//                    FilteredList<Participation> flp = new FilteredList(data, p -> true);//Pass the data to a filtered list

            cParticipants.setCellValueFactory(new PropertyValueFactory<>("nom"));
                        tvParticipants.setItems(data);
                        int nb=tvParticipants.getItems().size();
                        nbParticipants.setText(nb+" Participants");

    }     
    catch (SQLException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            

        } 

}
@FXML
        private void AnnulerParticipation(ActionEvent event) throws SQLException {
        
        
         Event e=tvEvent.getSelectionModel().getSelectedItem();
        
        if(e==null){
        
           System.out.println("Aucun événement séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");

            alert.showAndWait();
     
        }else {
        
        EventServices es=new EventServices();
        
         try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Annuler participation");
                alert.setHeaderText(null);
                alert.setContentText("Annuler la participation a l'événement " + e.getTitre()+"?");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    // System.out.println("sup1");
                    es.annulerParticipation(e.getTitre());
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Succés!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Participation annulée!");

                    alert1.showAndWait();
                }
            } catch (Exception ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
         
        }
        
         
        }
        }

        
    
        
