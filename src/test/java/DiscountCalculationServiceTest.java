import org.junit.jupiter.api.Test;
import services.DiscountCalculationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiscountCalculationServiceTest {
    DiscountCalculationService discountService = new DiscountCalculationService();

    // Test valid input with "S" and "MR" provider
    @Test
    public void testValidDiscountCalculation() {
        String result = discountService.applyDiscount("2025-03-01", "S", "MR");
        assertEquals("1.50 0.50", result);  // Replace with your expected output
    }

    // Test invalid size
    @Test
    public void testInvalidSize() {
        String result = discountService.applyDiscount("2025-03-01", "XXL", "MR");
        assertEquals("Ignored", result);  // Should return "Ignored"
    }

    // Test invalid provider
    @Test
    public void testInvalidProvider() {
        String result = discountService.applyDiscount("2025-03-01", "S", "XYZ");
        assertEquals("Ignored", result);  // Should return "Ignored"
    }

    // Test invalid date format
    @Test
    public void testInvalidDateFormat() {
        assertThrows(java.time.format.DateTimeParseException.class, () -> {
            discountService.applyDiscount("2025-13-01", "S", "MR");
        });
    }

    // Test third "L" size free package via LP
    @Test
    public void testThirdLSizeFree() {
        String result1 = discountService.applyDiscount("2025-03-01", "L", "LP");
        String result2 = discountService.applyDiscount("2025-03-02", "L", "LP");
        String result3 = discountService.applyDiscount("2025-03-03", "L", "LP");

        assertEquals("6.90 -", result1);
        assertEquals("6.90 -", result2);
        assertEquals("0.00 6.90", result3);
    }
}
