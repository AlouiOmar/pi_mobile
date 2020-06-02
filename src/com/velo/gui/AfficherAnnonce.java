/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.Format;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.velo.gui.ProfileForm;
import com.velo.gui.SideMenuBaseForm;
import com.velo.entities.Annonce;
import com.velo.entities.Stat;
import com.velo.services.AnnonceService;
import com.velo.gui.ModifierAnnonce;
import com.velo.util.Vars;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Aloui Omar
 */
public class AfficherAnnonce extends SideMenuBaseForm{
    private EncodedImage palceHolder;

    public AfficherAnnonce(Resources res,Annonce a) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic=null;
        if(a.getCategorie().equals("Vélo")){
            profilePic = res.getImage("bike.jpg"); 
        }else if(a.getCategorie().equals("Accessoire")){
            profilePic = res.getImage("accessoire.jpg");        
        }else{
            profilePic = res.getImage("pieces.jpg");        
        }
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("Retour");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_ARROW_BACK_IOS);
        settingsButton.addActionListener(e -> new AfficherListeAnnonces(res).show());
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label("  Détails de ", "WelcomeBlue"),
                                new Label("L'annonce", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);
        

       //#####begin
        String image=a.getPhoto();
        ImageViewer imgv = new ImageViewer();
        try {
                    palceHolder = EncodedImage.create("/giphy.gif");
                } catch (IOException ex) {

                }
                if(image!=null){
                URLImage urlImage = URLImage.createToStorage(palceHolder, image, "http://localhost/pi/web/uploads/annonce/"+image);
                imgv.setImage(urlImage);

                }
        Label lTitre=new Label("Titre: "+a.getTitre());
        Label lGouvernorat=new Label("Gouvernorat: "+a.getGouvernorat());
        Label lCategorie=new Label("Catégorie: "+a.getCategorie());
        Label lType=new Label("Type: "+a.getType());
        Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String ress = dateFormat.format(a.getDatep());
        String ress1 = dateFormat.format(a.getDate());
        Label lDate=new Label("Date: "+ress1);
        Label lDatep=new Label("Publiée: "+ress);
        Label lPrix=new Label("Prix: "+a.getPrix());
        SpanLabel lDescription=new SpanLabel("Description: "+a.getDescription());
        Button bModif=new Button("Modifier");
        Button bSupp=new Button("Supprimer");
        Container cb=new Container(BoxLayout.x());
        cb.addAll(bModif,bSupp);
        cb.setVisible(false);
        if(Vars.current_user.getRoles().contains("ADMIN") || Vars.current_user.getId() == a.getIdu()){
            cb.setVisible(true);
        }
        bModif.addActionListener(e->{
            ModifierAnnonce modifierAnnonce=new ModifierAnnonce(res,a);
//            Form modifierAnnonceForm=modifierAnnonce.getModifierAnnonceForm();
            modifierAnnonce.show();
            
//        Form mod=modifier(a);
//        mod.show();
        });
        bSupp.addActionListener(e1->{
            
                if(Dialog.show("voulez vous supprimer cette annonce", "", "Supprimer", "Annuler")){
//                db.execute("delete from annonce where id="+a.getId()+"");
//                    AnnonceService as=new AnnonceService();
                AnnonceService.getInstance().SupprimerAnnonce(a.getIda());
                System.out.println("Annonce supprimée avec succès !");
                AfficherListeAnnonces listeAnnonces=new AfficherListeAnnonces(res);
//                 Form listeAnnoncesForm = listeAnnonces.getListeAnnoncesForm();
                 listeAnnonces.show();
                }
            
                 
        });
                PickerComponent signallPicker = PickerComponent.createStrings("Contenu indésirable","Harcèlement","Discours haineux","Nudité","Violence","Autre").label("Cause");

        
        InteractionDialog dlg = new InteractionDialog("Signaler l'annonce aux administrateurs");
        InteractionDialog dlg1 = new InteractionDialog("Annonce Signalée avec succés!");
        dlg.setLayout(new BorderLayout());
        dlg1.setLayout(new BorderLayout());
        Label sp=new Label("Vous allez être redirigé vers la liste des annonces.");
