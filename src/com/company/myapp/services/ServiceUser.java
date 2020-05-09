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
import com.company.myapp.entities.Fos_user;
import com.company.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Raef
 */
public class ServiceUser {
    
    
    public ArrayList<Fos_user> users;
    
    public static ServiceUser instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceUser() {
         req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
    
    
    
    
    
      public ArrayList<Fos_user> parseUsers(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Fos_user u = new Fos_user();
               
                float id=Float.parseFloat(obj.get("id").toString());
                u.setId((int)id);
                
                
                
                u.setEmail(obj.get("email").toString());
                u.setNom(obj.get("nom").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setUsername(obj.get("username").toString());
               
                users.add(u);
            }
            
            
        } catch (IOException ex) {
            
        }
        return users;
    }
     
    
    
    
    
    
    
    
    
        public boolean LoginMobile(Fos_user u) {
        String url = Statics.BASE_URL + "/logMobile/" + u.getEmail();
               
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
     
    
    public ArrayList<Fos_user> getAllUsers(){
       
         
         String url = Statics.BASE_URL+"/showUserMobile";      
        req.setUrl(url);
        req.setPost(false);
        
   
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
     
    
    
    
    
}
