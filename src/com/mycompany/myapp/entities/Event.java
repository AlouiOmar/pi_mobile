/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.ArrayList;

/**
 *
 * @author Administrateur
 */
public class Event {
    private String titre,dateE,Description,region,nameC;
    private int idE,nbplaces;
    private Fos_user creator;
    private ArrayList<Fos_user> participants;

    public Event(String titre, String dateE, String Description, String region, String nameC, int idE, int nbplaces, Fos_user creator, ArrayList<Fos_user> participants) {
        this.titre = titre;
        this.dateE = dateE;
        this.Description = Description;
        this.region = region;
        this.nameC = nameC;
        this.idE = idE;
        this.nbplaces = nbplaces;
        this.creator = creator;
        this.participants = participants;
    }

 


    
    
    public Event(int idE,String titre, String dateE, String Description, String region, String nameC, int nbplaces) {
        this.idE=idE;
        this.titre = titre;
        this.dateE = dateE;
        this.Description = Description;
        this.region = region;
        this.nameC = nameC;
        this.nbplaces = nbplaces;
    }

    public Event(String titre, String dateE, String Description, String region, String nameC, int nbplaces) {
        this.titre = titre;
        this.dateE = dateE;
        this.Description = Description;
        this.region = region;
        this.nameC = nameC;
        this.nbplaces = nbplaces;
    }

    public Event(int idE) {
        this.idE = idE;
    }

    public Event() {
    }

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDateE() {
        return dateE;
    }

    public void setDateE(String dateE) {
        this.dateE = dateE;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public int getNbplaces() {
        return nbplaces;
    }

    public void setNbplaces(int nbplaces) {
        this.nbplaces = nbplaces;
    }

    public Fos_user getCreator() {
        return creator;
    }

    public void setCreator(Fos_user creator) {
        this.creator = creator;
    }

    public ArrayList<Fos_user> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Fos_user> participants) {
        this.participants = participants;
    }




    @Override
    public String toString() {
        return "Event{" + "titre=" + titre + ", dateE=" + dateE + ", Description=" + Description + ", region=" + region + ", nameC=" + nameC + ", idE=" + idE + ", nbplaces=" + nbplaces + ", creator=" + creator + '}';
    }


    
}