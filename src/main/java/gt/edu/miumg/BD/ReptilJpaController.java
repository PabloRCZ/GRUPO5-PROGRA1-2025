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

public class ReptilJpaController implements Serializable {

    public ReptilJpaController() {
        this.emf = Persistence.createEntityManagerFactory("gt.edu.miumg_Proyectozoologico_jar_1.0-SNAPSHOTPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reptil reptil) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reptil);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReptil(reptil.getId()) != null) {
                throw new PreexistingEntityException("Reptil " + reptil + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reptil reptil) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reptil = em.merge(reptil);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reptil.getId();
                if (findReptil(id) == null) {
                    throw new NonexistentEntityException("The reptil with id " + id + " no longer exists.");
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
            Reptil reptil;
            try {
                reptil = em.getReference(Reptil.class, id);
                reptil.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reptil with id " + id + " no longer exists.", enfe);
            }
            em.remove(reptil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reptil> findReptilEntities() {
        return findReptilEntities(true, -1, -1);
    }

    public List<Reptil> findReptilEntities(int maxResults, int firstResult) {
        return findReptilEntities(false, maxResults, firstResult);
    }

    private List<Reptil> findReptilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Reptil as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Reptil findReptil(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reptil.class, id);
        } finally {
            em.close();
        }
    }

    public int getReptilCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Reptil as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}