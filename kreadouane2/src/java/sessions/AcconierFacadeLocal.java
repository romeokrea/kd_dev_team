/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Acconier;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KBC
 */
@Local
public interface AcconierFacadeLocal {

    void create(Acconier acconier);

    void edit(Acconier acconier);

    void remove(Acconier acconier);

    Acconier find(Object id);

    List<Acconier> findAll();

    List<Acconier> findRange(int[] range);

    int count();
    
    public Acconier findByName(String name);
    
    public List<Acconier> findByStatus(String status);
    
    
}
