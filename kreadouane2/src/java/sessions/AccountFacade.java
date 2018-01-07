/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Account;
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
public class AccountFacade extends AbstractFacade<Account> implements AccountFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
    
    @Override
    public Account findByLoginMdp(String l, String p) {
        try {
            Query query = em.createNamedQuery("Account.findByLoginMdp");
            query.setParameter("login", l).setParameter("pass", p);
            return (Account) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
     @Override
    public List<Account> findByStatus(String s) {
        try {
            Query query = em.createNamedQuery("Account.findByStatus");
            query.setParameter("status", s);
            return (List<Account>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
