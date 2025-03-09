package org.example;

import services.DiscountCalculationService;
import services.FileReaderService;
import services.PrintingService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path filePath = Paths.get("src/main/java/data/input.txt");

        // Create service instances
        FileReaderService fileReaderService = new FileReaderService();
        DiscountCalculationService discountCalculationService = new DiscountCalculationService();
        PrintingService printingService = new PrintingService();

        try {
            List<String> lines = fileReaderService.readFile(filePath);

            for (String line : lines) {
                String[] parts = line.split(" ");
                if (parts.length != 3) {
                    printingService.print(parts[0], parts.length > 1 ? parts[1] : "", parts.length > 2 ? parts[2] : "", "Ignored");
                    continue;
                }

                String dateStr = parts[0];
                String sizeStr = parts[1];
                String providerStr = parts[2];
                String priceAndDiscount = discountCalculationService.applyDiscount(dateStr, sizeStr, providerStr);

                if (priceAndDiscount.equals("Ignored")) {
                    printingService.print(dateStr, sizeStr, providerStr, "Ignored");
                    continue;
                }

                printingService.print(dateStr, sizeStr, providerStr, priceAndDiscount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}