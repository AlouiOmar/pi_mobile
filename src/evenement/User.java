/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement;

/**
 *
 * @author Administrateur
 */
public class User {
    int id_U;
    String nom;

    public User(int id_U, String nom) {
        this.id_U = id_U;
        this.nom = nom;
    }

    public User(int id_U) {
        this.id_U = id_U;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId_U() {
        return id_U;
    }

    public void setId_U(int id_U) {
        this.id_U = id_U;
    }

    public User() {
    }
    
    
}
