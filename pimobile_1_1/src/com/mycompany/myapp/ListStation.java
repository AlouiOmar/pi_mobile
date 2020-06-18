/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ezzedine
 */
public class ListStation extends SideMenuBaseForm {

    public ListStation(Resources res) {
        //setTitle("All Stations");
        
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
                                    new Label("Nos Stations", "Title")
                                )
                            )
                );
        
//        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
//        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
  //      fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(titleCmp);
        
        
        
        for (Station s : ServiceStation.getInstance().getAllStations()) {
            Label tfname = new Label();
            tfname.setText("Nom : " + s.getNom());
            Label tfLat = new Label();
            tfLat.setText("Lat : " +s.getLattitude() );
            Label tfLon = new Label();
            tfLon.setText("Lon : " +s.getLongitude());
//            Container cnt2 = new Container(BoxLayout.x());
            Container cnt1 = new Container(BoxLayout.y());
            Button tfdelete = new Button(FontImage.MATERIAL_DELETE);
//            Button tfShowSingle = new Button("Détails");
            Button tfModifier = new Button("Modifier");
             Button tfPanier = new Button(FontImage.MATERIAL_ADD_SHOPPING_CART);
            cnt1.addAll(tfname, tfLat, tfLon, tfModifier, tfdelete);
//            cnt1.add(im);
//            cnt1.add(cnt2);
            add(cnt1);
            tfdelete.addActionListener((ActionEvent e) -> {
                
                    if (Dialog.show("Alert", "Voulez vous supprimer  " + s.getNom() + " !!", "OK", "Cancel")) {
                        if (ServiceStation.getInstance().deleteStation(s)) {
                            ToastBar.showMessage("Le Station a été supprimé", FontImage.MATERIAL_WARNING);
                            new ListStation(res).show();
                        } else {
                                 Dialog.show("Alert", "le Station " + s.getNom() + " est utilisé dans les Circuits   !!", "OK", "Cancel");
                                 new ListStation(res).show();
                        }
                    }
                
            });

            
            
//  try {
//            tfModifier.addActionListener((e1) -> {
//                new UpdateStationForm(p, current).show();
//            });

//             } catch (Exception e) {System.out.println(e.getMessage());
//                }
//    tfPanier.addActionListener((e2) -> {
//            ServiceProduit.getInstance().AddPanierProduit(p);
//            if(ServiceProduit.getInstance().AddPanierProduit(p)){
//              ToastBar.showMessage("Le produit a été ajouté à votre panier ", FontImage.MATERIAL_INFO);
//            }
//            });
        }
        
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceStation.getInstance().getAllStations().toString());
//        add(sp);
      setupSideMenu(res);
// getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> res.showBack());
    }
    
}
