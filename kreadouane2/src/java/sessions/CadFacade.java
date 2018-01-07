/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Cad;
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
public class CadFacade extends AbstractFacade<Cad> implements CadFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CadFacade() {
        super(Cad.class);
    }
    
    
    @Override
    public Cad findByName(String name) {
        try {
            Query query = em.createNamedQuery("Cad.findByNamecompany");
            query.setParameter("namecompany", name);
            return (Cad) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
     @Override
    public List<Cad> findByStatus(String status) {
        try {
            Query query = em.createNamedQuery("Cad.findByStatus");
            query.setParameter("status", status);
            return (List<Cad>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
