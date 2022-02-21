package pl.regula.kurs_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.regula.kurs_springboot.model.Product;
import pl.regula.kurs_springboot.model.ShopCart;

import java.util.List;

import static pl.regula.kurs_springboot.util.ShopUtil.initialiseShop;


@Service
@Profile("Start")
public class ShopService implements Shop {

    private List<Product> productList;

    private ShopCart shopCart;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public ShopService(ShopCart shopCart) {
        productList = initialiseShop();
        this.shopCart = shopCart;
    }


    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        System.out.println("WARIANT "+ profile);
        productList.forEach(System.out::println);
        shopCart.getProducts().addAll(productList);
        System.out.println("=============================================");
        System.out.println("Products succesfully added to shopcart.");
    }
}
