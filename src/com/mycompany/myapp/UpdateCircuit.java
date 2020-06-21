package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ezzedine
 */
public class UpdateCircuit extends SideMenuBaseForm {
    Resources theme;
    UpdateCircuit(Circuit c, Resources res) {
        
        super(BoxLayout.y());
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
                                    new Label("Nos Circuits", "Title")
                                )
                            )
                );
        
//        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
//        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
  //      fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(titleCmp);
        
        
          theme = UIManager.initFirstTheme("/theme");
        setTitle("Modifier  Circuit");
        setLayout(BoxLayout.y());
        Label tfNom1 = new Label("Nom:");
        TextField tfNom = new TextField(c.getNom());
        Label tfBegin1 = new Label("Begin:");
        ComboBox tfBegin=new ComboBox();
         try {
           for(Station st : ServiceStation.getInstance().getAllStations()){
               
               tfBegin.addItem(st.getNom().toString());
               
           
           }
       } catch (Exception e) {
       }
     
        Label tfPause1 = new Label("Pause:");
        ComboBox tfPause=new ComboBox();
         try {
           for(Station st : ServiceStation.getInstance().getAllStations()){
               
               tfPause.addItem(st.getNom().toString());
               
           
           }
       } catch (Exception e) {
       }
     
        Label tfEnd1 = new Label("End:");
        ComboBox tfEnd=new ComboBox();
         try {
           for(Station st : ServiceStation.getInstance().getAllStations()){
               
               tfEnd.addItem(st.getNom().toString());
               
           
           }
       } catch (Exception e) {
       }
        
        Label tfDistance1 = new Label("Distance:(km)");
        TextField tfDistance = new TextField("");
        
   
        Label Difficulty1 = new Label("Nom");
        String[] characters = { "easy", "medium", "hard"};
        Picker Difficulty = new Picker();
        Difficulty.setStrings(characters);
        Difficulty.setSelectedString(characters[0]);
//        Label tfMarque1 = new Label("Marque");
//        TextField tfMarque = new TextField(p.getMarque_P());
//        Label tfCategorie1 = new Label("Catégorie");
//        TextField tfCategorie = new TextField(p.getCategorie_P());
//        Label tfCouleur1 = new Label("Couleur");
//
//        TextField tfCouleur = new TextField(p.getCouleur_P());
//        Label tfPrix1 = new Label("Prix");
//        TextField tfPrix = new TextField((int)(float)p.getPrix_P());
        
//        Label tftel1 = new Label("Téléphone");
     // TextField tftel = new TextField(p.getTel());

        Button btnValider = new Button("Modifier");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length() == 0) ) {
                    Dialog.show("Alert", "Les champs sont vides , Veuillez les remplir", new Command("OK"));
                } else {
                    try {
                        c.setId(c.getId());
                        c.setNom(tfNom.getText());
                        c.setBegin(tfBegin.getSelectedItem().toString());
                        c.setPause(tfPause.getSelectedItem().toString());
                        c.setEnd(tfEnd.getSelectedItem().toString());
                        c.setDifficulty(Difficulty.getText());
                        c.setDistance(Float.parseFloat(tfDistance.getText()));
                        
                        ServiceCircuit.getInstance().UpdateCircuit(c);

                        Dialog.show("", "Le Circuit a été modifié avec succès", new Command("OK"));
                        new ListCircuit(theme);
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Distance must be a number", new Command("OK"));
                        
                    }

                }

            }

             
        });

        addAll(tfNom1,tfNom, tfBegin1,tfBegin,tfPause1,tfPause, tfEnd1,tfEnd,Difficulty1,Difficulty, tfDistance1,tfDistance);
     //add(tftel);
        add(btnValider);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListCircuit(res).show());
        setupSideMenu(res);
    }
    
}
