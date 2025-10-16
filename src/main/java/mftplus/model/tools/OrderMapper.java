package mftplus.model.tools;

import mftplus.model.entity.Order;
import mftplus.model.service.CustomerService;

import java.sql.ResultSet;

public class OrderMapper {
    public Order map(ResultSet rs) throws Exception {
        return Order.builder()
                .orderId(rs.getInt("ORDER_ID"))
                .customerId(rs.getInt("CUSTOMER_ID"))
                .orderDate(rs.getDate("ORDER_DATE").toLocalDate())
                .totalAmount(rs.getInt("TOTAL_AMOUNT"))
                .build();
    }
}
