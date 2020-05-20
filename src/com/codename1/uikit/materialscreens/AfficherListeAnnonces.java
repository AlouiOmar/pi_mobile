///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.codename1.uikit.materialscreens;
//
//import com.codename1.charts.ChartComponent;
//import com.codename1.charts.models.XYMultipleSeriesDataset;
//import com.codename1.charts.models.XYSeries;
//import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
//import com.codename1.charts.renderers.XYSeriesRenderer;
//import com.codename1.charts.views.CubicLineChart;
//import com.velo.gui.*;
//import com.codename1.components.ImageViewer;
//import com.codename1.db.Cursor;
//import com.codename1.db.Row;
//import com.codename1.ui.Button;
//import com.codename1.ui.Container;
//import com.codename1.ui.Display;
//import com.codename1.ui.EncodedImage;
//import com.codename1.ui.FontImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.Graphics;
//import com.codename1.ui.Image;
//import com.codename1.ui.Label;
//import com.codename1.ui.Toolbar;
//import com.codename1.ui.URLImage;
//import com.codename1.ui.events.ActionEvent;
//import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.layouts.BorderLayout;
//import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.layouts.FlowLayout;
//import com.codename1.ui.plaf.UIManager;
//import com.codename1.ui.util.Resources;
//import com.codename1.uikit.materialscreens.SideMenuBaseForm;
//import com.codename1.uikit.materialscreens.StatsForm;
//import com.velo.entities.Annonce;
//import com.velo.services.AnnonceService;
//import com.velo.util.Vars;
//import java.io.IOException;
//
///**
// *
// * @author Aloui Omar
// */
///*public class AfficherListeAnnonces extends SideMenuBaseForm{
//    private Form listeAnnoncesForm;
//    private Resources theme;
//    private EncodedImage palceHolder;
//
//
//    public AfficherListeAnnonces(Resources res) {
//         listeAnnoncesForm = new Form("Liste des Annonces", BoxLayout.y());
//         Button menuButton = new Button("");
//        menuButton.setUIID("Title");
//        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
//        menuButton.addActionListener(e -> getToolbar().openSideMenu());
//        
//        setupSideMenu(res);
////         listeAnnoncesForm.getToolbar().addCommandToSideMenu("Ajouter annonce", null, e->{
////             AjouterAnnonce listeAnnonces=new AjouterAnnonce();
////                 Form ajouterAnnonceForm = listeAnnonces.getAjoutAnnonceForm();
////                 ajouterAnnonceForm.show();});
////         listeAnnoncesForm.getToolbar().addCommandToSideMenu("Stat annonce", null, e->{
////             StatistiqueAnnonce listeAnnonces=new StatistiqueAnnonce();
////                 Form ajouterAnnonceForm = listeAnnonces.createPieChartForm();
////                 ajouterAnnonceForm.show();});
////         listeAnnoncesForm.getToolbar().addCommandToSideMenu("Liste des annonces", null, new ActionListener() {
////
////             public void actionPerformed(ActionEvent e) {
////                 listeAnnoncesForm.repaint();
////                 listeAnnoncesForm.refreshTheme();
////                 listeAnnoncesForm.removeAll();
////                 
////                 AfficherListeAnnonces listeAnnonces=new AfficherListeAnnonces();
////                 Form listeAnnoncesForm = listeAnnonces.getListeAnnoncesForm();
////                 listeAnnoncesForm.show();
////             }
////         });
////        
////        listeAnnoncesForm.getToolbar().addCommandToOverflowMenu("Se déconnecter", null,e1->{});
//        
//        AnnonceService as = new AnnonceService();
//        if (!as.getAnnonces().isEmpty()) {
//         for (Annonce a : as.getAnnonces()) {
//                
//                int id=a.getIda();
//                String titre=a.getTitre();
//                String type=a.getType();
//                String date=a.getDate().toString();
//                String image=a.getPhoto();
//                Label lTitre=new Label("Titre: "+titre);
//                Label lType=new Label("Type: "+type);
//                Label lDate=new Label("Date: "+date);
//                Button bAfficher=new Button("Afficher");
//                bAfficher.addActionListener(e->{
////                    Annonce a1=new Annonce();
//                    Vars.current_annonce=a;
//                  AfficherAnnonce afficherAnnonce=new AfficherAnnonce(a);
//                  Form afficherAnnonceForm=afficherAnnonce.getAffichertAnnonceForm();
//                  afficherAnnonceForm.show();
////                    Form fa=afficher(a);
////                    fa.show();
//                });
//                
//                System.out.println("Annonce= id: "+id+" Titre: "+titre+" Type: "+type+" Date: "+" image: "+image);
//                Container con=new Container(BoxLayout.x());
//                Container con1=new Container(BoxLayout.y());
//                        //Image green = Image.createImage(100, 100, 0xff00ff00);
//
////                ImageViewer imgv=new ImageViewer(theme.getImage("a.jpg").scaled(120, 100));
//                ImageViewer imgv = new ImageViewer();
////                EncodedImage palceHolder = EncodedImage.createFromImage(theme.getImage("round.png").scaled(120, 100), false);
//                try {
//                    palceHolder = EncodedImage.create("/giphy.gif");
//                } catch (IOException ex) {
//
//                }
//                if(image!=null){
//                URLImage urlImage = URLImage.createToStorage(palceHolder, image, "http://localhost/pi/web/uploads/annonce/"+image);
//                imgv.setImage(urlImage);
//
//                }
//                if(image==null){
//                    imgv=new ImageViewer(theme.getImage("noimagefound.jpg").scaled(120, 100));
//                }
//                con1.addAll(lTitre,lType,lDate);
//                con.addAll(imgv,con1);
//                listeAnnoncesForm.addAll(con,bAfficher);
//            }
//        }else {
//            listeAnnoncesForm.add(new Label("Aucun deal a afficher"));
//
//        } 
//         
//         
//    }
//
//    
//
//    @Override
//    protected void showOtherForm(Resources res) {
//        new StatsForm(res).show();
//    }
//    
//    
//    
//    
//}*/
//public class AfficherListeAnnonces extends SideMenuBaseForm {
//private EncodedImage palceHolder;
//
//    public AfficherListeAnnonces(Resources res) {
//        super(new BorderLayout());
//        Toolbar tb = getToolbar();
//        tb.setTitleCentered(false);
//        Image profilePic = res.getImage("user-picture.jpg");        
//        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
//        Graphics g = tintedImage.getGraphics();
//        g.drawImage(profilePic, 0, 0);
//        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
//        
//        tb.getUnselectedStyle().setBgImage(tintedImage);
//        
//        Button menuButton = new Button("");
//        menuButton.setUIID("Title");
//        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
//        menuButton.addActionListener(e -> getToolbar().openSideMenu());
//
//        Button settingsButton = new Button("");
//        settingsButton.setUIID("Title");
//        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
//        
//        Label space = new Label("", "TitlePictureSpace");
//        space.setShowEvenIfBlank(true);
//        Container titleComponent = 
//                BorderLayout.north(
//                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
//                ).
//                add(BorderLayout.CENTER, space).
//                add(BorderLayout.SOUTH, 
//                        FlowLayout.encloseIn(
//                                new Label("  Jennifer ", "WelcomeBlue"),
//                                new Label("Wilson", "WelcomeWhite")
//                        ));
//        titleComponent.setUIID("BottomPaddingContainer");
//        tb.setTitleComponent(titleComponent);
//        
//        Label separator = new Label("", "BlueSeparatorLine");
//        separator.setShowEvenIfBlank(true);
//        add(BorderLayout.NORTH, separator);
//        
//
//       //#####begin
//         AnnonceService as = new AnnonceService();
//        if (!as.getAnnonces().isEmpty()) {
//            Container con3=new Container(BoxLayout.y());
//         for (Annonce a : as.getAnnonces()) {
//                
//                int id=a.getIda();
//                String titre=a.getTitre();
//                String type=a.getType();
//                String date=a.getDate().toString();
//                String image=a.getPhoto();
//                Label lTitre=new Label("Titre: "+titre);
//                Label lType=new Label("Type: "+type);
//                Label lDate=new Label("Date: "+date);
//                Button bAfficher=new Button("Afficher");
//                bAfficher.addActionListener(e->{
////                    Annonce a1=new Annonce();
//                    Vars.current_annonce=a;
//                  AfficherAnnonce afficherAnnonce=new AfficherAnnonce(a);
//                  Form afficherAnnonceForm=afficherAnnonce.getAffichertAnnonceForm();
//                  afficherAnnonceForm.show();
////                    Form fa=afficher(a);
////                    fa.show();
//                });
//                
//                System.out.println("Annonce= id: "+id+" Titre: "+titre+" Type: "+type+" Date: "+" image: "+image);
//                Container con=new Container(BoxLayout.x());
//                Container con1=new Container(BoxLayout.y());
//                        //Image green = Image.createImage(100, 100, 0xff00ff00);
//
////                ImageViewer imgv=new ImageViewer(theme.getImage("a.jpg").scaled(120, 100));
//                ImageViewer imgv = new ImageViewer();
////                EncodedImage palceHolder = EncodedImage.createFromImage(theme.getImage("round.png").scaled(120, 100), false);
//                try {
//                    palceHolder = EncodedImage.create("/giphy.gif");
//                } catch (IOException ex) {
//
//                }
//                if(image!=null){
//                URLImage urlImage = URLImage.createToStorage(palceHolder, image, "http://localhost/pi/web/uploads/annonce/"+image);
//                imgv.setImage(urlImage);
//
//                }
//                if(image==null){
////                    imgv=new ImageViewer(res.getImage("noimagefound.jpg").scaled(120, 100));
//                }
//                con1.addAll(lTitre,lType,lDate);
//                con.addAll(imgv,con1);
//                
//                con3.addAll(con,bAfficher);
//            }
//         con3.setScrollableY(true);
//            System.out.println("sroll : "+con3.isScrollableY());
//                         add(BorderLayout.CENTER,con3);
//
//        }
////        else {
////            add(new Label("Aucune annonce à afficher"));
////
////        } 
//       //#####end
//
//        
//        
//        
//        
//        
//        
//        
//        
//        setupSideMenu(res);
//    }
//
//    
//    
//    
// @Override
//    protected void showOtherForm(Resources res) {
//        new StatsForm(res).show();
//    }
//   
//}