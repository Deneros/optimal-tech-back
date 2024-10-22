package org.nicolas.optimaltech.service;

import lombok.RequiredArgsConstructor;
import org.nicolas.optimaltech.dto.CreateProductRequest;
import org.nicolas.optimaltech.dto.ProductResponse;
import org.nicolas.optimaltech.dto.UpdateProductRequest;
import org.nicolas.optimaltech.entity.Product;
import org.nicolas.optimaltech.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        Product product = Product.builder()
                .name(createProductRequest.getName())
                .price(createProductRequest.getPrice())
                .stock(createProductRequest.getStock())
                .stockMinimum(createProductRequest.getStockMinimum())
                .build();

        Product savedProduct = productRepository.save(product);

        return ProductResponse.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .price(savedProduct.getPrice())
                .stock(savedProduct.getStock())
                .stockMinimum(savedProduct.getStockMinimum())
                .build();
    }

    public Optional<ProductResponse> updateProduct(Long id, UpdateProductRequest updateProductRequest) {
        return productRepository.findById(id).map(existingProduct -> {
            if (updateProductRequest.getName() != null) {
                existingProduct.setName(updateProductRequest.getName());
            }
            if (updateProductRequest.getPrice() != null) {
                existingProduct.setPrice(updateProductRequest.getPrice());
            }
            if (updateProductRequest.getStock() != null) {
                existingProduct.setStock(updateProductRequest.getStock());
            }
            if (updateProductRequest.getStockMinimum() != null) {
                existingProduct.setStockMinimum(updateProductRequest.getStockMinimum());
            }

            Product savedProduct = productRepository.save(existingProduct);

            return ProductResponse.builder()
                    .id(savedProduct.getId())
                    .name(savedProduct.getName())
                    .price(savedProduct.getPrice())
                    .stock(savedProduct.getStock())
                    .stockMinimum(savedProduct.getStockMinimum())
                    .build();
        });
    }


    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
