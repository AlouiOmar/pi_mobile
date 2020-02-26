/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement;
import evenement.*;

/**
 *
 * @author Administrateur
 */
public class Participation {
    int id_E;
    int id_U;
    String nom;
    String titre;

    public Participation(int id_E, int id_U, String nom, String titre) {
        this.id_E = id_E;
        this.id_U = id_U;
        this.nom = nom;
        this.titre = titre;
    }

    public Participation() {
    }

    @Override
    public String toString() {
        return "Participation{" + "id_E=" + id_E + ", id_U=" + id_U + ", nom=" + nom + ", titre=" + titre + '}';
    }

    public void setId_E(int id_E) {
        this.id_E = id_E;
    }

    public void setId_U(int id_U) {
        this.id_U = id_U;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getId_E() {
        return id_E;
    }

    public int getId_U() {
        return id_U;
    }

    public String getNom() {
        return nom;
    }

    public String getTitre() {
        return titre;
    }


    
    
}
