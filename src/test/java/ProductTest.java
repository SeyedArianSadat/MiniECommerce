import mftplus.model.entity.Product;
import mftplus.model.service.ProductService;

public class ProductTest {
    public static void main(String[] args) throws Exception {
        Product product1=Product.builder()
                .productId(1)
                .Name("sneaker")
                .price(200)
                .stock(3)
                .build();
        ProductService.getService().save(product1);

    }
}
