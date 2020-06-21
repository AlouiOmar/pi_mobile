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
import com.company.myapp.utils.BCrypt ;


/**
 *
 * @author Raef
 */
public class ServiceUser {
    private static Fos_user loggedUser = null;
    
    public static Fos_user getLoggedUser(){
        return loggedUser;
    }
    
    public static void setLoggedInUser(Fos_user loggedInUser) {
        ServiceUser.loggedUser = loggedInUser;
    }
    
    
    public ArrayList<Fos_user> users;
    
    public static ServiceUser instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceUser() {
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
               u.setPassword(obj.get("password").toString());
             
       
               
               
               
               u.setRoles(obj.get("roles").toString());
              
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
        
        
        
        
//        
//           public boolean LogoutMobile(Fos_user u) {
//      String url = Statics.BASE_URL + "/logout" ;
//               
//        req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//            NetworkManager.getInstance().addToQueueAndWait(req);
//       
//        return resultOK;
//    }
//        
     
    
    public ArrayList<Fos_user> getAllUsers(){
       
         
         String url = "http://127.0.0.1:8000"+"/showUserMobile";      
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
     
    
    
    
    
    
    public static boolean checkPassword(String password_plaintext, String stored_hash)
    {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2y$"))
        {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);
        return password_verified;
    }
    
    
    
    
      public static int recupererUser(String username, String password){
        int existe = 2;
        Map<String, Object> result;
        Fos_user loggedin = new Fos_user();
       
         String url = Statics.BASE_URL + "/getUser?username=" + username;
        ConnectionRequest request = new ConnectionRequest(url,false);
        NetworkManager.getInstance().addToQueueAndWait(request);
        JSONParser j = new JSONParser();
        String json = new String(request.getResponseData()) + "";
        try{
            result = j.parseJSON(new CharArrayReader(json.toCharArray()));
            if(result.isEmpty() ){
                System.out.println("user not found");
                existe = 1;
            }
            else{
                System.out.println("user correct, password correct");
                int id = (int)Double.parseDouble(result.get("id").toString());
                String db_pass  = (String)result.get("password");
                if(checkPassword(password, db_pass)){
                    loggedin.setId(id);
                    loggedin.setUsername(result.get("username").toString());
                    loggedin.setEmail(result.get("email").toString());
                    loggedUser = loggedin;
                    existe = 0;
                }
                else
                {
                    System.out.println("user correct, password incorrect");
                    existe = 2;
                } 
                
            }
            
        }
        catch (IOException ex) {
                ex.printStackTrace();
            }
        return existe;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
