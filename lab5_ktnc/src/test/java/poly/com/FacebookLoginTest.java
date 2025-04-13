package poly.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class FacebookLoginTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "E:\\kiemthunancao\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(12)); // Tăng thời gian chờ

        driver.get("https://www.facebook.com/");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password, boolean isSuccess) {
        // Nhập email
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys(email);

        // Nhập mật khẩu
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass")));
        passwordField.sendKeys(password);

        // Click nút "Đăng nhập"
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("login")));
        loginButton.click();

        if (isSuccess) {
            // Chờ trang chủ Facebook tải xong (chờ avatar hoặc menu tài khoản xuất hiện)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@aria-label='Tài khoản']")));

            // Kiểm tra URL xác nhận đăng nhập thành công
            Assert.assertTrue(driver.getCurrentUrl().contains("facebook.com"), "Đăng nhập không thành công!");
        } else {
            // Kiểm tra thông báo lỗi khi đăng nhập thất bại
            WebElement errorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(text(),'Bạn đã nhập mật khẩu không chính xác') or contains(text(),'tài khoản của bạn')]")));
            Assert.assertTrue(errorMessage.isDisplayed(), "Không tìm thấy thông báo lỗi khi login thất bại!");
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

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
