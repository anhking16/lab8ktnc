package poly.phunglv.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import poly.phunglv.bean.User;
import poly.phunglv.utils.JpaUtils;
import poly.phunglv.utils.XJPA;

public class UserDAO {
	
	//EntityManager em = JpaUtils.getEntityManager();
	EntityManager em = XJPA.getEntityManager();
	
	@Override
	protected void finalize() throws Throwable {
		em.close();// dong EntiryManager khi DAO bi giai phong
		super.finalize();
	}
	
	public User create(User entity) {
		try {
			em.getTransaction().begin();						
			em.persist(entity);
			em.getTransaction().commit();
			return entity;
		}catch (Exception e) {
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
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
	// Phương thức tìm kiếm người dùng theo nhiều trường (id, fullname, email)
	public List<User> findByKeyword(String keyword) {
	    String jpql = "SELECT u FROM User u WHERE u.fullname LIKE :keyword OR u.id LIKE :keyword OR u.email LIKE :keyword";
	    TypedQuery<User> query = em.createQuery(jpql, User.class);
	    query.setParameter("keyword", "%" + keyword + "%"); // Thêm ký tự % để tìm kiếm theo phần của chuỗi
	    return query.getResultList();
	}

	
	
	public User remove(String id) {
		try {
			User entity = this.findById(id);
			em.getTransaction().begin();			
			em.remove(entity);
			em.getTransaction().commit();
			return entity;
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
	public User findById(String id) {
		User entity = em.find(User.class, id);
		return entity;
	}
	public List<User> findAll(){
		String jpql = "SELECT o FROM User o";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		List<User> list = query.getResultList();				
		return list;
	}
}
