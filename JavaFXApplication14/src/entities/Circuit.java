/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


/**
     *
     * @author ezzedine
 */
public class Circuit {
    private int id;
    private String nom,begin,pause,end,difficulty;
    private float distance;
    
  

    public Circuit(int id, String nom, String begin, String pause,  String end, float distance,  String difficulty) {
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

   

    

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    

    



    public Circuit() {
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