/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Assureur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KBC
 */
@Local
public interface AssureurFacadeLocal {

    void create(Assureur assureur);

    void edit(Assureur assureur);

    void remove(Assureur assureur);

    Assureur find(Object id);

    List<Assureur> findAll();

    List<Assureur> findRange(int[] range);

    int count();
    
     public Assureur findByName(String name);
    
    public List<Assureur> findByStatus(String status);
    
}
