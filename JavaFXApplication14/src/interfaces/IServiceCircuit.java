/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Circuit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ezzedine
 */
public interface IServiceCircuit {

    public void addCircuit(Circuit s) throws SQLException;

    public void deleteCircuit(Circuit c) throws SQLException;

    public List<Circuit> getCircuits() throws SQLException;

    public void updateCircuit(Circuit c) throws SQLException;

    public List<Circuit> getCircuitsSortedByDistanceIncreasing() throws SQLException;

    public List<Circuit> getCircuitsSortedByDurationIncreasing() throws SQLException;

    public List<Circuit> getHardCircuits() throws SQLException;

    public List<Circuit> getEasyCircuits() throws SQLException;
}
