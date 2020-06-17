/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.BCrypt;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.Vars;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.ServicePost;
import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class LoginForm  extends Form{
 Form current;
    public LoginForm(Resources res) {
       
        current=this;
         setTitle("login");
        setLayout(new FlowLayout(CENTER,CENTER));
        Container cnt4 = new Container(BoxLayout.y());
        TextField text1 = new TextField("","saisir votre nom");
         TextField text2 = new TextField("","saisir votre mot de passe ");
         Button login  = new Button("Login");
          Button loginfc  = new Button("connectez avec facebook");
          
          
          login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                     
                ArrayList<fos_user>  users = ServiceUser.getInstance().getAllUsers();
        boolean res1=false;
        int i=0;
         if ((text1.getText().length()==0)||(text2.getText().length()==0))
              Dialog.show("Alert", "Veuillez remplir les champs vides","ok",null); 
           
                else
         {
        while(res1==false ){
            System.out.println("mot de passe de utilisateur   "+text2.getText());
            
        
           String password = text2.getText();
          
         
          String crypted = "$2a" + users.get(i).getPassword().substring(3);
            if(BCrypt.checkpw(password,crypted) && 
                users.get(i).getUsername().equals(text1.getText())){
                   res1=true;
                   
                   System.out.println(res1);
             int      idUser=users.get(i).getId();
                   System.out.println( "id de l'utilisateur   "+idUser);
                    System.out.println( "email de l'utilisateur   "+ users.get(i).getEmail());
                    Vars.current_user = users.get(i);
                    System.out.println("curent user  " +  Vars.current_user.getId());
        
            }
            else {
                i++;
               
                
            }
        }
         
       fos_user  Us = Vars.current_user;
        if(res1==true){
            
          
              new HomeForm(res,Us).show();
              

        }
        else{
             Dialog.show("Alert", "veuillez verifier votre username ou mot de passe ","ok",null);
        }
            }
            }
          });
          
          
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
       text1.getUnselectedStyle().setFont(l1_font);
       text2.getUnselectedStyle().setFont(l1_font);
           login.getUnselectedStyle().setFgColor(5542241);
        
       
        
     
//        
        
        
            
              cnt4.addAll(text1,text2,login);
              add(cnt4);
              show();
          

    }
}