/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.log.BCrypt;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.Vars;
import com.mycompany.myapp.entities.Fos_user;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class logform  extends Form{
 Form current;
    public logform() {
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
                     
                ArrayList<Fos_user>  users = ServiceUser.getInstance().getAllUsers();
        boolean res=false;
        int i=0;
         if ((text1.getText().length()==0)||(text2.getText().length()==0))
              Dialog.show("Alert", "Veuillez remplir les champs vides","ok",null); 
           
                else
         {
        while(res==false ){
            System.out.println("mot de passe de utilisateur   "+text2.getText());
            
        
           String password = text2.getText();
          
         
          String crypted = "$2a" + users.get(i).getPassword().substring(3);
            if(BCrypt.checkpw(password,crypted) && 
                users.get(i).getUsername().equals(text1.getText())){
                   res=true;
                   
                   System.out.println(res);
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
         
       Fos_user  Us = Vars.current_user;
        if(res==true){
            
          
              System.out.println("aazza");
              
//        
//            BaseForm f=new BaseForm();
//            f.setImg((EncodedImage) theme.getImage("user.jpg"));
//            f.setUrl("http://localhost/Minipo/web/uploads/post/" +users.get(i).getImage());
//            f.setNameuser(users.get(i).getUsername());
//            
//            BaseEmployeForm1 fe = new BaseEmployeForm1();
//            fe.setImg((EncodedImage) theme.getImage("user.jpg"));
//            fe.setUrl("http://localhost/Minipo/web/uploads/post/" +users.get(i).getImage());
//            fe.setNameuser(users.get(i).getUsername());
//                
//            BaseAgentRHForm11 rh=new BaseAgentRHForm11();
//            rh.setImg((EncodedImage) theme.getImage("user.jpg"));
//            rh.setUrl("http://localhost/Minipo/web/uploads/post/" +users.get(i).getImage());
//            rh.setNameuser(users.get(i).getUsername());
//                
//            BaseLivreurForm11 liv= new BaseLivreurForm11();
//            liv.setImg((EncodedImage) theme.getImage("user.jpg"));
//            liv.setUrl("http://localhost/Minipo/web/uploads/post/" +users.get(i).getImage());
//            liv.setNameuser(users.get(i).getUsername());

            
//            if (users.get(i).getRoles().contains("ROLE_USERS")){

              //  Hashtable themeData = theme.getTheme("Theme");
             //   UIManager.getInstance().setThemeProps(themeData);
             //   new HomeForm().show();
//            }
//             
//            if (users.get(i).getRoles().contains("ROLE_EMPLOYE")){
//            
//                Hashtable themeData = theme.getTheme("BaseEmp");
//                UIManager.getInstance().setThemeProps(themeData);
//                new EmployeRhForm().show();
//            }
//           if (users.get(i).getRoles().contains("ROLE_RH")){
//                
//                Hashtable themeData = theme.getTheme("BaseEmp");
//                UIManager.getInstance().setThemeProps(themeData);
//                new AgentRHForm().show();
//            }
//            if (users.get(i).getRoles().contains("ROLE_LIVREUR")){ 
//
//                Hashtable themeData = theme.getTheme("BaseEmp");
//                UIManager.getInstance().setThemeProps(themeData);
//                new LivreurForm().show();
//            }
        
        }
        else{
             Dialog.show("Alert", "veuillez verifier votre username ou mot de passe ","ok",null);
        }
            }
            }
          });
          cnt4.addAll(text1,text2,login,loginfc);
          add(cnt4);
          show();
          

    }
}