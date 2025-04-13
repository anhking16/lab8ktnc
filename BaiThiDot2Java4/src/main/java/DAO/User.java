package DAO;

import javax.persistence.*;

@Entity
@Table(name = "Registration")
public class User {

    @Id
    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "lastname", length = 20)
    private String lastname;

    @Column(name = "isAdmin")
    private boolean isAdmin;

    // Constructor không tham số cho JPA
    public User() {
    }

    // Constructor có tham số để dễ dàng tạo đối tượng
    public User(String username, String password, String lastname, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.isAdmin = isAdmin;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
