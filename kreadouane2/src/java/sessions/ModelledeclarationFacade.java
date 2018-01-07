/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Modelledeclaration;
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
public class ModelledeclarationFacade extends AbstractFacade<Modelledeclaration> implements ModelledeclarationFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModelledeclarationFacade() {
        super(Modelledeclaration.class);
    }
    
    @Override
    public Modelledeclaration findByCode(String code) {
        try {
            Query query = em.createNamedQuery("Modelledeclaration.findByCode");
            query.setParameter("code", code);
            return (Modelledeclaration) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
      @Override
    public List<Modelledeclaration> findByStatus(String s) {
        try {
            Query query = em.createNamedQuery("Modelledeclaration.findByStatus");
            query.setParameter("status", s);
            return (List<Modelledeclaration>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    
      @Override
    public Modelledeclaration findByLibelle(String libelle) {
        try {
            Query query = em.createNamedQuery("Modelledeclaration.findByLibelle");
            query.setParameter("libelle", libelle);
            return (Modelledeclaration) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
