/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.gui;

import com.codename1.capture.Capture;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.codename1.uikit.materialscreens.StatsForm;
import com.velo.entities.Annonce;
import com.velo.services.AnnonceService;
import com.velo.util.Vars;
import java.io.IOException;
import java.util.Date;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import org.apache.commons.text.RandomStringGenerator;

/**
 *
 * @author Aloui Omar
 */
public class ModifierAnnonce extends SideMenuBaseForm{

    public ModifierAnnonce(Resources res,Annonce a) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("updatee.jpg");        
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
        settingsButton.addActionListener(e -> new AfficherAnnonce(res,a).show());
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label("  Modifier ", "WelcomeBlue"),
                                new Label("Annonce", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);
        

       //#####begin
        Label lTitre=new Label("Titre");
        lTitre.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        TextField tTitre=new TextField(a.getTitre(),"Titre");
        tTitre.getAllStyles().setFgColor(ColorUtil.BLACK);
        tTitre.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        Label lType=new Label("Type");
        lType.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        //TextField tType=new TextField("","Type");
        PickerComponent tType = PickerComponent.createStrings("Vente", "Location").label("Type");
        tType.getPicker().setSelectedString(a.getType());
        
        Label lCategorie=new Label("Catégorie");
        lCategorie.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        PickerComponent tCategorie = PickerComponent.createStrings("Vélo","Pièce de rechange","Accessoire").label("Catégorie");
        tCategorie.getPicker().setSelectedString(a.getCategorie());
        Label lGouvernorat=new Label("Gouvernorat");
        lGouvernorat.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        PickerComponent tGouvernorat = PickerComponent.createStrings("Ariana","Béja","Ben Arous","Bizerte","Gabès","Gafsa","Jendouba","Kairouan","Kasserine","Kébili","Le Kef","Mahdia","La Manouba","Médenine","Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse","Tataouine","Tozeur","Tunis","Zaghouan").label("Gouvernorat");
        tGouvernorat.getPicker().setSelectedString(a.getGouvernorat());
        Label lPrix=new Label("Prix");
        lPrix.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        TextField tPrix=new TextField(String.valueOf((int) a.getPrix()),"Prix");
        tPrix.getAllStyles().setFgColor(ColorUtil.BLACK);
        tPrix.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        Label lDescriprion=new Label("Descriprion");
        lDescriprion.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        TextField tDescriprion=new TextField(a.getDescription(),"Descriprion");
        tDescriprion.setSingleLineTextArea(false);
        tDescriprion.setRows(3);
        tDescriprion.getAllStyles().setFgColor(ColorUtil.BLACK);
        tDescriprion.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        Label lPhoto=new Label("Photo");
        lPhoto.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        Button upload = new Button("upload");
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    //t7ot l url ta3 l fichier php
                    cr.setUrl("http://localhost/pi/web/uploads/annonce/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    try{
                    cr.addData("file", filepath, mime);
                    }catch(java.lang.NullPointerException ex){
                        System.out.println("no file selected");
                    }
//                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    RandomStringGenerator randomStringGenerator =
            new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .filteredBy(DIGITS)
                .build();
            String out=randomStringGenerator.generate(16).toLowerCase();
                    cr.setFilename("file", out + ".jpeg");//any unique name you want
//filenameinserver héyal variable l jdida ta3 l l'img
                    fileNameInServer += out + ".jpeg";
                    System.err.println("path2 =" + fileNameInServer);
                    //ta3mel l set mta3 l 'image
//                    Vars.current_deal = new Deal(1);
//                    Image im = new Image(1, out, "png");
                    System.out.println("filename"+fileNameInServer);
                    a.setPhoto(fileNameInServer);
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        Label lTypevelo=new Label("Type Vélo");
        lTypevelo.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        PickerComponent tTypevelo = PickerComponent.createStrings("Tout chemin","Hollandais","Tout terrain","Enfant","Course","Pliant","Couché","Remorques").label("Type vélo");
        Label lCouleur=new Label("Couleur");
        lCouleur.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        TextField tCouleur=new TextField("","Couleur");
        tCouleur.getAllStyles().setFgColor(ColorUtil.BLACK);
        tCouleur.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        if(tCategorie.getPicker().getSelectedString().equals("Vélo")){
            tCouleur.setText(a.getCouleur());
            tTypevelo.getPicker().setSelectedString(a.getTypevelo());
        }

