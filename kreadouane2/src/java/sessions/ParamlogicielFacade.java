/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Paramlogiciel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author KBC
 */
@Stateless
public class ParamlogicielFacade extends AbstractFacade<Paramlogiciel> implements ParamlogicielFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParamlogicielFacade() {
        super(Paramlogiciel.class);
    }
    
    @Override
    public Paramlogiciel findByLien(String lien) {
        try {
            Query query = em.createNamedQuery("Paramlogiciel.findByLien");
            query.setParameter("lien", lien);
            return (Paramlogiciel) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    
      @Override
    public Paramlogiciel findByValeur(String valeur) {
        try {
            Query query = em.createNamedQuery("Paramlogiciel.findByValeur");
            query.setParameter("valeur", valeur);
            return (Paramlogiciel) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
