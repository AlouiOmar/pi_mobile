/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entities.Circuit;
import entities.Circuit;
import entities.SMSService;
import static entities.SMSService.ACCOUNT_SID;
import static entities.SMSService.AUTH_TOKEN;
import entities.Station;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.omg.CORBA.portable.ValueFactory;
import services.ServiceCircuit;
import services.ServiceStation;
import utile.MaConnection;

/**
 * FXML Controller class
 *
 * @author ezzedine
 */
public class CircuitController implements Initializable {

    ObservableList DifficultyList = FXCollections.observableArrayList("easy", "medium", "hard");
    ObservableList ConditionList = FXCollections.observableArrayList();

    List<String> BeginList;
    List<String> EndList;
    List<String> PauseList;
    Connection cnx = MaConnection.getInstance().getConnection();

    ServiceCircuit SC;
    ServiceStation SS;
    @FXML
    private Button button;
    @FXML
    private Button afficher;
    @FXML
    private TableView<Circuit> table;
    @FXML
    private TableColumn<Circuit, String> nameCol;
    private TableColumn<Circuit, String> beginCol;
    @FXML
    private TableColumn<Circuit, String> endCol;
    @FXML
    private TableColumn<Circuit, String> pauseCol;
    @FXML
    private TableColumn<Circuit, Float> distanceCol;
    @FXML
    private TableColumn<Circuit, String> difficultyCol;

    ObservableList<Circuit> data;
    ObservableList<Station> datas;
    @FXML
    private AnchorPane mapAnchor;
    @FXML
    private Button loadMap;
    @FXML
    private Button btnGestionStation;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TableColumn<Circuit,String> departCol;
     @FXML
    private void selctedItem(MouseEvent event) {
    }
    @FXML
    private void conditionAction(InputMethodEvent event) {
    }

    @FXML
    private TextField name;
    @FXML
    private ChoiceBox<String> begin;
    @FXML
    private ChoiceBox<String> end;
    @FXML
    private ChoiceBox<String> pause;
    @FXML
    private TextField distance;
    @FXML
    private ChoiceBox<String> difficulty;
    private TextField duration;
    @FXML
    private Button btnRefrech;
    @FXML
    private TextField recherche;
    @FXML
    private ChoiceBox<String> condition;

