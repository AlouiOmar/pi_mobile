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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ezzedine
 */
class ServiceStation {
    public ArrayList<Station> stations;
    
    public static ServiceStation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
  

    private ServiceStation() {
         req = new ConnectionRequest();
    }

    public static ServiceStation getInstance() {
        if (instance == null) {
            instance = new ServiceStation();
        }
        return instance;
    }

 
    

    public boolean addStation(Station t) {
        String url = Statics.BASE_URL + "/Circuits/addStationMobile/" + t.getNom()+ "/" + t.getLattitude()+"/"+ t.getLongitude();
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

    public ArrayList<Station> parseStations(String jsonText){
        try {
            
            stations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> stationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)stationsListJson.get("root");
            for(Map<String,Object> obj : list){
                Station t = new Station();
                
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                Double lattitude = Double.parseDouble((String) obj.get("lattitude").toString());
                float Lattitudef = Float.parseFloat(String.valueOf(lattitude));
                t.setLattitude(Lattitudef);
                Double longitude = Double.parseDouble((String) obj.get("longitude").toString());
                float Longitudef = Float.parseFloat(String.valueOf(longitude));
                t.setLongitude(Longitudef);
                stations.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return stations;
    }
    
    public ArrayList<Station> getAllStations(){
        String url = Statics.BASE_URL+"/Circuits/getStationMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                stations = parseStations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return stations;
    }
    
     public boolean deleteStation(Station s) {
        String url = Statics.BASE_URL + "/Circuits/deleteStationMobile/" + s.getId();
               
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
