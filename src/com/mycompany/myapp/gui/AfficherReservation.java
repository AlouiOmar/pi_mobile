/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.company.myapp.entities.SideMenuBaseForm;
import com.codename1.l10n.Format;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.ServiceReservation;
import com.mycompany.myapp.utils.Statics_1;

/**
 *
 * @author nahawnd
 */
public class AfficherReservation extends SideMenuBaseForm {

    private EncodedImage palceHolder;

    public AfficherReservation(Resources res, Reservation r) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = null;

//        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
//        Graphics g = tintedImage.getGraphics();
//        g.drawImage(profilePic, 0, 0);
//        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
//
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
        getToolbar().addMaterialCommandToLeftSideMenu("Locations", FontImage.MATERIAL_STORE, ev -> new ListLocationsForm(res).show());
        getToolbar().addMaterialCommandToLeftSideMenu("Reserver", FontImage.MATERIAL_STORE, ev -> new AddReservationForm(res).show());
        
        Button settingsButton = new Button("Retour");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_ARROW_BACK_IOS);
        settingsButton.addActionListener(e -> new ListReservationsForm(res).show());

        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                        add(BorderLayout.CENTER, space).
                        add(BorderLayout.SOUTH,
                                FlowLayout.encloseIn(
                                        new Label("  Détails de ", "WelcomeBlue"),
                                        new Label("reservation", "WelcomeWhite")
                                ));

        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);

        //#####begin
        Label lTitre = new Label("Titre: " + r.getTitre());
        Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String ress = dateFormat.format(r.getDateDeb());
//        String ress1 = dateFormat.format(r.getDateFin());
//        Label ldateDeb = new Label("Date debut: " + ress);
//        Label ldateFin = new Label("Date fin: " + ress1);
        Button bModif = new Button("Modifier");
        Button bSupp = new Button("Supprimer");
        Container cb = new Container(BoxLayout.x());
        cb.addAll(bModif, bSupp);
        cb.setVisible(false);

        if (Statics_1.current_user.getRoles().contains("ADMIN") || Statics_1.current_user.getId() == r.getId()) {
            cb.setVisible(true);
        }
        bModif.addActionListener(e -> {
            UpdateReservationForm updateReservationForm = new UpdateReservationForm(res, r);

            updateReservationForm.show();

        });
        bSupp.addActionListener(e1 -> {
            if (Dialog.show("voulez vous supprimer cette reservation", "", "Supprimer", "Annuler")) {

                ServiceReservation.getInstance().DeleteReservation(r.getId());
                System.out.println("Reservation supprimée avec succès !");
                ListReservationsForm listLocationsForm = new ListReservationsForm(res);
                listLocationsForm.show();
            }

        });
        PickerComponent signallPicker = PickerComponent.createStrings("Contenu indésirable", "Harcèlement", "Discours haineux", "Nudité", "Violence", "Autre").label("Cause");

//        setupSideMenu(res);
    }

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }

}
