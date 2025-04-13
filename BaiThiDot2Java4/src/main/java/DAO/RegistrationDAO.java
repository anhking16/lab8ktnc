package DAO;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jakarta.servlet.Registration;

public class RegistrationDAO {
    private EntityManagerFactory emf;

    public RegistrationDAO() {
        emf = Persistence.createEntityManagerFactory("Thi_Java4");
    }

    // Tìm kiếm theo lastname
    public List<Registration> searchByLastname(String lastname) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT r FROM Registration r WHERE r.lastname LIKE :lastname";
            return em.createQuery(jpql, Registration.class)
                     .setParameter("lastname", "%" + lastname + "%")
                     .getResultList();
        } finally {
            em.close();
        }
    }

    // Xóa user theo username
    public void deleteUser(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Registration user = em.find(Registration.class, username);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Đóng EntityManagerFactory khi ứng dụng tắt
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
