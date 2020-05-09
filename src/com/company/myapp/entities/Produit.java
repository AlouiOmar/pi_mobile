/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.myapp.entities;

/**
 *
 * @author Raef
 */
public class Produit {

    private int id_P;
    private String nom_P;
    private String marque_P;
    private String categorie_P;
    private String couleur_P;
    private float prix_P;
    private String date;
    private String photo_P;
    private int type_P;
    private int userId;
    private int tel;

    public Produit() {
    }

    public Produit(int id_P, String nom_P, String marque_P, String categorie_P, String couleur_P, float prix_P, String date, String photo_P, int type_P, int userId, int tel) {
        this.id_P = id_P;
        this.nom_P = nom_P;
        this.marque_P = marque_P;
        this.categorie_P = categorie_P;
        this.couleur_P = couleur_P;
        this.prix_P = prix_P;
        this.date = date;
        this.photo_P = photo_P;
        this.type_P = type_P;
        this.userId = userId;
        this.tel = tel;
    }

    
    
     public Produit(int id_P, String nom_P, String marque_P, String categorie_P, String couleur_P, float prix_P,int tel) {
        this.id_P = id_P;
        this.nom_P = nom_P;
        this.marque_P = marque_P;
        this.categorie_P = categorie_P;
        this.couleur_P = couleur_P;
        this.prix_P = prix_P;
        this.tel = tel;
    }
    
      public Produit(String nom_P, String marque_P, String categorie_P, String couleur_P, float prix_P,int tel) {
        
        this.nom_P = nom_P;
        this.marque_P = marque_P;
        this.categorie_P = categorie_P;
        this.couleur_P = couleur_P;
        this.prix_P = prix_P;
        this.tel = tel;
    }
    
    
    public Produit(String nom_P, String marque_P, String categorie_P, String couleur_P, float prix_P, String photo_P ,int tel) {
        this.nom_P = nom_P;
        this.marque_P = marque_P;
        this.categorie_P = categorie_P;
        this.couleur_P = couleur_P;
        this.prix_P = prix_P;
         this.photo_P = photo_P;
          this.tel = tel;

    }

    public Produit(String nom_P, String marque_P, String categorie_P, String couleur_P, float prix_P, String date, String photo_P) {
        this.nom_P = nom_P;
        this.marque_P = marque_P;
        this.categorie_P = categorie_P;
        this.couleur_P = couleur_P;
        this.prix_P = prix_P;
        this.date = date;
        this.photo_P = photo_P;

    }

    public int getId_P() {
        return id_P;
    }

    public void setId_P(int id_P) {
        this.id_P = id_P;
    }

    public String getNom_P() {
        return nom_P;
    }

    public void setNom_P(String nom_P) {
        this.nom_P = nom_P;
    }

    public String getMarque_P() {
        return marque_P;
    }

    public void setMarque_P(String marque_P) {
        this.marque_P = marque_P;
    }

    public String getCategorie_P() {
        return categorie_P;
    }

    public void setCategorie_P(String categorie_P) {
        this.categorie_P = categorie_P;
    }

    public String getCouleur_P() {
        return couleur_P;
    }

    public void setCouleur_P(String couleur_P) {
        this.couleur_P = couleur_P;
    }

    public float getPrix_P() {
        return prix_P;
    }

    public void setPrix_P(float prix_P) {
        this.prix_P = prix_P;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoto_P() {
        return photo_P;
    }

    public void setPhoto_P(String photo_P) {
        this.photo_P = photo_P;
    }

    public int getType_P() {
        return type_P;
    }

    public void setType_P(int type_P) {
        this.type_P = type_P;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_P=" + id_P + ", nom_P=" + nom_P + ", marque_P=" + marque_P + ", categorie_P=" + categorie_P + ", couleur_P=" + couleur_P + ", prix_P=" + prix_P + ", date=" + date + ", photo_P=" + photo_P + ", type_P=" + type_P + ", userId=" + userId + ", tel=" + tel + '}';
    }

}
