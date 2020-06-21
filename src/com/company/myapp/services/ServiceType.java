/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.company.myapp.entities.TypeProduit;
import com.company.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
;

/**
 *
 * @author Raef
 */
public class ServiceType {
    public ArrayList<TypeProduit> types;
    
    public static ServiceType instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceType() {
         req = new ConnectionRequest();
    }

    public static ServiceType getInstance() {
        if (instance == null) {
            instance = new ServiceType();
        }
        return instance;
    }
    
    
   
    public ArrayList<TypeProduit> parseTypes(String jsonText){
        try {
            types=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                TypeProduit tp = new TypeProduit();
                
                float id=Float.parseFloat(obj.get("idTp").toString());
                tp.setId_TP((int)id);
                tp.setLibelle_TP(obj.get("libelleTp").toString());
                
                 
                types.add(tp);
            }
            
            
        } catch (IOException ex) {
            
        }
        return types;
    }
     
     
     public ArrayList<TypeProduit> getAllTypes(){
       
         
         String url = Statics.BASE_URL+"/showTypeProdMobile";      
        req.setUrl(url);
        req.setPost(false);
        
   
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                types = parseTypes(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return types;
    }
    
    
    
    
    
    
}
