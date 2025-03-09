import org.junit.jupiter.api.Test;
import services.PrintingService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintingServiceTest {
    private String getPrintedOutput(String date, String size, String provider, String priceAndDiscount) {
        PrintingService printingService = new PrintingService();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        System.setOut(printStream);

        printingService.print(date, size, provider, priceAndDiscount);
        return outputStream.toString().trim();
    }

    @Test
    public void testValidPrintOutput() {
        String expectedOutput = "2025-03-01 S MR 1.50 0.50";
        String result = getPrintedOutput("2025-03-01", "S", "MR", "1.50 0.50");
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testInvalidPrintOutput() {
        String expectedOutput = "2025-03-01 XXL MR Ignored";
        String result = getPrintedOutput("2025-03-01", "XXL", "MR", "Ignored");
        assertEquals(expectedOutput, result);
    }
}
