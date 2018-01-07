/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Redevanceservice;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KBC
 */
@Stateless
public class RedevanceserviceFacade extends AbstractFacade<Redevanceservice> implements RedevanceserviceFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RedevanceserviceFacade() {
        super(Redevanceservice.class);
    }
    
}
