/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.*;
import com.codename1.l10n.DateFormat;
import com.codename1.ui.*;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.fos_user;
import static com.mycompany.myapp.pi_mobile.theme;
import com.mycompany.myapp.services.ServicePost;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;


/**
 *
 * @author USER
 */
public class ListPostForm extends Form{
    
//            private ArrayList<Post> blog=new ArrayList<Post>();       
//            private String imageURL;
//            private String name;
           private EncodedImage placeHolder ;
         //  Hashtable themeData = theme.getTheme("Theme");
//            
            
            
         

    Form current;
    
       

    public ListPostForm(Resources res,Form previous,fos_user user)
    { 
       
        System.out.println(user);
         Container cnt3 = new Container(BoxLayout.y());
        TextField textchercher = new TextField();
       Button chercher =new Button("chercher");
        cnt3.addAll(textchercher,chercher);
        add(cnt3);
       
        setTitle("List post");
     //   ImageViewer ivLogo=new ImageViewer();
            
        for (Post ev : ServicePost.getInstance().getAllPosts()){
            
      //       String image = ev.get(i).getImagename();
            
       placeHolder = EncodedImage.createFromImage(theme.getImage("Point_d_interrogation.jpg"), false);   
          String url="http://localhost/final_pi/web/uplods/post/"+ev.getPhoto();
      Image image1=URLImage.createToStorage(placeHolder, url, url,URLImage.RESIZE_SCALE);
       ImageViewer img=new ImageViewer(image1);   
       img.setPreferredSize(new Dimension(200,200));
            
            
            
            Form f1= new Form();
          int IdE=ev.getId();
             Label lbid=new Label();
             Label lbDateE=new Label();
            Label lbTitre=new Label();
            Label lbRating=new Label();
            Label lbDescription=new Label();
           Label lbcreator=new Label();
            Label lbphoto=new Label();
         //    ImageViewer imgphoto = new ImageViewer();
           // lbphoto.setText(ev.getPhoto());
           
            Label lbNbr=new Label();
         
            Container cnt1 = new Container(BoxLayout.x());
            Container cnt2 = new Container(BoxLayout.y());
             lbid.setText("id: "+ev.getId());
            lbTitre.setText("Titre: "+ev.getTitle());
         //  lbDateE.setText("Date de : "+ev.getPostdate());
            lbDescription.setText("Description: "+ev.getDescription());
            lbRating.setText("rating: "+ev.getRating());
            lbphoto.setText(ev.getPhoto());
            
        //    String image = ev.getPhoto();
       //    String url="http://localhost/final_pi/web/uplods/post/"+image;
           // placeHolder = EncodedImage.createFromImage(theme.getImage("Point_d_interrogation.jpg"), false);
//             Image image1=URLImage.createToStorage(null, url, url,URLImage.RESIZE_SCALE);
         //   ImageViewer img=new ImageViewer(image1);
          //  lbphoto.setText("photo: "+ev.getPhoto());
         //   lbcreator.setText("creator   : "+ev.getUser().getUsername());
           // System.out.println("vvv  " +ev.getUser().getUsername());
       //     Button participer=new Button("Participer");
            Button modifier=new Button("Modifier");
            Button details=new Button("Details");
            Button supprimer=new Button("Supprimer");
           
            cnt1.addAll(modifier,details,supprimer);
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            //methode modifier 
            modifier.addActionListener(a->{ 
               Form me=new Form("Modifier post ",BoxLayout.y());
                 TextField mtfTitre = new TextField(ev.getTitle(),"Titre");
           //     Picker    mdpDateE= new Picker();
                TextField mtfDescription=new TextField(ev.getDescription(),"Description");
                TextField mtfRating=new TextField(Integer.toString(ev.getRating()),"rating ");
                Container mcnt = new Container(BoxLayout.y());
               Label labelid = new Label (Integer.toString(ev.getId()));
        Label nbrating = new Label("Nombre de Rating : "+Integer.toString(ev.getRating()));
                Slider mnbrating = new Slider();
                mcnt.add(nbrating);
                mcnt.add(mnbrating);
                mcnt.add(labelid);
                mnbrating.setEditable(true);
                mnbrating.setMinValue(0);
                mnbrating.setMaxValue(5);
                
                
                Button btnModifier = new Button("Modifier post");
        
                btnModifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                if ((mtfTitre.getText().length()==0)||(mtfDescription.getText().length()==0) )
                     Dialog.show("Alert", "Please fill all the fields","ok",null);  
               
                else 
                {
                    
                    Post ev = new Post(IdE,mtfTitre.getText(),mtfDescription.getText(),mnbrating.getProgress());
                    
                    if( ServicePost.getInstance().modifier(ev)){
                        
                        
                         Dialog.show("Success", "Vous avez modifié post "+ev.getTitle(),"ok",null); 
                   
                        }
                    else{
                        Dialog.show("ERROR","Veuillez réessayer","ok",null); 
                 
                        }
                  new ListPostForm(res,previous,user).show();

                  }                                                 
                }   
                });
                 
       
       me.addAll(mtfTitre,mtfDescription,mcnt,btnModifier);
           me.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
             me.show();        
                
            });
            
            supprimer.addActionListener(b->{
                    if( ServicePost.getInstance().deletePost(ev)){
                         Dialog.show("Success", "Vous avez supprimé post "+ev.getTitle(),"ok",null); 
                        }
                    else{
                           Dialog.show("ERROR","Veuillez réessayer","ok",null); 
                        }
                      new ListPostForm(res,previous,user).show();
            });
            
            
            
            
            cnt2.addAll(img,lbphoto,lbid,lbTitre,lbDateE,lbDescription,lbRating,cnt1);
            add(cnt2);
            show();
        






            
            
            
            
            
            
            
            
            
            
            

