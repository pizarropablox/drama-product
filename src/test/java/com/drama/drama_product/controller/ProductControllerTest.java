package com.drama.drama_product.controller;

import com.drama.drama_product.model.Product;
import com.drama.drama_product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        // Inicializa los mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct_Success() {
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(100.0);
        product.setDescription("Description of product 1");

        // Simula la respuesta del servicio
        when(productService.createProduct(product)).thenReturn(product);

        // Llama al endpoint del controlador
        ResponseEntity<Product> response = productController.createProduct(product);

        // Verificaciones
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(product, response.getBody());
    }

    @Test
    void testGetAllProducts() {
        Product product = new Product();
        product.setName("Product 1");
        List<Product> products = List.of(product);

        // Simula la respuesta del servicio
        when(productService.getAllProducts()).thenReturn(products);

        // Llama al endpoint del controlador
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        // Verificaciones
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(products, response.getBody());
    }

    @Test
    void testGetProductById_Success() {
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(100.0);
        product.setDescription("Description of product 1");

        // Simula la respuesta del servicio
        when(productService.getProductById(1L)).thenReturn(product);

        // Llama al endpoint del controlador
        ResponseEntity<Product> response = productController.getProductById(1L);

        // Verificaciones
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(product, response.getBody());
    }

    @Test
    void testUpdateProduct_Success() {
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Product 1");
        existingProduct.setPrice(100.0);
        existingProduct.setDescription("Description of product 1");

        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");
        updatedProduct.setPrice(120.0);
        updatedProduct.setDescription("Updated description");

        // Simula la respuesta del servicio
        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(updatedProduct);

        // Llama al endpoint del controlador
        ResponseEntity<Product> response = productController.updateProduct(1L, updatedProduct);

        // Verificaciones
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(updatedProduct.getName(), response.getBody().getName());
        assertEquals(120.0, response.getBody().getPrice());
    }

    @Test
    void testDeleteProduct_Success() {
        // Llama al endpoint del controlador para borrar un producto
        ResponseEntity<Void> response = productController.deleteProduct(1L);

        // Verificaciones
        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value()); // No content
        verify(productService, times(1)).deleteProduct(1L); // Verifica que el servicio fue llamado
    }
}
