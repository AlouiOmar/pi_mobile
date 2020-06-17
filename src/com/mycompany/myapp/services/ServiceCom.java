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
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class ServiceCom {
    
     public ArrayList<Commentaire> tasks;
    
    public static ServiceCom instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCom() {
         req = new ConnectionRequest();
    }

    public static ServiceCom getInstance() {
        if (instance == null) {
            instance = new ServiceCom();
        }
        return instance;
    }

    
        public ArrayList<Commentaire> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");


            for(Map<String,Object> obj : list){
                Commentaire Co = new Commentaire();
              //  float id = Float.parseFloat(obj.get("id").toString());
                //t.getIdcour(obj.get("idvelo").toString());
              // t.setDate(obj.get("date").toString());
               
                Co.setId((int)Float.parseFloat(obj.get("id").toString()));
            //  t.setId(obj.get("id").hashCode());
              // t.setCreator((int)Float.parseFloat(obj.get("creator").toString()));
            //  Co.setPost_id((int)Float.parseFloat(obj.get("post_id").toString()));
              //Co.setUser_id((int)Float.parseFloat(obj.get("user_id").toString()));
              Co.setContent(obj.get("content").toString());
              
             //  t.setPostdate(obj.get("postdate").toString());
            //  Co.setRating((int)Float.parseFloat(obj.get("rating").toString()));
             //  t.setBlocke((int)Float.parseFloat(obj.get("blocke").toString()));
               
               
                //t.setDate(obj.get("date").toString());
                
                tasks.add(Co);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    
}

         public ArrayList<Commentaire> getAllTasks(Post P){
        String url = Statics.BASE_URL +"/detailedpost1/"+P.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
         /*
public boolean addEv(Post e) {
        String url = Statics.BASE_URL + "/addpost" +"/"+"86"+"/"+e.getTitle()+"/"+e.getDescription()+"/"+e.getPhoto()+"/"+e.getBlocke()+"/"+e.getRating();
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

 public void Supprimer(int id) {
           // Evenement e= new Evenement();
        ConnectionRequest req = new ConnectionRequest();
         // String url = Statics.BASE_URL ;
        //cnx.setUrl("http://localhost/PIDEV/web/app_dev.php/api/eventsupp"+"/"+e.getIdEvenement());
    String url = Statics.BASE_URL+"/deletepost/"+id; 
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            System.out.println(req.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
}
*/
         
public boolean addCo(Commentaire e,int c,fos_user Us) {
       String url = Statics.BASE_URL + "/addcomment/"+Us.getId()+"/" +c+"/"+e.getContent();
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


         public boolean SuppCom(int e) {
           // Post e= new Post();
        ConnectionRequest req = new ConnectionRequest();
         // String url = Statics.BASE_URL ;
        //cnx.setUrl("http://localhost/PIDEV/web/app_dev.php/blog/deletepost"+"/"+e.getId);
    String url = Statics.BASE_URL+"/deletecomment/"+e; 
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            System.out.println(req.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
       
}

public boolean ModCom(Commentaire e) {
        String url = Statics.BASE_URL + "/updatecomment" +"/"+e.getId()+"/"+e.getContent();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    
}
