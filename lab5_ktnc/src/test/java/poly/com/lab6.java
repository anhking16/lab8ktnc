package poly.com;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class lab6 {
    WebDriver driver;
    WebDriverWait wait;
    Workbook workbook;
    Sheet sheet;
    int rowNum = 1;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "E:\\kiemthunancao\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        driver.get("https://www.facebook.com/");
    }

    @BeforeClass
    public void setupExcel() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Login Test Results");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Email");
        header.createCell(1).setCellValue("Password");
        header.createCell(2).setCellValue("Expected Result");
        header.createCell(3).setCellValue("Actual Result");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password, boolean isSuccess) {
        String actualResult;
        try {
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys(email);
            
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass")));
            passwordField.sendKeys(password);
            
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("login")));
            loginButton.click();
            
            if (isSuccess) {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@aria-label='Tài khoản']")));
                Assert.assertTrue(driver.getCurrentUrl().contains("facebook.com"), "Đăng nhập không thành công!");
                actualResult = "Success";
            } else {
                WebElement errorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[contains(text(),'Bạn đã nhập mật khẩu không chính xác') or contains(text(),'tài khoản của bạn')]")));
                Assert.assertTrue(errorMessage.isDisplayed(), "Không tìm thấy thông báo lỗi khi login thất bại!");
                actualResult = "Failed";
            }
        } catch (Exception e) {
            actualResult = "Error: " + e.getMessage();
        }
        
        // Ghi kết quả vào Excel
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(email);
        row.createCell(1).setCellValue(password);
        row.createCell(2).setCellValue(isSuccess ? "Success" : "Failed");
        row.createCell(3).setCellValue(actualResult);
    }

    @AfterClass
    public void saveExcel() throws IOException {
    	try (FileOutputStream fileOut = new FileOutputStream("E:\\kiemthunancao\\TestResults1.xlsx")) {
 
            workbook.write(fileOut);
        }
        workbook.close();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
                {"2019.2020.91.kyanh@gmail.com", "MK@tkb16", true},  
                {"2019.2020.91.kyanh@gmail.com", "yes", false},  
                {"wrongemail@example.com", "MK@tkb16", false},  
                {"", "", false}  
        };
    }
}
