/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.util.SuccessCallback;
import com.company.myapp.entities.Produit;
import com.company.myapp.entities.TypeProduit;
import com.company.myapp.services.ServiceProduit;
import com.company.myapp.services.ServiceType;
import java.io.IOException;
import com.velo.gui.SideMenuBaseForm;

/**
 *
 * @author Raef
 */
public class AddProductForm extends SideMenuBaseForm{
public static String urlimg;
   public AddProductForm(Resources res) {
    
              super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
     
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        
        
        

        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Nouveau produit", "Title")
                                )
                            )
                );
        
//        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
//        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
  //      fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(titleCmp);
                        
        TextField tfNom = new TextField("","Nom");
        TextField tfMarque= new TextField("", "Marque");
         TextField tfCategorie= new TextField("", "Catégorie");
         TextField tfCouleur= new TextField("", "Couleur");
         TextField tfPrix= new TextField("", "Prix");
         TextField tfPhoto= new TextField("", "photo");
          
         tfPhoto.setText("velo6.jpg");
         Button uplo=new Button("upload");
         
         TextField tfTel= new TextField("", "Téléphone");
         ComboBox tftypes=new ComboBox();
         try {
           for(TypeProduit tp : ServiceType.getInstance().getAllTypes()){
               
               tftypes.addItem(tp.getLibelle_TP());
               
           
           }
       } catch (Exception e) {
       }
     
        System.out.println(tftypes.getSelectedItem().toString());
        Button btnValider = new Button("Ajouter");
        tfNom.setSingleLineTextArea(false);
        tfMarque.setSingleLineTextArea(false);
        tfCategorie.setSingleLineTextArea(false);
        tfCouleur.setSingleLineTextArea(false);
         tfPrix.setSingleLineTextArea(false);
        tfPhoto.setSingleLineTextArea(false);
       
         tfTel.setSingleLineTextArea(false);
         tftypes.setFocusable(false);
         Container content;
       content = BoxLayout.encloseY(
              
            new FloatingHint(tfNom),
            
             new FloatingHint(tfMarque),
               new FloatingHint(tfCategorie),
               new FloatingHint(tfCouleur),
               new FloatingHint(tfPrix),
              new FloatingHint(tfTel)
         
       );
        content.setScrollableY(true);
      
  
//        
//      Label imageLbl = new Label();
//        SuccessCallback<String> callback = (String capturedPhoto) -> {
//    String rotatedPhoto = FileSystemStorage.getInstance().getAppHomePath()+"rotatedphoto.jpg";
//            System.out.println(rotatedPhoto);
//                    int fileNameIndex = rotatedPhoto.lastIndexOf("/") + 1;               
//   //     urlimg=rotatedPhoto.substring(fileNameIndex);
//
//    if (capturedPhoto != null) {
//		try {
//		    // note: we set a maxSize to perform a faster rotation
//		    int maxSize = CN.convertToPixels(50);
//                    
//		    Image img = Image.exifRotation(capturedPhoto, rotatedPhoto, maxSize);
//		    imageLbl.setIcon(img);
//		    revalidate();
//		} catch (IOException ex) {
//		    Log.e(ex);
//		}
//    };
//};
//        
//        
//        
//        
//        uplo.addActionListener(a -> {});
//        
        
        
        
        
        
        
        
        
        
        
        
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfMarque.getText().length()==0)||(tfCategorie.getText().length()==0) ||(tfCouleur.getText().length()==0)||(tfPrix.getText().length()==0))
                    Dialog.show("Alert", "Les champs sont vides , Veuillez les remplir", new Command("OK"));
                else
                {
                    try {
                        
                        Produit p = new Produit(tfNom.getText(), tfMarque.getText(),tfCategorie.getText(),tfCouleur.getText(),Float.parseFloat(tfPrix.getText()),tfPhoto.getText(),(int)Float.parseFloat(tfTel.getText()));
                       
                        if( ServiceProduit.getInstance().addProduit(p,tftypes.getSelectedItem().toString(),com.velo.util.Vars.current_user.getId())){
                          Dialog.show("Alert", "Le produit a été ajouté avec succès", new Command("OK"));
                            new AddProductForm(res).show();}
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Price must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
  
        addAll(content,tftypes ,btnValider);
     
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
 //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->ProfileForm(res));
            setupSideMenu(res);   
    }

    @Override
    protected void showOtherForm(Resources res) {
    }

    

  

    
}
