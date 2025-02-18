package org.example.app.entity.product;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    @Id
    // GenerationType.IDENTITY покладається на IdentityGenerator,
    // який очікує значення, згенеровані стовпцем ідентичності в БД.
    // Ці значення автоматично збільшуються.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/generationtype
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String productName;
    @Column(name = "measure")
    private String measure;
    @Column(name = "quota")
    private Double quota;
    @Column(name = "price")
    private Double price;

    public Product() {
    }

    public Product(Long id, String productName, String measure, Double quota, Double price) {
        this.id = id;
        this.productName = productName;
        this.measure = measure;
        this.quota = quota;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(productName, product.productName) && Objects.equals(measure, product.measure) && Objects.equals(quota, product.quota) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(productName);
        result = 31 * result + Objects.hashCode(measure);
        result = 31 * result + Objects.hashCode(quota);
        result = 31 * result + Objects.hashCode(price);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", measure='" + measure + '\'' +
                ", quota=" + quota +
                ", price=" + price +
                '}';
    }
}
