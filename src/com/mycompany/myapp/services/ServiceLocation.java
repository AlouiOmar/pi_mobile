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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Location;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nahawnd
 */
public class ServiceLocation {

    public ArrayList<Location> locations;
    public static ServiceLocation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceLocation() {
        req = new ConnectionRequest();
    }

    public static ServiceLocation getInstance() {
        if (instance == null) {
            instance = new ServiceLocation();
        }
        return instance;
    }

    public ArrayList<Location> getAllLocations(String json) {

        ArrayList<Location> listLocations = new ArrayList<>();
        try {

            JSONParser j = new JSONParser();

            Map<String, Object> locations = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) locations.get("root");
            System.out.println(json);
            for (Map<String, Object> obj : list) {
                Location l = new Location();

                l.setId((int) Float.parseFloat(obj.get("id").toString()));
                l.setTitre(obj.get("titre").toString());
                l.setLieu(obj.get("lieu").toString());
                l.setPrix(Float.parseFloat(obj.get("prix").toString()));
                l.setPhoto(obj.get("photo").toString());
                String sdateCreation = obj.get("date creation").toString();
                try {
                    Date dateCreation = new SimpleDateFormat("dd/MM/yyyy").parse(sdateCreation);
                    l.setDateCreation(dateCreation);

                } catch (ParseException ex) {
                    System.out.println("ex date" + ex);
                }

                l.setId((int) Float.parseFloat(obj.get("id").toString()));
                l.setTitre(obj.get("titre").toString());
                l.setLieu(obj.get("lieu").toString());
                l.setPrix(Float.parseFloat(obj.get("prix").toString()));
                // l.setIdU((int) Float.parseFloat(obj.get("idU").toString()));
                l.setUsername(obj.get("username").toString());
                listLocations.add(l);
            }

        } catch (IOException ex) {
        }

        return listLocations;

    }

    public ArrayList<Location> parseLocations(String jsonText) {
        try {
            locations = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
            c'est la clé définissant le tableau de tâches.*/
            Map<String, Object> locationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
             */
            List<Map<String, Object>> list = (List<Map<String, Object>>) locationsListJson.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                Location l = new Location();   //Création des tâches et récupération de leurs données
                float id = Float.parseFloat(obj.get("id").toString());
                l.setId((int) id);
                l.setTitre(obj.get("titre").toString());
                l.setLieu(obj.get("lieu").toString());
                l.setPrix((Float) Float.parseFloat(obj.get("prix").toString()));
                l.setPhoto(obj.get("photo").toString());
                req.addArgument("date creation", new SimpleDateFormat("dd-MM-yyyy").format(l.getDateCreation()));

                locations.add(l);     //Ajouter la tâche extraite de la réponse Json à la liste
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return locations;
    }

    public ArrayList<Location> getAllLocations() {
        String url = Statics.BASE_URL2 + "/api/locations/all/";
        req.setUrl(url);
//        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                locations = parseLocations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return locations;
    }

    public boolean addLocation(Location l) {
        req.removeAllArguments();
        req.setPost(true);

        req.setUrl(Statics.BASE_URL2 + "/api/location/new"
                + "/" + l.getTitre()
                + "/" + l.getLieu()
                + "/" + l.getPrix()
                + "/" + l.getPhoto()
                + "/" + l.getDateCreation() //création de l'URL
                + "/" + l.getUsername());
        
        req.addArgument("titre", String.valueOf(l.getTitre()));
        req.addArgument("Lieu", String.valueOf(l.getLieu()));
        req.addArgument("prix", String.valueOf(l.getPrix()));
        req.addArgument("Photo", String.valueOf(l.getPhoto()));
        req.addArgument("date creation", new SimpleDateFormat("dd-MM-yyyy").format(l.getDateCreation()));
        req.addArgument("Username", String.valueOf(l.getUsername()));

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean updateLocation(Location l, Resources res) {
        req.removeAllArguments();
        req.setPost(true);
        
        req.setUrl(Statics.BASE_URL2 + " /api/location/update/");
        req.addArgument("id", String.valueOf(l.getId()));
        req.addArgument("titre", String.valueOf(l.getTitre()));
        req.addArgument("lieu", String.valueOf(l.getLieu()));
        req.addArgument("prix", String.valueOf(l.getPrix()));
        req.addArgument("photo", l.getPhoto());
        req.addArgument("date creation", new SimpleDateFormat("dd-MM-yyyy").format(l.getDateCreation()));

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                Dialog.show("Succés", "Publication modifié", "ok", null);
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//appel asynchrone
          return resultOK;
     
    }

    public void DeleteLocation(int id) {
          req.removeAllArguments();
        req.setPost(true);
        String url = "/api/location/delete?id=" + id;
        System.err.println(url);
        req.setUrl(url);

        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            Dialog.show("Succés", "Publication Supprimer", "ok", null);
            System.out.println(str);

        });
//        NetworkManager.getInstance().addToQueueAndWait(req); //appel asynchrone

    }

    public ArrayList<Location> findLocation(int id) {
//        String url = Statics.BASE_URL2 + "/api/location/show/" + id;
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                locations = parseLocations(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
////        NetworkManager.getInstance().addToQueueAndWait(req);
//
//        return locations;
    

    
    
      req.removeAllArguments();
        req.setPost(false);
        req.setUrl(Statics.BASE_URL2 + "/api/location/show/" + id);
        req.addArgument("id",Integer.toString(id));
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceLocation ds = new ServiceLocation();
              locations = parseLocations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
        return locations;
    
    }
    public ArrayList<Location> getLocationsByTitre(String tittre) {
        String url = Statics.BASE_URL2 + "/api/location/show/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                locations = parseLocations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return locations;
    }

    public void EnvoiNotification(int id) {

    }


}
