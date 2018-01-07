/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Regimedouanier;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KBC
 */
@Local
public interface RegimedouanierFacadeLocal {

    void create(Regimedouanier regimedouanier);

    void edit(Regimedouanier regimedouanier);

    void remove(Regimedouanier regimedouanier);

    Regimedouanier find(Object id);

    List<Regimedouanier> findAll();

    List<Regimedouanier> findRange(int[] range);

    int count();
    
    public List<Regimedouanier> findByStatus(String s);
    
    public Regimedouanier findByCode(String code);
    
    public Regimedouanier findByLibelle(String libelle);
    
}
