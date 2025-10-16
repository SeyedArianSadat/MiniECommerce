package mftplus.model.tools;

import mftplus.model.entity.OrderItem;

import java.sql.ResultSet;

public class OrderItemMapper {
    public OrderItem map(ResultSet rs) throws Exception {
        return OrderItem.builder()
                .itemId(rs.getInt("ITEM_ID"))
            .orderId(rs.getInt("ORDER_ID"))
                .productId(rs.getInt("PRODUCT_ID"))
                .price(rs.getInt("PRICE"))
                .quantity(rs.getInt("QUANTITY"))
                .build();
    }
}
