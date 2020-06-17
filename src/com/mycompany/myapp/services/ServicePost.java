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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Calendar;
import com.codename1.ui.Display;
import static com.codename1.ui.events.ActionEvent.Type.Calendar;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.DateUtil;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.Vars;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Map;

/**
 *
 * @author USER
 */
public class ServicePost {
    
  public ArrayList<Post> posts;
    
    public static ServicePost instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicePost() {
         req = new ConnectionRequest();
    }

    public static ServicePost getInstance() {
        
        if (instance == null) {
            instance = new ServicePost();
        }
        return instance;
    }

    public boolean addPost(Post t,fos_user u)
    {
//         String url = Statics.BASE_URL + "/event/events/Ajout?NomEvent=" + e.getNomEvent()+ "&CategorieEvent=" + e.getCategorieEvent()+ "&NbrPlaceDispo=" + e.getNbrPlaceDispo()+"&Description="+e.getDescription()+"&Adresse="+e.getAdresse();
//        String url = Statics.BASE_URL + "/club/Ajout?NomClub=" + c.getNomClub()+ "&ActiviteClub=" + c.getActiviteClub()+ "&Effectif=" + c.getEffectif();
   //  String url = Statics.BASE_URL + "/posts/new?description=" + t.getDescription()+ "&title=" + t.getTitle()+ "&rating=" + t.getRating()+ "&photo=" + t.getPhoto();
       String url = Statics.BASE_URL + "/posts/create?description=" + t.getDescription()
               +"&title="+t.getTitle()
               +"&rating="+t.getRating()
               +"&creator="+u.getId()
               + "&photo="+t.getPhoto();
//    String url = Statics.BASE_URL + "/posts/new?description="+t.getDescription()+"&title="+t.getTitle()+"&rating="+t.getRating();
     req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() 
        {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
   // Message m = new Message( "post ajouté: son description est  "+t.getDescription());
  
      System.out.println(" email de utilisateur " + u.getEmail());

     //  Display.getInstance().sendMessage(new String[] {u.getEmail()},"mail d'ajout ", m);

        return resultOK;   
    }
    
    
    
    public ArrayList<Post> parsePosts(String jsonText){
        try {
            posts=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Post t = new Post();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
               t.setRating(((int)Float.parseFloat(obj.get("rating").toString())));
                t.setDescription(obj.get("description").toString());
             //   t.setPostdate(obj.get("postdate").toString());
                t.setTitle(obj.get("title").toString());
              //    float idcreator = Float.parseFloat(obj.get("creator").toString());
               // t.setCreator((int)idcreator);
               
       
               //  t.setNbrpost(((int)Float.parseFloat(obj.get("nbrpost").toString())));
                t.setPhoto(obj.get("photo").toString());
//                    
//    SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
//    String datetime = dateformat.format(obj.get("postdate").toString());
//    System.out.println(datetime);
//               t.setPostdate(datetime.toString());
            posts.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return posts;
    }
    
    
     public ArrayList<Post> getAllPosts(){
        String url = Statics.BASE_URL+"/posts/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                posts = parsePosts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return posts;
    }
     
     public boolean modifier(Post ev) {
         
       String url = Statics.BASE_URL+"/posts/update/"+ev.getId()+"?title=" + ev.getTitle()+"&description="+ev.getDescription()+"&rating="+ev.getRating();
   //    String url = Statics.BASE_URL +"/posts/update/"+e.getId()+"/"+e.getTitle()+"/"+e.getDescription()+"/"+e.getRating();
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
     
     
       public boolean deletePost(Post ev) {
        String url = Statics.BASE_URL + "/posts/delete/"+ev.getId();
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
       
       
      public ArrayList<Post> getRech(String ch){
        String url = Statics.BASE_URL+"/posts/chercherpost/"+ch;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                posts = parsePosts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return posts;
    }
    
    
    
    
}
