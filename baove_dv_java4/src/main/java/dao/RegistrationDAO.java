package dao;

import util.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class RegistrationDAO implements RegistrationDAOInterface {

    @Override
    public List<Registration> findAll() {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            TypedQuery<Registration> query = em.createQuery("SELECT r FROM Registration r", Registration.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Registration findByUsername(String username) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            return em.find(Registration.class, username);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Registration> searchByLastname(String lastname) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            TypedQuery<Registration> query = em.createQuery(
                "SELECT r FROM Registration r WHERE r.lastname LIKE :lastname",
                Registration.class
            );
            query.setParameter("lastname", "%" + lastname + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void save(Registration registration) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            if (findByUsername(registration.getUsername()) == null) {
                em.persist(registration);
            } else {
                em.merge(registration); // Update nếu username đã tồn tại
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Save operation failed.", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteByUsername(String username) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Registration registration = findByUsername(username);
            if (registration != null) {
                em.remove(em.contains(registration) ? registration : em.merge(registration));
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Delete operation failed.", e);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean isUsernameExist(String username) {
        return findByUsername(username) != null;
    }
}
