/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.company.myapp.entities.SideMenuBaseForm;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.utils.Statics;
import com.velo.gui.AfficherListeAnnonces;
import com.velo.util.Vars;

/**
 *
 * @author nahawnd
 */
public class HomeForm_2 extends SideMenuBaseForm {

    Form current;
    Form home;
    private EncodedImage palceHolder;

    public HomeForm_2(Resources theme) {
        current = this; //Récupération de l'interface(Form) en cours
        home = this;
        setTitle(" Bienvenue dans Vélo.TN");
        setLayout(BoxLayout.y());

//          super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        Container ch = new Container(BoxLayout.y());

        Image logo = theme.getImage("logo.png");

        ImageViewer imagelogo = new ImageViewer(logo);

        Style imagestyle = imagelogo.getAllStyles();

        imagestyle.setMarginTop(100);

        ch.add(imagelogo);

        Label texthome = new Label("Bienvenue dans Vélo.TN");
        int fontsize = Display.getInstance().convertToPixels(50);
        Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        Style styletext = texthome.getUnselectedStyle();
        styletext.setMarginTop(100);
        styletext.setMarginBottom(50);
        styletext.setFont(font);
        styletext.setFgColor(0xE3051A);
        Container centered = BorderLayout.centerAbsolute(texthome);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        getToolbar().addMaterialCommandToLeftSideMenu("menuButton", FontImage.MATERIAL_MENU, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            }
        });

        getToolbar().addMaterialCommandToLeftSideMenu("Profile", FontImage.MATERIAL_SHOPPING_CART, ev -> new ProfileForm(theme).show());
        getToolbar().addMaterialCommandToLeftSideMenu("Deconnexion", FontImage.MATERIAL_ADD_CIRCLE, ev -> new LoginForm(theme).show());
//        getToolbar().addMaterialCommandToLeftSideMenu("Modifier location", FontImage.MATERIAL_STORE, ev -> new UpdateLocationForm(res,l).show());
//        getToolbar().addMaterialCommandToLeftSideMenu("Reserver", FontImage.MATERIAL_STORE, ev -> new AddReservationForm(res).show());

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);

         Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton)
                                //                                .add(BorderLayout.EAST, settingsButton)
                                .add(BorderLayout.EAST, settingsButton)
                ).
                        add(BorderLayout.CENTER, space).
                        add(BorderLayout.SOUTH,
                                FlowLayout.encloseIn(
                                        new Label("   ", "WelcomeBlue"),
                                        new Label("", "WelcomeWhite")
                                ));
        
        
        
        //begin
        Button btnAddLocation = new Button("Louer");
        Button btnListLocations = new Button("Consulter liste locations");
        Button btnChercherLocation = new Button("Chercher location");

        Button btnAddReservation = new Button(" Reserver vélo");
        Button btnListReservations = new Button("Consulter liste Reservations");
        Button btnannonce = new Button(" Liste des annonces");
        btnannonce.addActionListener(e -> {Vars.current_choice = 1;new AfficherListeAnnonces(theme).show();});

        btnAddLocation.addActionListener(e -> new AddLocationForm(theme).show());
        btnListLocations.addActionListener(e -> new ListLocationsForm(theme).show());

        btnAddReservation.addActionListener(e -> new AddReservationForm(theme).show());
        btnListReservations.addActionListener(e -> new ListReservationsForm(theme).show());

        addAll(btnAddLocation, btnListLocations, btnAddReservation, btnListReservations,btnannonce);

        ButtonGroup bg = new ButtonGroup();

        Container radioContainer = new Container();

//        Button skip = new Button("Profil");
//        skip.setUIID("SkipButton");
//        skip.addActionListener(e -> new ProfileForm(theme).show());
//
//        Container southLayout = BoxLayout.encloseY(
//                radioContainer,
//                skip
//        );
//        add(BorderLayout.south(
//                southLayout
//        ));
//
    }

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
}
