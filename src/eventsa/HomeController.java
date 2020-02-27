/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventsa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnProduct;
    @FXML
    private Button btnAnnonce;
    @FXML
    private Button btnLocation;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnCircuit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void ProduitAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/eventsa/Produit.fxml"));
        try {
            Parent root = loader.load();
                            btnProduct.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AnnonceAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/eventsa/Annonce.fxml"));
        try {
            Parent root = loader.load();
                             btnAnnonce.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LocationAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/eventsa/Location.fxml"));
        try {
            Parent root = loader.load();
                            btnLocation.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EvenementAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/eventsa/Evenement.fxml"));
        try {
            Parent root = loader.load();
                            btnEvent.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void CircuitAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/eventsa/Circuit.fxml"));
        try {
            Parent root = loader.load();
                            btnCircuit.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
