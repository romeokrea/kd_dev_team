/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Codeadditionnels;
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
public class CodeadditionnelsFacade extends AbstractFacade<Codeadditionnels> implements CodeadditionnelsFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CodeadditionnelsFacade() {
        super(Codeadditionnels.class);
    }
    
    
    
      @Override
    public List<Codeadditionnels> findByStatus(String s) {
        try {
            Query query = em.createNamedQuery("Codeadditionnels.findByStatus");
            query.setParameter("status", s);
            return (List<Codeadditionnels>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    
      @Override
    public Codeadditionnels findByCategorie(String categorie) {
        try {
            Query query = em.createNamedQuery("Codeadditionnels.findByCategorie");
            query.setParameter("categorie", categorie);
            return (Codeadditionnels) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
