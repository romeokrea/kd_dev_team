/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Acconier;
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
public class AcconierFacade extends AbstractFacade<Acconier> implements AcconierFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcconierFacade() {
        super(Acconier.class);
    }
    
    @Override
    public Acconier findByName(String name) {
        try {
            Query query = em.createNamedQuery("Acconier.findByNamecompany");
            query.setParameter("namecompany", name);
            return (Acconier) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
     @Override
    public List<Acconier> findByStatus(String status) {
        try {
            Query query = em.createNamedQuery("Acconier.findByStatus");
            query.setParameter("status", status);
            return (List<Acconier>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
