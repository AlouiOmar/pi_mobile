/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.materialscreens;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.models.XYSeries;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.CubicLineChart;
import com.codename1.charts.views.PointStyle;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.gui.DetailsForm;
import com.mycompany.myapp.gui.ListPostForm;
import static com.mycompany.myapp.pi_mobile.theme;
import com.mycompany.myapp.services.ServicePost;

/**
 *
 * @author Shai Almog
 */
public class StatsForm extends SideMenuBaseForm {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    private EncodedImage placeHolder ;
    public StatsForm(Resources res,fos_user user) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("user-picture.jpg");        
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label("  Jennifer ", "WelcomeBlue"),
                                new Label("Wilson", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);
        //#######s
{ 
       
        System.out.println(user);
         Container cnt3 = new Container(BoxLayout.y());
        TextField textchercher = new TextField();
       Button chercher =new Button("chercher");
        cnt3.addAll(textchercher,chercher);
//        add(cnt3);
       
        setTitle("List post");
     //   ImageViewer ivLogo=new ImageViewer();
                        Container cnt2 = new Container(BoxLayout.y());

        for (Post ev : ServicePost.getInstance().getAllPosts()){
            
      //       String image = ev.get(i).getImagename();
            
       placeHolder = EncodedImage.createFromImage(res.getImage("Point_d_interrogation.jpg"), false);   
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
//                  new ListPostForm(previous,user).show();

                  }                                                 
                }   
                });
                 
       
       me.addAll(mtfTitre,mtfDescription,mcnt,btnModifier);
           me.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new StatsForm(res,user));
             me.show();        
                
            });
            
            supprimer.addActionListener(b->{
                    if( ServicePost.getInstance().deletePost(ev)){
                         Dialog.show("Success", "Vous avez supprimé post "+ev.getTitle(),"ok",null); 
                        }
                    else{
                           Dialog.show("ERROR","Veuillez réessayer","ok",null); 
                        }
//                      new ListPostForm(previous,user).show();
            });
            
            
            
            
            cnt2.addAll(img,lbid,lbTitre,lbDateE,lbDescription,lbRating,cnt1);
            cnt2.setScrollableY(true);
//            show();
        






            
            
            
            
            
            
            
            
            
            
            

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
        add(BorderLayout.CENTER,cnt2);
        show();
       chercher.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                String motchercher = textchercher.getText();
                  Form f1 = new Form(BoxLayout.y());
                  Container cnt3 = new Container(BoxLayout.y());
        TextField textchercher = new TextField();
       Button chercher =new Button("chercher");
       
        cnt3.addAll(textchercher,chercher);
//        add(cnt3);
       
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
//                  new ListPostForm(previous,user).show();

                  }                                                 
                }   
                });
                 
       
       me.addAll(mtfTitre,mtfDescription,mcnt,btnModifier);
           me.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ProfileForm(res));
             me.show();        
                
            });
            
            supprimer.addActionListener(b->{
                    if( ServicePost.getInstance().deletePost(ev)){
                         Dialog.show("Success", "Vous avez supprimer post "+ev.getTitle(),"ok",null); 
                        }
                    else{
                           Dialog.show("ERROR","Veuillez réessayer","ok",null); 
                        }
//                      new ListPostForm(previous,user).show();
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
//            f1.add(cnt2);
//            f1.show();
           
            add(BorderLayout.CENTER,cnt2);
            
            
             f1.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ProfileForm(res));
            
            
            
            
            
            
              
//                 Label lbTitre=new Label();
//          lbTitre.setText("Titre: "+rr.getTitle());
//                 f1.add(lbTitre);
//                 f1.show();
//               
             }
        
            
        
        }
       });
  
        
       
  
     
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e1-> new ProfileForm(res));
    
   
    
}
        //########e
        setupSideMenu(res);
    }

    
    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }

   
}
