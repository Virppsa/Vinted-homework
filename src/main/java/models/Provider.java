package models;

import java.util.Map;

public enum Provider {
    LP(Map.of(
            Size.S, 1.50,
            Size.M, 4.90,
            Size.L, 6.90
    )),
    MR(Map.of(
            Size.S, 2.00,
            Size.M, 3.00,
            Size.L, 4.00
    ));

    private final Map<Size, Double> prices;
    Provider(Map<Size, Double> prices) {
        this.prices = prices;
    }
    public double getPrice(Size packageSize) {
        return prices.get(packageSize);
    }
}