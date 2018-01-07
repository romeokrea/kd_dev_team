/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Banque;
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
public class BanqueFacade extends AbstractFacade<Banque> implements BanqueFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BanqueFacade() {
        super(Banque.class);
    }
    
    
    @Override
    public Banque findByName(String name) {
        try {
            Query query = em.createNamedQuery("Banque.findByNamecompany");
            query.setParameter("namecompany", name);
            return (Banque) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
     @Override
    public List<Banque> findByStatus(String status) {
        try {
            Query query = em.createNamedQuery("Banque.findByStatus");
            query.setParameter("status", status);
            return (List<Banque>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
