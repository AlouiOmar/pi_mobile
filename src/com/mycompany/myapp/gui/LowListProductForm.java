/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.company.myapp.entities.Produit;
import com.company.myapp.services.ServiceProduit;
import com.velo.util.Vars;
import com.velo.gui.SideMenuBaseForm;
/**
 *
 * @author Raef
 */
public class LowListProductForm extends SideMenuBaseForm{
     Resources theme;
    Form current;
Database db;
    public LowListProductForm(Form previous){
    
     
        
        
        current = this;
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme_1");
    
       
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
                                    new Label("Nos produits", "Title")
                                )
                            )
                );
        
//        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
//        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
  //      fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(titleCmp);
        
        
        
        
      
        
        
        
        
        
        
        
        Label Labfiltrer=new Label("Filter par :");
              ComboBox list=new ComboBox();
        list.addItem("Tous les produits");
        list.addItem("prix élevé -> prix bas");
        list.addItem("prix bas -> prix prix élevé");
            addAll(Labfiltrer,list);
            
            list.addActionListener((e)->{
        if(list.getSelectedIndex()==0){
          
        new ListProductForm(previous).show();
        }
        
        if(list.getSelectedIndex()==1){
            new HighListProductForm(previous).show();
          
        }
        if(list.getSelectedIndex()==2){
  
            new LowListProductForm(previous).show();
        }
        
            });

         for (Produit p : ServiceProduit.getInstance().getLowProduits()) {
if (p.getUserId() == Vars.current_user.getId()){
            Label tfname = new Label();
            tfname.setText("Nom : " + p.getNom_P());
            Label tfprice = new Label();
            tfprice.setText("Prix : " +(int)p.getPrix_P() + " DT");
            Container cnt1 = new Container(BoxLayout.x());
            Container cnt2 = new Container(BoxLayout.y());
            Container cnt3 = new Container(BoxLayout.x());
            Button tfdelete = new Button(FontImage.MATERIAL_DELETE);
            Button tfShowSingle = new Button("Détails");
            Button tfModifier = new Button("Modifier");
          
             cnt3.addAll(tfShowSingle, tfModifier, tfdelete);
            cnt2.addAll(tfname, tfprice, cnt3);
            

            String urll = "http://localhost/pi/web/public/uploads/" + p.getPhoto_P();
            EncodedImage enc = EncodedImage.createFromImage(theme.getImage("loading.png").scaled(250, 250), false);

            URLImage urlimg = URLImage.createToStorage(enc, p.getNom_P(), urll);
            ImageViewer image = new ImageViewer(urlimg);
            Image im = image.getImage();

            cnt1.add(im);
            cnt1.add(cnt2);
            add(cnt1);
            tfdelete.addActionListener((e) -> {
                if (Dialog.show("Alert", "Voulez vous supprimer  " + p.getNom_P() + " !!", "OK", "Cancel")) {
                    if (ServiceProduit.getInstance().deleteProduit(p)) {
                        ToastBar.showMessage("Le produit a été supprimé", FontImage.MATERIAL_WARNING);
                        new ListProductForm(previous).show();
                    } else {
                        new ListProductForm(previous).show();
                    }
                }

            });
            tfShowSingle.addActionListener((e) -> {

                ServiceProduit.getInstance().getProduitSingle(p);

                new ShowSingleForm(p, current).show();

            });
  try {
            tfModifier.addActionListener((e1) -> {
              
               
                new UpdateProductForm(p, current).show();
                   
               
            });            
            
          
            
            
            
            
            
            
             } catch (Exception e) {System.out.println(e.getMessage());
                }
  
  
  
  
//    tfPanier.addActionListener((e2) -> {
//            
//
//
//
//             try {
//
//            db = Database.openOrCreate("VeloDB");
//
//            db.execute("create table if not exists Panier(id INTEGER PRIMARY KEY AUTOINCREMENT,Prix Float,NomProd TEXT,idUser INTEGER);");
//            System.out.println("DB crée");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        
//          
//         String querry = "insert into Panier(id,Prix,NomProd,idUser) values(null,'" + p.getPrix_P()+ "','"
//                    + p.getNom_P() + "','" + LoginForm.idus + "')";
//            try {
//                db.execute(querry);
//                db.close();
//                System.out.println("addeed");
//              ToastBar.showMessage("Le produit a été ajouté à votre panier ", FontImage.MATERIAL_INFO);
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//
//            }  
//            
//            
//            });
        }else{



    
    
    
       Label tfname = new Label();
            tfname.setText("Nom : " + p.getNom_P());
            Label tfprice = new Label();
            tfprice.setText("Prix : " +(int)p.getPrix_P() + " DT");
            Container cnt1 = new Container(BoxLayout.x());
            Container cnt2 = new Container(BoxLayout.y());
            Container cnt3 = new Container(BoxLayout.x());
        
            Button tfShowSingle = new Button("Détails");
        
             Button tfPanier = new Button(FontImage.MATERIAL_ADD_SHOPPING_CART);
             cnt3.addAll(tfShowSingle,tfPanier);
            cnt2.addAll(tfname, tfprice, cnt3);
            

            String urll = "http://localhost/pi/web/public/uploads/" + p.getPhoto_P();
            EncodedImage enc = EncodedImage.createFromImage(theme.getImage("loading.png").scaled(250, 250), false);

            URLImage urlimg = URLImage.createToStorage(enc, p.getNom_P(), urll);
            ImageViewer image = new ImageViewer(urlimg);
            Image im = image.getImage();

            cnt1.add(im);
            cnt1.add(cnt2);
            add(cnt1);
       
            tfShowSingle.addActionListener((e) -> {

                ServiceProduit.getInstance().getProduitSingle(p);

                new ShowSingleForm(p, current).show();

            });

  
  
  
  
        tfPanier.addActionListener((e2) -> {
            



             try {

            db = Database.openOrCreate("VeloDB");

            db.execute("create table if not exists Panier(id INTEGER UNIQUE,Prix Float,NomProd TEXT,idUser INTEGER);");
            System.out.println("DB crée");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
          
         String querry = "insert into Panier(id,Prix,NomProd,idUser) values("+p.getId_P()+",'" + p.getPrix_P()+ "','"
                    + p.getNom_P() + "','" + LoginForm.idus + "')";
            try {
                db.execute(querry);
                db.close();
                System.out.println("addeed");
              ToastBar.showMessage("Le produit a été ajouté à votre panier ", FontImage.MATERIAL_INFO);
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                ToastBar.showMessage("Produit déja ajouté !!  ", FontImage.MATERIAL_WARNING);

            }  
            
            
            });
    
    
    
    
    
    
}

        }

   
    
     setupSideMenu(theme);   
    }

    @Override
    protected void showOtherForm(Resources res) {
          }

}
