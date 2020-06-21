/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.velo.gui;

//import com.codename1.uikit.materialscreens.*;
import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.AfficherEventsForm;
import com.mycompany.myapp.AddCircuit;
import com.mycompany.myapp.ListCircuit;
import com.mycompany.myapp.ListMyCircuit;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.gui.AddLocationForm;
import com.mycompany.myapp.gui.AddProductForm;
import com.mycompany.myapp.gui.AjouterEventForm;
import com.mycompany.myapp.gui.HomeForm_1;
import com.mycompany.myapp.gui.ListLocationsForm;
import com.mycompany.myapp.gui.ListPanierMobileForm;
import com.mycompany.myapp.gui.ListProductForm;
import com.mycompany.myapp.gui.ListReservationsForm;
import com.mycompany.myapp.utils.Statics_1;
import com.velo.gui.AfficherListeAnnonces;
import com.velo.gui.AjouterAnnonce;
import com.velo.gui.StatistiqueAnnonce;
import com.velo.util.Vars;
import java.io.IOException;


/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {
            private EncodedImage palceHolder;
            Form current;


    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
        String image=Vars.current_user.getPhoto();
        Image profilePic = null;
        try {
                    palceHolder = EncodedImage.create("/giphy.gif");
                } catch (IOException ex) {

                }
                if(image!=null){
                profilePic = URLImage.createToStorage(palceHolder, image, "http://localhost/pi/web/uploads/user/"+image);

                }
//        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("  "+Vars.current_user.getNom()+" "+Vars.current_user.getPrenom(), profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
//        getToolbar().addMaterialCommandToSideMenu("  Dashboard", FontImage.MATERIAL_DASHBOARD,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Profile", FontImage.MATERIAL_SETTINGS,  e -> showOtherForm(res));
//        getToolbar().addMaterialCommandToSideMenu("  Tasks", FontImage.MATERIAL_ACCESS_TIME,  e -> showOtherForm(res));
//        getToolbar().addMaterialCommandToSideMenu("  Activity", FontImage.MATERIAL_TRENDING_UP,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Ajouter annonce", FontImage.MATERIAL_ADD,  e -> new AjouterAnnonce(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Liste des annonces", FontImage.MATERIAL_LIST,  e -> {Vars.current_choice = 1;new AfficherListeAnnonces(res).show();});
      if(Vars.current_user.getRoles().contains("ADMIN")){
        getToolbar().addMaterialCommandToSideMenu("  Toutes les Annonces", FontImage.MATERIAL_LIST,  e -> {Vars.current_choice = 8;new AfficherListeAnnonces(res).show();});
        getToolbar().addMaterialCommandToSideMenu("  Annonces signalées", FontImage.MATERIAL_LIST,  e -> {Vars.current_choice = 4;new AfficherListeAnnonces(res).show();});
        getToolbar().addMaterialCommandToSideMenu("  Statistique des annonces", FontImage.MATERIAL_TRENDING_UP,  e -> new StatistiqueAnnonce(res).show());

        }
        getToolbar().addMaterialCommandToSideMenu("  Mes annonces", FontImage.MATERIAL_LIST,  e -> {Vars.current_choice = 2;new AfficherListeAnnonces(res).show();});
        getToolbar().addMaterialCommandToSideMenu("  Ajouter Produit", FontImage.MATERIAL_DASHBOARD,  e -> new AddProductForm(res).show());
       
        getToolbar().addMaterialCommandToSideMenu("  Nos produits", FontImage.MATERIAL_LIST,  e -> new ListProductForm(new AjouterAnnonce(res)).show());
      getToolbar().addMaterialCommandToSideMenu("  Votre Panier", FontImage.MATERIAL_SHOPPING_CART,  e -> new ListPanierMobileForm(new AjouterAnnonce(res)).show());
       
        getToolbar().addMaterialCommandToSideMenu("  Ajouter Circiut", FontImage.MATERIAL_DASHBOARD,  e -> new AddCircuit(res).show());
      getToolbar().addMaterialCommandToSideMenu("  Nos Circuits", FontImage.MATERIAL_LIST,  e -> new ListCircuit(res).show());
      getToolbar().addMaterialCommandToSideMenu("  Mes Circuits", FontImage.MATERIAL_LIST,  e -> new ListMyCircuit(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Liste des événements", FontImage.MATERIAL_LIST,  e -> new AfficherEventsForm(res).show());
      getToolbar().addMaterialCommandToSideMenu("  Ajouter un événement", FontImage.MATERIAL_SHOPPING_CART,  e -> new AjouterEventForm(res).show());
//      getToolbar().addMaterialCommandToSideMenu("  Déconnection", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
                getToolbar().addMaterialCommandToSideMenu("  Blog", FontImage.MATERIAL_LIST,  e -> {
                    fos_user us=new fos_user();
                    us.setId(Vars.current_user.getId());
                    new HomeForm_1(res,us).show();});
 getToolbar().addMaterialCommandToSideMenu(" Louer ", FontImage.MATERIAL_ADD, e -> new AddLocationForm(res).show());
        getToolbar().addMaterialCommandToSideMenu(" Liste Locations ", FontImage.MATERIAL_LIST, e -> {
            Statics_1.current_choice = 1;
            new ListLocationsForm(res).show();
        });
    
        getToolbar().addMaterialCommandToSideMenu(" Liste Reservation ", FontImage.MATERIAL_LIST, e -> {
            Statics_1.current_choice = 1;
            new ListReservationsForm(res).show();
        });
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> {Vars.current_user = null; new LoginForm(res).show();});
//        getToolbar().addMaterialCommandToSideMenu("  Liste des annonces",FontImage.MATERIAL_EXIT_TO_APP , new ActionListener() {
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
    }
    
    protected abstract void showOtherForm(Resources res);
}
