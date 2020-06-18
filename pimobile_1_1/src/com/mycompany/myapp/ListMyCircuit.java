/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ToastBar;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ezzedine
 */
public class ListMyCircuit extends SideMenuBaseForm{
Database db;
ArrayList <Circuit> circuits;
    public ListMyCircuit(Resources res) {
        
           super(BoxLayout.y());
         
           circuits=new ArrayList<>();
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
                                    new Label("Mes Circuits", "Title")
                                )
                            )
                );
        tb.setTitleComponent(titleCmp);
    
             
        setLayout(BoxLayout.y());
       
    
       
        Container filtre = new Container (BoxLayout.x());
        
    try {
        db=Database.openOrCreate("velo");
        Cursor c=db.executeQuery("select * from circuit;");
        while (c.next()) {
            Row r=c.getRow();
            String name=r.getString(1);
            String begin=r.getString(2);
            String pause=r.getString(3);
            String end=r.getString(4);
            String dificulty=r.getString(5);
            float distance=r.getFloat(6);
            Circuit cc=new Circuit(1, name, begin, pause, end, distance, dificulty);
            circuits.add(cc);
        }
        db.close();
        for(Circuit c1:circuits){
        Label tfname = new Label();
            tfname.setText("Nom : " + c1.getNom());
            Label tfDepart = new Label();
            tfDepart.setText("Begin : " +c1.getBegin() );
            Label tfPause = new Label();
            tfPause.setText("Pause : " +c1.getPause());
            Label tfEnd = new Label();
            tfEnd.setText("End : " +c1.getEnd());
            Label tfDifficulty = new Label();
            tfDifficulty.setText("Difficulty : " +c1.getDifficulty());
            Label tfDistance = new Label();
            tfDistance.setText("Distance : " +c1.getDistance()+" Km");
            Container cnt2 = new Container(BoxLayout.y());
            Container cnt1 = new Container(BoxLayout.y());

            Button tfdelete = new Button(FontImage.MATERIAL_DELETE);
            Button tfShowMaps = new Button("Shwow Map");
           
            Button tfModifier = new Button("Modifier");
             Button tfPanier = new Button(FontImage.MATERIAL_ADD_SHOPPING_CART);
            cnt1.addAll(tfname, tfDepart, tfPause,tfEnd,tfDifficulty,tfDistance,tfShowMaps, tfModifier, tfdelete);
            cnt2.add(cnt1);
            //cnt1.add(cnt2);
            add(cnt2);
            tfdelete.addActionListener((e) -> {
                if (Dialog.show("Alert", "Voulez vous supprimer  " + c1.getNom() + " !!", "OK", "Cancel")) {
                    if (ServiceCircuit.getInstance().deleteCircuit(c1)) {
                        try {
                            db=Database.openOrCreate("velo");
                            db.execute("delete from circuit where name="+c1.getNom());
                            db.close();
                        } catch (IOException ex) {
                            
                           }
                        ToastBar.showMessage("La Circuit a été supprimé", FontImage.MATERIAL_WARNING);
                       
                    } else {
                     
                        
                    }
                }
                
            });
        }
    } catch (IOException ex) {
     }
        
        
for (Circuit c : ServiceCircuit.getInstance().getAllCircuits()) {
            
           

        }
setupSideMenu(res);
    }
    }
    

