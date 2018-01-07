/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Cotation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KBC
 */
@Local
public interface CotationFacadeLocal {

    void create(Cotation cotation);

    void edit(Cotation cotation);

    void remove(Cotation cotation);

    Cotation find(Object id);

    List<Cotation> findAll();

    List<Cotation> findRange(int[] range);

    int count();
    
       public List<Cotation> findByStatus(String s);
    
    public Cotation findByReferance(String Referance);
    
    public int findMax();
    
}
