/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.uikit.materialscreens.AfficherEventsForm;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
Resources res;
    public HomeForm() {
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddEvent = new Button("Ajouter Event");
        Button btnListEvents = new Button("Afficher Events");
        Button btnWeather = new Button("Voir meteo");
        btnAddEvent.addActionListener(e-> new AjouterEventForm(res).show());
        btnListEvents.addActionListener(e-> new AfficherEventsForm(res).show());
        btnWeather.addActionListener(e-> new WeatherForm(current).show());
        addAll(btnAddEvent,btnListEvents,btnWeather);
        
        
    }
    
    
}
