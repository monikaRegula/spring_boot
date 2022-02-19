package pl.regula.kurs_springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static pl.regula.kurs_springboot.ShopUtil.addDiscountAfterVat;
import static pl.regula.kurs_springboot.ShopUtil.initialiseShop;

@Service
@Profile("Pro")
public class ShopProService implements Shop {

    @Value("${discount-value}")
    private int discount;

    @Value("${vat-value}")
    private int vat;

    @Value("${spring.profiles.active}")
    private String profile;

    private ShopCart shopCart;
    private List<Product> productList;

    @Autowired
    public ShopProService(ShopCart shopCart) {
        this.shopCart = shopCart;
        productList = initialiseShop();
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        System.out.println("WARIANT " + profile);
        productList.forEach(System.out::println);
        List<Product> newProducts = productList.stream().map(p ->
                new Product(p.getName(), addDiscountAfterVat(p.getPrice(), vat, discount))
        ).collect(toList());
        System.out.println("=========================================");
        System.out.println("All product has "+vat + "% of tax");
        System.out.println("Discount " +discount + "% on all products");
        newProducts.forEach(System.out::println);
        newProducts.forEach(p->shopCart.getProducts().add(p));
        System.out.println("Products succesfully added to shopcart.");
    }
}
