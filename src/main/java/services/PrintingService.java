package services;

public class PrintingService {
    public void print(String dateStr, String sizeStr, String providerStr, String priceAndDiscount) {
        if (priceAndDiscount.equals("Ignored")) {
            System.out.printf("%s %s %s %s\n", dateStr, sizeStr, providerStr, "Ignored");
        } else {
            String[] parts = priceAndDiscount.split(" ");
            double priceAfterDiscount = Double.parseDouble(parts[0]);
            String discount = parts[1];
            System.out.printf("%s %s %s %.2f %s\n", dateStr, sizeStr, providerStr, priceAfterDiscount, discount);
        }
    }
}
