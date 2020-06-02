/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.velo.util.Vars;
import com.velo.entities.Annonce;
import com.velo.entities.Stat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aloui Omar
 */
public class AnnonceService {
    public static AnnonceService instance=null;
    public boolean resultOK;
    private ConnectionRequest con;

    private AnnonceService() {
         con = new ConnectionRequest();
    }

    public static AnnonceService getInstance() {
        
        if (instance == null) {
            instance = new AnnonceService();
        }
        return instance;
    }
    
    public ArrayList<Annonce> getListAnnonces(String json) {

        ArrayList<Annonce> listAnnonces = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> annonces = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) annonces.get("root");
            System.out.println(json);
            for (Map<String, Object> obj : list) {
                Annonce a = new Annonce();

                a.setIda((int) Float.parseFloat(obj.get("ida").toString()));
                a.setCategorie(obj.get("categorie").toString());
                a.setTitre(obj.get("titre").toString());
                a.setDescription(obj.get("description").toString());
                a.setPhoto(obj.get("photo").toString());
                String sDate=obj.get("date").toString();
                String sDatep=obj.get("datep").toString();
                try {
                    Date date=new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
                    Date datep=new SimpleDateFormat("dd/MM/yyyy").parse(sDatep);
                    a.setDate(date);
                    a.setDatep(datep);
                } catch (ParseException ex) {
                    System.out.println("ex date"+ex);
                }
                
                if (obj.get("active") == "true") {
                            a.setActive(true);
                        } else {
                            a.setActive(false);
                        }
                a.setType(obj.get("type").toString());
                if((obj.get("categorie").toString()).equals("Vélo")){
                a.setTypevelo(obj.get("type_velo").toString());
                a.setCouleur(obj.get("couleur").toString());
                }
                a.setGouvernorat(obj.get("gouvernorat").toString());
                a.setPrix(Double.parseDouble(obj.get("prix").toString()));
                a.setIdu((int) Float.parseFloat(obj.get("idu").toString()));
                listAnnonces.add(a);

            }

        } catch (IOException ex) {
        }

        return listAnnonces;

    }
    
    ArrayList<Annonce> listAnnonces = new ArrayList<>();
    ArrayList<Stat> listStat = new ArrayList<>();
     public ArrayList<Annonce> getAnnonces() {
         con.removeAllArguments();
         con.setPost(false);
//                  con = new ConnectionRequest();

//        ConnectionRequest con = new ConnectionRequest();
        con.removeAllArguments();
        if (Vars.current_choice == 1) {
            con.setUrl(Vars.base_url+"/annonce/listm");
//            con.addArgument("user", String.valueOf(Vars.current_user.getId()));
        }
        else  if (Vars.current_choice == 2) {
                con.setUrl(Vars.base_url+"/annonce/mesannoncem/");
                con.addArgument("user", String.valueOf(Vars.current_user.getId()));
            } 
        else if (Vars.current_choice == 3) {
                    con.setUrl(Vars.base_url+"/annonce/recherche/");
                    con.addArgument("recherche", String.valueOf(Vars.current_search));
                } 
        else  if (Vars.current_choice == 4) {
                        con.setUrl(Vars.base_url+"/annonce/listsignm");
//                        con.addArgument("categorie", Vars.current_type);
//                        con.addArgument("id", String.valueOf(Vars.current_user.getId()));

                    } 
        else  if (Vars.current_choice == 5) {
                        con.setUrl(Vars.base_url+"/annonce/listcat/");
                        con.addArgument("categorie", Vars.current_type);
//                        con.addArgument("id", String.valueOf(Vars.current_user.getId()));

                    } 
        else  if (Vars.current_choice == 6) {
                        con.setUrl(Vars.base_url+"/annonce/listprix/");
                        con.addArgument("choix", Vars.current_type);
//                        con.addArgument("id", String.valueOf(Vars.current_user.getId()));

                    }
        else  if (Vars.current_choice == 7) {
                        con.setUrl(Vars.base_url+"/annonce/listdate");
//                        con.addArgument("choix", Vars.current_type);
//                        con.addArgument("id", String.valueOf(Vars.current_user.getId()));

                    }
        else  if (Vars.current_choice == 8) {
                        con.setUrl(Vars.base_url+"/annonce/ttlistm");
//                        con.addArgument("choix", Vars.current_type);
//                        con.addArgument("id", String.valueOf(Vars.current_user.getId()));

                    }
//        else {
//                        con.setUrl(Vars.base_url+"/Deal/Recherche_Ville_Dealm/");
//                        con.addArgument("ville", Vars.current_type);
////                        con.addArgument("id", String.valueOf(Vars.current_user.getId()));
//                    }
//                }
//            }
//        }
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceService ds = new AnnonceService();
                listAnnonces = ds.getListAnnonces(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnnonces;
    }
     
      public void AjouterAnnonce(Annonce a) {
//        ConnectionRequest con = new ConnectionRequest();
        con.removeAllArguments();
        con.setPost(true);
        con.setUrl(Vars.base_url+"/annonce/ajoutm/");
//        con.addArgument("ens", String.valueOf(x));
        con.addArgument("categorie", a.getCategorie());
        con.addArgument("titre", String.valueOf(a.getTitre()));
        con.addArgument("description", a.getDescription());
        con.addArgument("photo", a.getPhoto());
        con.addArgument("date", new SimpleDateFormat("dd-MM-yyyy").format(a.getDate()));
        con.addArgument("active", String.valueOf(a.isActive()));
        con.addArgument("prix", String.valueOf(a.getPrix()));
        con.addArgument("type", String.valueOf(a.getType()));
        if(a.getCategorie().equals("Vélo")){
        con.addArgument("type_velo", String.valueOf(a.getTypevelo()));
        con.addArgument("couleur", String.valueOf(a.getCouleur()));

        }
        con.addArgument("gouvernorat", String.valueOf(a.getGouvernorat()));
        con.addArgument("idu", String.valueOf(a.getIdu()));
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("http response: "+con.getResponseCode());
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
      
    public void SupprimerAnnonce(int ida) {
//        ConnectionRequest con = new ConnectionRequest();
        con.removeAllArguments();
        con.setPost(true);
        con.setUrl(Vars.base_url+"/annonce/supprimerm/");
        con.addArgument("ida", String.valueOf(ida));
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("http response: "+con.getResponseCode());
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
    public void SignalAnnonce(int id,String cause) {
//        ConnectionRequest con = new ConnectionRequest();
                con.removeAllArguments();

        con.setPost(true);
        con.setUrl(Vars.base_url+"/annonce/reportm/");
        con.addArgument("id", String.valueOf(id));
        con.addArgument("cause", String.valueOf(cause));
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("http response: "+con.getResponseCode());
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
    public void ModifierAnnonce(Annonce a) {
//        ConnectionRequest con = new ConnectionRequest();
        con.removeAllArguments();
        con.setPost(true);
        con.setUrl(Vars.base_url+"/annonce/modifierm/");
//        con.addArgument("ens", String.valueOf(x));
        con.addArgument("ida", String.valueOf(a.getIda()));
        con.addArgument("categorie", a.getCategorie());
        con.addArgument("titre", String.valueOf(a.getTitre()));
        con.addArgument("description", a.getDescription());
        con.addArgument("photo", a.getPhoto());
        con.addArgument("date", new SimpleDateFormat("dd-MM-yyyy").format(a.getDate()));
        con.addArgument("active", String.valueOf(a.isActive()));
        con.addArgument("prix", String.valueOf(a.getPrix()));
        con.addArgument("type", String.valueOf(a.getType()));
        if(a.getCategorie().equals("Vélo")){
        con.addArgument("type_velo", String.valueOf(a.getTypevelo()));
        con.addArgument("couleur", String.valueOf(a.getCouleur()));

        }
        con.addArgument("gouvernorat", String.valueOf(a.getGouvernorat()));
        con.addArgument("idu", String.valueOf(a.getIdu()));
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("http response: "+con.getResponseCode());
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
//     ArrayList<Annonce> statGouv = new ArrayList<>();
     public ArrayList<Stat> getStatGouv() {
//        ConnectionRequest con = new ConnectionRequest();
        con.removeAllArguments();
        con.setUrl(Vars.base_url+"/annonce/statgm");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceService ds = new AnnonceService();
                listStat = ds.getListStatGouv(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listStat;
    }
     
    public ArrayList<Stat> getStatType() {
//        ConnectionRequest con = new ConnectionRequest();
        con.removeAllArguments();
        con.setPost(false);
        con.setUrl(Vars.base_url+"/annonce/statty");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceService ds = new AnnonceService();
                listStat = ds.getListStatType(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listStat;
    }
     public ArrayList<Stat> getStatSignCat() {
//        ConnectionRequest con = new ConnectionRequest();
        con.removeAllArguments();
        con.setPost(false);
        con.setUrl(Vars.base_url+"/annonce/statsc");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceService ds = new AnnonceService();
                listStat = ds.getListStatSignCat(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listStat;
    }
     
    public ArrayList<Stat> getStatSignCause() {
//        ConnectionRequest con = new ConnectionRequest();
        con.removeAllArguments();    
        con.setPost(false);
        con.setUrl(Vars.base_url+"/annonce/statscau");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceService ds = new AnnonceService();
                listStat = ds.getListStatSignCause(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listStat;
    }
    public ArrayList<Stat> getListStatGouv(String json) {

        ArrayList<Stat> statGouv = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> annonces = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) annonces.get("root");

            for (Map<String, Object> obj : list) {
                Stat a = new Stat();

                a.setNbr((int) Float.parseFloat(obj.get("nombre").toString()));
                a.setNom(obj.get("gouvernorat").toString());
                statGouv.add(a);

            }

        } catch (IOException ex) {
        }

        return statGouv;

    }
    public ArrayList<Stat> getListStatType(String json) {

        ArrayList<Stat> statGouv = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> annonces = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) annonces.get("root");

            for (Map<String, Object> obj : list) {
                Stat a = new Stat();

                a.setNbr((int) Float.parseFloat(obj.get("nombre").toString()));
                a.setNom(obj.get("type").toString());
                statGouv.add(a);

            }

        } catch (IOException ex) {
        }

        return statGouv;

    }
    
    public ArrayList<Stat> getListStatSignCat(String json) {

        ArrayList<Stat> statGouv = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> annonces = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) annonces.get("root");

            for (Map<String, Object> obj : list) {
                Stat a = new Stat();

                a.setNbr((int) Float.parseFloat(obj.get("nombre").toString()));
                a.setNom(obj.get("categorie").toString());
                statGouv.add(a);

            }

        } catch (IOException ex) {
        }

        return statGouv;

    }
    
    public ArrayList<Stat> getListStatSignCause(String json) {

        ArrayList<Stat> statGouv = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> annonces = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) annonces.get("root");

            for (Map<String, Object> obj : list) {
                Stat a = new Stat();

                a.setNbr((int) Float.parseFloat(obj.get("nombre").toString()));
                a.setNom(obj.get("cause").toString());
                statGouv.add(a);

            }

        } catch (IOException ex) {
        }

        return statGouv;

    }
    
    public ArrayList<Stat> getCause(int id) {
//        ConnectionRequest con = new ConnectionRequest();
        con.removeAllArguments();
        con.setPost(false);
        con.setUrl(Vars.base_url+"/annonce/cause/");
        con.addArgument("id",Integer.toString(id));
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceService ds = new AnnonceService();
                listStat = ds.getSignCause(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listStat;
    }
    
    public ArrayList<Stat> getSignCause(String json) {

        ArrayList<Stat> statGouv = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> annonces = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) annonces.get("root");

            for (Map<String, Object> obj : list) {
                Stat a = new Stat();

                a.setNbr((int) Float.parseFloat(obj.get("nombre").toString()));
                a.setNom(obj.get("cause").toString());
                statGouv.add(a);

            }

        } catch (IOException ex) {
        }

        return statGouv;

    }
    
}
