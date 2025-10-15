package mftplus.model.repository;

import mftplus.model.entity.Product;

import java.util.Collections;
import java.util.List;

public class ProductRepository implements Repository<Product,Integer>,AutoCloseable {
    @Override
    public void close() throws Exception {

    }

    @Override
    public void save(Product product) throws Exception {

    }

    @Override
    public void edit(Product product) throws Exception {

    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public List<Product> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Product findById(Integer id) throws Exception {
        return null;
    }
}
