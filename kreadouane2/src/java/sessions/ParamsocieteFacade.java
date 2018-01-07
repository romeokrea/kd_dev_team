/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Paramsociete;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author KBC
 */
@Stateless
public class ParamsocieteFacade extends AbstractFacade<Paramsociete> implements ParamsocieteFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParamsocieteFacade() {
        super(Paramsociete.class);
    }
    
    @Override
    public Paramsociete findByNom(String nom) {
        try {
            Query query = em.createNamedQuery("Paramsociete.findByNom");
            query.setParameter("nom", nom);
            return (Paramsociete) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    
      @Override
    public Paramsociete findByValeur(String valeur) {
        try {
            Query query = em.createNamedQuery("Paramsociete.findByValeur");
            query.setParameter("valeur", valeur);
            return (Paramsociete) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
