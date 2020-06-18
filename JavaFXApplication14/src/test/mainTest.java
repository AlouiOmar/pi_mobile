/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.SMSService;
import entities.Circuit;
import entities.Station;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import services.ServiceCircuit;
import services.ServiceStation;

/**
 *
 * @author ezzedine
 */
public class mainTest {

    public static void main(String[] args) throws SQLException {
//        SMSService sms=new SMSService();
//        sms.getClass()
        ServiceStation SS = new ServiceStation();
//        ServiceCircuit SC = new ServiceCircuit();
//        
//        Station s = new Station(9, "ariana", 0f, 0f);
//        Station s1 = new Station(1255, "mnigla", 15.0258f, 33.21926f);
//        
//        Circuit c1 = new Circuit(3, "haha", 1, 2, 3, 12.5f, Time.valueOf(LocalTime.of(02, 30, 10)), "easy");
//        Circuit c2 = new Circuit(1, "haha", 1, 2, 3, 12.5f, Time.valueOf(LocalTime.of(02, 10, 10)), "hard");
//        Circuit c = new Circuit(3, "ariana", 1, 2, 3, 12.5f, Time.valueOf(LocalTime.of(02, 10, 10)), "medium");
//        
//        SS.addStation(s);
//        SC.addCircuit(c1);
//        SC.addCircuit(c2);
//        
//        SC.deleteCircuit(c1);
//        SS.deleteStation(s);
//        
//        SS.getStations().stream().forEach(System.out::println);
//        /*List<Circuit> ls = SC.getCircuits();
//        ls.stream().forEach(System.out::println);
//        
//        SS.updateStation(s);
//        SC.updateCircuit(c1);
//
//        List<Circuit> ls4 = SC.getCircuitsSortedByDistanceIncreasing();
//        ls4.stream().forEach(System.out::println);
//
//        System.out.println("*******************");
//
//        List<Circuit> ls1 = SC.getCircuitsSortedByDurationIncreasing();
//        ls1.stream().forEach(System.out::println);
//
//        System.out.println("*******************");
//
//        List<Circuit> ls2 = SC.getHardCircuits();
//        ls2.stream().forEach(System.out::println);
//
//        System.out.println("*******************");
//
//        List<Circuit> ls3 = SC.getEasyCircuits();
//        ls3.stream().forEach(System.out::println);*/
    
           try {
            System.out.println(SS.getIdStationByName("jjjjjjjjjj"));
        } catch (Exception e) {
               System.out.println(e.getMessage());
        }
    }

}
