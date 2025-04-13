package poly.com.lab4ktnc;


import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class VatCalculatorTest {
  public void f() {
	  VatCalculator calc = new VatCalculator();
	  double expected = 10;
	  Assert.assertEquals(calc.getVatOnAmount(100), expected);
	  Assert.assertNotEquals(calc.getVatOnAmount(0), expected);

	  }
}
