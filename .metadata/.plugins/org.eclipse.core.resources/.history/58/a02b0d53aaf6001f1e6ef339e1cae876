package com.poly.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.Account;

public interface AccountDAO extends JpaRepository<Account, String> {
    Optional<Account> findByEmail(String email); // Tìm tài khoản qua email
    boolean existsById(String id); // Kiểm tra xem id đã tồn tại chưa
    boolean existsByEmail(String email); // Kiểm tra xem email đã tồn tại chưa
}
