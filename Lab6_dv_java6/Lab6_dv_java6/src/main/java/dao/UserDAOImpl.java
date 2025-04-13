package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Inteface.UserDAO;
import Model.User;

public class UserDAOImpl implements UserDAO {
	
	private EntityManager entityManager = Persistence.createEntityManagerFactory("PolyOE1").createEntityManager();

    @Override
    public User findById(String id) {
        try {
            // Sử dụng JPQL để truy vấn dữ liệu
            String jpql = "SELECT u FROM User u WHERE u.id = :id";
            List<User> results = entityManager.createQuery(jpql, User.class)
                    .setParameter("id", id) // Gán tham số
                    .getResultList(); // Lấy danh sách kết quả
            
            if (results.isEmpty()) {
                return null; // Không tìm thấy user
            }
            return results.get(0); // Trả về user đầu tiên (duy nhất)
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi khác
        }
    }
	
	@Override
	public List<User> findAll() {
		 return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    
	}
	
	@Override
	public void create(User user) {
		EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
	}

	@Override
	public void update(User user) {
		EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(user);
        transaction.commit();
	}

	@Override
	public void deleteById(String id) {
		 EntityTransaction transaction = entityManager.getTransaction();
	        transaction.begin();
	        User user = findById(id);
	        if (user != null) {
	            entityManager.remove(user);
	        }
	        transaction.commit();
	    }
	
	}


