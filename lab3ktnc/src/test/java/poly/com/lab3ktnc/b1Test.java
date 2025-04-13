package poly.com.lab3ktnc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

 class b1Test {
	@BeforeAll 
	static void initAll() {
	System.out.println("@BeforeAll - Run before all methods once");
	}
	@BeforeEach 
	void init() {

	System.out.println(" @BeforeEach - Run before each test methods ");
	}
	@DisplayName("First test")
	@Test
	void testMethod1() {
	System.out.println(" Test method 1");

	}
	@Test
	@Disabled
	void testMethod2() {
	System.out.println(" Test method 2");
	}
	@Test
	void testMethod3() {
	System.out.println(" Test method 3");
	}
	@AfterEach 
	void tearDown() {
	System.out.println(" @AfterEach - Run after each test methods");

	}

	@AfterAll
	static void tearDownAll() {
	System.out.println(" @AfterAll - Run after all test methods once");
}
}
