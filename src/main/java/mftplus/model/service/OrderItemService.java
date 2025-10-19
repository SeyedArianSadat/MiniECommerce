package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.OrderItem;
import mftplus.model.repository.OrderItemRepository;


import java.util.List;

public class OrderItemService implements Service<OrderItem, Integer> {
    @Getter
    private static OrderItemService service = new OrderItemService();

    private OrderItemService() {


    }
    @Override
    public void save(OrderItem orderItem) throws Exception {
        try (OrderItemRepository orderItemRepository = new OrderItemRepository()) {
            orderItemRepository.save(orderItem);
        }


    }

    @Override
    public void edit(OrderItem orderItem) throws Exception {
        try (OrderItemRepository orderItemRepository = new OrderItemRepository()) {
            orderItemRepository.save(orderItem);
        }

    }

    @Override
    public void delete(Integer id) throws Exception {
        try (OrderItemRepository orderItemRepository = new OrderItemRepository()) {
            orderItemRepository.delete(id);
        }

    }

    @Override
    public List<OrderItem> findAll() throws Exception {
        try (OrderItemRepository orderItemRepository = new OrderItemRepository()) {
            return orderItemRepository.findAll();
        }

    }

    @Override
    public OrderItem findById(Integer id) throws Exception {
        try (OrderItemRepository orderItemRepository = new OrderItemRepository()) {
            return orderItemRepository.findById(id);
        }

    }
}
