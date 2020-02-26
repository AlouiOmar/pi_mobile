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
public class Circuit {
    String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Circuit(String nom) {
        this.nom = nom;
    }

    public Circuit() {
    }

    @Override
    public String toString() {
        return "Circuit{" + "nom=" + nom + '}';
    }
    
}
