package poly.phunglv.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import poly.phunglv.bean.User;
import poly.phunglv.utils.XJPA;

public class UserDAOadmin {
    EntityManager em = XJPA.getEntityManager();

    @Override
    protected void finalize() throws Throwable {
        em.close();
        super.finalize();
    }

    public User create(User entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public User update(User entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public User remove(String username) {
        try {
            User entity = this.findById(username);
            em.getTransaction().begin();
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public User findById(String username) {
        return em.find(User.class, username);
    }

    public List<User> findAll() {
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        return query.getResultList();
    }

    public List<User> findByKeyword(String keyword) {
        String jpql = "SELECT u FROM User u WHERE u.username LIKE :keyword OR u.lastname LIKE :keyword";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }
}
