package com.jamlech.productservice.product;

import com.jamlech.productservice.product.dto.ProductRequest;
import com.jamlech.productservice.product.dto.ProductResponse;
import com.jamlech.productservice.product.exceptions.ProductNotFoundException;
import com.jamlech.productservice.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

@Transactional
    public ProductResponse createProduct(ProductRequest request) {
        var product = Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build();
        var savedProduct = productRepository.save(product);
        log.info("Product created: {}", product.getName());
        return productMapper.mapToProductResponse(savedProduct);
    }
    @Transactional(readOnly = true)
    public ProductResponse getProduct(String id) {
        var product = productRepository.findById(id).orElseThrow(() ->
                        new ProductNotFoundException(
                                String.format("Product with id %s not found", id))
                );
        log.info("Product found: {}", product.getName());
        return productMapper.mapToProductResponse(product);
    }
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        log.info("Products found: {}", products.size());
        return products.stream()
                .map(productMapper::mapToProductResponse)
                .toList();
    }
    @Transactional
    public ProductResponse updateProduct(String id, ProductRequest request) {
        var product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException(
                        String.format("Product with id %s not found", id))
        );
        if (request.name() != null) {
            product.setName(request.name());
        }
        if (request.description() != null) {
            product.setDescription(request.description());
        }
        if (request.price() != null) {
            product.setPrice(request.price());
        }
        var savedProduct = productRepository.save(product);
        log.info("Product updated: {}", product.getName());
        return productMapper.mapToProductResponse(savedProduct);
    }
    @Transactional
    public void deleteProduct(String id) {
    var product = productRepository.findById(id).orElseThrow(() ->
            new ProductNotFoundException(
                    String.format("Product with id %s not found", id))
    );
        productRepository.delete(product);
        log.info("Product deleted: {}", id);
    }
}
