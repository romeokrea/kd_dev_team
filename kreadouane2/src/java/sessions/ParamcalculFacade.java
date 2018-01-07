/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Paramcalcul;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author KBC
 */
@Stateless
public class ParamcalculFacade extends AbstractFacade<Paramcalcul> implements ParamcalculFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParamcalculFacade() {
        super(Paramcalcul.class);
    }
    
    @Override
    public Paramcalcul findByGuce(String guce) {
        try {
            Query query = em.createNamedQuery("Paramcalcul.findByGuce");
            query.setParameter("guce", guce);
            return (Paramcalcul) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    
      @Override
    public Paramcalcul findByValeur(String valeur) {
        try {
            Query query = em.createNamedQuery("Paramcalcul.findByValeur");
            query.setParameter("valeur", valeur);
            return (Paramcalcul) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
