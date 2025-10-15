package mftplus.model.service;

import mftplus.model.entity.Order;

import java.util.Collections;
import java.util.List;

public class OrderService implements Service<Order,Integer> {
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
