/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;




/**
 *
 * @author ezzedine
 */
public class Circuit {
    private int id,userId;
    private String nom,begin,pause,end,difficulty;
    private float distance,blat,blon,plat,plon,elat,elon;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
  

    public Circuit(int id, String nom, String begin, String pause, String end, float distance,  String difficulty) {
        this.id = id;
        this.nom = nom;
        this.begin = begin;
        this.pause = pause;
        this.end = end;
        this.distance = distance;
        this.difficulty = difficulty;
        
    }

    public Circuit(String nom, String begin, String pause, String end, String difficulty, float distance) {
        this.nom = nom;
        this.begin = begin;
        this.pause = pause;
        this.end = end;
        this.difficulty = difficulty;
        this.distance = distance;
    }

    public Circuit(int id, String nom, String begin, String pause, String end, String difficulty, float distance, float blat, float blon, float plat, float plon, float elat, float elon) {
        this.id = id;
        this.nom = nom;
        this.begin = begin;
        this.pause = pause;
        this.end = end;
        this.difficulty = difficulty;
        this.distance = distance;
        this.blat = blat;
        this.blon = blon;
        this.plat = plat;
        this.plon = plon;
        this.elat = elat;
        this.elon = elon;
    }

   

    

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    

    



    public Circuit() {
    }

    public float getBlat() {
        return blat;
    }

    public void setBlat(float blat) {
        this.blat = blat;
    }

    public float getBlon() {
        return blon;
    }

    public void setBlon(float blon) {
        this.blon = blon;
    }

    public float getPlat() {
        return plat;
    }

    public void setPlat(float plat) {
        this.plat = plat;
    }

    public float getPlon() {
        return plon;
    }

    public void setPlon(float plon) {
        this.plon = plon;
    }

    public float getElat() {
        return elat;
    }

    public void setElat(float elat) {
        this.elat = elat;
    }

    public float getElon() {
        return elon;
    }

    public void setElon(float elon) {
        this.elon = elon;
    }


  
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getPause() {
        return pause;
    }

    public void setPause(String pause) {
        this.pause = pause;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Circuit{" + "id=" + id +  ", nom=" + nom + ", begin=" + begin + ", pause=" + pause + ", end=" + end + ", difficulty=" + difficulty +  ", distance=" + distance +  '}';
    }

  

    

   

   
   
}
