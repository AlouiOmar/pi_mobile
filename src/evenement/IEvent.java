/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public interface IEvent {
    public void AjouterEvent(Event e) throws SQLException;
    public void modifierEvent(Event e) throws SQLException;
    public void supprimerEvent(Event e) throws SQLException;
    public Event getEvent(int ide) throws SQLException;
    public List<Event> afficherEvents() throws SQLException;
}
