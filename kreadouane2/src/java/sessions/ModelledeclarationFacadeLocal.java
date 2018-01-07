/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Modelledeclaration;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KBC
 */
@Local
public interface ModelledeclarationFacadeLocal {

    void create(Modelledeclaration modelledeclaration);

    void edit(Modelledeclaration modelledeclaration);

    void remove(Modelledeclaration modelledeclaration);

    Modelledeclaration find(Object id);

    List<Modelledeclaration> findAll();

    List<Modelledeclaration> findRange(int[] range);

    int count();
    
      public List<Modelledeclaration> findByStatus(String s);
    
    public Modelledeclaration findByCode(String code);
    
    public Modelledeclaration findByLibelle(String libelle);
    
}
