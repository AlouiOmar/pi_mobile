/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.ServiceCom;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.util.Resources;
/**
 *
 * @author USER
 */
public class AddComFrom extends Form {
 Form current;
    public AddComFrom(Resources res,fos_user u, Post P, Commentaire C) {
        System.out.println(u);
         setTitle("Détails Produits");
         setLayout(BoxLayout.y());
         
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new DetailsForm(res,C,P,u).show());
         
        TextField Con = new TextField("","content");
        
        Button btnValider = new Button("Add Commentaire");
        Button btnAnnuler = new Button("Annuler");
        
         Commentaire Co=new Commentaire();
        
        
         btnAnnuler.addActionListener(new ActionListener() {
                   @Override
         public void actionPerformed(ActionEvent evt) {
                
           new DetailsForm(res,C,P,u).show();
            }} );
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Con.getText().length()==0))
                     Dialog.show("ERROR","Please fill all the fields","ok",null); 
                 
                    
            
                else
                {
                    try {
                       
                        Commentaire e = new Commentaire(Con.getText());
                        System.out.println(u.getId());
                        if( ServiceCom.getInstance().addCo(e,P.getId(),u))
                            Dialog.show("Success","Nouveau Commentaire Ajouter","ok",null);
                        
                        else
                            Dialog.show("ERROR", "Server error", "ok",null);
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", "OK",null);
                       
                    }
                    new DetailsForm(res,C,P,u).show();
                }
                
                
            }
        });
        
        addAll(Con,btnValider,btnAnnuler);
        
        
    }
    
}
