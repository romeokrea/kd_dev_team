/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Paramlogiciel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KBC
 */
@Local
public interface ParamlogicielFacadeLocal {

    void create(Paramlogiciel paramlogiciel);

    void edit(Paramlogiciel paramlogiciel);

    void remove(Paramlogiciel paramlogiciel);

    Paramlogiciel find(Object id);

    List<Paramlogiciel> findAll();

    List<Paramlogiciel> findRange(int[] range);

    int count();
    
    public Paramlogiciel findByLien(String lien);
    
    public Paramlogiciel findByValeur(String valeur);
    
}
