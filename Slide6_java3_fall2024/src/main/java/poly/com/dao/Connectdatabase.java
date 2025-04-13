package poly.com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connectdatabase{

 	// Hàm main
    public static void main(String[] args) 
    {
  
    ///
    // Kết nối cơ sở dữ liệu
    DeparmentDao dao = new DeparmentDao();
    //EmployeesDao daoem = new EmployeesDao();
    
    
    // Thêm phòng ban
   // dao.insertDepartment("D07", "Anh Tú", "Phòng Giám đốc");
    // test thu tuc ham
 //dao.addDepartment("D08", "HR", "anhtu thu tuc ham");
    // Cập nhật phòng ban
   // dao.updateDepartment("D06", "Logistics", "Logistics and Supply Chain Management");
    
    // Tìm phòng ban theo ID
   // dao.findDepartmentById("D06");
    
    // Xóa phòng ban
   // dao.deleteDepartment("D07");
    
    /// test nhan vien
    
 // Thêm nhân viên
    //daoem.insertEmployee("Etu", "password123", "John Doe", "john.jpg", true, "1985-08-15", 50000, "D01");
    
    // Cập nhật nhân viên
   // daoem.updateEmployee("E001", "newpass123", "John Doe Updated", "john_updated.jpg", true, "1985-08-15", 55000, "D02");
    
    // Tìm nhân viên theo ID
   // daoem.findEmployeeById("E001");
    
    // Xóa nhân viên
    //daoem.deleteEmployee("E001");
    
    ///
    // In tất cả các phòng ban
   dao.printAllDepartments();
    //daoem.printAllEmployees();
    ////
    
    
    ///
    }

   
    
        
  
}
