package com.poly.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Users")
public class Account implements Serializable {
    @Id
    private String id; // Thay 'username' thành 'id' cho phù hợp với bảng Users
    private String password;
    private String fullname;
    private String email;
    private Boolean admin; // Trường 'admin' thay cho 'activated'
}
