/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.l10n.Format;
import com.codename1.l10n.SimpleDateFormat;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
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
import com.mycompany.myapp.entities.Location;
import com.mycompany.myapp.services.ServiceLocation;
import com.mycompany.myapp.services.ServiceReservation;
import com.mycompany.myapp.utils.Statics_1;
import java.io.IOException;
import java.util.ArrayList;
import com.velo.gui.SideMenuBaseForm;

/**
 *
 * @author nahawnd
 */
public class ListLocationsForm extends SideMenuBaseForm {

    private EncodedImage palceHolder;

    int id;

    public static Form details;

    ServiceLocation l = new ServiceLocation();

    ArrayList<Location> locations = l.getAllLocations();

    private static ListLocationsForm instance;

    public static ListLocationsForm getInstance() {
        return instance;
    }

    public ListLocationsForm() {
        instance = this;
    }

    public ListLocationsForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
//        Image profilePic = res.getImage("velo.jpg");
//        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
//        Graphics g = tintedImage.getGraphics();
//        g.drawImage(profilePic, 0, 0);
//        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());

//        tb.getUnselectedStyle().setBgImage(tintedImage);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        getToolbar().addMaterialCommandToLeftSideMenu("menuButton", FontImage.MATERIAL_MENU, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });

        getToolbar().addMaterialCommandToLeftSideMenu("Profile", FontImage.MATERIAL_SHOPPING_CART, ev -> new ProfileForm(res).show());
        getToolbar().addMaterialCommandToLeftSideMenu("Louer", FontImage.MATERIAL_ADD_CIRCLE, ev -> new AddLocationForm(res).show());
        getToolbar().addMaterialCommandToLeftSideMenu("Modifier location", FontImage.MATERIAL_STORE, ev -> new UpdateLocationForm(res, l).show());
        getToolbar().addMaterialCommandToLeftSideMenu("Reserver", FontImage.MATERIAL_STORE, ev -> new AddReservationForm(res).show());

//        Button settingsButton = new Button("");
//        settingsButton.setUIID("Title");
//        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        Button backButton = new Button("Retour");
        backButton.setUIID("Title");
        FontImage.setMaterialIcon(backButton, FontImage.MATERIAL_ARROW_BACK_IOS);
        backButton.addActionListener(e -> new HomeForm_2(res).show());

        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton)
                                //                                .add(BorderLayout.EAST, settingsButton)
                                .add(BorderLayout.EAST, backButton)
                ).
                        add(BorderLayout.CENTER, space).
                        add(BorderLayout.SOUTH,
                                FlowLayout.encloseIn(
                                        new Label("  Liste des ", "WelcomeBlue"),
                                        new Label("Locations", "WelcomeWhite")
                                ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);
        Container con4 = new Container(BoxLayout.x());
        Container con5 = new Container(new BorderLayout());

        PickerComponent signallPicker = PickerComponent.createStrings("Prix haut", "Prix bas", "Plus récent").label("Critère");
        InteractionDialog dlg1 = new InteractionDialog("Filter par:");
        Dimension pre = dlg1.getContentPane().getPreferredSize();
        dlg1.setLayout(new BorderLayout());
        dlg1.add(BorderLayout.CENTER, signallPicker);
        Button ok = new Button("Ok");
        Button close = new Button("Annuler");
        close.addActionListener((ee) -> dlg1.dispose());

        Container cont4 = new Container(BoxLayout.x());
        Container cont5 = new Container(BoxLayout.y());
//        cont5.add(BoxLayout.encloseXCenter(cont4));
        cont4.addAll(close, ok);
        cont5.add(BoxLayout.encloseXCenter(cont4));
        dlg1.addComponent(BorderLayout.SOUTH, cont5);
        ok.addActionListener(e -> {
            System.out.println("Critère: " + signallPicker.getPicker().getSelectedString());

            if (signallPicker.getPicker().getSelectedString().equals("Prix croissant") || signallPicker.getPicker().getSelectedString().equals("Prix décroissant")) {
                if (signallPicker.getPicker().getSelectedString().equals("Prix croissant")) {
                    Statics_1.current_type = "asc";
                } else {
                    Statics_1.current_type = "desc";
                }
                Statics_1.current_choice = 4;
                new ListLocationsForm(res).show();
            }
            if (signallPicker.getPicker().getSelectedString().equals("Plus récent")) {
                Statics_1.current_choice = 7;
                new ListLocationsForm(res).show();
            }
        });

        Button bTri = new Button("Trier par");
        bTri.getAllStyles().setBgColor(ColorUtil.BLACK);
        bTri.getAllStyles().setFgColor(ColorUtil.BLACK);
        FontImage.setMaterialIcon(bTri, FontImage.MATERIAL_SORT, 5);
        bTri.addActionListener(e -> {
            dlg1.show(200, 200, 100, 100);
        });
//        con5.add(BorderLayout.EAST, bTri);
//        con5.add(BorderLayout.WEST, con4);
        TextField tRech = new TextField("", "Recherche");
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
            if (!tRech.getText().equals("")) {
                System.out.println("recherche: " + tRech.getText());
                Statics_1.current_search = tRech.getText();
                Statics_1.current_choice = 3;
                new ListLocationsForm(res).show();
            }
        });
