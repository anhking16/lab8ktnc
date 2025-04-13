package poly.com;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class test1 {
    @Test
    public void test1() throws InterruptedException { // Thêm throws InterruptedException để dùng Thread.sleep()
        WebDriver driver = new ChromeDriver();
        try {
            String url = "http://www.google.com";
            String expectedTitle = "Google";
            driver.get(url);
            String actualTitle = driver.getTitle();
            
            if (actualTitle.contentEquals(expectedTitle)) {
                System.out.println("Test Pass");
            } else {
                System.out.println("Test Fail");
            }

           
            Thread.sleep(5000); 

        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
