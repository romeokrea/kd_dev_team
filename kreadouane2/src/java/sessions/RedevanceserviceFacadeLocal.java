/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Redevanceservice;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KBC
 */
@Local
public interface RedevanceserviceFacadeLocal {

    void create(Redevanceservice redevanceservice);

    void edit(Redevanceservice redevanceservice);

    void remove(Redevanceservice redevanceservice);

    Redevanceservice find(Object id);

    List<Redevanceservice> findAll();

    List<Redevanceservice> findRange(int[] range);

    int count();
    
}
