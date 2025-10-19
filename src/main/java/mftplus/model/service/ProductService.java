package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Product;
import mftplus.model.repository.ProductRepository;

import java.util.Collections;
import java.util.List;

public class ProductService implements Service<Product, Integer> {
    @Getter
    private static ProductService service = new ProductService();

    private ProductService() {


    }

    @Override
    public void save(Product product) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            productRepository.save(product);
        }


    }

    @Override
    public void edit(Product product) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            productRepository.edit(product);
        }

    }

    @Override
    public void delete(Integer id) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            productRepository.delete(id);
        }

    }

    @Override
    public List<Product> findAll() throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            return productRepository.findAll();
        }
    }

    @Override
    public Product findById(Integer id) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            return productRepository.findById(id);
        }
    }
}
