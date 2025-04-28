package model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL)
    private List<Product> products;

    public Farmer() {
    }

    public Farmer(String name) {
        this.name = name;
    }

   
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
