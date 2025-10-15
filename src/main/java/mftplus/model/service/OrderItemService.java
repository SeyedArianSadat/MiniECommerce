package mftplus.model.service;

import mftplus.model.entity.OrderItem;

import java.util.Collections;
import java.util.List;

public class OrderItemService implements Service<OrderItem,Integer> {
    @Override
    public void save(OrderItem orderItem) throws Exception {

    }

    @Override
    public void edit(OrderItem orderItem) throws Exception {

    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public List<OrderItem> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public OrderItem findById(Integer id) throws Exception {
        return null;
    }
}
