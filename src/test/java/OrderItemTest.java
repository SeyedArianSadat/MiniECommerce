import mftplus.model.entity.OrderItem;
import mftplus.model.service.OrderItemService;

public class OrderItemTest {
    public static void main(String[] args) throws Exception{
        OrderItem orderItem1=OrderItem.builder()
                .orderId(1)
                .itemId(1)
                .price(20)
                .quantity(20)
                .productId(1)
                .quantity(1)
                .build();
        OrderItemService.getService().save(orderItem1);

    }
}
