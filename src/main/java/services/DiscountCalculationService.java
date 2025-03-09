package services;

import models.Provider;
import models.Size;

import java.time.LocalDate;

public class DiscountCalculationService {
    private static double totalDiscountThisMonth = 0.0;
    private static int lpLCountThisMonth = 0;
    private static LocalDate currentMonth = LocalDate.now();

    public String applyDiscount(String dateStr, String sizeStr, String providerStr) {
        if (!isValidSize(sizeStr)) {
            return "Ignored";
        }

        if (!isValidProvider(providerStr)) {
            return "Ignored";
        }

        Size size = Size.valueOf(sizeStr);
        Provider provider = Provider.valueOf(providerStr);
        LocalDate date = LocalDate.parse(dateStr);

        if (!date.getMonth().equals(currentMonth.getMonth())) {
            currentMonth = date;
            totalDiscountThisMonth = 0.0;
            lpLCountThisMonth = 0;
        }

        double price = provider.getPrice(size);
        double discount = 0.0;

        if (size == Size.S) {
            double lpPrice = Provider.LP.getPrice(Size.S);
            double mrPrice = Provider.MR.getPrice(Size.S);
            price = Math.min(lpPrice, mrPrice);
            discount = Math.max(lpPrice, mrPrice) - price;
        }

        if (size == Size.L && provider == Provider.LP) {
            lpLCountThisMonth++;
            if (lpLCountThisMonth == 3) {
                discount = price;
                price = 0.0;
            }
        }

        if (totalDiscountThisMonth + discount > 10.0) {
            discount = 10.0 - totalDiscountThisMonth;
            price -= discount;
        }

        totalDiscountThisMonth += discount;

        return String.format("%.2f %s", price, (discount == 0.0) ? "-" : String.format("%.2f", discount));
    }

    // Helper methods
    private boolean isValidSize(String sizeStr) {
        try {
            Size.valueOf(sizeStr);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isValidProvider(String providerStr) {
        try {
            Provider.valueOf(providerStr);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
