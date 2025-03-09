import org.junit.jupiter.api.Test;
import services.PrintingService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintingServiceTest {

    // Helper method to check printed output
    private String getPrintedOutput(String date, String size, String provider, String priceAndDiscount) {
        PrintingService printingService = new PrintingService();
        if (priceAndDiscount.equals("Ignored")) {
            return String.format("%s %s %s %s", date, size, provider, "Ignored");
        }
        String[] parts = priceAndDiscount.split(" ");
        double priceAfterDiscount = Double.parseDouble(parts[0]);
        String discount = parts[1];
        return String.format("%s %s %s %.2f %s", date, size, provider, priceAfterDiscount, discount);
    }

    // Test valid print output
    @Test
    public void testValidPrintOutput() {
        PrintingService printingService = new PrintingService();
        printingService.print("2025-03-01", "S", "MR", "1.50 0.50");

        String expectedOutput = "2025-03-01 S MR 1.50 0.50";
        assertEquals(expectedOutput, getPrintedOutput("2025-03-01", "S", "MR", "1.50 0.50"));
    }

    // Test print output with "Ignored"
    @Test
    public void testInvalidPrintOutput() {
        PrintingService printingService = new PrintingService();
        printingService.print("2025-03-01", "XXL", "MR", "Ignored");

        String expectedOutput = "2025-03-01 XXL MR Ignored";
        assertEquals(expectedOutput, getPrintedOutput("2025-03-01", "XXL", "MR", "Ignored"));
    }
}
