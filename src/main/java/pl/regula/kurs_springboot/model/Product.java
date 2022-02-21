package pl.regula.kurs_springboot.model;


import java.math.BigDecimal;
import java.text.DecimalFormat;


public class Product {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private String name;
    private BigDecimal price;


    public Product() {
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + df.format(price) +
                '}';
    }
}
