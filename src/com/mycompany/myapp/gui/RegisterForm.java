/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.File;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.ServicePost;
import com.mycompany.myapp.services.ServiceUser;

/**
 *
 * @author USER
 */
public class RegisterForm extends Form {

    public RegisterForm(Form current) { 
        setTitle("Register");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> current.showBack());
   
         TextField tnom = new TextField ("","nom");
         TextField tprenom= new TextField ("","prenom");
         TextField temail= new TextField ("","email");
         TextField tage= new TextField ("","age");
         TextField ttelephone= new TextField ("","telephone");
       TextField tpassor=new TextField("", "saisir password");
       
TextField tphoto= new TextField ("","photo");
         tpassor.setConstraint(TextField.PASSWORD);
        ComboBox<String> cb= new ComboBox();
        TextField troles= new TextField ();
        troles.setVisible(false);
         Button btnval=new Button("valider");
        
        Label lb = new Label("Role");
        cb.addItem("administrateur");
        cb.addItem("utilisateur");
    
         cb.addActionListener((e)->{
           if(cb.getSelectedIndex()== 0){
                 troles.setText("administrateur");
             }
           
             if(cb.getSelectedIndex()== 1){
                 
                      troles.setText("utilisateur");
             }           
    });
       
     
    
       addAll(tnom,tprenom,temail,tage,ttelephone,tpassor,cb,btnval);
      
       
       btnval.addActionListener(new ActionListener() {      
       @Override
            public void actionPerformed(ActionEvent evt) {
            
                 if ((tnom.getText().length()==0)||(tprenom.getText().length()==0)
                         ||(tage.getText().length()==0)||(temail.getText().length()==0)
                         ||(ttelephone.getText().length()==0)
                         ||(tpassor.getText().length()==0 ))
                     
                       Dialog.show("Alert", "Please fill all the fields","ok",null);    
                     
                   else
                {
                    try {
                        fos_user user = new fos_user(tnom.getText(),tnom.getText(),temail.getText(),temail.getText(),1,tpassor.getText(),troles.getText(),tprenom.getText(),Integer.parseInt(tage.getText()),Integer.parseInt(ttelephone.getText()),tphoto.getText());
                         System.out.println(user);
                        if( ServiceUser.getInstance().addUser(user))
                            Dialog.show("Success","Connection accepted","ok",null);
                        else
                            Dialog.show("ERROR", "Server error", "ok",null);
                    }
                    catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number","ok",null);
            }
                    
                }   
                     
                     
                     
                     
                     
                         
                
                
            }
       
       
            });
       
       
    
    
    
    
    
    
    

   
}
}
