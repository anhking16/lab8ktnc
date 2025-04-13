package com.poly.utils;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.poly.bean.Product;



import com.poly.bean.Product;

public class ProductDAO {
    EntityManager em = JpaUtils.getEntityManager();

    @Override
    protected void finalize() throws Throwable {
        em.close();
        super.finalize();
    }

    public Product create(Product entity) {
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

    public boolean update(Product entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);  // Thực hiện cập nhật
            em.getTransaction().commit();
            return true;  // Trả về true nếu cập nhật thành công
        } catch (Exception e) {
            em.getTransaction().rollback();  // Rollback giao dịch nếu có lỗi
            throw new RuntimeException("Có lỗi khi cập nhật sản phẩm", e);  // Ném lại ngoại lệ
        }
    }



    public Product remove(String code) {
        try {
            Product entity = this.findById(code);
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public Product findById(String code) {
        return em.find(Product.class, code);
    }

    public List<Product> findAll() {
        String jpql = "SELECT p FROM Product p";
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        return query.getResultList();
    }
}
