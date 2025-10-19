import mftplus.model.entity.Customer;
import mftplus.model.service.CustomerService;


public class CustomerTest {
    public static void main(String[] args) throws Exception {
        Customer customer1=Customer
                .builder()
                .customerId(1)
                .Name("ali")
                .Family("alizade")
                .Email("dughdua")
                .Phone("12344")
                .build();
        CustomerService.getService().save(customer1);
        CustomerService.getService().findAll();
    }
}
