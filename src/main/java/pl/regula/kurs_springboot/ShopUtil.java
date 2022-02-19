package pl.regula.kurs_springboot;


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
        Random random = new Random();
        Product product1 = new Product("banana", RANDOM_MIN + (RANDOM_MAX - RANDOM_MIN) * random.nextDouble());
        Product product2 = new Product("orange", RANDOM_MIN + (RANDOM_MAX - RANDOM_MIN) * random.nextDouble());
        Product product3 = new Product("pear", RANDOM_MIN + (RANDOM_MAX - RANDOM_MIN) * random.nextDouble());
        Product product4 = new Product("apple", RANDOM_MIN + (RANDOM_MAX - RANDOM_MIN) * random.nextDouble());
        Product product5 = new Product("strawberry", RANDOM_MIN + (RANDOM_MAX - RANDOM_MIN) * random.nextDouble());
        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        double summary = productList.stream().mapToDouble(p -> p.getPrice()).sum();
        System.out.println("Summary of products: " + df.format(summary));
        return productList;
    }

    public static double addDiscount(double price, double discount){
        return price - (price * discount/100);
    }

    public static double addVat(double price, double vat){
        return price + (price * vat/100);
    }

    public static double addDiscountAfterVat(double price, double vat, double discount){
        double addedVat = addVat(price, vat);
        return addDiscount(addedVat, discount);
    }
}
