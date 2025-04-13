package poly.com.testmaven;

public class Calculator {

    // Phương thức cộng
    public int add(int a, int b) {
        return a + b;
    }

    // Phương thức trừ
    public int subtract(int a, int b) {
        return a - b;
    }

    // Phương thức nhân
    public int multiply(int a, int b) {
        return a * b;
    }

    // Phương thức chia, xử lý trường hợp chia cho 0
    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Không thể chia cho 0");
        }
        return a / b;
    }
}
