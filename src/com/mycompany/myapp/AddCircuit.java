/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ToastBar;
import com.codename1.db.Database;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.velo.util.Vars;
import java.io.IOException;
import java.util.Map;
import com.velo.gui.SideMenuBaseForm;


/**
 *
 * @author ezzedine
 */
public class AddCircuit extends SideMenuBaseForm {

    String accountSID = "ACc2f893ae62a40b5c8386ff775df3013b";
    String authToken = "ebbd8746b2373469b66175dcaad900ae";
    String fromPhone = "+18564153325";
    String val;
    Database db;
    Fos_user loggedInUser;

    public AddCircuit(Resources res) {
     
        super(BoxLayout.y());
         loggedInUser=new Fos_user();
         loggedInUser.setId(2);

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Add a new Circuit", "Title")
                        )
                )
        );

//        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
//        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        //      fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(titleCmp);

        setLayout(BoxLayout.y());

        //ListStation stations = new ListStation(previous);
        Label tfNom1 = new Label("Nom");
        TextField tfNom = new TextField("");

        Label tfBegin1 = new Label("Begin");
        ComboBox tfBegin = new ComboBox();
        try {
            for (Station st : ServiceStation.getInstance().getAllStations()) {

                tfBegin.addItem(st.getNom().toString());

            }
        } catch (Exception e) {
        }

        //System.out.println(tfBegin.getSelectedItem().toString());
        Label tfPause1 = new Label("Pause");
        ComboBox tfPause = new ComboBox();
        try {
            for (Station st : ServiceStation.getInstance().getAllStations()) {

                tfPause.addItem(st.getNom().toString());

            }
        } catch (Exception e) {
        }

        //System.out.println(tfBegin.getSelectedItem().toString());
        Label tfEnd1 = new Label("End");
        ComboBox tfEnd = new ComboBox();
        try {
            for (Station st : ServiceStation.getInstance().getAllStations()) {

                tfEnd.addItem(st.getNom().toString());

            }
        } catch (Exception e) {
        }
        //System.out.println(tfEnd.getSelectedItem().toString());

        Label tfDistance1 = new Label("Distance:");
        TextField tfDistance = new TextField("");

        Label Difficulty1 = new Label("Difficulty");
        String[] characters = {"easy", "medium", "hard"};
        Picker Difficulty = new Picker();
        Difficulty.setStrings(characters);
        Difficulty.setSelectedString(characters[0]);

//  
        // SMSService sms=new SMSService();
        Button btnValider = new Button("Ajouter Circuit");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length() == 0) || (tfBegin.getSelectedItem().toString().length() == 0) || (tfPause.getSelectedItem().toString().length() == 0) || (tfEnd.getSelectedItem().toString().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Circuit t = new Circuit(tfNom.getText(), tfBegin.getSelectedItem().toString(), tfPause.getSelectedItem().toString(), tfEnd.getSelectedItem().toString(), Difficulty.getText(), Float.parseFloat(tfDistance.getText()));
                       t.setUserId(Vars.current_user.getId());
                        if (ServiceCircuit.getInstance().addCircuit(t)) {

                            db = Database.openOrCreate("velo");
                            db.execute("create table if not exists circuit(id Integer,name text,depart text,pause text,end text,dificulty text,distance float);");
                            db.execute("insert into circuit (id,name,depart,pause,end,dificulty,distance) "
                                    + "values(1,"
                                    + "'" + t.getNom() + "','"
                                    + t.getBegin() + "','"
                                    + t.getPause() + "','"
                                    + t.getEnd() + "','"
                                    + t.getDifficulty() + "',"
                                    + t.getDistance() + ");");
                           
                            db.close();

                            val="le circuit"+t.getNom()+"est ajouté avec succes";
                            String destinationPhone="+21695592018";
                            Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/"+accountSID+"/Messages.json").
                            queryParam("To", destinationPhone).
                            queryParam("From", fromPhone).
                            queryParam("Body", val).
                            basicAuth(accountSID, authToken).
                            getAsJsonMap();
                            if(result.getResponseData() != null) {
                            String error = (String)result.getResponseData().get("error_message");
                              //ToastBar.showInfoMessage("SUCCES:un message est envoyé ");
                              
                             
                            Dialog.show("","Circuit ajouté ",new Command("OK")); 
                            new AddCircuit(res).show();
                            } else{
                            Dialog.show("ERROR", "Server error", new Command("OK"));}
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Distance doit etre un entier", new Command("OK"));
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());   
                    }

                }

            }
        });

        addAll(tfNom1, tfNom, tfBegin1, tfBegin, tfPause1, tfPause, tfEnd1, tfEnd, Difficulty1, Difficulty, tfDistance1, tfDistance, btnValider);
        setupSideMenu(res);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListCircuit(previous).show());

    }

    @Override
    protected void showOtherForm(Resources res) {
    }
}
