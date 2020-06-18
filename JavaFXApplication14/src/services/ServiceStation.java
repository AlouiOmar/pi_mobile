/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Station;
import interfaces.IServiceStation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utile.MaConnection;

/**
 *
 * @author ezzedine
 */
public class ServiceStation implements IServiceStation {

    Connection cnx;

    public ServiceStation() {
        cnx = MaConnection.getInstance().getConnection();

    }

    @Override
    public void addStation(Station s) throws SQLException {

        Statement stm = cnx.createStatement();
        String query = "INSERT INTO `station` "
                + "(`id`, `name`, `lat`,`lon`)"
                + " VALUES (NULL, '" + s.getNom()+ "',"
                + " '" + s.getLattitude()+ "',"
                + " '" + s.getLongitude()+ "')";
        stm.executeUpdate(query);
        System.out.println("iii");
    }

    @Override
    public void deleteStation(Station s) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "DELETE FROM `station` WHERE `id`=" + s.getId();
        stm.executeUpdate(query);
        System.out.println("iii");
    }

    /**
     *
     * @return @throws SQLException
     */
    @Override
    public  List<Station> getStations() throws SQLException {
        String querry = "SELECT * FROM `station` ";
        com.mysql.jdbc.Statement stm = (com.mysql.jdbc.Statement) cnx.createStatement();

        ResultSet rs = stm.executeQuery(querry);

        List<Station> stations = new ArrayList<>();
        while (rs.next()) {
            Station s = new Station();
            s.setId(rs.getInt("id"));
            s.setNom(rs.getString(2));
            s.setLattitude(rs.getFloat(3));
            s.setLongitude(rs.getFloat(4));
            // System.out.println(s);
            stations.add(s);
            //personnes.stream().forEach(System.out::println);
            
        }
       return stations;
    }
    
    @Override
    public void updateStation(Station s) throws SQLException {

        Statement stm = cnx.createStatement();
        String query;
        query = "UPDATE `station` SET `name` = '"+s.getNom()+"', `lat` = '"+s.getLattitude()+
                "', `lon` = '"+s.getLongitude()+"' WHERE `station`.`id` = "+s.getId()+"";
        stm.executeUpdate(query);
        System.out.println("iii");
    }
    @Override
    public int getIdStationByName(String name) throws SQLException{
    String querry="SELECT `id` FROM `station` WHERE `nom`='"+name+"'";
    Statement stm=cnx.createStatement();
    ResultSet rs=stm.executeQuery(querry);
    int i=0;
        while (rs.next()) {            
            i=rs.getInt(1);
        }
    return i;
    
    }

    @Override
    public String getNameStationById(int id) throws SQLException {
       String querry="SELECT `nom` FROM `station` WHERE `id`="+id;
       Statement stm=cnx.createStatement();
       ResultSet rs=stm.executeQuery(querry);
       String name="";
        while (rs.next()) {            
            name =rs.getString(1);
        }
    return name;
    }
    
}
