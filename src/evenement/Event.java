/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement;

import connection.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.activation.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Administrateur
 */
public class Event {
    int id_E;
    String titre;
    String date_E;
    String Description;
    String region ;
    String name_C;
//    String point_depart;
//    String point_arrivee;

    public Event(int id_E, String titre, String date_E, String Description, String region,String name_C) {
        this.id_E = id_E;
        this.titre = titre;
        this.date_E = date_E;
        this.Description = Description;
        this.region = region;
        this.name_C=name_C;
    }

    public int getId_E() {
        return id_E;
    }

    public String getTitre() {
        return titre;
    }

    public String getDate_E() {
        return date_E;
    }

    public String getDescription() {
        return Description;
    }

    public String getRegion() {
        return region;
    }

    public void setId_E(int id_E) {
        this.id_E = id_E;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDate_E(String date_E) {
        this.date_E = date_E;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getName_C() {
        return name_C;
    }

    public void setName_C(String name_C) {
        this.name_C = name_C;
    }

   
    public Event() {
    }

    @Override
    public String toString() {
        return "Event{" + "id_E=" + id_E + ", titre=" + titre + ", date_E=" + date_E + ", Description=" + Description + ", region=" + region + ", name_C=" + name_C + '}';
    }

    
}