        tCategorie.getPicker().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String selectedValue = tCategorie.getPicker().getSelectedString();
                if (selectedValue.equals("Vélo")) {
                    //Cancel pressed
                    tCouleur.setEditable(true);
                    tCouleur.setEnabled(true);
                    tTypevelo.setEnabled(true);
                } else if (selectedValue.equals("Pièce de rechange") || selectedValue.equals("Accessoire")) {
                    // Selected same value
                    tCouleur.setText("");
//                    tTypevelo.setText("");
                    tCouleur.setEnabled(false);
                    tCouleur.setEditable(false);
                    tTypevelo.setEnabled(false);
                }else {
                    // Selected new value
//                    doSomething();
                }
            }
        });
        ComboBox cb=new ComboBox();
        cb.addItem("Achat");
        cb.addItem("Vente");
        cb.setVisible(false);
        Label lDate=new Label("Date");
        System.out.println("date md : "+a.getDate());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String datesmd=simpleDateFormat.format(a.getDate());
        Date datemd=null;
        try {
            datemd = simpleDateFormat.parse(datesmd);
        } catch (ParseException ex) {
            System.out.println("date exception: "+ex);
        }
        System.out.println("date md formated : "+datesmd);
        PickerComponent tDate = PickerComponent.createDate(datemd).label("Date");
        Button bModifier=new Button("Modifier annonce");
        Container cont=new Container(BoxLayout.y());
        cont.addAll(lTitre,tTitre,lCategorie,tCategorie,lGouvernorat,tGouvernorat,lType,tType,lDate,tDate,lPrix,tPrix,lDescriprion,tDescriprion,lPhoto,upload,lTypevelo,tTypevelo,lCouleur,tCouleur,bModifier,cb);
        add(BorderLayout.CENTER,cont);
        bModifier.addActionListener(e1->{
            
                System.out.println("Picker type : "+tType.getPicker().getSelectedString());
                System.out.println("Combobox :"+cb.getSelectedItem().toString());
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date5=new Date();
                date5=(Date) tDate.getPicker().getValue();
                String dd=simpleDateFormat.format(date5);
                System.out.println(dd);
                AnnonceService as=new AnnonceService();
                
                a.setTitre(tTitre.getText());
                a.setCategorie(tCategorie.getPicker().getSelectedString());
                a.setGouvernorat(tGouvernorat.getPicker().getSelectedString());
                a.setType(tType.getPicker().getSelectedString());
                a.setDate(date5);
                a.setPrix((int) Integer.parseInt(tPrix.getText()));
                a.setDescription(tDescriprion.getText());
                if(tCategorie.getPicker().getSelectedString().equals("Vélo")){
                    a.setTypevelo(tTypevelo.getPicker().getSelectedString());
                    a.setCouleur(tCouleur.getText());
                }else{
                    a.setTypevelo(null);
                    a.setCouleur(null);
                }
//                a.setPhoto("9860939036c45b557702b55617fd103f.jpeg");
                a.setIdu(78);
                
                System.out.println(a);
                if(verifierChamps(a, tPrix.getText())){
                as.ModifierAnnonce(a);
                System.out.println("Annonce modifiée avec succès");
                Vars.current_choice=1;
                AfficherListeAnnonces listeAnnonces=new AfficherListeAnnonces(res);
//                 Form listeAnnoncesForm = listeAnnonces.getListeAnnoncesForm();
                 listeAnnonces.show();
                }
            
        });
       //#####end

        
        
        
        
        
        
        
        
        setupSideMenu(res);
    }

    public boolean verifierChamps(Annonce a,String prix){
        int p;
         if(a.getTitre().equals("")){
                Dialog.show("Error", "Veuillez remplir le champ par un titre","OK",null);
                return false;
            }
         if(prix.equals("")){
                Dialog.show("Error", "Veuillez écrire un prix","OK",null);
                return false;
            }
         try
        {
          p=Integer.parseInt(prix);
        }
        catch(NumberFormatException ex)
        {
            Dialog.show("Error", "Le prix doit contenir que des caractères numériques!","OK",null);
            return false;
        }
         if(p <= 0 ){
                Dialog.show("Error", "Veuillez écrire un prix supérieur à 0","OK",null);
                return false;
            }
            
         if(a.getDescription().equals("")){
                Dialog.show("Error", "Veuillez écrire une description","OK",null);
                return false;
            }
         
         if(a.getCategorie().equals("Vélo")){
             if(a.getCouleur().equals("")){
                Dialog.show("Error", "Veuillez choisir une couleur","OK",null);
                return false;
                                                    }
                }
         
        return true;
    }
    
    /*public ModifierAnnonce(Resources res){
        modifierAnnonceForm=new Form("Ajouter Annocne",BoxLayout.y());
        modifierAnnonceForm.getToolbar().addCommandToRightBar("Retour", null,e -> new AfficherAnnonce(res,a).getAffichertAnnonceForm().show());
//        modifierAnnonceForm.getToolbar().addCommandToSideMenu("Ajouter annonce", null, e->{modifierAnnonceForm.show();});
//        modifierAnnonceForm.getToolbar().addCommandToSideMenu("Liste des annonces", null, new ActionListener() {
//
//             public void actionPerformed(ActionEvent e) {
//                 AfficherListeAnnonces listeAnnonces=new AfficherListeAnnonces();
//                 Form listeAnnoncesForm = listeAnnonces.getListeAnnoncesForm();
////                 listeAnnoncesForm.repaint();
////                 listeAnnoncesForm.refreshTheme();
////                 listeAnnoncesForm.removeAll();
//                 listeAnnoncesForm.show();
//             }
//         });
        Label lTitre=new Label("Titre");
        lTitre.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        TextField tTitre=new TextField(a.getTitre(),"Titre");
        tTitre.getAllStyles().setFgColor(ColorUtil.BLACK);
        tTitre.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        Label lType=new Label("Type");
        lType.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        //TextField tType=new TextField("","Type");
        PickerComponent tType = PickerComponent.createStrings("Vente", "Location").label("Type");
        tType.getPicker().setSelectedString(a.getType());
        
        Label lCategorie=new Label("Catégorie");
        lCategorie.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        PickerComponent tCategorie = PickerComponent.createStrings("Vélo","Pièce de rechange","Accessoire").label("Catégorie");
        tCategorie.getPicker().setSelectedString(a.getCategorie());
        Label lGouvernorat=new Label("Gouvernorat");
        lGouvernorat.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        PickerComponent tGouvernorat = PickerComponent.createStrings("Ariana","Béja","Ben Arous","Bizerte","Gabès","Gafsa","Jendouba","Kairouan","Kasserine","Kébili","Le Kef","Mahdia","La Manouba","Médenine","Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse","Tataouine","Tozeur","Tunis","Zaghouan").label("Gouvernorat");
        tGouvernorat.getPicker().setSelectedString(a.getGouvernorat());
        Label lPrix=new Label("Prix");
        lPrix.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        TextField tPrix=new TextField(String.valueOf((int) a.getPrix()),"Prix");
        tPrix.getAllStyles().setFgColor(ColorUtil.BLACK);
        tPrix.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        Label lDescriprion=new Label("Descriprion");
        lDescriprion.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        TextField tDescriprion=new TextField(a.getDescription(),"Descriprion");
        tDescriprion.setSingleLineTextArea(false);
        tDescriprion.setRows(3);
        tDescriprion.getAllStyles().setFgColor(ColorUtil.BLACK);
        tDescriprion.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        Label lPhoto=new Label("Photo");
        lPhoto.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        Button upload = new Button("upload");
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    //t7ot l url ta3 l fichier php
                    cr.setUrl("http://localhost/pi/web/uploads/annonce/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    try{
                    cr.addData("file", filepath, mime);
                    }catch(java.lang.NullPointerException ex){
                        System.out.println("no file selected");
                    }
//                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    RandomStringGenerator randomStringGenerator =
            new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .filteredBy(DIGITS)
                .build();
            String out=randomStringGenerator.generate(16).toLowerCase();
                    cr.setFilename("file", out + ".jpeg");//any unique name you want
//filenameinserver héyal variable l jdida ta3 l l'img
                    fileNameInServer += out + ".jpeg";
                    System.err.println("path2 =" + fileNameInServer);
                    //ta3mel l set mta3 l 'image
//                    Vars.current_deal = new Deal(1);
//                    Image im = new Image(1, out, "png");
                    System.out.println("filename"+fileNameInServer);
                    a.setPhoto(fileNameInServer);
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        Label lTypevelo=new Label("Type Vélo");
        lTypevelo.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        PickerComponent tTypevelo = PickerComponent.createStrings("Tout chemin","Hollandais","Tout terrain","Enfant","Course","Pliant","Couché","Remorques").label("Type vélo");
        Label lCouleur=new Label("Couleur");
        lCouleur.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        TextField tCouleur=new TextField("","Couleur");
        tCouleur.getAllStyles().setFgColor(ColorUtil.BLACK);
        tCouleur.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        if(tCategorie.getPicker().getSelectedString().equals("Vélo")){
            tCouleur.setText(a.getCouleur());
            tTypevelo.getPicker().setSelectedString(a.getTypevelo());
        }

        tCategorie.getPicker().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String selectedValue = tCategorie.getPicker().getSelectedString();
                if (selectedValue.equals("Vélo")) {
                    //Cancel pressed
                    tCouleur.setEditable(true);
                    tCouleur.setEnabled(true);
                    tTypevelo.setEnabled(true);
                } else if (selectedValue.equals("Pièce de rechange") || selectedValue.equals("Accessoire")) {
                    // Selected same value
                    tCouleur.setText("");
//                    tTypevelo.setText("");
                    tCouleur.setEnabled(false);
                    tCouleur.setEditable(false);
                    tTypevelo.setEnabled(false);
                }else {
                    // Selected new value
//                    doSomething();
                }
            }
        });
        ComboBox cb=new ComboBox();
        cb.addItem("Achat");
        cb.addItem("Vente");
        cb.setVisible(false);
        Label lDate=new Label("Date");
        System.out.println("date md : "+a.getDate());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String datesmd=simpleDateFormat.format(a.getDate());
        Date datemd=null;
        try {
            datemd = simpleDateFormat.parse(datesmd);
        } catch (ParseException ex) {
            System.out.println("date exception: "+ex);
        }
        System.out.println("date md formated : "+datesmd);
        PickerComponent tDate = PickerComponent.createDate(datemd).label("Date");
        Button bModifier=new Button("Modifier annonce");
        modifierAnnonceForm.addAll(lTitre,tTitre,lCategorie,tCategorie,lGouvernorat,tGouvernorat,lType,tType,lDate,tDate,lPrix,tPrix,lDescriprion,tDescriprion,lPhoto,upload,lTypevelo,tTypevelo,lCouleur,tCouleur,bModifier,cb);
        bModifier.addActionListener(e1->{
            
                System.out.println("Picker type : "+tType.getPicker().getSelectedString());
                System.out.println("Combobox :"+cb.getSelectedItem().toString());
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date5=new Date();
                date5=(Date) tDate.getPicker().getValue();
                String dd=simpleDateFormat.format(date5);
                System.out.println(dd);
                AnnonceService as=new AnnonceService();
                
                a.setTitre(tTitre.getText());
                a.setCategorie(tCategorie.getPicker().getSelectedString());
                a.setGouvernorat(tGouvernorat.getPicker().getSelectedString());
                a.setType(tType.getPicker().getSelectedString());
                a.setDate(date5);
                a.setPrix((int) Integer.parseInt(tPrix.getText()));
                a.setDescription(tDescriprion.getText());
                if(tCategorie.getPicker().getSelectedString().equals("Vélo")){
                    a.setTypevelo(tTypevelo.getPicker().getSelectedString());
                    a.setCouleur(tCouleur.getText());
                }else{
                    a.setTypevelo(null);
                    a.setCouleur(null);
                }
//                a.setPhoto("9860939036c45b557702b55617fd103f.jpeg");
                a.setIdu(78);
                
                System.out.println(a);
                as.ModifierAnnonce(a);
                System.out.println("Annonce modifiée avec succès");
                AfficherListeAnnonces listeAnnonces=new AfficherListeAnnonces(res);
//                 Form listeAnnoncesForm = listeAnnonces.getListeAnnoncesForm();
                 listeAnnonces.show();
            
        });
    }

    public Form getModifierAnnonceForm() {
        return modifierAnnonceForm;
    }

    public void setModifierAnnonceForm(Form modifierAnnonceForm) {
        this.modifierAnnonceForm = modifierAnnonceForm;
    }*/
    
    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
    
}
