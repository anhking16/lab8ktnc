package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.model.SinhVien;

public interface SinhVienDAO extends JpaRepository<SinhVien, String> {
}
