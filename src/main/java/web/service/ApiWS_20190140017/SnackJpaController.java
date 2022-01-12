/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service.ApiWS_20190140017;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import web.service.ApiWS_20190140017.exceptions.NonexistentEntityException;
import web.service.ApiWS_20190140017.exceptions.PreexistingEntityException;

/**
 *
 * @author ASUS
 */
public class SnackJpaController implements Serializable {

    public SnackJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("web.service_ApiWS_20190140017_jar_0.0.1-SNAPSHOTPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public SnackJpaController() {
    }

    public void create(Snack snack) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(snack);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSnack(snack.getIdSnack()) != null) {
                throw new PreexistingEntityException("Snack " + snack + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Snack snack) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            snack = em.merge(snack);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = snack.getIdSnack();
                if (findSnack(id) == null) {
                    throw new NonexistentEntityException("The snack with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Snack snack;
            try {
                snack = em.getReference(Snack.class, id);
                snack.getIdSnack();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The snack with id " + id + " no longer exists.", enfe);
            }
            em.remove(snack);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Snack> findSnackEntities() {
        return findSnackEntities(true, -1, -1);
    }

    public List<Snack> findSnackEntities(int maxResults, int firstResult) {
        return findSnackEntities(false, maxResults, firstResult);
    }

    private List<Snack> findSnackEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Snack.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Snack findSnack(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Snack.class, id);
        } finally {
            em.close();
        }
    }

    public int getSnackCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Snack> rt = cq.from(Snack.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
