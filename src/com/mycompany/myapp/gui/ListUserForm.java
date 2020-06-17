/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.ServicePost;
import com.mycompany.myapp.services.ServiceUser;

/**
 *
 * @author USER
 */
public class ListUserForm extends Form{

    public ListUserForm(Form current) {
        
         setTitle("List des utilisateur");
        for (fos_user ev : ServiceUser.getInstance().getAllUsers()){
          int IdE=ev.getId();
             Label lbid=new Label();
             Label lbusername=new Label();
            Label lbfirstname=new Label();
            Label lbpassword=new Label();
    
         //   Label lbphoto=new Label();
           // lbphoto.setText(ev.getPhoto());
           
           
         
            Container cnt1 = new Container(BoxLayout.x());
            Container cnt2 = new Container(BoxLayout.y());
             lbid.setText("id: "+ev.getId());
            lbusername.setText("username: "+ev.getUsername());
            lbfirstname.setText("firstname : "+ev.getFirstname());
            lbpassword.setText("email: "+ev.getEmail());
         
         
        
       //     Button participer=new Button("Participer");
            Button modifier=new Button("Modifier");
            Button details=new Button("Details");
            Button supprimer=new Button("Supprimer");
           
            cnt1.addAll(modifier,details,supprimer);
            
        cnt2.addAll(lbid,lbusername,lbfirstname,lbpassword,cnt1);
       
        addAll(cnt2);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> current.showBack());
    }
    
    }
}
