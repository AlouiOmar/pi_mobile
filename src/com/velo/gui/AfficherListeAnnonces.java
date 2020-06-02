/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.db.Cursor;
import com.codename1.db.Row;
import com.codename1.l10n.Format;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.velo.gui.ProfileForm;
import com.velo.gui.SideMenuBaseForm;
import com.velo.entities.Annonce;
import com.velo.services.AnnonceService;
import com.velo.util.Vars;
import java.io.IOException;
import java.util.Date;
import javafx.scene.paint.Color;

/**
 *
 * @author Aloui Omar
 */

public class AfficherListeAnnonces extends SideMenuBaseForm {
private EncodedImage palceHolder;

    public AfficherListeAnnonces(Resources res) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("velo.jpg");        
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
                                new Label("  Liste des ", "WelcomeBlue"),
                                new Label("Annonces", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);
        Container con4=new Container(BoxLayout.x());
        Container con5=new Container(new BorderLayout());
        
        PickerComponent signallPicker = PickerComponent.createStrings("Vélo","Pièce de rechange","Accessoire","Prix croissant","Prix décroissant","Plus récent").label("Critère");        
        InteractionDialog dlg1 = new InteractionDialog("Choisissez le critère de tri");
        Dimension pre = dlg1.getContentPane().getPreferredSize();
        dlg1.setLayout(new BorderLayout());
        dlg1.add(BorderLayout.CENTER, signallPicker);
        Button ok = new Button("Ok");
        Button close = new Button("Annuler");
        close.addActionListener((ee) -> dlg1.dispose());
        Container cont4=new Container(BoxLayout.x());
        Container cont5=new Container(BoxLayout.y());
//        cont5.add(BoxLayout.encloseXCenter(cont4));
        cont4.addAll(close,ok);
        cont5.add(BoxLayout.encloseXCenter(cont4));
        dlg1.addComponent(BorderLayout.SOUTH, cont5);
        ok.addActionListener(e->{
            System.out.println("Critère: "+signallPicker.getPicker().getSelectedString());
            if(signallPicker.getPicker().getSelectedString().equals("Vélo") || signallPicker.getPicker().getSelectedString().equals("Pièce de rechange") || signallPicker.getPicker().getSelectedString().equals("Accessoire"))
                {
                    Vars.current_type=signallPicker.getPicker().getSelectedString();
                    Vars.current_choice = 5;
                    new AfficherListeAnnonces(res).show();
                }
            if(signallPicker.getPicker().getSelectedString().equals("Prix croissant")  || signallPicker.getPicker().getSelectedString().equals("Prix décroissant"))
                {
                   if(signallPicker.getPicker().getSelectedString().equals("Prix croissant")) {
                       Vars.current_type="asc";
                   }else{
                       Vars.current_type="desc"; 
                   }
                    Vars.current_choice = 6;
                    new AfficherListeAnnonces(res).show();
                }
            if(signallPicker.getPicker().getSelectedString().equals("Plus récent")){
                Vars.current_choice = 7;
                new AfficherListeAnnonces(res).show();
            }
        });
        
        
        Button bTri=new Button("Trier par");
        bTri.getAllStyles().setBgColor(ColorUtil.BLACK);
        bTri.getAllStyles().setFgColor(ColorUtil.BLACK);
        FontImage.setMaterialIcon(bTri, FontImage.MATERIAL_SORT,5);
        bTri.addActionListener(e -> {
        dlg1.show(200, 200, 100, 100);
        });
        con5.add(BorderLayout.EAST, bTri);
        con5.add(BorderLayout.WEST, con4);
        TextField tRech=new TextField("","Recherche");
        tRech.getAllStyles().setFgColor(ColorUtil.BLACK);
        tRech.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        Button rechButton = new Button("");
        rechButton.getAllStyles().setBgColor(ColorUtil.BLACK);
        rechButton.getAllStyles().setFgColor(ColorUtil.BLACK);
        int color = 0xffffff;
        Font materialFontBack = FontImage.getMaterialDesignFont();           
        FontImage imageBack = FontImage.createFixed("\uE8B6", materialFontBack, ColorUtil.BLACK, 30, 30);
        

