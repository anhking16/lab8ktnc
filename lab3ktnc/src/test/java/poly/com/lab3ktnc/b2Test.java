package poly.com.lab3ktnc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class b2Test {
    private b2 calculator;

    // Phương thức này chạy trước mỗi bài kiểm thử
    @BeforeEach
    public void setUp() {
        calculator = new b2();
    }

    // Kiểm thử phép cộng
    @Test
    public void testAdd() {
        assertEquals(5, calculator.add(2, 3), "2 + 3 phải bằng 5");
        assertEquals(-1, calculator.add(2, -3), "2 + (-3) phải bằng -1");
        assertEquals(0, calculator.add(0, 0), "0 + 0 phải bằng 0");
    }

    // Kiểm thử phép trừ
    @Test
    public void testSubtract() {
        assertEquals(1, calculator.subtract(3, 2), "3 - 2 phải bằng 1");
        assertEquals(5, calculator.subtract(2, -3), "2 - (-3) phải bằng 5");
        assertEquals(0, calculator.subtract(0, 0), "0 - 0 phải bằng 0");
    }

    // Kiểm thử phép nhân
    @Test
    public void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3), "2 * 3 phải bằng 6");
        assertEquals(-6, calculator.multiply(2, -3), "2 * (-3) phải bằng -6");
        assertEquals(0, calculator.multiply(0, 5), "0 * 5 phải bằng 0");
    }

    // Kiểm thử phép chia
    @Test
    public void testDivide() {
        assertEquals(2, calculator.divide(6, 3), "6 / 3 phải bằng 2");
        assertEquals(-2, calculator.divide(6, -3), "6 / (-3) phải bằng -2");
        assertEquals(0, calculator.divide(0, 3), "0 / 3 phải bằng 0");
    }

    // Kiểm thử phép chia cho 0 (phải ném ngoại lệ)
    @Test
    public void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0), "Chia cho 0 ");
    }
}
