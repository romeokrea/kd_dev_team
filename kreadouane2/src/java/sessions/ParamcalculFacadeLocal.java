/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Paramcalcul;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KBC
 */
@Local
public interface ParamcalculFacadeLocal {

    void create(Paramcalcul paramcalcul);

    void edit(Paramcalcul paramcalcul);

    void remove(Paramcalcul paramcalcul);

    Paramcalcul find(Object id);

    List<Paramcalcul> findAll();

    List<Paramcalcul> findRange(int[] range);

    int count();
    
    public Paramcalcul findByGuce(String guce);
    
    public Paramcalcul findByValeur(String valeur);
    
}
