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
public class TypeProduit {
   
    private int id_TP;
    private String libelle_TP;
    
    
    
    
    
    public TypeProduit(){}

    public TypeProduit(int id_TP, String libelle_TP) {
        this.id_TP = id_TP;
        this.libelle_TP = libelle_TP;
    }

    public int getId_TP() {
        return id_TP;
    }

    public void setId_TP(int id_TP) {
        this.id_TP = id_TP;
    }

    public String getLibelle_TP() {
        return libelle_TP;
    }

    public void setLibelle_TP(String libelle_TP) {
        this.libelle_TP = libelle_TP;
    }

    @Override
    public String toString() {
        return "TypeProduit{" + "id_TP=" + id_TP + ", libelle_TP=" + libelle_TP + '}';
    }
    
    
    
    
}
