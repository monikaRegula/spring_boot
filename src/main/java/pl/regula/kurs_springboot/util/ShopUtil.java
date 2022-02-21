package pl.regula.kurs_springboot.util;


import pl.regula.kurs_springboot.model.Product;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShopUtil {
    private static final int RANDOM_MIN = 50;
    private static final int RANDOM_MAX = 300;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static List<Product> initialiseShop() {
        List<Product> productList;
        Product product1 = new Product("banana", generateRandomPrice());
        Product product2 = new Product("orange", generateRandomPrice());
        Product product3 = new Product("pear", generateRandomPrice());
        Product product4 = new Product("apple", generateRandomPrice());
        Product product5 = new Product("strawberry", generateRandomPrice());
        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        BigDecimal summary = productList.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Summary of products: " + df.format(summary));
        return productList;
    }

    public static BigDecimal addDiscount(BigDecimal price, BigDecimal discount){
        return price.subtract (price.multiply(discount.divide(BigDecimal.valueOf(100.0))));
    }

    public static BigDecimal addVat(BigDecimal price, BigDecimal vat){
        return price.add(price.multiply(vat.divide(BigDecimal.valueOf(100.0))));
    }

    public static BigDecimal addDiscountAfterVat(BigDecimal price, BigDecimal vat, BigDecimal discount){
        BigDecimal addedVat = addVat(price, vat);
        return addDiscount(addedVat, discount);
    }

    private static BigDecimal generateRandomPrice(){
        Random random = new Random();
        double randomDouble = RANDOM_MIN + (RANDOM_MAX - RANDOM_MIN) * random.nextDouble();
        return new BigDecimal(randomDouble);
    }
}
