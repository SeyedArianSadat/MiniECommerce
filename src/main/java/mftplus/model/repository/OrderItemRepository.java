package mftplus.model.repository;

import mftplus.model.entity.Customer;
import mftplus.model.entity.OrderItem;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.OrderItemMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderItemRepository implements Repository<OrderItem,Integer>,AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private final OrderItemMapper Mapper = new OrderItemMapper();

    public  OrderItemRepository() throws SQLException {
        connection= ConnectionProvider.getProvider().getOracleConnection();

    }
    @Override
    public void save(OrderItem orderItem) throws Exception{
        orderItem.setItemId(ConnectionProvider.getProvider().getNextId("ITEM_SEQ"));
        preparedStatement=connection.prepareStatement("insert into ORDER_ITEM(ORDER_ID,ITEM_ID,PRODUCT_ID,PRICE,QUANTITY) values(?,?,?,?,?)");
        preparedStatement.setInt(1, orderItem.getOrderId());
        preparedStatement.setInt(2, orderItem.getItemId());
        preparedStatement.setInt(3, orderItem.getProductId());
        preparedStatement.setInt(4, orderItem.getPrice());
        preparedStatement.setInt(5, orderItem.getQuantity());
        preparedStatement.executeUpdate();
    }

    @Override
    public void edit(OrderItem orderItem) throws Exception {
        preparedStatement=connection.prepareStatement("update ORDER_ITEM set ITEM_ID=?,PRODUCT_ID=?,PRICE=?,QUANTITY=? where ORDER_ID=?");
        preparedStatement.setInt(1, orderItem.getItemId());
        preparedStatement.setInt(2, orderItem.getProductId());
        preparedStatement.setInt(3, orderItem.getPrice());
        preparedStatement.setInt(4, orderItem.getQuantity());
        preparedStatement.setInt(5, orderItem.getOrderId());
        preparedStatement.executeUpdate();

    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement=connection.prepareStatement("delete from ORDER_ITEM where ORDER_ID=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

    @Override
    public List<OrderItem> findAll() throws Exception {
            List<OrderItem> orderItems = new ArrayList<>();
            preparedStatement = connection.prepareStatement("select * from ORDER_ITEM order by ORDER_ID");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                orderItems.add(Mapper.map(rs));
            return orderItems;
    }

    @Override
    public OrderItem findById(Integer id) throws Exception {
        preparedStatement=connection.prepareStatement("select * from ORDER_ITEM where ORDER_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.next() ? Mapper.map(rs) : null;

    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) {
            preparedStatement.close();
            connection.close();
        }
    }
}
