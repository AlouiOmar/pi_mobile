/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Dialog;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.Vars;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.ServicePost;
import rest.file.uploader.tn.FileUploader;
import java.util.*;
import com.codename1.uikit.materialscreens.StatsForm;
import static com.mycompany.myapp.pi_mobile.theme;
/**
 *
 * @author USER
 */
public class AddPostForm extends SideMenuBaseForm
{
     private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    private EncodedImage placeHolder ;
    
//private FileUploader file;
//String fileNameInServer; 
//private String imgPath;
      String img;
         private String imgPath;
         String Imagecode ;
         private FileUploader file;
         String fileNameInServer;
Post pass=new Post();
   public  AddPostForm(Resources res,fos_user u) {
       
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
       
  /*      #############################################s*/
    
  {    
       Label pathphoto = new Label(".");
      setTitle("addpost");
     //   setLayout(BoxLayout.y());
        Container cnt5 = new Container(BoxLayout.y());
      //   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> current.showBack());
         
       
         
         
         
         Style s = UIManager.getInstance().getComponentStyle("Title");
         Button  capturephoto = new Button("prenez une photo");
         Label picture = new Label("", "Container");
         
         
         
         Label lab1= new Label("saisir  un titre");
         TextField texttitre = new TextField();
         Label lab2 = new Label("saisir  une description");  
         TextField textdescription= new TextField();
         Label lab3 = new Label("saisir  rating de 1 à 5");
         TextField textrating = new TextField();
        
       Button tphoto = new Button("ajouter  photo");
         Button btn = new Button("add post");
          btn.getUnselectedStyle().setFgColor(5542241);
        
 
        
         
         btn.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
            if ((texttitre.getText().length()==0)||(textdescription.getText().length()==0)|| (textrating.getText().length()==0))
             Dialog.show("Alert", "Please fill all the fields","ok",null);    
           
            else
                {
                    try {
                         //  System.out.println("name  "+pass.getPhoto());
                        Post t = new Post(texttitre.getText(),textdescription.getText(),Integer.parseInt(textrating.getText()),fileNameInServer);
                   //       Post t = new Post(texttitre.getText(),textdescription.getText(),Integer.parseInt(textrating.getText()));
                         System.out.println(t);
                        if( ServicePost.getInstance().addPost(t,u))
                        {
                            System.out.println("t"+t+"utilisateur de l'id   "+u.getId());
                            Dialog.show("Success","Connection accepted","ok",null);
                        }
                        else
                            Dialog.show("ERROR", "Server error", "ok",null);
                    }
                    catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number","ok",null);
            }
                    
                }
                
                
            }
        });
         
//          capturephoto.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                
//              String i=  Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
//              if(i!=null)
//              {
//                  System.out.println(i);
//                  
//                    String str = "how to do in java provides java tutorials";
//         
//         String k="file://C:/Users/USER/AppData/Local/Temp/";
//        
//         System.out.println("length: "+k.length());
//         imgPath=i.substring(40);
//        System.out.println("devenir  "+i.substring(40));
//                  
//                  try {
//                      Image img = Image.createImage(i);
//                         picture.setIcon(img);
//                         current.revalidate();
//                  } catch (Exception e) {
//                  }
//                 }}} );
//          
          
          
          
         
          tphoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 try {
                    imgPath = Capture.capturePhoto();
                    System.out.println(imgPath);
                    String link=imgPath.toString();
                    int pod=link.indexOf("/",2);
                    String news=link.substring(pod + 2, link.length());
                    System.out.println(""+news);
                    FileUploader fu = new FileUploader("http://localhost/final_pi/web/uplods/");
                    fileNameInServer = fu.upload(news);
                    
                    pathphoto.setText(fileNameInServer);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
//            @Override
//            public void actionPerformed(ActionEvent evt) {}
              
          });
         
      
         
         
   
         
         
         
         
        
         
         
         
         
         
         cnt5.addAll(lab1,texttitre,lab2,textdescription,lab3,textrating,pathphoto,tphoto,btn);
        add(BorderLayout.CENTER,cnt5);
  }   
       
       
       setupSideMenu(res);
    }   
         
    
     
 @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }


}

    
    
    