//        con4.add(BoxLayout.encloseXRight(tRech, rechButton));

        add(BorderLayout.NORTH, con5);

        //begin
        if (!ServiceLocation.getInstance().getAllLocations().isEmpty()) {
            Container con3 = new Container(BoxLayout.y());

            for (Location l : ServiceLocation.getInstance().getAllLocations()) {

                int id = l.getId();
                String titre = l.getTitre();
                String lieu = l.getLieu();
                String prix = String.valueOf(l.getPrix());
//                String dateCreation = a.getDateCreation().toString();
                String image = l.getPhoto();
                String username = l.getUsername();

                Label lTitre = new Label("Titre: " + titre);
                Label lLieu = new Label("Lieu: " + lieu);
                Label lPrix = new Label("Prix: " + prix);
                Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                String ress = dateFormat.format(a.getDateCreation());
//                Label lDateCreation = new Label("Publiée le : " + ress);          
                Label lUsername = new Label("Locataire: " + username);
              
          
                
                 Button bReserver = new Button("Reserver");
                bReserver.addActionListener(e -> {
//                    ServiceReservation.getInstance().addReservation(r.getId);
                    Statics_1.current_location = l;
                    System.out.println("test");
                    AddReservationForm addReservationForm = new AddReservationForm(res);
                    addReservationForm.show();

                });

                      Button bAfficher = new Button("Afficher");
                bAfficher.addActionListener(e -> {
                    ServiceLocation.getInstance().findLocation(l.getId());
                    Statics_1.current_location = l;
                    System.out.println("test");
                    AfficherLocation afficherLocation = new AfficherLocation(res, l);
                    afficherLocation.show();

                });
                 Button bModifier = new Button("Modifier");
                bModifier.addActionListener(e -> {

                    Statics_1.current_location = l;
                    System.out.println("test");
                    UpdateLocationForm updateLocationForm = new UpdateLocationForm(res, l);
                    updateLocationForm.show();

                });
                
                 Button bSupp = new Button("Supprimer");
                 bSupp.addActionListener(e1 -> {
            if (Dialog.show("voulez vous supprimer cette location", "", "Supprimer", "Annuler")) {
                ServiceLocation.getInstance().DeleteLocation(l.getId());
                System.out.println("Publication supprimée avec succès !");
                ListLocationsForm listLocationsForm = new ListLocationsForm(res);

                listLocationsForm.show();
            }

        });
                 
                 
                 
                System.out.println("Location= id: " + id + " Titre: " + titre + " Lieu: " + lieu 
                        + " Prix: " + prix
                        + " Date: " 
                        + " image: " + image
                        + " locataire: " + username );
                Container con = new Container(BoxLayout.x());
                Container con1 = new Container(BoxLayout.y());
                //Image green = Image.createImage(100, 100, 0xff00ff00);

//                ImageViewer imgv=new ImageViewer(theme.getImage("a.jpg").scaled(120, 100));
                ImageViewer imgv = new ImageViewer();
//                EncodedImage palceHolder = EncodedImage.createFromImage(theme.getImage("round.png").scaled(120, 100), false);
                try {
                    palceHolder = EncodedImage.create("/giphy.gif");
                } catch (IOException ex) {

                }
                if (image != null) {
                    URLImage urlImage = URLImage.createToStorage(palceHolder, image, "http://localhost/PiDev_Velo.tn/web/uploads/location/" + image);
                    imgv.setImage(urlImage);

                }
                if (image == null) {
//                    imgv=new ImageViewer(res.getImage("noimagefound.jpg").scaled(120, 100));
                }
                con1.addAll(lTitre, lLieu,lPrix, lUsername );
                con.addAll(imgv, con1);

                con3.addAll(con, bReserver,bModifier);
            }
            con3.setScrollableY(true);
            System.out.println("sroll : " + con3.isScrollableY());
            add(BorderLayout.CENTER, con3);

        } else {
            Container cont = new Container(BoxLayout.y());
            cont.add(BoxLayout.encloseXCenter(new Label("Aucune location à afficher")));
            add(BorderLayout.CENTER, cont);

        }
        ///end

//        setupSideMenu(res);

    }

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
}
