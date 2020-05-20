/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.entities;

import java.util.Date;

/**
 *
 * @author Aloui Omar
 */
public class Annonce {
    private int ida;
    private int idu;
    private String categorie;
    private String titre;
    private String description;
    private double prix;
    private String photo;
    private Date date;
    private Date datep;
    private boolean active;
    private String type;
    private String typevelo;
    private String couleur;
    private String gouvernorat;

    public Annonce() {
       
    }

    public Annonce(  String categorie, String titre, String description, double prix, String photo,Date date,String type) {
        
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.photo = photo;
        this.date=date;
        this.active=true;
        this.type = type;
    }

    public Annonce( String categorie, String titre, String description, double prix, String photo,Date date, String type, String typevelo, String couleur) {
        
        
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.photo = photo;
        this.date=date;
        this.active=true;
        this.type = type;
        this.typevelo = typevelo;
        this.couleur = couleur;
    }

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypevelo() {
        return typevelo;
    }

    public void setTypevelo(String typevelo) {
        this.typevelo = typevelo;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Date getDatep() {
        return datep;
    }

    public void setDatep(Date datep) {
        this.datep = datep;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

   

    @Override
    public String toString() {
        return "Annonce{" + "ida=" + ida + ", idu=" + idu + ", categorie=" + categorie + ", titre=" + titre + ", description=" + description + ", prix=" + prix + ", photo=" + photo + ", date=" + date + ", active=" + active + ", type=" + type + ", typevelo=" + typevelo + ", couleur=" + couleur + '}';
    }

   

    
    
}
