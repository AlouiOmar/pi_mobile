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

/**
 *
 * @author bhk
 */

public class AfficherEventsForm extends SideMenuBaseForm{
    Form current;
    public AfficherEventsForm(Resources res) {

        current=this;
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
            
            lbTitre.setText("Titre: "+ev.getTitre());
            lbDateE.setText("Date de l'evenement: "+ev.getDateE());
            lbDescription.setText("Description: "+ev.getDescription());
            lbRegion.setText("Region: "+ev.getRegion());
            lbNameC.setText("Circuit: "+ev.getNameC());
            nb.setText("Nombre de places: "+Integer.toString(ev.getNbplaces()));
            Button participer=new Button("Participer");
            Button modifier=new Button("Modifier");
            Button details=new Button("Details");
            Button supprimer=new Button("Supprimer");
            cnt1.addAll(participer,modifier,details,supprimer);
            
            
            modifier.addActionListener(a->{
                Form me=new Form("Modifier evenement",BoxLayout.y());               
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
