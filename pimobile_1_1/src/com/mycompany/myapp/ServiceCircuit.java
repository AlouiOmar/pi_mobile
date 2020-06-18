/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Circuit;
import com.mycompany.myapp.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ezzedine
 */
class ServiceCircuit {
    public ArrayList<Circuit> circuits;
    
    public static ServiceCircuit instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCircuit() {
         req = new ConnectionRequest();
    }

    public static ServiceCircuit getInstance() {
        if (instance == null) {
            instance = new ServiceCircuit();
        }
        return instance;
    }

    public boolean addCircuit(Circuit t) {
        String url = Statics.BASE_URL + "/Circuits/addCircuitMobile/"+t.getUserId()+"/" + t.getNom()+ "/" + t.getBegin()+"/"+ t.getPause()+"/"+t.getEnd()+"/"+t.getDifficulty()+"/"+t.getDistance();
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

    public ArrayList<Circuit> parseCircuits(String jsonText){
        try {
            circuits=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> circuitsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)circuitsListJson.get("root");
            for(Map<String,Object> obj : list){
                Circuit t = new Circuit();
                Map<String,Object> departObj= (Map<String, Object>) obj.get("depart");
                
                Map<String,Object> pauseObj= (Map<String, Object>) obj.get("pause");
                Map<String,Object> endObj= (Map<String, Object>) obj.get("end");
                float id = Float.parseFloat(obj.get("id").toString());
                float distance = Float.parseFloat(obj.get("distance").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                t.setBegin(departObj.get("nom").toString());
                t.setBlat(Float.parseFloat(departObj.get("lattitude").toString()));
                t.setBlon(Float.parseFloat(departObj.get("longitude").toString()));
                t.setPause(pauseObj.get("nom").toString());
                t.setPlat(Float.parseFloat( pauseObj.get("lattitude").toString()));
                t.setPlon(Float.parseFloat(pauseObj.get("longitude").toString()));
                t.setEnd(endObj.get("nom").toString());
                t.setElat(Float.parseFloat(endObj.get("lattitude").toString()));
                t.setElon(Float.parseFloat(endObj.get("longitude").toString()));
                t.setDifficulty(obj.get("difficulty").toString());
                Map<String,Object> userObj=(Map<String,Object>) obj.get("userId");
                float idUser=Float.parseFloat(userObj.get("id").toString());
                int idUserInt=(int) idUser;
                t.setUserId(idUserInt);
                t.setDistance(distance);
                circuits.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return circuits;
    }
    
    public ArrayList<Circuit> getAllCircuits(){
        String url = Statics.BASE_URL+"/Circuits/getCircuitMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                circuits = parseCircuits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return circuits;
    }

     public boolean deleteCircuit(Circuit c) {
         if (c.getId()!=1) {
             String url = Statics.BASE_URL + "/Circuits/deleteCircuitMobile/" + c.getId();
               
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
         }else{
         
         String url = Statics.BASE_URL + "/Circuits/deleteCircuitMobileByName/" + c.getNom();
               
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;}
    }
     
     
     
     
     
     
     
     public boolean UpdateCircuit(Circuit c) {
        String url = Statics.BASE_URL + "/Circuits/updateCircuitMobile/" + c.getId()
                + "/" + c.getNom()
                + "/" + c.getBegin()
                + "/" +c.getPause()
                +"/"+ c.getEnd()
                + "/" +c.getDifficulty()
                 + "/" +c.getDistance();
               
        
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
     public ArrayList<Circuit> HighDistance(){
       
         
         String url = Statics.BASE_URL+"/Circuits/HighDistMobile";      
        req.setUrl(url);
        req.setPost(false);
        
   
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                circuits = parseCircuits(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return circuits;
    }
     public ArrayList<Circuit> LowDistance(){
       
         
         String url = Statics.BASE_URL+"/Circuits/LowDistMobile";      
        req.setUrl(url);
        req.setPost(false);
        
   
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                circuits = parseCircuits(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return circuits;
    }
}

