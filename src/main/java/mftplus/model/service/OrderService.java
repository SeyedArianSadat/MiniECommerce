package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Order;
import mftplus.model.repository.OrderRepository;

import java.util.Collections;
import java.util.List;

public class OrderService implements Service<Order, Integer> {
    @Getter
    private static OrderService service = new OrderService();

    private OrderService() {


    }

    @Override
    public void save(Order order) throws Exception {
        try (OrderRepository orderRepository = new OrderRepository()) {
            orderRepository.save(order);
        }

    }

    @Override
    public void edit(Order order) throws Exception {
        try (OrderRepository orderRepository = new OrderRepository()) {
            orderRepository.save(order);
        }

    }

    @Override
    public void delete(Integer id) throws Exception {
        try (OrderRepository orderRepository = new OrderRepository()) {
            orderRepository.delete(id);
        }

    }

    @Override
    public List<Order> findAll() throws Exception {
        try (OrderRepository orderRepository = new OrderRepository()) {
            return orderRepository.findAll();
        }
    }

    @Override
    public Order findById(Integer id) throws Exception {
        try (OrderRepository orderRepository = new OrderRepository()) {
            return orderRepository.findById(id);
        }

    }
}
