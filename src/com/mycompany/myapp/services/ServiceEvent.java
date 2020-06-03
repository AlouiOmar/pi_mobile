/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Participation;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.Fos_user;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceEvent {

    public ArrayList<Event> events;
    
    public static ServiceEvent instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEvent() {
         req = new ConnectionRequest();
    }

    public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }

    public boolean addEvent(Event ev) {
        String url = Statics.BASE_URL + "createevent?titre=" + ev.getTitre() + "&dateE=" + ev.getDateE() + "&description=" + ev.getDescription()+ "&region=" + ev.getRegion() + "&nameC=" + ev.getNameC() +"&nbplaces=" + ev.getNbplaces();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
        public boolean editEvent(Event ev) {
        String url = Statics.BASE_URL + "editevent/"+ev.getIdE()+"?titre=" + ev.getTitre() + "&dateE=" + ev.getDateE() + "&description=" + ev.getDescription()+ "&region=" + ev.getRegion() + "&nameC=" + ev.getNameC() +"&nbplaces=" + ev.getNbplaces();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public ArrayList<Event> parseEvents(String jsonText){
        try {
            events=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)eventsListJson.get("root");
            for(Map<String,Object> obj : list){
                Event ev = new Event();
//                float id = Float.parseFloat(obj.get("id").toString());
//                t.setId((int)id);
//                t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                float idE = Float.parseFloat(obj.get("id").toString());
                ev.setIdE((int)idE);  
                ev.setTitre(obj.get("titre").toString());
                ev.setDateE(obj.get("dateE").toString());
                ev.setDescription(obj.get("description").toString());
                ev.setRegion(obj.get("region").toString());
                ev.setNameC(obj.get("nameC").toString());
                float nbplaces = Float.parseFloat(obj.get("nbplaces").toString());
                ev.setNbplaces((int)nbplaces);
                
                events.add(ev);
            }
            
            
        } catch (IOException ex) {
            
        }
        return events;
    }
    
    public ArrayList<Event> afficherEvents(){
        String url = Statics.BASE_URL+"showevent";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
    public boolean deleteEvent(Event ev) {
        String url = Statics.BASE_URL + "deleteevent/"+ev.getIdE();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
}
