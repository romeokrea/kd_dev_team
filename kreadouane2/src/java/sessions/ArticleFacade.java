/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Article;
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
public class ArticleFacade extends AbstractFacade<Article> implements ArticleFacadeLocal {
    @PersistenceContext(unitName = "kreadouane2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticleFacade() {
        super(Article.class);
    }
    
    @Override
    public Article findByCode(String code) {
        try {
            Query query = em.createNamedQuery("Article.findByCode");
            query.setParameter("code", code);
            return (Article) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
      @Override
    public List<Article> findByStatus(String s) {
        try {
            Query query = em.createNamedQuery("Article.findByStatus");
            query.setParameter("status", s);
            return (List<Article>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    
      @Override
    public Article findByDescription(String description) {
        try {
            Query query = em.createNamedQuery("Article.findByDescription");
            query.setParameter("description", description);
            return (Article) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
