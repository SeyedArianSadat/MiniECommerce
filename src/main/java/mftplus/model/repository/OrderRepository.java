package mftplus.model.repository;


import mftplus.model.entity.Order;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.OrderMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements Repository<Order, Integer>, AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private final OrderMapper Mapper = new OrderMapper();

    public OrderRepository(Connection connection) throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Order order) throws Exception {
        order.setOrderId(ConnectionProvider.getProvider().getNextId("ORDER_SEQ"));
        preparedStatement = connection.prepareStatement("insert into ORDERS(ORDER_ID,CUSTOMER_ID, ORDER_DATE,TOTAL_AMOUNT) VALUES(?,?,?,?)");
        preparedStatement.setInt(1, order.getOrderId());
        preparedStatement.setInt(2, order.getCustomerId());
        preparedStatement.setDate(3, Date.valueOf(order.getOrderDate()));
        preparedStatement.setInt(4, order.getTotalAmount());
        preparedStatement.executeUpdate();

    }

    @Override
    public void edit(Order order) throws Exception {
        preparedStatement = connection.prepareStatement("update ORDERS set  CUSTOMER_ID=?,ORDER_DATE=?,TOTAL_AMOUNT=? where ORDER_ID=?");
        preparedStatement.setInt(1, order.getCustomerId());
        preparedStatement.setDate(2, Date.valueOf(order.getOrderDate()));
        preparedStatement.setInt(3, order.getTotalAmount());
        preparedStatement.setInt(4, order.getOrderId());
        preparedStatement.executeUpdate();

    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("delete from ORDERS where ORDER_ID=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

    @Override
    public List<Order> findAll() throws Exception {
        List<Order> orders = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from ORDERS order by ORDER_ID");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next())
            orders.add(Mapper.map(rs));
        return orders;

    }

    @Override
    public Order findById(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from ORDERS where ORDER_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.next() ? Mapper.map(rs) : null;

    }

    @Override
    public void close() throws Exception {
            if (connection != null) {
            connection.close();
            connection.close();
            }
        }

    }
