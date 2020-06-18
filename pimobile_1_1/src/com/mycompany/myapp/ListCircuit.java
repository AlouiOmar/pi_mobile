/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.FloatingActionButton;
import com.mycompany.myapp.ServiceCircuit;
import com.codename1.components.ToastBar;
import com.codename1.db.Database;
import com.codename1.io.Log;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.twilio.rest.preview.wireless.sim.Usage;
import java.io.IOException;

/**
 *
 * @author ezzedine
 */
public class ListCircuit extends SideMenuBaseForm {

    Fos_user loggedInUser;
    Database db;

    public ListCircuit(Resources res) {
        //setTitle("All Circuits");

        super(BoxLayout.y());
        loggedInUser = new Fos_user();
        loggedInUser.setId(2);
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
                                new Label("Nos Circuits", "Title")
                        )
                )
        );

//        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
//        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        //      fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(titleCmp);

        // current = this;
        setLayout(BoxLayout.y());
        // theme = UIManager.initFirstTheme("/theme_1");

//    Toolbar tb = getToolbar();
//        tb.setTitleCentered(false);
//        
//        
////          Toolbar.setGlobalToolbar(true);
////        Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
//
//
//     
//        Button menuButton = new Button("");
////        menuButton.setUIID("Title");
////        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
//        //menuButton.addActionListener(e -> getToolbar().openSideMenu());
//        
//        
//        
//        
//
//        Container titleCmp = BoxLayout.encloseY(
//                        FlowLayout.encloseIn(menuButton),
//                        BorderLayout.centerAbsolute(
//                                BoxLayout.encloseY(
//                                    new Label("Nos Circuits", "Title")
//                                )
//                            )
//                );
//        
//        
////        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
////        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
//  //      fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
//            
//            tb.setTitleComponent(titleCmp);
        Container filtre = new Container(BoxLayout.x());

        Label Labfiltrer = new Label("Tri par :");
        ComboBox list = new ComboBox();
        list.addItem("Tous les Circuits");
        list.addItem("Dist élevé -> Dist bas");
        list.addItem("Dist bas -> Dist élevé");
        //addAll(Labfiltrer,list);
        filtre.addAll(Labfiltrer, list);
        add(filtre);
        list.addActionListener((e) -> {
            if (list.getSelectedIndex() == 0) {

                new ListCircuit(res).show();
            }

            if (list.getSelectedIndex() == 1) {
                new DescDistance(res).show();

            }
            if (list.getSelectedIndex() == 2) {

                new AscDistance(res).show();
            }

        });

        for (Circuit c : ServiceCircuit.getInstance().getAllCircuits()) {
            Label tfname = new Label();
            tfname.setText("Nom : " + c.getNom());
            Label tfDepart = new Label();
            tfDepart.setText("Begin : " + c.getBegin());
            Label tfPause = new Label();
            tfPause.setText("Pause : " + c.getPause());
            Label tfEnd = new Label();
            tfEnd.setText("End : " + c.getEnd());
            Label tfDifficulty = new Label();
            tfDifficulty.setText("Difficulty : " + c.getDifficulty());
            Label tfDistance = new Label();
            tfDistance.setText("Distance : " + c.getDistance() + " Km");
            Container cnt2 = new Container(BoxLayout.y());
            Container cnt1 = new Container(BoxLayout.y());
//            Container map = new Container();
//             BrowserComponent browser = new BrowserComponent();
//                    System.out.println("https://www.google.com/maps/dir/"+c.getBlat()+","+c.getBlon()+"/"+c.getPlat()+","+c.getPlon()+"/"+c.getElat()+","+c.getElon()+"/");
//                    browser.setURL("https://www.google.com/maps/dir/"+c.getBlat()+","+c.getBlon()+"/"+c.getPlat()+","+c.getPlon()+"/"+c.getElat()+","+c.getElon()+"/");
//               map.add(browser);

            Button tfdelete = new Button(FontImage.MATERIAL_DELETE);
            Button tfShowMaps = new Button("Shwow Map");

            Button tfModifier = new Button("Modifier");
            Button tfPanier = new Button(FontImage.MATERIAL_ADD_SHOPPING_CART);
            cnt1.addAll(tfname, tfDepart, tfPause, tfEnd, tfDifficulty, tfDistance, tfShowMaps);
            System.out.println(c.getUserId());
            if ( c.getUserId()==loggedInUser.getId()) {
                cnt1.addAll(tfModifier, tfdelete);
            }
            cnt2.add(cnt1);
            //cnt1.add(cnt2);
            add(cnt2);
            tfdelete.addActionListener((e) -> {
                if (Dialog.show("Alert", "Voulez vous supprimer  " + c.getNom() + " !!", "OK", "Cancel")) {
                    if (ServiceCircuit.getInstance().deleteCircuit(c)) {
                        try {
                            db = Database.openOrCreate("velo");
                            db.execute("delete from circuit where name=" + c.getNom());
                            db.close();
                        } catch (IOException ex) {
                        }
                        ToastBar.showMessage("La Circuit a été supprimé", FontImage.MATERIAL_WARNING);
                        new ListCircuit(res).show();
                    } else {
                        try {
                            db.close();
                        } catch (IOException ex) {
                        }
                        new ListCircuit(res).show();
                    }
                }

            });
            tfModifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) {
                    new UpdateCircuit(c, res).show();
                }

            });
            Command back = new Command("Back") {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    showBack();
                }
            };

            tfShowMaps.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Form hi = new Form("Browser", new BorderLayout());
                    BrowserComponent browser = new BrowserComponent();
                    //System.out.println("https://www.google.com/maps/dir/"+c.getBlat()+","+c.getBlon()+"/"+c.getPlat()+","+c.getPlon()+"/"+c.getElat()+","+c.getElon()+"/");
                    browser.setURL("https://www.google.com/maps/dir/" + c.getBlat() + "," + c.getBlon() + "/" + c.getPlat() + "," + c.getPlon() + "/" + c.getElat() + "," + c.getElon() + "/");
                    hi.add(BorderLayout.CENTER, browser);
                    hi.getToolbar().setBackCommand(back);
                    hi.setBackCommand(back);
                    hi.show();

                }
            });

//Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
//FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
//   getToolbar().addCommandToLeftBar("Left", icon, (e) -> Log.p("Clicked"));
//getToolbar().addCommandToRightBar("Right", icon, (e) -> Log.p("Clicked"));
//getToolbar().addCommandToOverflowMenu("Overflow", icon, (e) -> Log.p("Clicked"));
//getToolbar().addCommandToSideMenu("Sidemenu", icon, (e) -> Log.p("Clicked"));
            // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
            setupSideMenu(res);
        }

    }
}
