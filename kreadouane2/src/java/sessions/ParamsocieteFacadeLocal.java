/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Paramsociete;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KBC
 */
@Local
public interface ParamsocieteFacadeLocal {

    void create(Paramsociete paramsociete);

    void edit(Paramsociete paramsociete);

    void remove(Paramsociete paramsociete);

    Paramsociete find(Object id);

    List<Paramsociete> findAll();

    List<Paramsociete> findRange(int[] range);

    int count();
    
    public Paramsociete findByNom(String nom);
    
    public Paramsociete findByValeur(String valeur);
    
}
