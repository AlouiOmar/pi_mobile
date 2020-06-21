/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ezzedine
 */
public class DescDistance extends SideMenuBaseForm {
    
    public DescDistance(Resources res){
    
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
                                    new Label("Nos Circuits", "Title")
                                )
                            )
                );
        
//        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
//        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
  //      fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(titleCmp);
        
       // current = this;
//        setLayout(BoxLayout.y());
//       // theme = UIManager.initFirstTheme("/theme_1");
//    
//       
//    Toolbar tb = getToolbar();
//        tb.setTitleCentered(false);
//     
//        Button menuButton = new Button("");
//        menuButton.setUIID("Title");
//        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
//        menuButton.addActionListener(e -> getToolbar().openSideMenu());
//        
//        
//        
//        
//
//        Container titleCmp = BoxLayout.encloseY(
//                        FlowLayout.encloseIn(menuButton),
//                        BorderLayout.centerAbsolute(
//                                BoxLayout.encloseY(
//                                    new Label("Nos Circuits", "Title")
//                                )
//                            )
//                );
//        
////        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
////        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
//  //      fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
//        tb.setTitleComponent(titleCmp);
        
        
        
        
      
        
        
        
        
        
        
        Label Labfiltrer=new Label("Tri par :");
              ComboBox list=new ComboBox();
        list.addItem("Tous les Circuits");
        list.addItem("Dist élevé -> Dist bas");
        list.addItem("Dist bas -> Dist élevé");
            addAll(Labfiltrer,list);
            
            list.addActionListener((e)->{
        if(list.getSelectedIndex()==0){
          
        new ListCircuit(res).show();
        }
        
        if(list.getSelectedIndex()==1){
            new DescDistance(res).show();
          
        }
        if(list.getSelectedIndex()==2){
  
            new AscDistance(res).show();
        }
        
            });

        for (Circuit c : ServiceCircuit.getInstance().HighDistance()) {

            
            
            Label tfname = new Label();
            tfname.setText("Nom : " + c.getNom());
            Label tfDepart = new Label();
            tfDepart.setText("Begin : " + c.getBegin());
            Label tfPause = new Label();
            tfPause.setText("Pause : " +c.getPause());
            Label tfEnd = new Label();
            tfEnd.setText("End : " +c.getEnd());
            Label tfDifficulty = new Label();
            tfDifficulty.setText("Difficulty : " +c.getDifficulty());
            Label tfDistance = new Label();
            tfDistance.setText("Distance : " +c.getDistance());
            Container cnt2 = new Container(BoxLayout.y());
            Container cnt1 = new Container(BoxLayout.y());
            Button tfdelete = new Button(FontImage.MATERIAL_DELETE);
          //  Button tfShowSingle = new Button("Détails");
            Button tfModifier = new Button("Modifier");
            cnt1.addAll(tfname, tfDepart, tfPause,tfEnd,tfDifficulty,tfDistance, tfModifier, tfdelete);
            cnt2.add(cnt1);
            //cnt1.add(cnt2);
            add(cnt2);
            tfdelete.addActionListener((e) -> {
                if (Dialog.show("Alert", "Voulez vous supprimer  " + c.getNom() + " !!", "OK", "Cancel")) {
                    if (ServiceCircuit.getInstance().deleteCircuit(c)) {
                        ToastBar.showMessage("La Circuit a été supprimé", FontImage.MATERIAL_WARNING);
                        new ListCircuit(res).show();
                    } else {
                        new ListCircuit(res).show();
                    }
                }
                
            });
//            Container cnt1 = new Container(BoxLayout.x());
//            Container cnt2 = new Container(BoxLayout.y());
//            Container cnt3 = new Container(BoxLayout.x());
//            Button tfdelete = new Button(FontImage.MATERIAL_DELETE);
//            Button tfShowSingle = new Button("Détails");
//            Button tfUpdate = new Button("Modifier");
//            Button tfPanier = new Button(FontImage.MATERIAL_ADD_SHOPPING_CART);
//            cnt3.addAll(tfShowSingle,tfPanier, tfUpdate, tfdelete);
//            cnt2.addAll(tfname, tfprice,cnt3);
//
//            String urll = "http://localhost/PiSymfony/web/public/uploads/" + p.getPhoto_P();
//            EncodedImage enc = EncodedImage.createFromImage(theme.getImage("loading.png").scaled(250, 250), false);
//            URLImage urlimg = URLImage.createToStorage(enc, p.getNom_P(), urll);
//            ImageViewer image = new ImageViewer(urlimg);
//            Image im = image.getImage();
//
//            cnt1.add(im);
//            cnt1.add(cnt2);
//            add(cnt1);
//            tfdelete.addActionListener((e) -> {
//                if (Dialog.show("Alert", "Voulez vous supprimer  " + p.getNom_P() + " !!", "OK", "Cancel")) {
//                    if (ServiceProduit.getInstance().deleteProduit(p)) {
// ToastBar.showMessage("Le produit est supprimé",FontImage.MATERIAL_WARNING);
//                        new ListProductForm(previous).show();
//                    } else {
//                        new ListProductForm(previous).show();
//                    }
//                }
//
//            });
            //tfShowSingle.addActionListener((e)->{
                
                
            //ServiceProduit.getInstance().getProduitSingle(p);
            
           
            
            
           
            
            
              
            
            
            
            
           
            
            

        }
       
  setupSideMenu(res);
// getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