    /**
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SS = new ServiceStation();
        try {
            BeginList = SS.getStations().stream().map(e -> e.getNom()).collect(Collectors.toList());
            EndList = SS.getStations().stream().map(e -> e.getNom()).collect(Collectors.toList());
            PauseList = SS.getStations().stream().map(e -> e.getNom()).collect(Collectors.toList());
            ObservableList observableBeginList = FXCollections.observableArrayList(BeginList);
            ObservableList observableEndList = FXCollections.observableArrayList(EndList);
            ObservableList observablePauseList = FXCollections.observableArrayList(PauseList);

            difficulty.setValue("medium");
            difficulty.setItems(DifficultyList);
            condition.setValue("easy");
            condition.setItems(ConditionList);
            begin.setItems(observableBeginList);
            pause.setItems(observablePauseList);
            end.setItems(observableEndList);

            addButtonToTable();
        } catch (SQLException ex) {
            Logger.getLogger(CircuitController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void btnLoadMap(ActionEvent event) throws IOException {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/Map.fxml"));
            //mapAnchor.getChildren().clear();
            mapAnchor.getChildren().add(root);
        } catch (Exception iOException) {
            System.out.println(iOException.getMessage());
        }
    }

    @FXML
    private void add(ActionEvent event){

      
           try {
               
               
               Statement stm = cnx.createStatement();
               ChoiceBox cb = new ChoiceBox();
               cb.getItems().addAll("item1", "item2", "item3");
               int beg=SS.getIdStationByName(begin.getValue());
               int en=SS.getIdStationByName(end.getValue());
               int pa=SS.getIdStationByName(pause.getValue());
               //System.out.println(difficulty.getValue());
               String query = "INSERT INTO `circuit` (`id`, `nom`, `depart`,`pause`,`end`,`difficulty`,`distance`,`userId`) VALUES (NULL, '"
                       + name.getText() + "','"
                       + beg + "','"
                       + pa + "','"
                       + en + "','"
                       + difficulty.getValue() + "','"
                       + Float.parseFloat(distance.getText()) + "',"
                       + "4)";
               
               try {
                   if(Float.parseFloat(distance.getText())<0){
                       Alert alert=new Alert(Alert.AlertType.WARNING);
                       alert.setTitle("Warning");
                       alert.setHeaderText("Erreur");
                       alert.setContentText("Le champ distance doit etre positif !!");
                       alert.show();
                       distance.clear();
                       
                       
                   }
                   stm.executeUpdate(query);
                   System.out.println("add success");
                   Alert alert=new Alert(Alert.AlertType.INFORMATION);
                   alert.setHeaderText("Ajout Circuit");
                   alert.setContentText("Circuit ajouté avec succès");
                   alert.show();
                   SMSService sms=new SMSService();
                   sms.main(null);
                   
                   refrechTable();
                   name.clear();
                   begin.setCursor(Cursor.NONE);
                   end.setCursor(Cursor.NONE);
                   pause.setCursor(Cursor.NONE);
                   distance.clear();
                   
                   // System.out.println("jawk bhyyy");
               } catch (Exception sQLException) {
                   Alert alertt=new Alert(Alert.AlertType.ERROR);
                   alertt.setTitle("Warning");
                   alertt.setHeaderText("Erreur");
                   alertt.setContentText("Verifiez les champs !!");
                   alertt.show();
               }
               
               
           } catch (SQLException ex) {
               System.out.println(ex.getMessage());   }
         
    
    }

    @FXML
    private void afficherAction(ActionEvent event) throws SQLException {
        try {
            
            Circuit c = new Circuit();
            SC = new ServiceCircuit();
            SS=new ServiceStation();
             List<Circuit> ls = SC.getCircuits();
        
            data = FXCollections.observableArrayList(ls);
            FilteredList<Circuit> fls=new FilteredList(data,e -> true);
            
            nameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
            departCol.setCellValueFactory(new PropertyValueFactory<>("begin"));
            pauseCol.setCellValueFactory(new PropertyValueFactory<>("pause"));
            endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            difficultyCol.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
            distanceCol.setCellValueFactory(new PropertyValueFactory<>("distance"));
            
            table.setItems(fls);
            condition.getItems().addAll("nom","depart","pause","fin","difficulté","distance");
            recherche.setOnKeyReleased(keyEvent ->
            {
                switch(condition.getValue())
                {
                    case "nom":
                    fls.setPredicate(e -> e.getNom().toLowerCase().contains(recherche.getText().toLowerCase().trim()));
                    break;
                    case "depart":
                    fls.setPredicate(e -> String.valueOf(e.getBegin()).toLowerCase().contains(recherche.getText().toLowerCase().trim()));
                    break;
                    case "pause":
                    fls.setPredicate(e -> String.valueOf(e.getPause()).toLowerCase().contains(recherche.getText().toLowerCase().trim()));
                    break;
                    case "fin":
                      fls.setPredicate(e -> String.valueOf(e.getEnd()).toLowerCase().contains(recherche.getText().toLowerCase().trim()));
                    break;
                    case "distance":
                 fls.setPredicate(e -> String.valueOf(e.getDistance()).toLowerCase().contains(recherche.getText().toLowerCase().trim()));
                    break;
                    case "difficulté":
                    fls.setPredicate(e -> e.getDifficulty().toLowerCase().contains(recherche.getText().toLowerCase().trim()));
                    break;
                }
            });
            condition.getSelectionModel().selectedItemProperty().addListener((obs,oldVal,newVal) ->
            { if (newVal != null)
            {
                recherche.setText("");
                fls.setPredicate(null);
            }
            });
        } catch (SQLException sQLException) {
            System.out.println(sQLException.getMessage());
        }
                
    }

    private void refrechTable() throws SQLException {
        data.removeAll();
        List<Circuit> lp = SC.getCircuits();
        data = FXCollections.observableArrayList(lp);
        table.setItems(data);
        //addButtonToTable();
    }
private void refrechTable(List<Circuit> lp) throws SQLException {
        data.removeAll();
    
        data = FXCollections.observableArrayList(lp);
        table.setItems(data);
        //addButtonToTable();
    }
    private void addButtonToTable() {
        TableColumn<Circuit, Void> colBtn = new TableColumn("delete");
        Callback<TableColumn<Circuit, Void>, TableCell<Circuit, Void>> cellFactory
                = new Callback<TableColumn<Circuit, Void>, TableCell<Circuit, Void>>() {
            @Override
            public TableCell<Circuit, Void> call(final TableColumn<Circuit, Void> param) {
                final TableCell<Circuit, Void> cell = new TableCell<Circuit, Void>() {
                    private final Button btn = new Button("delete");

                    {
                        btn.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);\n"
                                + "    -fx-background-radius: 30;\n"
                                + "    -fx-background-insets: 0;\n"
                                + "    -fx-text-fill: white;");
                        btn.setOnAction(
                                (ActionEvent event) -> {
                                    Circuit p = getTableView().getItems().get(getIndex());
                                    try {
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.setTitle("Confirmation Dialog");
                                        alert.setHeaderText("Are you sure to delete " + p.getNom());
                                        alert.setContentText("Are you ok with this?");
                                        Optional<ButtonType> result = alert.showAndWait();
                                        if (result.get() == ButtonType.OK) {
                                            SC.deleteCircuit(p);
                                            refrechTable();
                                        }
                                    } catch (Exception e) {
                                    }
                                    System.out.println("selectedData: " + p);
                                }
                        );
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        TableColumn<Circuit, Void> colBtnUpdate = new TableColumn("update");
        Callback<TableColumn<Circuit, Void>, TableCell<Circuit, Void>> cellFactory2
                = new Callback<TableColumn<Circuit, Void>, TableCell<Circuit, Void>>() {
            @Override
            public TableCell<Circuit, Void> call(final TableColumn<Circuit, Void> param) {
                final TableCell<Circuit, Void> cell;
                cell = new TableCell<Circuit, Void>() {
                    private final Button btn = new Button("Modifier");
                    private Circuit Circuit;

                    {
                        btn.setStyle("-fx-background-color: \n"
                                + "        #c3c4c4,\n"
                                + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
                                + "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
                                + "    -fx-background-radius: 30;\n"
                                + "    -fx-background-insets: 0,1,1;\n"
                                + "    -fx-text-fill: black;\n"
                                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
                        btn.setOnAction(
                                (ActionEvent event) -> {
                                    Circuit p = getTableView().getItems().get(getIndex());
                                    try {
                                        FXMLLoader loader = new FXMLLoader(
                                        getClass().getResource("/javafxapplication4/ModifierCircuit.fxml"));
                                         
                                        Scene scene = new Scene(loader.load());
                                        ModifierCircuitController md = loader.getController();
                                        md.setCircuit(p);
                                        Stage stageAff = new Stage();
                                        stageAff.setScene(scene);
                                        stageAff.show();
                                        ((Node) (event.getSource())).getScene().getWindow().hide();
                                       
                                    
//                                        TextInputDialog dialog = new TextInputDialog("");
//                                        TextInputDialog dialog2 = new TextInputDialog("");
//                                        TextInputDialog dialog3 = new TextInputDialog("");
//                                        TextInputDialog dialog4 = new TextInputDialog("");
//                                        TextInputDialog dialog5 = new TextInputDialog("");
//                                        TextInputDialog dialog6 = new TextInputDialog("");
//                                        TextInputDialog dialog7 = new TextInputDialog("");
//                                        dialog.setTitle("Text Input Dialog");
//                                        dialog.setHeaderText("Update the Name de circuit " + p.getNom());
//                                        dialog.setContentText("Please enter the new Name:");
//                                        dialog2.setHeaderText("Update the Name de circuit " + p.getBegin());
//                                        dialog2.setContentText("Please enter Begin:");
//                                        dialog3.setHeaderText("Update the Name de circuit " + p.getEnd());
//                                        dialog3.setContentText("Please enter Begin:");
//                                        dialog4.setHeaderText("Update the Name de circuit " + p.getPause());
//                                        dialog4.setContentText("Please enter Begin:");
//                                        dialog5.setHeaderText("Update the Name de circuit " + p.getDistance());
//                                        dialog5.setContentText("Please enter Begin:");
//                                        dialog7.setHeaderText("Update the Name de circuit " + p.getDifficulty());
//                                        dialog7.setContentText("Please enter Begin:");
//                                        //Circuit c = new Circuit(p.getName(),p.getBegin(),p.getEnd(),p.getPause(),p.getDistance(),p.getDuration(),p.getDifficulty());
//                                        //Circuit c = new Circuit(6,p.getName(), 1, 2, 3, 12.5f, Time.valueOf(LocalTime.of(02, 10, 10)), "medium");
//                                        // Traditional way to get the response value.
//                                        Optional<String> result = dialog.showAndWait();
//                                        Optional<String> result2 = dialog2.showAndWait();
//                                        Optional<String> result3 = dialog3.showAndWait();
//                                        Optional<String> result4 = dialog4.showAndWait();
//                                        Optional<String> result5 = dialog5.showAndWait();
//                                        Optional<String> result6 = dialog6.showAndWait();
//                                        Optional<String> result7 = dialog7.showAndWait();
                                        //Circuit c = Circuit(21, dialog.getContentText(), Integer.parseInt(dialog2.getContentText()), Integer.parseInt(dialog3.getContentText()), Float.parseFloat(dialog5.getContentText()), dialog7.getContentText());

//                                        if (result.isPresent()) {
//                                            SC.updateCircuit(c);
//                                            refrechTable();
//                                            System.out.println("Your name: " + dialog.getContentText());
//                                        }
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }

                                }
                        );
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }

                    private Circuit Circuit(int i, String contentText, int parseInt, int parseInt0, float parseFloat, String contentText0) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                  

                 
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        colBtnUpdate.setCellFactory(cellFactory2);
        table.getColumns().addAll(colBtn, colBtnUpdate);

    }

    @FXML
    private void btnRefrechAction(ActionEvent event) throws SQLException {
        refrechTable();
    }


    private void conditionAction(MouseEvent event) throws SQLException {
        Statement stm = cnx.createStatement();
        ChoiceBox cb = new ChoiceBox();
        cb.getItems().addAll("item1", "item2", "item3");
    }

    private void rechercheAction(InputMethodEvent event) throws SQLException {
        try {
            String querry = "SELECT * FROM `circuit` WHERE `n` LIKE " + recherche.getText();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(querry);
            List<Circuit> circuits = new ArrayList<>();
            while (rs.next()) {
                Circuit c = new Circuit();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setBegin(SS.getNameStationById(rs.getInt("depart")));
                c.setPause(SS.getNameStationById(rs.getInt("pause")));
                c.setEnd(SS.getNameStationById(rs.getInt("end")));
                c.setDifficulty(rs.getString("difficulty"));
                c.setDistance(rs.getFloat("distance"));
                System.out.println(rs);
                circuits.add(c);
            }
            refrechTable(circuits);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    @FXML
    private void btnGestionStationAction(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            if(mainPane.getChildren()!=null)
            {
                mainPane.getChildren().clear();
            }
            AnchorPane tmp = loader.load();
            mainPane.getChildren().add(tmp);
        } catch (IOException ex) {
            }
    }

    

   



}
