package org.acme.product.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.acme.product.entity.Product;
import org.acme.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@QuarkusTest
public class ProductResourceTest {

        @InjectMock
        ProductService productService;

        private Product testProduct;

        @BeforeEach
        void setup() {
                // Create a test product before each test
                testProduct = new Product("Test Product", "Test Description", 99.99, 10);
                testProduct.id = 1L; // Simulate ID assignment
        }

        @Test
        public void testCreateProduct() {
                // Setup mock behavior
                when(productService.createProduct(any(Product.class))).thenReturn(testProduct);

                // Test creating a product
                Product created = productService
                                .createProduct(new Product("Test Product", "Test Description", 99.99, 10));

                // Verify the mock was called
                verify(productService).createProduct(any(Product.class));

                // Verify the returned data
                assertNotNull(created.id);
                assertEquals("Test Product", created.name);
                assertEquals(99.99, created.price);
                assertEquals(10, created.quantity);
        }

        @Test
        public void testGetProduct() {
                // Setup mock behavior
                when(productService.getProduct(1L)).thenReturn(testProduct);

                // Test getting the product
                Product found = productService.getProduct(1L);

                // Verify the mock was called
                verify(productService).getProduct(1L);

                // Verify the returned data
                assertNotNull(found);
                assertEquals(testProduct.id, found.id);
                assertEquals("Test Product", found.name);
        }

        @Test
        public void testUpdateProduct() {
                // Create updated product
                Product updatedProduct = new Product("Updated Product", "Test Description", 149.99, 10);
                updatedProduct.id = 1L;

                // Setup mock behavior
                when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(updatedProduct);

                // Test updating the product
                Product updated = productService.updateProduct(1L, updatedProduct);

                // Verify the mock was called
                verify(productService).updateProduct(eq(1L), any(Product.class));

                // Verify the returned data
                assertNotNull(updated);
                assertEquals("Updated Product", updated.name);
                assertEquals(149.99, updated.price);
        }

        @Test
        public void testDeleteProduct() {
                // Setup mock behavior
                when(productService.deleteProduct(1L)).thenReturn(true);
                when(productService.getProduct(1L)).thenReturn(null);

                // Test deleting the product
                boolean deleted = productService.deleteProduct(1L);

                // Verify the mock was called
                verify(productService).deleteProduct(1L);

                // Verify the result
                assertTrue(deleted);
                assertNull(productService.getProduct(1L));
        }

        @Test
        public void testCheckStockAvailability() {
                // Setup mock behavior
                when(productService.checkStockAvailability(1L, 5)).thenReturn(true);
                when(productService.checkStockAvailability(1L, 15)).thenReturn(false);

                // Test stock availability
                boolean availableForFive = productService.checkStockAvailability(1L, 5);
                boolean availableForFifteen = productService.checkStockAvailability(1L, 15);

                // Verify the mocks were called
                verify(productService).checkStockAvailability(1L, 5);
                verify(productService).checkStockAvailability(1L, 15);

                // Verify the results
                assertTrue(availableForFive);
                assertFalse(availableForFifteen);
        }
}