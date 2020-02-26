/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement;
import eventsa.*;
import connection.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;

/**
 *
 * @author Administrateur
 */
public class EventServices {
        Statement stm;

    public void AjouterEvent(Event e) throws SQLException {
        
        try {
            
            
            String req="INSERT INTO event (titre, date_E, Description, region, name_C) VALUES (?,?,?,?,?)";
            PreparedStatement ps1= Connection.getInstance().getConnection().prepareStatement(req);
            ps1.setString(1,e.getTitre());
            ps1.setString(2,e.getDate_E());
            ps1.setString(3,e.getDescription());
            ps1.setString(4,e.getRegion());
            ps1.setString(5,e.getName_C());
//            ps1.setString(5,e.getPoint_depart());
//            ps1.setString(6,e.getPoint_arrivee());
            ps1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
    
    public void modifierEvent(Event e) throws SQLException{
       try {

            String req="UPDATE event SET titre = ?, date_E = ?, Description = ?, region = ? where titre=?";
            PreparedStatement ps2= Connection.getInstance().getConnection().prepareStatement(req);
            
            ps2.setString(1,e.getTitre());
            ps2.setString(2,e.getDate_E());
            ps2.setString(3,e.getDescription());
            ps2.setString(4,e.getRegion());
                        ps2.setString(5,e.getTitre());
//            ps2.setString(5,e.getPoint_depart());
//            ps2.setString(6,e.getPoint_arrivee());
            
   
            ps2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   public void supprimerEvent(Event e) throws SQLException {

        String req = "DELETE FROM event WHERE titre =?";
            PreparedStatement ps3=Connection.getInstance().getConnection().prepareStatement(req);
                        ps3.setString(1,e.getTitre());

        ps3.executeUpdate();

    }
//    public void supprimerEvent(Event e) throws SQLException{
//        try {
//            String req="DELETE FROM event WHERE titre=?";
//            PreparedStatement ps3=Connection.getInstance().getConnection().prepareStatement(req);
//            ps3.setInt(1,e.getId_E());
//            ps3.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }        
    public Event getEvent(String title) throws SQLException{
        Event e=new Event();
        try {
            String req="SELECT * FROM event where titre='"+title+"'";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
            e.setTitre(rs.getString("titre"));
            e.setDate_E(rs.getString("Date_E"));
            e.setDescription(rs.getString("description"));
            e.setRegion(rs.getString("region"));
//            e.setPoint_depart(rs.getString("point_depart"));
//            e.setPoint_arrivee(rs.getString("point_arrivee"));           
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }
    public List<Event> afficherEvents() throws SQLException {
        List<Event> l = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM event";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Event e = new Event();
            e.setId_E(rs.getInt("id_E"));
            e.setTitre(rs.getString("titre"));
            e.setDate_E(rs.getString("Date_E"));
            e.setDescription(rs.getString("description"));
            e.setRegion(rs.getString("Region"));
            e.setName_C(rs.getString("name_C"));

//            e.setPoint_depart(rs.getString("Point_Depart"));
//            e.setPoint_arrivee(rs.getString("Point_Arrivee"));
            
           
            
            
                l.add(e);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
  public void Participer(Event e,User u) throws SQLException {
        
        try {
            
            
            String req="INSERT INTO Participation (id_E,id_U,nom, titre) VALUES (?,?,?,?)";
            PreparedStatement ps1= Connection.getInstance().getConnection().prepareStatement(req);
            ps1.setInt(1, e.getId_E());
            ps1.setInt(2, u.getId_U());
            ps1.setString(3,u.getNom());
            ps1.setString(4,e.getTitre());
//            ps1.setString(5,e.getPoint_depart());
//            ps1.setString(6,e.getPoint_arrivee());
            ps1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }}
    public List<Participation> afficherParticipation(String titre) throws SQLException {
        List<Participation> l = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT nom FROM participation where titre='"+titre+"'";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Participation p=new Participation();
            
            p.setNom(rs.getString("nom"));
           
            
            
                l.add(p);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
//    public List<Circuit> ComboBoxCircuits() throws SQLException {
//        List<Circuit> l = new ArrayList<>();
//       // UserServices uu=new UserServices();
//       
//        try {
//            String req="SELECT name FROM circuit";
//            Statement s=Connection.getInstance().getConnection().createStatement();
//            ResultSet rs=s.executeQuery(req);
//            while(rs.next())
//            {
//                
//            Circuit c = new Circuit();
//            c.setNom(rs.getString("name"));
//            
////            e.setPoint_depart(rs.getString("Point_Depart"));
////            e.setPoint_arrivee(rs.getString("Point_Arrivee"));
//            
//           
//            
//            
//                l.add(c);
//            s.close();
//            rs.close();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return l;
//    }
}
