package poly.com.lab3ktnc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class b3Test {


    // Test với các số nguyên dương (kết quả mong đợi: TRUE)
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10, 100, 9999})
    public void testIsPositiveInteger_Valid(int number) {
        assertTrue(b3.isPositiveInteger(number), number + " phải là số nguyên dương");
    }

  
    @ParameterizedTest
    @ValueSource(ints = {1,-1, -10, -100, -9999})
    public void testIsPositiveInteger_Invalid(int number) {
        assertFalse(b3.isPositiveInteger(number), number + " không phải là số nguyên dương");
    }

}