//            
//        Container cont3=new Container(BoxLayout.y());
//        cont3.addAll(sp,signallPicker);
        dlg.add(BorderLayout.CENTER, signallPicker);
        dlg1.add(BorderLayout.CENTER, sp);
        Dimension pre = dlg.getContentPane().getPreferredSize();
        int dim=(Display.getInstance().getDisplayWidth()) - (pre.getWidth() + pre.getWidth() / 6);
        Button close = new Button("Annuler");
        Button signaler = new Button("Signaler");
        Button ok = new Button("Ok");
        Container cont4=new Container(BoxLayout.x());
        cont4.addAll(close,signaler);
        Container cont5=new Container(BoxLayout.y());
        cont5.add(BoxLayout.encloseXCenter(cont4));
        close.addActionListener((ee) -> dlg.dispose());
        
        signaler.addActionListener((ee) -> {
            System.out.println("id annonce: "+a.getIda()+" cause: "+signallPicker.getPicker().getSelectedString());
//            AnnonceService as=new AnnonceService();
            AnnonceService.getInstance().SignalAnnonce(a.getIda(), signallPicker.getPicker().getSelectedString());
            dlg.dispose();
            dlg1.show(pre.getHeight()*6, pre.getHeight()*6, pre.getWidth()/2, pre.getWidth()/2);
        });
        
        dlg.addComponent(BorderLayout.SOUTH, cont5);
        dlg1.addComponent(BorderLayout.SOUTH, ok);
        ok.addActionListener(e->{Vars.current_choice = 1;new AfficherListeAnnonces(res).show();});
        Button signalButton = new Button("Signaler", "Label"); 
        signalButton.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 0, 0));
        signalButton.getSelectedStyle().setFgColor(ColorUtil.rgb(255, 0, 0));
//        signalButton.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
//        signalButton.setUIID("Title");
        FontImage.setMaterialIcon(signalButton, FontImage.MATERIAL_FLAG,5);
        FontImage.setMaterialIcon(bSupp, FontImage.MATERIAL_DELETE,5);
        FontImage.setMaterialIcon(bModif, FontImage.MATERIAL_UPDATE,5);
        
        Container cont6=new Container(new BorderLayout());
        cont6.add(BorderLayout.WEST, lTitre);
        cont6.add(BorderLayout.EAST, signalButton);


        signalButton.addActionListener(l -> { 
            
            dlg.show(pre.getHeight()*6, pre.getHeight()*6, pre.getWidth()/2, pre.getWidth()/2);
//            Display.getInstance().invokeAndBlock(() -> { }); 
//            signalPicker.getPicker().released(); 
        });
        Container cont=new Container(BoxLayout.y());
        cont.addAll(imgv,cont6,lGouvernorat,lCategorie,lType,lDate,lDatep,lPrix,lDescription);
        if(a.getCategorie().equals("Vélo")){
            Label lTypeVelo=new Label("Type de vélo: "+a.getTypevelo());
            Label lCouleur=new Label("Couleur: "+a.getCouleur());
            cont.add(lTypeVelo);
            cont.add(lCouleur);
        }
        if(Vars.current_choice == 4){
            System.out.println(Vars.current_choice );
//            AnnonceService as=new AnnonceService();
            ArrayList<Stat> listc=AnnonceService.getInstance().getCause(a.getIda());
            Container cont8=new Container(BoxLayout.y());
            cont8.add(new Label("Cause du signalement: "));
            for(int i=0;i<listc.size();i++){
                System.out.println(listc.get(i).toString());
                cont8.add(new Label("Cause: "+listc.get(i).getNom()+" Nombre de signalement: "+listc.get(i).getNbr()));
            }
            cont.add(cont8);
        }
        cont.add(BoxLayout.encloseXCenter(cb));
        add(BorderLayout.CENTER,cont);

       //#####end

        
        
        
        
        
        
        
        
        setupSideMenu(res);
    }



 
     @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
}