//méthode détails 
Commentaire Cmm =new Commentaire();            
  
fos_user  Us = new fos_user();
          
            details.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new DetailsForm(res,Cmm,ev,user).show();
                }
            });    
 //fin methode détails
            
        }
        
       chercher.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                String motchercher = textchercher.getText();
                  Form f1 = new Form(BoxLayout.y());
                  Container cnt3 = new Container(BoxLayout.y());
        TextField textchercher = new TextField();
       Button chercher =new Button("chercher");
       
        cnt3.addAll(textchercher,chercher);
        add(cnt3);
       
        f1.setTitle("la liste des  posts");
            for( Post ev: ServicePost.getInstance().getRech(motchercher)){
                int IdE=ev.getId();
             Label lbid=new Label();
             Label lbDateE=new Label();
            Label lbTitre=new Label();
            Label lbRating=new Label();
            Label lbDescription=new Label();
           Label lbcreator=new Label();
         //   Label lbphoto=new Label();
           // lbphoto.setText(ev.getPhoto());
           
            Label lbNbr=new Label();
         
            Container cnt1 = new Container(BoxLayout.x());
            Container cnt2 = new Container(BoxLayout.y());
             lbid.setText("id: "+ev.getId());
            lbTitre.setText("Titre: "+ev.getTitle());
         //   lbDateE.setText("Date de : "+ev.getPostdate());
            lbDescription.setText("Description: "+ev.getDescription());
            lbRating.setText("rating: "+ev.getRating());
           
           // lbcreator.setText("creator   : "+ev.getCreator());
        
       //     Button participer=new Button("Participer");
            Button modifier=new Button("Modifier");
            Button details=new Button("Details");
            Button supprimer=new Button("Supprimer");
            
            
            
              cnt1.addAll(modifier,details,supprimer);
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            //methode modifier 
            modifier.addActionListener(a->{ 
               Form me=new Form("Modifier post ",BoxLayout.y());
                 TextField mtfTitre = new TextField(ev.getTitle(),"Titre");
           //     Picker    mdpDateE= new Picker();
                TextField mtfDescription=new TextField(ev.getDescription(),"Description");
                TextField mtfRating=new TextField(Integer.toString(ev.getRating()),"rating ");
                Container mcnt = new Container(BoxLayout.y());
               Label labelid = new Label (Integer.toString(ev.getId()));
        Label nbrating = new Label("Nombre de Rating : "+Integer.toString(ev.getRating()));
                Slider mnbrating = new Slider();
                mcnt.add(nbrating);
                mcnt.add(mnbrating);
                mcnt.add(labelid);
                mnbrating.setEditable(true);
                mnbrating.setMinValue(0);
                mnbrating.setMaxValue(5);
                
                
                Button btnModifier = new Button("Modifier post");
        
                btnModifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                if ((mtfTitre.getText().length()==0)||(mtfDescription.getText().length()==0) )
                     Dialog.show("Alert", "Please fill all the fields","ok",null);  
               
                else 
                {
                    
                    Post ev = new Post(IdE,mtfTitre.getText(),mtfDescription.getText(),mnbrating.getProgress());
                    
                    if( ServicePost.getInstance().modifier(ev)){
                        
                        
                         Dialog.show("Success", "Vous avez modifié post "+ev.getTitle(),"ok",null); 
                   
                        }
                    else{
                        Dialog.show("ERROR","Veuillez réessayer","ok",null); 
                 
                        }
                  new ListPostForm(res,previous,user).show();

                  }                                                 
                }   
                });
                 
       
       me.addAll(mtfTitre,mtfDescription,mcnt,btnModifier);
           me.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
             me.show();        
                
            });
            
            supprimer.addActionListener(b->{
                    if( ServicePost.getInstance().deletePost(ev)){
                         Dialog.show("Success", "Vous avez supprimer post "+ev.getTitle(),"ok",null); 
                        }
                    else{
                           Dialog.show("ERROR","Veuillez réessayer","ok",null); 
                        }
                      new ListPostForm(res,previous,user).show();
            });
            
            
            
            Commentaire Cmm =new Commentaire();            
  
fos_user  Us = new fos_user();
          
            details.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new DetailsForm(res,Cmm,ev,user).show();
                }
            });    
            
           
         //   cnt1.addAll(modifier,details,supprimer);
            cnt2.addAll(lbid,lbTitre,lbDateE,lbDescription,lbRating,cnt1);
            f1.add(cnt2);
            f1.show();
        

            
            
             f1.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
            
            
            
            
            
            
              
//                 Label lbTitre=new Label();
//          lbTitre.setText("Titre: "+rr.getTitle());
//                 f1.add(lbTitre);
//                 f1.show();
//               
             }
        
            
        
        }
       });
  
        
       
  
     
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e1->previous.show());
    
   
    
}
}
