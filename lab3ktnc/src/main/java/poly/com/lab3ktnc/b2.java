package poly.com.lab3ktnc;

public class b2 {
	//A Phép cộng
	public int add(int a, int b) {
	return a + b;
	}
	// Phép trừ
	public int subtract(int a, int b) {
	return a - b;
	}
	// Phép nhân
	public int multiply(int a, int b) { 
	return a * b;
	}
	// Phép chia
	public int divide(int a, int b) {
	if (b == 0) {
	throw new ArithmeticException("Cannot divide by zero");
	}
	return a / b;
}
}
