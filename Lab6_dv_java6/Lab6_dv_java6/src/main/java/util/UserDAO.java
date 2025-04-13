package util;

import java.util.List;
import Model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;



public class UserDAO {

    public User create(User entity) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Lỗi khi tạo User: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public User update(User entity) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Lỗi khi cập nhật User: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public User remove(String id) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            em.getTransaction().begin();
            User entity = em.find(User.class, id);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Lỗi khi xóa User: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }


    public User findById(String id) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.email = :username"; // Thay đổi cột theo tên trong database
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("username", id);
            return query.getSingleResult(); // Hoặc xử lý trường hợp không có kết quả
        } catch (Exception e) {
            return null; // Trả về null nếu không tìm thấy user
        } finally {
            em.close();
        }
    }


    public List<User> findAll() {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            String jpql = "SELECT o FROM User o";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    public User findByEmail(String email) {
        try {
            EntityManager em = JpaUtils.getEntityManager();
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                     .setParameter("email", email)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}

