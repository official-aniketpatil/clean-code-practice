package com.epam.engx.cleancode.functions.task4;

import com.epam.engx.cleancode.functions.task4.thirdpartyjar.Product;

import java.util.Iterator;
import java.util.List;

public class Order {

    private List<Product> products;
    private double orderPrice;

    public Double getPriceOfAvailableProducts() {
        removeNotAvailableProducts();
    	return calculateOrderPrice(products);
    }
    
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    private boolean isProductNotAvailable(Product product) {
    	return !product.isAvailable();
    }
    
    private Double calculateOrderPrice(List<Product> products) {
    	for (Product p : products)
            orderPrice += p.getProductPrice();
        return orderPrice;
    }
    public void removeNotAvailableProducts() {
    	Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (isProductNotAvailable(p))
                iterator.remove();
        }
    }
    
}
