package com.poly.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sinh_vien") // Ánh xạ với bảng sinh_vien trong CSDL
public class SinhVien {
    @Id
    private String masv;
    private String tensv;
    private String chuyen_nganh;
    private String email;
    private String sdt;
}
