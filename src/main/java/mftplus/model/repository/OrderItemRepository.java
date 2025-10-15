package mftplus.model.repository;

import mftplus.model.entity.OrderItem;

import java.util.Collections;
import java.util.List;

public class OrderItemRepository implements Repository<OrderItem,Integer>,AutoCloseable {
    @Override
    public void close() throws Exception {

    }

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
