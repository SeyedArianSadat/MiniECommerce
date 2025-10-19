import mftplus.model.entity.Order;
import mftplus.model.service.OrderService;

import java.time.LocalDate;

public class OrderTest {
    public static void main(String[] args) throws Exception{
        Order order1 =Order.builder()
                .orderId(1)
                .customerId(1)
                .orderDate(LocalDate.of(2000,12,2))
                .totalAmount(20)
                .build();
        OrderService.getService().save(order1);

    }
}
