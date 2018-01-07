/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Cad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KBC
 */
@Local
public interface CadFacadeLocal {

    void create(Cad cad);

    void edit(Cad cad);

    void remove(Cad cad);

    Cad find(Object id);

    List<Cad> findAll();

    List<Cad> findRange(int[] range);

    int count();
    
      public Cad findByName(String name);
    
    public List<Cad> findByStatus(String status);
    
    
}
