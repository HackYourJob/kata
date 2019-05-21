package workshop.bdd.fruitshop.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private double totalPrice = 0L;
    private Map<String, Double> productPrices = new HashMap<>();
    private Map<String, Double> productDiscount = new HashMap<>();
    private List<CartItem> items = new ArrayList<>();

    public void addProduct(String productName, int quantity) {
        items.add(new CartItem(productName, quantity));
    }

    public void setProductPrice(String productName, Double productUnitPrice) {
        productPrices.put(productName, productUnitPrice);
    }

    // TODO CBO: 11/04/19 use strategy Map<CanApply, Apply>
    public Double computeTotalPrice() {
        for (CartItem item : items) {
            if (productDiscount.get(item.productName) != null) {
                double productsPrice = productPrices.get(item.productName) * item.quantity;
                totalPrice += productsPrice * productDiscount.get(item.productName);
            } else {
                totalPrice += productPrices.get(item.productName) * item.quantity;
            }
        }
        return totalPrice;
    }

    public void applyReduction(String productName, Double discountRatio) {
        productDiscount.put(productName, 1 - discountRatio / 100);
    }

    private class CartItem {
        final String productName;
        final int quantity;

        CartItem(String productName, int quantity) {
            this.productName = productName;
            this.quantity = quantity;
        }
    }
}
