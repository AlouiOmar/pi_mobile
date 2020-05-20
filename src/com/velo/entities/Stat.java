/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.entities;

/**
 *
 * @author Hamrouni
 */
public class Stat {
    private int nbr;
    private String nom;

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Stat() {
    }
    
    @Override
    public String toString() {
        return "Stat{" + "nbr=" + nbr + ", nom=" + nom + '}';
    }

    public Stat(int nbr, String nom) {
        this.nbr = nbr;
        this.nom = nom;
    }
    
    
}
