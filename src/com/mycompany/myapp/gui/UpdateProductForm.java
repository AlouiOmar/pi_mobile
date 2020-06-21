/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.company.myapp.entities.Produit;
import com.company.myapp.services.ServiceProduit;

/**
 *
 * @author Raef
 */
public class UpdateProductForm extends Form {
  Resources theme;
    UpdateProductForm(Produit p, Form previous) {
          theme = UIManager.initFirstTheme("/theme");
        setTitle("Modifier  produit");
        setLayout(BoxLayout.y());
        Label tfNom1 = new Label("Nom");
        TextField tfNom = new TextField(p.getNom_P());
        Label tfMarque1 = new Label("Marque");
        TextField tfMarque = new TextField(p.getMarque_P());
        Label tfCategorie1 = new Label("Catégorie");
        TextField tfCategorie = new TextField(p.getCategorie_P());
        Label tfCouleur1 = new Label("Couleur");

        TextField tfCouleur = new TextField(p.getCouleur_P());
        Label tfPrix1 = new Label("Prix");
        TextField tfPrix = new TextField(""+(int)(float)p.getPrix_P());
        
        Label tfteleph = new Label("Téléphone");
      TextField tfteleph1 = new TextField(""+(int)(float)p.getTel());

        Button btnValider = new Button("Modifier");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length() == 0) || (tfMarque.getText().length() == 0) || (tfCategorie.getText().length() == 0) || (tfCouleur.getText().length() == 0) || (tfPrix.getText().length() == 0)) {
                    Dialog.show("Alert", "Les champs sont vides , Veuillez les remplir", new Command("OK"));
                } else {
                    try {
                        p.setId_P(p.getId_P());
                        p.setNom_P(tfNom.getText());
                        p.setMarque_P(tfMarque.getText());
                        p.setCategorie_P(tfCategorie.getText());
                        p.setCouleur_P(tfCouleur.getText());
                        p.setPrix_P(Float.parseFloat(tfPrix.getText()));
                        p.setTel(Integer.parseInt(tfteleph1.getText()));
                        ServiceProduit.getInstance().UpdateProduit(p);

                        Dialog.show("", "Le produit a été modifié avec succès", new Command("OK"));

                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Price must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfNom1,tfNom, tfMarque1,tfMarque, tfCategorie1,tfCategorie, tfCouleur1,tfCouleur, tfPrix1,tfPrix,tfteleph,tfteleph1);
     //add(tftel);
        add(btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListProductForm(previous).show());

    }

}
