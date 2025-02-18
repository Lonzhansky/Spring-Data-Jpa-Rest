package org.example.app.controller;

import org.example.app.dto.product.*;
import org.example.app.entity.product.Product;
import org.example.app.service.product.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

// @RestController - комбінація @Controller і @ResponseBody,
// що означає, що замість рендерингу сторінок він просто відповідає
// даними, які ми йому надали.
// Це природно для REST API, повертати інформацію після
// потрапляння в кінцеву точку API.
// @GetMapping, @DeleteMapping, @PostMapping
// зазначають типи HTTP-запитів, які оброблюють методи.
// Це похідні варіанти анотації @RequestMapping з методом
// RequestMethod.METHOD, встановленим для відповідних типів.
// @RequestMapping зіставляє REST-запити з контролером або
// методами оброблювача.
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

//    @Autowired
//    @Qualifier("productServiceImpl")
//    private ProductService productService;

//    @Autowired
//    public void setProductService(@Qualifier("productServiceImpl") ProductService productService) {
//        this.productService = productService;
//    }

    private final ProductService productService;

    public ProductController(@Qualifier("productServiceImpl") ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDtoCreateResponse> createProduct(
            @RequestBody ProductDtoRequest request) {
        Product product = productService.create(request);
        // ternary operator usage
        return (product != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoCreateResponse.of(true,
                                product)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoCreateResponse.of(false,
                                null));
    }

    @GetMapping
    public ResponseEntity<ProductDtoListResponse> getAllProducts() {
        Optional<List<Product>> optional = productService.getAll();
        if (optional.isPresent()) {
            List<Product> list = optional.get();
            return (!list.isEmpty()) ?
                    ResponseEntity.status(HttpStatus.OK)
                            .body(ProductDtoListResponse.of(false,
                                    list)) :
                    ResponseEntity.status(HttpStatus.OK)
                            .body(ProductDtoListResponse.of(true,
                                    Collections.emptyList()));
        } else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ProductDtoListResponse.of(true,
                            Collections.emptyList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoGetByIdResponse> getProductById(
            @PathVariable("id") Long id) {
        Product product = productService.getById(id);
        return (product != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoGetByIdResponse.of(id, true,
                                product)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoGetByIdResponse.of(id, false,
                                null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDtoUpdateResponse> updateProductById(
            @PathVariable("id") Long id,
            @RequestBody ProductDtoRequest request) {
        return (productService.getById(id) != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoUpdateResponse.of(id, true,
                                productService.updateById(id, request))) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoUpdateResponse.of(id, false,
                                null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDtoDeleteResponse> deleteProductById(
            @PathVariable(value = "id") Long id) {
        return (productService.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoDeleteResponse.of(id, true)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoDeleteResponse.of(id, false));
    }

    @GetMapping("/find-by-price/{price}")
    public ResponseEntity<ProductDtoListResponse> getByPrice(
            @PathVariable(value = "price") Double price) {
        List<Product> list = productService.findByPrice(price);
        return (!list.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoListResponse.of(false,
                                list)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoListResponse.of(true,
                                Collections.emptyList()));
    }

}
