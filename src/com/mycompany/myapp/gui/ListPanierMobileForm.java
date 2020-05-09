/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.rest.Response;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import com.company.myapp.entities.Produit;
import com.company.myapp.services.ServiceProduit;
import java.util.Map;

/**
 *
 * @author Raef
 */
public class ListPanierMobileForm  extends SideMenuBaseForm{
    
    Resources theme;
    Form current;
    
    public ListPanierMobileForm(Form previous){
    
    
     current = this;
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme_1");
    
       
          Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = theme.getImage("user-picture.jpg");
        Image mask = theme.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        
        
        

        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Jennifer Wilson", "Title"),
                                    new Label("UI/UX Designer", "SubTitle")
                                )
                            ).add(BorderLayout.WEST, profilePicLabel),
                        GridLayout.encloseIn(2)
                );
        
//        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
//        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
  //      fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(titleCmp);
        
        
        
        
        
        
        
        
        
        
        

      
//
//        Button confirmer=new Button("send");
//        
//         
//          add(confirmer);
//        
//       //
//        try {
//            
//        
//        confirmer.addActionListener(e->{
//        
//        
//        
//        
//        
//        
//        
//        
//        
//        
//        
//Message m = new Message("Body of message");
//            String textAttachmentUri = null;
// m.getAttachments().put(textAttachmentUri, "text/plain");
//           String imageAttachmentUri = null;
// m.getAttachments().put(imageAttachmentUri, "image/png");
//  Display.getInstance().sendMessage(new String[] {"raef.bchiri@esprit.tn"}, "Votre panier est vide", m);
//      
//      
//         
////   Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
////m.setMimeType(Message.MIME_HTML);
////
////// notice that we provide a plain text alternative as well in the send method
////boolean success = m.sendMessageViaCloudSync("Codename One", "destination@domain.com", "Name Of User", "Message Subject",
////                            "Check out Codename One at https://www.codenameone.com/");
////       
////       
//       
//       
//       });
//        
//        
//        } catch (Exception e) {System.out.println(e.getMessage());
    //    }
    setupSideMenu(theme);   
    }
}
