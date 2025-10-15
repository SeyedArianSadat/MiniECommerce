package mftplus.model.repository;

import mftplus.model.entity.Order;

import java.util.Collections;
import java.util.List;

public class OrderRepository implements Repository<Order,Integer>,AutoCloseable {
    @Override
    public void close() throws Exception {

    }

    @Override
    public void save(Order order) throws Exception {

    }

    @Override
    public void edit(Order order) throws Exception {

    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public List<Order> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Order findById(Integer id) throws Exception {
        return null;
    }
}
