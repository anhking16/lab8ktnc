package poly.test;

import poly.bean.User;
import poly.dao.UserManager;

public class UserTest {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();

        // Test create()
        User newUser  = new User("U04", "pass", "Nguyen Van Teo", "teonv@fpt.edu.vn", false);
        userManager.create(newUser );

        // Test findAll()
        System.out.println("Danh sách người dùng:");
        userManager.findAll();

        // Test findById()
        userManager.findById("U01");

        // Test update()
        newUser .setFullname("Nguyen Van Teo Updated");
        userManager.update(newUser );
        System.out.println("Cập nhật người dùng thành công!");

        // Test deleteById()
        userManager.deleteById("U04");

        // Kiểm thử phương thức phân trang
        System.out.println("Page 3:");
        userManager.findUsersByPage(2, 5);  // Trang thứ 3 với kích thước trang là 5
    }
}