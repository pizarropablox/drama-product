package com.drama.drama_product.service;

import com.drama.drama_product.model.Product;
import com.drama.drama_product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
void testCreateProduct_Success() {
    Product product = new Product();
    product.setName("Product 1");
    product.setPrice(100.0);
    product.setDescription("Description of product 1");

    when(productRepository.save(any(Product.class))).thenReturn(product);

    Product createdProduct = productService.createProduct(product);

    assertNotNull(createdProduct);
    assertEquals("Product 1", createdProduct.getName());
    assertEquals(100.0, createdProduct.getPrice());
}

}
