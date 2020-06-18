/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Station;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ezzedine
 */
public interface IServiceStation {

    public void addStation(Station s) throws SQLException;

    /**
     *
     * @param s
     * @throws SQLException
     */
    public void deleteStation(Station s) throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Station> getStations() throws SQLException;
    
    public void updateStation(Station s) throws SQLException ;
    
    public int getIdStationByName(String name)throws SQLException;
    public String getNameStationById(int id)throws SQLException;
            
}

