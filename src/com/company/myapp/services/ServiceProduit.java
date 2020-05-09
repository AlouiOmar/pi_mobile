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
import com.company.myapp.entities.Produit;
import com.company.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Raef
 */
public class ServiceProduit {
    
     public ArrayList<Produit> produits;
    
    public static ServiceProduit instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceProduit() {
         req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }
    
     public boolean addProduit(Produit p,String tp) {
        String url = Statics.BASE_URL + "/AddMobile/" + p.getId_P()
                + "/" + p.getNom_P()
                + "/" + p.getMarque_P()
                + "/" +p.getCategorie_P()
                +"/"+ p.getCouleur_P()
                + "/" +p.getPrix_P()    
                + "/"+tp
                + "/" +p.getPhoto_P()
                + "/" +p.getTel();
        
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
     
     
     
     
     public boolean deleteProduit(Produit p) {
        String url = Statics.BASE_URL + "/deleteProdMobile/" + p.getId_P();
               
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
     
     
     
     
    
     
     public ArrayList<Produit> parseProduits(String jsonText){
        try {
            produits=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Produit p = new Produit();
                Map<String,Object> objType =(Map<String,Object>) obj.get("typeP");
                float idType=Float.parseFloat(objType.get("idTp").toString());
                int idTypeI=(int) idType;
                p.setType_P(idTypeI);
                float id=Float.parseFloat(obj.get("idP").toString());
                p.setId_P((int)id);
                p.setNom_P(obj.get("nomP").toString());
                p.setMarque_P(obj.get("marqueP").toString());
                p.setCategorie_P(obj.get("categorieP").toString());
                p.setCouleur_P(obj.get("couleurP").toString());
                p.setPrix_P(((float)Float.parseFloat(obj.get("prixP").toString())));
                p.setDate(obj.get("date").toString());
               
                p.setPhoto_P(obj.get("photoP").toString());
               p.setTel(((int)Float.parseFloat(obj.get("tel").toString())));
//             
//                 Map<String,Object> objuser =(Map<String,Object>) obj.get("userid");
//                float idUser=Float.parseFloat(objuser.get("id").toString());
//               int idUserI=(int) idUser;
//               p.setUserId(idUserI);
//                
                produits.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
        return produits;
    }
     
     
     public ArrayList<Produit> getAllProduits(){
       
         
         String url = Statics.BASE_URL+"/showProdMobile";      
        req.setUrl(url);
        req.setPost(false);
        
   
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseProduits(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
     
     
     
     public ArrayList<Produit> getHighProduits(){
       
         
         String url = Statics.BASE_URL+"/highPriceMobile";      
        req.setUrl(url);
        req.setPost(false);
        
   
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseProduits(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
      public ArrayList<Produit> getLowProduits(){
       
         
         String url = Statics.BASE_URL+"/lowPriceMobile";      
        req.setUrl(url);
        req.setPost(false);
        
   
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseProduits(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
     
    
     
      public ArrayList<Produit> getProduitSingle(Produit p){
       
         
         String url = Statics.BASE_URL+"/ShowMobileSingle/"+ p.getId_P();      
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
      
      
      
       public boolean UpdateProduit(Produit p) {
        String url = Statics.BASE_URL + "/updateProdMobile/" + p.getId_P()
                + "/" + p.getNom_P()
                + "/" + p.getMarque_P()
                + "/" +p.getCategorie_P()
                +"/"+ p.getCouleur_P()
                + "/" +p.getPrix_P()
                 + "/" +p.getTel();
               
        
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
 
    
       
       
       
        public boolean AddPanierProduit(Produit p) {
        String url = Statics.BASE_URL + "/panier/addPanierMobile/" + p.getId_P();
               
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
     
     
        
         public ArrayList<Produit> getProduitsPanierMobile(){
       
         
         String url = Statics.BASE_URL+"/panier/AffichePanierMobile";      
        req.setUrl(url);
        req.setPost(false);
        
   
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseProduits(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      
        return produits;
    }
     
      public boolean deleteProduitPanierMobile(Produit p) {
        String url = Statics.BASE_URL + "/panier/RemovePanierMobile/" + p.getId_P();
               
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
