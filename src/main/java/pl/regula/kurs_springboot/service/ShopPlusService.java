package pl.regula.kurs_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.regula.kurs_springboot.model.Product;
import pl.regula.kurs_springboot.model.ShopCart;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static pl.regula.kurs_springboot.util.ShopUtil.addVat;
import static pl.regula.kurs_springboot.util.ShopUtil.initialiseShop;

@Service
@Profile("Plus")
public class ShopPlusService implements Shop {

    private ShopCart shopCart;
    private List<Product> productList;

    @Autowired
    public ShopPlusService(ShopCart shopCart) {
        productList = initialiseShop();
        this.shopCart = shopCart;
    }

    @Value("${vat-value}")
    private int vat;

    @Value("${spring.profiles.active}")
    private String profile;

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        System.out.println("WARIANT " + profile);
        productList.forEach(System.out::println);
        List<Product> newProducts = productList.stream().map(p ->
                new Product(p.getName(), addVat(p.getPrice(), new BigDecimal(vat)))
        ).collect(toList());
        newProducts.forEach(p->shopCart.getProducts().add(p));
        System.out.println("=============================================");
        System.out.println("vat " +vat + "% on all products");
        newProducts.forEach(System.out::println);
        System.out.println("Products succesfully added to shopcart.");
    }

}
