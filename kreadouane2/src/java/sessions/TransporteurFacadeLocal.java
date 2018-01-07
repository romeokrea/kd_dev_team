/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Transporteur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KBC
 */
@Local
public interface TransporteurFacadeLocal {

    void create(Transporteur transporteur);

    void edit(Transporteur transporteur);

    void remove(Transporteur transporteur);

    Transporteur find(Object id);

    List<Transporteur> findAll();

    List<Transporteur> findRange(int[] range);

    int count();
    
     public Transporteur findByName(String name);
    
    public List<Transporteur> findByStatus(String status);
    
}
