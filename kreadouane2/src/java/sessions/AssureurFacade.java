/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Assureur;
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
public class AssureurFacade extends AbstractFacade<Assureur> implements AssureurFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssureurFacade() {
        super(Assureur.class);
    }
    
    
    @Override
    public Assureur findByName(String name) {
        try {
            Query query = em.createNamedQuery("Assureur.findByNamecompany");
            query.setParameter("namecompany", name);
            return (Assureur) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
     @Override
    public List<Assureur> findByStatus(String status) {
        try {
            Query query = em.createNamedQuery("Assureur.findByStatus");
            query.setParameter("status", status);
            return (List<Assureur>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
