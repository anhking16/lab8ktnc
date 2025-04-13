package poly.com.testmaven;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 class CalculatorTest {

    private final Calculator calculator = new Calculator();

    // Kiểm tra phép cộng
    @Test
    void testAdd() {
        assertEquals(5, calculator.add(3, 2), "Phép cộng không đúng");
    }

    // Kiểm tra phép trừ
    @Test
    void testSubtract() {
        assertEquals(1, calculator.subtract(3, 2), "Phép trừ không đúng");
    }

    // Kiểm tra phép nhân
    @Test
    void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3), "Phép nhân không đúng");
    }

    // Kiểm tra phép chia
    @Test
    void testDivide() {
        assertEquals(2, calculator.divide(6, 3), "Phép chia không đúng");
    }

    // Kiểm tra trường hợp chia cho 0
    @Test
    void testDivideByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(6, 0);
        });
        assertEquals("Không thể chia cho 0", exception.getMessage(), "Lỗi chia cho 0 không đúng");
    }
}
