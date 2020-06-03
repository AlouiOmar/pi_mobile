/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.Weather;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.WeatherService;


/**
 *
 * @author Administrateur
 */
public class WeatherForm extends Form {

    Form current;
String cityname;
    
    public WeatherForm(Form previous) {
        current=this;
     Button btnGetWx=new Button("Voir météo");
     TextField txtZipcode=new TextField();
     Label lblCity=new Label();
     Label lblTime=new Label();
     Label lblTemperature=new Label();
     Label lblDescription=new Label(); 
     Container cnt=new Container(BoxLayout.y());
     cnt.addAll(txtZipcode,btnGetWx);
     add(cnt);
     
     show();
     btnGetWx.addActionListener(r->{
                Form w=new Form("Weather",BoxLayout.y());
              cityname = txtZipcode.getText();
              Weather  weather;
              weather = WeatherService.getInstance().afficherWeather(cityname);
                    lblCity.setText("Citée: "+weather.getCity());
                    lblTime.setText("Date: "+weather.getWdate());
                    lblTemperature.setText("Temperature: "+weather.getTemperature());
                    lblDescription.setText("Status: "+weather.getDescription());
                w.addAll(lblCity,lblTime,lblTemperature,lblDescription);
               w.show();   
                
            });
            
    }  
}
