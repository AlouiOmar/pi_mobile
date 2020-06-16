/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.ServiceParticipation;
import com.codename1.uikit.materialscreens.LoginForm;
import com.codename1.uikit.materialscreens.ProfileForm;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.codename1.uikit.materialscreens.StatsForm;
import com.mycompany.myapp.entities.Weather;
import com.mycompany.myapp.services.WeatherService;
import javafx.scene.control.ToolBar;
import static jdk.nashorn.internal.objects.NativeJava.extend;

/**
 *
 * @author bhk
 */

public class AfficherEventsForm extends SideMenuBaseForm{
    public AfficherEventsForm(Resources res) {

        super(BoxLayout.y());
        setUIID("LoginForm");
        setTitle("List Events");
        for (Event ev : ServiceEvent.getInstance().afficherEvents()){
            int IdE=ev.getIdE();
            Label lbTitre=new Label();
            Label lbDateE=new Label();
            Label lbDescription=new Label();
            Label lbRegion=new Label();
            Label lbNameC=new Label();
            Label nb=new Label();
            Container cnt1 = new Container(BoxLayout.x());
            Container cnt2 = new Container(BoxLayout.y());
            cnt2.getStyle().setBgColor(0x99CCCC);
            cnt2.getStyle().setBgTransparency(255);
            lbTitre.setText("Titre: "+ev.getTitre());
            lbDateE.setText("Date de l'evenement: "+ev.getDateE());
            lbRegion.setText("Region: "+ev.getRegion());
            lbNameC.setText("Circuit: "+ev.getNameC());
            nb.setText("Nombre de places: "+Integer.toString(ev.getNbplaces()));
            Button participer=new Button("Participer");
            Button modifier=new Button("Modifier");
            Button details=new Button("Details");
            Button supprimer=new Button("Supprimer");
            cnt1.addAll(participer,modifier,details,supprimer);
            
            details.addActionListener(r->{
                Form det=new Form("Details de l'événement "+ev.getTitre(),BoxLayout.y());
                Label lbdTitre=new Label("Titre: "+ev.getTitre());
                Label lbdDateE=new Label("Date de l'evenement: "+ev.getDateE());
                Label lbdDescription=new Label("Description: "+ev.getDescription());
                Label lbdRegion=new Label("Region: "+ev.getRegion());
                Label lbdNameC= new Label("Circuit: "+ev.getNameC());
                Command back = new Command("Back") {
                 @Override
                public void actionPerformed(ActionEvent evt) {
                show();
                  }
                        };
                det.getToolbar().setBackCommand(back);
                Toolbar myToolbar = det.getToolbar();
                Button buttonToolbar = myToolbar.findCommandComponent(back);
                Button weath=new Button("voir météo région");
                det.addAll(lbdTitre,lbdDateE,lbdDescription,lbdRegion,lbdNameC,weath);
                det.show();
                weath.addActionListener(z->{
                    
                    Form wa=new Form("Météo dans "+ev.getRegion(),BoxLayout.y());
                    Weather  weather;
                    weather = WeatherService.getInstance().afficherWeather(ev.getRegion());
                    Label lblCity=new Label ("Citée: "+weather.getCity());
                    Label lblTime=new Label ("Date: "+weather.getWdate());
                    Label lblTemperature=new Label ("Temperature: "+weather.getTemperature());
                    Label lblDescription=new Label ("Status: "+weather.getDescription());
                    Command black = new Command("Back") {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                    det.show();
                     }
                        };
                    det.getToolbar().setBackCommand(black);
                    Toolbar mmyToolbar = det.getToolbar();
                    Button mbuttonToolbar = myToolbar.findCommandComponent(back);
                    wa.addAll(lblCity,lblTime,lblTemperature,lblDescription);
                    
                    wa.show();
                    });
                
            });
            
            modifier.addActionListener(a->{
                Form me=new Form("Modifier evenement",BoxLayout.y());     
                Command baack = new Command("Back") {
                 @Override
                public void actionPerformed(ActionEvent evt) {
                show();
                  }
                        };
                me.getToolbar().setBackCommand(baack);
                Toolbar emyToolbar = me.getToolbar();
                Button fbuttonToolbar = emyToolbar.findCommandComponent(baack);
                TextField mtfTitre = new TextField(ev.getTitre(),"Titre");
                Picker    mdpDateE= new Picker();
                TextField mtfDescription=new TextField(ev.getDescription(),"Description");
                TextField mtfRegion=new TextField(ev.getRegion(),"Region");
                TextField mtfNameC=new TextField(ev.getNameC(),"Circuit");
                Container mcnt = new Container(BoxLayout.y());
                Label mlbnbplaces = new Label("Nombre de places : "+Integer.toString(ev.getNbplaces()));
                Slider mnbplaces = new Slider();
                mcnt.add(mlbnbplaces);
                mcnt.add(mnbplaces);
                mnbplaces.setEditable(true);
                mnbplaces.setMinValue(0);
                mnbplaces.setMaxValue(100);
                mnbplaces.addActionListener((e)->{        
                mlbnbplaces.setText("Nombre de places :"+mnbplaces.getProgress());
                });       
                Button btnModifier = new Button("Modifier evenement");
                
                btnModifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                if ((mtfTitre.getText().length()==0)||(mdpDateE.getText().length()==0)||(mtfDescription.getText().length()==0)||(mtfRegion.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    Event ev = new Event(IdE,mtfTitre.getText(),mdpDateE.getText(),mtfDescription.getText(),mtfRegion.getText(),mtfNameC.getText(),mnbplaces.getProgress());
                    if( ServiceEvent.getInstance().editEvent(ev)){
                        Dialog.show("Success","Vous avez modifié l'evenement "+ev.getTitre(),new Command("OK"));
                        }
                    else{
                        Dialog.show("ERROR", "Veuillez réessayer", new Command("OK"));
                        }
                    new AfficherEventsForm(res).show();
                    }                                                 
                }   
                });       
            me.addAll(mtfTitre,mdpDateE,mtfDescription,mtfRegion,mtfNameC,mcnt,btnModifier);
//            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
            me.show();                        
            });
            
            
            supprimer.addActionListener(b->{
                    if( ServiceEvent.getInstance().deleteEvent(ev)){
                        Dialog.show("Success","Vous avez supprimé l'evenement "+ev.getTitre(),new Command("OK"));
                        }
                    else{
                        Dialog.show("ERROR", "Veuillez réessayer", new Command("OK"));
                        }
                    new AfficherEventsForm(res).show();
            });
            
            
            participer.addActionListener(y->{
                    if( ServiceParticipation.getInstance().participer(ev)){
                        Dialog.show("Success","Vous avez supprimé l'evenement "+ev.getTitre(),new Command("OK"));
                        }
                    else{
                        Dialog.show("ERROR", "Veuillez réessayer", new Command("OK"));
                        }
                    new AfficherEventsForm(res).show();
             
            });
            cnt2.addAll(lbTitre,lbDateE,lbDescription,lbRegion,lbNameC,nb,cnt1);
            add(cnt2);
            }
          setupSideMenu(res);

//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }




    
    
}
