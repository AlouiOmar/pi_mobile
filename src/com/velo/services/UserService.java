/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.services;

import com.velo.entities.User;
import com.velo.util.Vars;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author Aloui Omar
 */
public class UserService {
    
    public static UserService instance=null;
    public boolean resultOK;
    private ConnectionRequest con;

    private UserService() {
         con = new ConnectionRequest();
    }

    public static UserService getInstance() {
        
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }


    public void Login(String username, String password) {
//        ConnectionRequest con = new ConnectionRequest();
        con.setPost(true);
        con.setUrl(Vars.base_url+"/loginm/");
        con.addArgument("username", username);
        con.addArgument("password", password);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();
                if (json.compareTo("Failed") > 0) {
                    Map<String, Object> u;
                    try {
                        u = j.parseJSON(new CharArrayReader(json.toCharArray()));

                        Vars.current_user = new User((int) Float.parseFloat(u.get("id").toString()));
                        Vars.current_user.setUsername(u.get("username").toString());
                        Vars.current_user.setEmail(u.get("email").toString());
                        Vars.current_user.setPhoto(u.get("photo").toString());
                        Vars.current_user.setNom(u.get("nom").toString());
                        Vars.current_user.setPrenom(u.get("prenom").toString());
                        Vars.current_user.setTelephone(u.get("telephone").toString());
                        Vars.current_user.setRoles(u.get("roles").toString());
                        System.out.println("tel : "+u.get("telephone").toString());
                        System.out.println(Vars.current_user);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void Register(User user) {
//        ConnectionRequest con = new ConnectionRequest();
        con.setPost(true);
        con.setUrl(Vars.base_url+"/registerm/");
        con.addArgument("username", user.getUsername());
        con.addArgument("nom", user.getNom());
        con.addArgument("prenom", user.getPrenom());
        con.addArgument("email", user.getEmail());
        con.addArgument("telephone", user.getTelephone());
        con.addArgument("password", user.getPassword());
        con.addArgument("photo", user.getPhoto());
        con.addArgument("roles", "ROLE_USER");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();
                if (json.compareTo("Failed") > 0) {
                    Map<String, Object> u;
                    try {
                        u = j.parseJSON(new CharArrayReader(json.toCharArray()));

                        Vars.current_user=user;
                        Vars.current_user.setId((int) Float.parseFloat(u.get("id").toString()));
                        Vars.current_user.setRoles("ROLE_USER");
                        System.out.println(Vars.current_user);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
   /* public boolean Register() {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(true);
        con.setUrl(Vars.base_url+"/registerm/");
        con.addArgument("username", user.getUsername());
        con.addArgument("nom", user.getNom());
        con.addArgument("prenom", user.getPrenom());
        con.addArgument("email", user.getEmail());
        con.addArgument("telephone", user.getTelephone());
        con.addArgument("password", user.getPassword());
        con.addArgument("photo", user.getPhoto());
        con.addArgument("roles", "ROLE_USER");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();

                if (!json.equals("Failed")) {
                    Vars.current_user=user;
                    Map<String, Object> u;
                    try {
                        u = j.parseJSON(new CharArrayReader(json.toCharArray()));
                        
                        Vars.current_user.setId((int) Float.parseFloat(u.get("id").toString()));
                        Vars.unlock_newMarker=1;
//                        Vars.current_user.setEmail(u.get("email").toString());
                        System.out.println("account created "+Vars.unlock_newMarker);

                    } catch (IOException ex) {
                        System.out.println("erreur parsing user");
                    }
                }else{
                    return true;
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);

    }*/
}
