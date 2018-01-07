/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Contact;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KBC
 */
@Local
public interface ContactFacadeLocal {

    void create(Contact contact);

    void edit(Contact contact);

    void remove(Contact contact);

    Contact find(Object id);

    List<Contact> findAll();

    List<Contact> findRange(int[] range);

    int count();
    
    public Contact findByName(String name);
    
    public List<Contact> findByStatus(String status);
    
    public List<Contact> findByCadIdStatus(int idCad, String status);
    
    public List<Contact> findByTransIdStatus(int idTrans, String status);
    
    public List<Contact> findByAccIdStatus(int idAcc, String status);
    
    public List<Contact> findByBanqueIdStatus(int idBanq, String status);
    
    public List<Contact> findByAssureurIdStatus(int idAss, String status);
    
}
