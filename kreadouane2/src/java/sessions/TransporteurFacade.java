/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Transporteur;
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
public class TransporteurFacade extends AbstractFacade<Transporteur> implements TransporteurFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransporteurFacade() {
        super(Transporteur.class);
    }
    
    
    @Override
    public Transporteur findByName(String name) {
        try {
            Query query = em.createNamedQuery("Transporteur.findByNamecompany");
            query.setParameter("namecompany", name);
            return (Transporteur) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
     @Override
    public List<Transporteur> findByStatus(String status) {
        try {
            Query query = em.createNamedQuery("Transporteur.findByStatus");
            query.setParameter("status", status);
            return (List<Transporteur>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
