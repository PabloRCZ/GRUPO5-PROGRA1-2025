package gt.edu.miumg.BD;

import gt.edu.miumg.BD.exceptions.NonexistentEntityException;
import gt.edu.miumg.BD.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

public class AveJpaController implements Serializable {

    public AveJpaController() {
        this.emf = Persistence.createEntityManagerFactory("gt.edu.miumg_Proyectozoologico_jar_1.0-SNAPSHOTPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ave ave) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ave);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAve(ave.getId()) != null) {
                throw new PreexistingEntityException("Ave " + ave + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ave ave) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ave = em.merge(ave);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ave.getId();
                if (findAve(id) == null) {
                    throw new NonexistentEntityException("The ave with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ave ave;
            try {
                ave = em.getReference(Ave.class, id);
                ave.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ave with id " + id + " no longer exists.", enfe);
            }
            em.remove(ave);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ave> findAveEntities() {
        return findAveEntities(true, -1, -1);
    }

    public List<Ave> findAveEntities(int maxResults, int firstResult) {
        return findAveEntities(false, maxResults, firstResult);
    }

    private List<Ave> findAveEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Ave as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Ave findAve(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ave.class, id);
        } finally {
            em.close();
        }
    }

    public int getAveCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Ave as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}