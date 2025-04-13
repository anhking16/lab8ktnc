package poly.com.testmaven;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AppTest2  {
	

	
	public void testApp2() {

	assertTrue( true );
	}
	@Test
	public void testIsEventNumber() {
		App demo1 = new App();
		boolean result = demo1.isEventNumber(2);
		assertTrue(result);
	}
	@Test
	public void testIsEventNumber1() {
	App demo1 = new App();
	boolean result = demo1.isEventNumber(4);
	assertTrue(result);

	}
	
}
