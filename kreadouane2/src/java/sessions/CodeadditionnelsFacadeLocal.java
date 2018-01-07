/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Codeadditionnels;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KBC
 */
@Local
public interface CodeadditionnelsFacadeLocal {

    void create(Codeadditionnels codeadditionnels);

    void edit(Codeadditionnels codeadditionnels);

    void remove(Codeadditionnels codeadditionnels);

    Codeadditionnels find(Object id);

    List<Codeadditionnels> findAll();

    List<Codeadditionnels> findRange(int[] range);

    int count();
    
    public List<Codeadditionnels> findByStatus(String s);
    
    public Codeadditionnels findByCategorie(String categorie);
    
}
