/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Cotation;
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
public class CotationFacade extends AbstractFacade<Cotation> implements CotationFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CotationFacade() {
        super(Cotation.class);
    }
    
    @Override
    public List<Cotation> findByStatus(String s) {
        try {
            Query query = em.createNamedQuery("Cotation.findByStatus");
            query.setParameter("status", s);
            return (List<Cotation>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
      @Override
    public Cotation findByReferance(String Referance) {
        try {
            Query query = em.createNamedQuery("Cotation.findByReferance");
            query.setParameter("code", Referance);
            return (Cotation) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public int findMax() {
        try {
            Query query = em.createNamedQuery("Cotation.findMax");
            return (int) query.getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }
    
}
