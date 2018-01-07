/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Contact;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author KBC
 */
@Stateless
public class ContactFacade extends AbstractFacade<Contact> implements ContactFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContactFacade() {
        super(Contact.class);
    }
    
    
    @Override
    public Contact findByName(String name) {
        try {
            Query query = em.createNamedQuery("Contact.findByName");
            query.setParameter("name", name);
            return (Contact) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
     @Override
    public List<Contact> findByStatus(String status) {
        try {
            Query query = em.createNamedQuery("Contact.findByStatus");
            query.setParameter("status", status);
            return (List<Contact>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
     @Override
    public List<Contact> findByCadIdStatus(int idCad, String status) {
        try {
            Query query = em.createNamedQuery("Contact.findByCadId");
            query.setParameter("idCad", idCad).setParameter("status", status);
            return (List<Contact>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
     @Override
    public List<Contact> findByTransIdStatus(int idTrans, String status) {
        try {
            Query query = em.createNamedQuery("Contact.findByTransId");
            query.setParameter("idTrans", idTrans).setParameter("status", status);
            return (List<Contact>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
     @Override
    public List<Contact> findByAccIdStatus(int idAcc, String status) {
        try {
            Query query = em.createNamedQuery("Contact.findByAccId");
            query.setParameter("idAcc", idAcc).setParameter("status", status);
            return (List<Contact>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Contact> findByBanqueIdStatus(int idBanq, String status) {
        try {
            Query query = em.createNamedQuery("Contact.findByBanqueId");
            query.setParameter("idBanq", idBanq).setParameter("status", status);
            return (List<Contact>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Contact> findByAssureurIdStatus(int idAss, String status) {
        try {
            Query query = em.createNamedQuery("Contact.findByAssureurId");
            query.setParameter("idAss", idAss).setParameter("status", status);
            return (List<Contact>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