        rechButton.setUIID("Title");
//        FontImage.setMaterialIcon(rechButton, FontImage.MATERIAL_SEARCH);
        rechButton.setIcon(imageBack);
        rechButton.addActionListener(e -> {
                                                if(!tRech.getText().equals("")){
                                                System.out.println("recherche: "+tRech.getText());
                                                Vars.current_search=tRech.getText();
                                                Vars.current_choice=3;
                                                new AfficherListeAnnonces(res).show();
                                                }
                                     });
        con4.add(BoxLayout.encloseXRight(tRech,rechButton));
        
        add(BorderLayout.NORTH, con5);
        

       //#####begin
//         AnnonceService as = new AnnonceService();
        if (!AnnonceService.getInstance().getAnnonces().isEmpty()) {
            Container con3=new Container(BoxLayout.y());
         for (Annonce a : AnnonceService.getInstance().getAnnonces()) {
                
                int id=a.getIda();
                String titre=a.getTitre();
                String type=a.getType();
                String categorie=a.getCategorie();
                String date=a.getDatep().toString();
                String image=a.getPhoto();
                String prix=String.valueOf(a.getPrix());
                Label lTitre=new Label("Titre: "+titre);
                Label lCategorie=new Label("Catégorie: "+categorie);
                Label lType=new Label("Type: "+type);
                Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String ress = dateFormat.format(a.getDatep());
//                System.out.println("Date = " + ress);
                Label lDate=new Label("Publiée le : "+ress);
                Label lPrix=new Label("Prix: "+prix);
                Button bAfficher=new Button("Afficher");
                bAfficher.addActionListener(e->{
//                    Annonce a1=new Annonce();
                    Vars.current_annonce=a;
                    System.out.println("testtt");
                  AfficherAnnonce afficherAnnonce=new AfficherAnnonce(res,a);
//                  Form afficherAnnonceForm=afficherAnnonce.getAffichertAnnonceForm();
                  afficherAnnonce.show();
//                    Form fa=afficher(a);
//                    fa.show();
                });
                
                System.out.println("Annonce= id: "+id+" Titre: "+titre+" Type: "+type+" Date: "+" image: "+image);
                Container con=new Container(BoxLayout.x());
                Container con1=new Container(BoxLayout.y());
                        //Image green = Image.createImage(100, 100, 0xff00ff00);

//                ImageViewer imgv=new ImageViewer(theme.getImage("a.jpg").scaled(120, 100));
                ImageViewer imgv = new ImageViewer();
//                EncodedImage palceHolder = EncodedImage.createFromImage(theme.getImage("round.png").scaled(120, 100), false);
                try {
                    palceHolder = EncodedImage.create("/giphy.gif");
                } catch (IOException ex) {

                }
                if(image!=null){
                URLImage urlImage = URLImage.createToStorage(palceHolder, image, "http://localhost/pi/web/uploads/annonce/"+image);
                imgv.setImage(urlImage);

                }
                if(image==null){
//                    imgv=new ImageViewer(res.getImage("noimagefound.jpg").scaled(120, 100));
                }
                con1.addAll(lTitre,lCategorie,lType,lDate,lPrix);
                con.addAll(imgv,con1);
                
                con3.addAll(con,bAfficher);
            }
         con3.setScrollableY(true);
            System.out.println("sroll : "+con3.isScrollableY());
                         add(BorderLayout.CENTER,con3);

        }
        else {
            Container cont=new Container(BoxLayout.y());
            cont.add(BoxLayout.encloseXCenter(new Label("Aucune annonce à afficher")));
            add(BorderLayout.CENTER,cont);

        } 
       //#####end

        
        
        
        
        
        
        
        
        setupSideMenu(res);
    }

//    public AfficherListeAnnonces(Resources res) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    
    
    
 @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
   
}


