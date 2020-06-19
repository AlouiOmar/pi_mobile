/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.rest.Response;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import com.company.myapp.entities.Produit;
import com.company.myapp.services.ServiceProduit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Raef
 */
public class ListPanierMobileForm extends SideMenuBaseForm {

    Resources theme;
    Form current;
    Database db;
    private int count = 0;
    ArrayList<String> arrayName = new ArrayList();
    ArrayList<Float> arrayPrice = new ArrayList();
    public static String Name = "Les produits choisis : \n \n";

    public ListPanierMobileForm(Form previous) {

        current = this;
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme_1");

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = theme.getImage("log.png");
        Image mask = theme.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Panier", "Title"),
                                new Label(LoginForm.userName, "SubTitle")
                        )
                ).add(BorderLayout.WEST, profilePicLabel),
                GridLayout.encloseIn(2)
        );

//        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
//        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        //      fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(titleCmp);

        Button notify = new Button("Générer dévis");

        try {
            db = Database.openOrCreate("VeloDB");

            db.execute("create table if not exists Panier(id INTEGER UNIQUE,Prix Float,NomProd TEXT,idUser INTEGER);");
            System.out.println("DB crée");

            Button tfdeleteAll = new Button("Vider Panier");

            tfdeleteAll.addActionListener(e -> {
                String querry = "DROP TABLE Panier";
                if (Dialog.show("Alert", "Voulez vous Vraiment vider votre panier !!", "OK", "Cancel")) {
                    try {
                        db = Database.openOrCreate("VeloDB");
                        db.execute(querry);
                        db.close();
                    } catch (IOException ex) {
                    }
                    System.out.println("deletedAll");
                    ToastBar.showMessage("Le panier est vider maintenant", FontImage.MATERIAL_WARNING);
                    Label vide = new Label("Votre panier est vide");
                    new ListPanierMobileForm(previous).show();
                    add(vide);
                } else {
                    new ListPanierMobileForm(previous).show();
                }
            });
            
            
            Cursor nbrProductInCart = db.executeQuery("select count(*) from Panier");
            Row rNbr = nbrProductInCart.getRow();
            int nbr = rNbr.getInteger(0);
            if (nbr != 0) {
                Cursor c = db.executeQuery("select * from Panier where iduser=" + LoginForm.idus);
                while (c.next()) {

                    Row row = c.getRow();
                    int id = row.getInteger(0);
                    Float prix = row.getFloat(1);
                    String Nomprod = row.getString(2);

                    String iduser = row.getString(3);

                    System.out.println("Prix : " + prix);
                    System.out.println("Nom du produit : " + Nomprod);
                    System.out.println("Utilisateur : " + iduser);

                    count += prix;

                    if (iduser.length() == 0) {

                        Label tableVidee = new Label("Votre panier est vide !!");

                    } else {

                        Button tfdelete = new Button(FontImage.MATERIAL_DELETE);

                        Container hi = new Form("", new TableLayout(3, 3));
                        hi.add(new Label(Nomprod)).
                                add(new Label(prix.toString())).
                                add((tfdelete));

                        arrayName.add(Nomprod);

                        notify.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String Total = "Votre totale panier est :  " + count + "\n ";

                                for (int i = 0; i < arrayName.size(); i++) {

                                    Name += arrayName.get(i) + " \n";

                                    // String Price="Le prix du produit : "+arrayPrice.get(j).toString()+"\n "; 
                                }

                                Message m = new Message(Total + "\n  \n \n \n  \n \n \n  \n \n");

                                Display.getInstance().sendMessage(new String[]{"raef.bchiri1@esprit.tn"}, "Dévis Mr/Mme: " + LoginForm.userName, m);

                            }
                        });

                        tfdelete.addActionListener(e -> {

                            if (Dialog.show("Alert", "Voulez vous Vraiment supprimer " + Nomprod + " ?", "OK", "Cancel")) {
                                try {

                                    Database db = Database.openOrCreate("VeloDB");
                                    db.execute("delete from Panier where id=" + id);
                                    db.close();

                                    System.out.println("deleted");
                                    ToastBar.showMessage("Le poduit a été supprimé du panier", FontImage.MATERIAL_WARNING);
                                    new ListPanierMobileForm(current).show();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                    System.out.println("nnnnnnnn");
                                }

                            } else {

                                new ListPanierMobileForm(previous).show();
                            }

                        });
                        add(hi);

                    }

                }
               
                add(tfdeleteAll);
                add(notify);
            } else {
                Label tableVide = new Label("Votre panier est vide !!");
                add(tableVide);
            } db.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

//
//        Button confirmer=new Button("send");
//        
//         
//          add(confirmer);
//        
//       //
//        try {
//            
//        
//        confirmer.addActionListener(e->{
//        
//        
//        
//        
//        
//        
//        
//        
//        
//        
//        
//Message m = new Message("Body of message");
//            String textAttachmentUri = null;
// m.getAttachments().put(textAttachmentUri, "text/plain");
//           String imageAttachmentUri = null;
// m.getAttachments().put(imageAttachmentUri, "image/png");
//  Display.getInstance().sendMessage(new String[] {"raef.bchiri@esprit.tn"}, "Votre panier est vide", m);
//      
//      
//         
////   Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
////m.setMimeType(Message.MIME_HTML);
////
////// notice that we provide a plain text alternative as well in the send method
////boolean success = m.sendMessageViaCloudSync("Codename One", "destination@domain.com", "Name Of User", "Message Subject",
////                            "Check out Codename One at https://www.codenameone.com/");
////       
////       
//       
//       
//       });
//        
//        
//        } catch (Exception e) {System.out.println(e.getMessage());
        //    }
        setupSideMenu(theme);
    }
}
