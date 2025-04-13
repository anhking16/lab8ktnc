package poly.com.servlet;

import java.io.IOException;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import poly.com.entity.User;
@WebServlet("/")
public class UserManager extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		findAll();
//		findById();
		create();
//		update();
//		deleteById();
//		findALLEmailvsRole();
//		findPage();
	}
	
	// Nạp persistence.xml và tạo EntityManagerFactory
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
	// Tạo EntityManager để bắt đầu làm việc với CSDL
	EntityManager em = emf.createEntityManager();
	
	private void findAll() {
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			
			//Câu lệnh JPQL truy vấn thực thể
			String jpql = "SELECT o FROM User o";
			//Tạo Query/TypedQuery<T> để truy vấn
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			//Truy vấn danh sách thực thể
			List<User> list = query.getResultList();
			System.out.println("--------------------------------");
			list.forEach(user ->{
				System.out.println(user.getFullname()+"\t"+user.getAdmin());
			});
//			for(User user : list) {
//				System.out.println(user.getFullname()+"\t"+user.getAdmin());
//			}
			
			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Error: "+e);
		}
		em.close();
		emf.close();		
	}
	private void findById() {
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			User user = em.find(User.class, "ID001");
			System.out.println("--------------------------------");
			System.out.println(user.getFullname()+"\t"+user.getAdmin());
			
			
			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Error: "+e);
		}
		em.close();
		emf.close();		
	}
	private void create() {		
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			
			User entity = new User();
			entity.setId("locl");
			entity.setFullname("Locmeomeo2");
			entity.setEmail("teolv@gmail.com");
			entity.setPassword("123456");

			// Insert vào CSDL
			em.persist(entity);
			
			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Error: "+e);
		}
		em.close();
		emf.close();		
	}
	private void update() {
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			
			User entity = new User();
			entity.setId("ID003");
			entity.setFullname("Le Van Teo 2");
			entity.setEmail("teolv2@gmail.com");
			entity.setPassword("abc");

			// Insert vào CSDL
			em.merge(entity);
			
			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Error: "+e);
			}
			em.close();
			emf.close();
	}
	private void deleteById() {
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			
			User entity = em.find(User.class, "ID003");
			// Insert vào CSDL
			em.remove(entity);
			
			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Error: "+e);
			}
			em.close();
			emf.close();		
	}
	private void findALLEmailvsRole() {
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			
			//Câu lệnh JPQL truy vấn thực thể
			String jpql = "SELECT o FROM User o WHERE o.email LIKE :search AND o.admin = :role";
			//Tạo Query/TypedQuery<T> để truy vấn
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			// Sử dụng phương thức setParameter() để cung cấp giá trị cho các tham số
			query.setParameter("search", "%@fpt.edu.vn");
			query.setParameter("role", false);
			//Truy vấn danh sách thực thể
			List<User> list = query.getResultList();
			System.out.println("--------------------------------");
			for(User user : list) {
				System.out.println(user.getFullname()+"\t"+user.getEmail());
			}
			
			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Error: "+e);
		}finally {
			em.close();
			emf.close();
		}	
	}
	private void findPage() {
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			
			String jpql = "SELECT o FROM User o";
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setFirstResult(3);
			query.setMaxResults(5);
			List<User> list = query.getResultList();
			System.out.println("--------------------------------");
			for(User user : list) {
				System.out.println(user.getId()+"\t"+user.getFullname()+"\t"+user.getEmail()+"\t"+user.getAdmin());
			}
			
			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Error: "+e);
		}
		em.close();
		emf.close();	
	}	
}
