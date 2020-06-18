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
public class Station {
    private int id;
    private String nom;
    private Float lattitude,longitude;

    public Station(int id, String nom, Float lattitude, Float longitude) {
        this.id = id;
        this.nom = nom;
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public Station() {
    }

    public Station(String nom, Float lattitude, Float longitude) {
        this.nom = nom;
        this.lattitude = lattitude;
        this.longitude = longitude;
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

    public Float getLattitude() {
        return lattitude;
    }

    public void setLattitude(Float lattitude) {
        this.lattitude = lattitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Station{" + "id=" + id + ", nom=" + nom + ", lattitude=" + lattitude + ", longitude=" + longitude + '}';
    }

   
    
    
}
