/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.velo.gui.SideMenuBaseForm;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.services.ServiceEvent;
import com.velo.gui.AfficherListeAnnonces;
import com.velo.util.Vars;
import java.io.IOException;

/**
 *
 * @author bhk
 */
public class AjouterEventForm extends SideMenuBaseForm{

    public AjouterEventForm(Resources res) {
        setTitle("Ajout de l'evenement");
        setLayout(BoxLayout.y());
                setUIID("LoginForm");
                    getStyle().setBgColor(0x99CCCC);
            getStyle().setBgTransparency(255);
        TextField tfTitre = new TextField("","Titre");
        Picker    dpDateE= new Picker();
        TextField tfDescription=new TextField("","Description");
        TextField tfRegion=new TextField("","Region");
        TextField tfNameC=new TextField("","Circuit");
        Container cnt = new Container(BoxLayout.y());
        Label lbnbplaces = new Label("Nombre de places : 0");
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> getToolbar().openSideMenu());
       
        lbnbplaces.getStyle().setFgColor(0xff000);
        Slider nbplaces = new Slider();
           nbplaces.getStyle().setBgTransparency(255);
        cnt.add(lbnbplaces);
        cnt.add(nbplaces);
        nbplaces.setEditable(true);
        nbplaces.setMinValue(0);
        nbplaces.setMaxValue(100);
        nbplaces.addActionListener((e)->{        
        lbnbplaces.setText("Nombre de places : "+nbplaces.getProgress());
        });       
        Button btnValider = new Button("Creer evenement");
        btnValider.setUIID("LoginButton");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitre.getText().length()==0)||(dpDateE.getText().length()==0)||(tfDescription.getText().length()==0)||(tfRegion.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
//                    Integer.parseInt(tfStatus.getText()),
                    Event ev = new Event(tfTitre.getText(),dpDateE.getText(),tfDescription.getText(),tfRegion.getText(),tfNameC.getText(),nbplaces.getProgress());
                    if( ServiceEvent.getInstance().addEvent(ev)){
                        Dialog.show("Succés","Evenement"+tfTitre.getText()+"a été ajouté avec succés!",new Command("OK"));
                    }
                    else{
                        Dialog.show("Erreur!", "Veuillez réessayer ultériérement", new Command("OK"));
                    }  
                    
                }
                
                
            }
        });
        
        addAll(tfTitre,dpDateE,tfDescription,tfRegion,tfNameC,cnt,btnValider);
        setupSideMenu(res);
                
    }

    @Override
    protected void showOtherForm(Resources res) {
    }
    
    
}
