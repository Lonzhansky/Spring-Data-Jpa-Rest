package org.example.app.entity.product;

import org.example.app.dto.product.ProductDtoRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product dtoCreateToEntity(ProductDtoRequest request) {
        Product product = new Product();

        Long id = request.id();
        if (id != null) product.setId(id);

        String productName = request.productName();
        if (productName != null) {
            if (!productName.isBlank())
                product.setProductName(productName);
        }

        String measure = request.measure();
        if (measure != null) {
            if (!measure.isBlank())
                product.setMeasure(measure);
        }

        Double quota = request.quota();
        if (quota != null) product.setQuota(quota);

        Double price = request.price();
        if (price != null) product.setPrice(price);

        return product;
    }

    public Product dtoUpdateToEntity(Long id, ProductDtoRequest request,
                                  Product productToUpdate) {

        if (id != null) productToUpdate.setId(id);

        String productName = request.productName();
        if (productName != null) {
            if (!productName.isBlank())
                productToUpdate.setProductName(productName);
        }

        String measure = request.measure();
        if (measure != null) {
            if (!measure.isBlank())
                productToUpdate.setMeasure(measure);
        }

        Double quota = request.quota();
        if (quota != null) productToUpdate.setQuota(quota);

        Double price = request.price();
        if (price != null) productToUpdate.setPrice(price);

        return productToUpdate;
    }
}
