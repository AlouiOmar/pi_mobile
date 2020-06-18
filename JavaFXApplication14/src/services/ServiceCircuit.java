/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Circuit;
import interfaces.IServiceCircuit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import utile.MaConnection;

/**
 *
 * @author ezzedine
 */
public class ServiceCircuit implements IServiceCircuit {

    Connection cnx;

    public ServiceCircuit() {
        cnx = MaConnection.getInstance().getConnection();

    }

    @Override
    public void addCircuit(Circuit c) throws SQLException {

        Statement stm = cnx.createStatement();
        String query = "INSERT INTO `circuit` (`id`, `name`, `begin`,`pause`,`end`,`difficulty`,`distance`) VALUES (NULL, '"
                + c.getNom() + "','"
                + c.getBegin() + "','"
                + c.getPause()+ "','"
                + c.getEnd()+ "',"
                + c.getDifficulty()+ ",'"
                + c.getDistance() + "')";
        stm.executeUpdate(query);
        System.out.println("add success");
    }

    @Override
    public void deleteCircuit(Circuit c) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "DELETE FROM `circuit` WHERE `id`=" + c.getId();
        stm.executeUpdate(query);
        System.out.println("iii");
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Circuit> getCircuits() throws SQLException {
//        String querry = "SELECT * FROM `circuit` ";
String querry = "SELECT * FROM `circuit`  ";
        com.mysql.jdbc.Statement stm = (com.mysql.jdbc.Statement) cnx.createStatement();
ServiceStation SS = new ServiceStation();
        ResultSet rs = stm.executeQuery(querry);
        List<Circuit> ls = new ArrayList<>();
        List<Circuit> circuits = new ArrayList<>();
        while (rs.next()) {
            Circuit c = new Circuit();
            c.setId(rs.getInt("id"));
            c.setNom(rs.getString("nom"));
            c.setBegin(SS.getNameStationById(rs.getInt("depart")));
            c.setPause(SS.getNameStationById(rs.getInt("pause")));
            c.setEnd(SS.getNameStationById(rs.getInt("end")));   
            c.setDifficulty(rs.getString("difficulty"));
            c.setDistance(rs.getFloat("distance"));
            //System.out.println(rs);
            circuits.add(c);
            
        }
      //  circuits.stream().forEach(System.out::println);
        return circuits;
    }

    @Override
    public void updateCircuit(Circuit c) throws SQLException {
        try {
            ServiceStation SS = new ServiceStation();
            int beg = SS.getIdStationByName(c.getBegin());
            int en = SS.getIdStationByName(c.getEnd());
            int pa = SS.getIdStationByName(c.getPause());
            Statement stm = cnx.createStatement();
            String sql = "update circuit set nom='" + c.getNom() + "', depart=" + beg + ", pause=" + pa + ",end=" + en
                    + ", difficulty='" + c.getDifficulty() + "', distance=" + c.getDistance() + "where id=" + c.getId() + ";";
            stm.execute(sql);
        } catch (Exception sQLException) {
            System.out.println(sQLException.getMessage());
        }
//       PreparedStatement pla = cnx.prepareStatement("update circuit set   nom=?,depart=?,pause=?,end=?, difficulty=?,distance=? where nom_P=?");
//        pla.setString(1, c.getNom());
//        pla.setInt(2, beg);
//        pla.setInt(3, pa);
//        pla.setInt(4, en);
//        pla.setString(5, c.getDifficulty());
//        pla.setFloat(6, c.getDistance());
//        pla.setInt(7, c.getId());
//        pla.executeUpdate();
    }

    @Override
    public List<Circuit> getCircuitsSortedByDistanceIncreasing() throws SQLException {
        List<Circuit>ls= getCircuits();
        /*String querry = "SELECT * FROM circuit";
        Statement stm = (Statement) cnx.createStatement();
        ResultSet rs = stm.executeQuery(querry);
        List<Circuit> circuits = new ArrayList<>();
        while (rs.next()) {
        Circuit c = new Circuit();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setBegin(rs.getInt("begin"));
        c.setEnd(rs.getInt("end"));
        c.setPause(rs.getInt("pause"));
        c.setDistance(rs.getFloat("distance"));
        c.setDuration(rs.getTime("duration"));
        c.setDifficulty(rs.getString("difficulty"));
        circuits.add(c);
        }
        */
        List<Circuit> sortedList = new ArrayList<>();
        sortedList = ls.stream().sorted(Comparator.comparing(Circuit::getDistance))
                .collect(Collectors.toList());
        return sortedList;
    }
//    @Override
//    public List<Circuit> getCircuitsSortedByDurationIncreasing() throws SQLException {
//        List<Circuit>ls= getCircuits();
//          List<Circuit> sortedList = new ArrayList<>();
//        sortedList = ls.stream().sorted(Comparator.comparing(Circuit::getDuration))
//                .collect(Collectors.toList());
//        return sortedList;
//    }

    @Override
    public List<Circuit> getHardCircuits() throws SQLException {
        List<Circuit>ls= getCircuits();
          List<Circuit> filtredList = new ArrayList<>();
        filtredList = ls.stream().filter(c->c.getDifficulty().equals("hard"))
                .collect(Collectors.toList());
        return filtredList;
    }

    @Override
    public List<Circuit> getEasyCircuits() throws SQLException {
    
        List<Circuit>ls= getCircuits();
          List<Circuit> filtredList = new ArrayList<>();
        filtredList = ls.stream().filter(c->c.getDifficulty().equals("easy"))
                .collect(Collectors.toList());
        return filtredList;
    
    }

    @Override
    public List<Circuit> getCircuitsSortedByDurationIncreasing() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
