import org.junit.jupiter.api.Test;
import services.DiscountCalculationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiscountCalculationServiceTest {
    DiscountCalculationService discountService = new DiscountCalculationService();

    @Test
    public void testValidDiscountCalculation() {
        String result = discountService.applyDiscount("2025-03-01", "S", "MR");
        assertEquals("1.50 0.50", result);  // Replace with your expected output
    }

    @Test
    public void testInvalidSize() {
        String result = discountService.applyDiscount("2025-03-01", "XXL", "MR");
        assertEquals("Ignored", result);  // Should return "Ignored"
    }

    @Test
    public void testInvalidProvider() {
        String result = discountService.applyDiscount("2025-03-01", "S", "XYZ");
        assertEquals("Ignored", result);  // Should return "Ignored"
    }

    @Test
    public void testInvalidDateFormat() {
        assertThrows(java.time.format.DateTimeParseException.class, () -> {
            discountService.applyDiscount("2025-13-01", "S", "MR");
        });
    }

    @Test
    public void testThirdLSizeFree() {
        String result1 = discountService.applyDiscount("2025-03-01", "L", "LP");
        String result2 = discountService.applyDiscount("2025-03-02", "L", "LP");
        String result3 = discountService.applyDiscount("2025-03-03", "L", "LP");

        assertEquals("6.90 -", result1);
        assertEquals("6.90 -", result2);
        assertEquals("0.00 6.90", result3);
    }

    @Test
    public void testAccumulativeDiscounts() {
        String result1 = discountService.applyDiscount("2025-03-01", "S", "MR");
        String result2 = discountService.applyDiscount("2025-03-02", "S", "MR");
        String result3 = discountService.applyDiscount("2025-03-03", "S", "MR");
        String result4 = discountService.applyDiscount("2025-03-04", "S", "MR");

        assertEquals("1.50 0.50", result1);
        assertEquals("1.50 0.50", result2);
        assertEquals("1.50 0.50", result3);
        assertEquals("1.50 0.50", result4);
    }

    @Test
    public void testDateTransition() {
        String result1 = discountService.applyDiscount("2025-01-31", "S", "MR");
        String result2 = discountService.applyDiscount("2025-02-01", "S", "MR");

        assertEquals("1.50 0.50", result1);
        assertEquals("1.50 0.50", result2);
    }

}
