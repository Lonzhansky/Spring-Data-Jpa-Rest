package org.example.app.service.product;

import org.example.app.dto.product.ProductDtoRequest;
import org.example.app.entity.product.Product;
import org.example.app.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService extends BaseService<Product, ProductDtoRequest> {
    Product create(ProductDtoRequest request);
    Optional<List<Product>> getAll();
    Product getById(Long id);
    Product updateById(Long id, ProductDtoRequest request);
    boolean deleteById(Long id);
    List<Product> findByPrice(Double price);
}
