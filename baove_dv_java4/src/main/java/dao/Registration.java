package dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ADMIN") // Tên bảng trong cơ sở dữ liệu
public class Registration {
    @Id
    @Column(name = "username", nullable = false, length = 50)
    private String username; // Tên người dùng (Primary Key)

    @Column(name = "password", nullable = false)
    private String password; // Mật khẩu

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname; // Họ người dùng

    @Column(name = "isAdmin", nullable = false) // Tên cột tương ứng trong DB
    private boolean admin; // Đổi tên thành `admin` để loại bỏ nhầm lẫn

    // Getter cho admin (tương thích EL)
    public boolean getAdmin() {
        return admin;
    }
}
