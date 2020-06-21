/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Raef
 */
public class HomeForm extends Form {

    Form current;
    Resources theme;

    public HomeForm() {
        setTitle("Home");
        setLayout(new FlowLayout(CENTER));

        theme = UIManager.initFirstTheme("/theme");

        current = this;
        
        Label title = new Label();
        title.setText("Vélo.tn");

        String urll = "http://localhost/local/27.jpg";
        EncodedImage enc = EncodedImage.createFromImage(theme.getImage("loading.png"), false);
        URLImage urlimg = URLImage.createToStorage(enc, "d", urll);
        ImageViewer image = new ImageViewer(urlimg);
       
        Container hi = new Container( BoxLayout.y());
 hi.add(title);
        hi.add( image);
        add(hi);

      //  getToolbar().addCommandToLeftSideMenu("Ajouter ", null, e -> new AddProductForm(current).show());
        getToolbar().addCommandToLeftSideMenu("Afficher", null, e -> new ListProductForm(current).show());

    }
}
