package org.example.app.service.product;

import org.example.app.dto.product.ProductDtoCreateResponse;
import org.example.app.dto.product.ProductDtoRequest;
import org.example.app.entity.product.Product;
import org.example.app.entity.product.ProductMapper;
import org.example.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Qualifier("productServiceImpl")
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper mapper;
    @Autowired
    @Qualifier("productRepository")
    private ProductRepository productRepository;

    @Override
    @Transactional
    public Product create(ProductDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        Product product = mapper.dtoCreateToEntity(request);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Optional<List<Product>> getAll() {
        Iterable<Product> iterable = productRepository.findAll();
        // Конвертуємо Iterable в List,
        // оскільки interface CrudRepository<T, ID>
        // має саме метод Iterable<T> findAll();
        List<Product> list =
                StreamSupport.stream(iterable.spliterator(), false)
                        .toList();
        // Запаковуємо List в Optional та повертаємо
        return Optional.of(list);
    }

    @Override
    @Transactional
    public Product getById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        return productRepository.findById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public Product updateById(Long id, ProductDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        if (id == null) {
            throw new IllegalArgumentException("Id must be provided!");
        }
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product productToUpdate =
                    mapper.dtoUpdateToEntity(id, request,
                            optional.get());
            productRepository.save(productToUpdate);
        }
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public List<Product> findByPrice(Double price) {
        return productRepository.findByPrice(price)
                .orElse(Collections.emptyList());
    }
}
