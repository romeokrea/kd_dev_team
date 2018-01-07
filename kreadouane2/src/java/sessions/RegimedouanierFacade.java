/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Regimedouanier;
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
public class RegimedouanierFacade extends AbstractFacade<Regimedouanier> implements RegimedouanierFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegimedouanierFacade() {
        super(Regimedouanier.class);
    }
    
    
     @Override
    public Regimedouanier findByCode(String code) {
        try {
            Query query = em.createNamedQuery("Regimedouanier.findByCode");
            query.setParameter("code", code);
            return (Regimedouanier) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
      @Override
    public List<Regimedouanier> findByStatus(String s) {
        try {
            Query query = em.createNamedQuery("Regimedouanier.findByStatus");
            query.setParameter("status", s);
            return (List<Regimedouanier>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    
      @Override
    public Regimedouanier findByLibelle(String libelle) {
        try {
            Query query = em.createNamedQuery("Regimedouanier.findByLibelle");
            query.setParameter("libelle", libelle);
            return (Regimedouanier) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
