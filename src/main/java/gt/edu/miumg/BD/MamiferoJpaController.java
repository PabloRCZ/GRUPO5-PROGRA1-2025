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

public class MamiferoJpaController implements Serializable {

    public MamiferoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("gt.edu.miumg_Proyectozoologico_jar_1.0-SNAPSHOTPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mamifero mamifero) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(mamifero);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMamifero(mamifero.getId()) != null) {
                throw new PreexistingEntityException("Mamifero " + mamifero + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mamifero mamifero) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mamifero = em.merge(mamifero);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mamifero.getId();
                if (findMamifero(id) == null) {
                    throw new NonexistentEntityException("The mamifero with id " + id + " no longer exists.");
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
            Mamifero mamifero;
            try {
                mamifero = em.getReference(Mamifero.class, id);
                mamifero.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mamifero with id " + id + " no longer exists.", enfe);
            }
            em.remove(mamifero);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mamifero> findMamiferoEntities() {
        return findMamiferoEntities(true, -1, -1);
    }

    public List<Mamifero> findMamiferoEntities(int maxResults, int firstResult) {
        return findMamiferoEntities(false, maxResults, firstResult);
    }

    private List<Mamifero> findMamiferoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Mamifero as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Mamifero findMamifero(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mamifero.class, id);
        } finally {
            em.close();
        }
    }

    public int getMamiferoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Mamifero as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}