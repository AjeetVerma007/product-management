package org.acme.product.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.product.entity.Product;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Transactional
    public Product createProduct(Product product) {
        product.persist();
        return product;
    }

    public Product getProduct(Long id) {
        return Product.findById(id);
    }

    public List<Product> getAllProducts() {
        return Product.listAll();
    }

    public List<Product> getAllProductsByPriceAsc() {
        return Product.list("ORDER BY price ASC");
    }

    @Transactional
    public Product updateProduct(Long id, Product product) {
        Product entity = Product.findById(id);
        if (entity != null) {
            entity.name = product.name;
            entity.description = product.description;
            entity.price = product.price;
            entity.quantity = product.quantity;
        }
        return entity;
    }

    @Transactional
    public boolean deleteProduct(Long id) {
        return Product.deleteById(id);
    }

    public boolean checkStockAvailability(Long id, Integer count) {
        Product product = Product.findById(id);
        return product != null && product.quantity >= count;
    }
}