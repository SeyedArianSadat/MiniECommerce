import mftplus.model.entity.Customer;

public class CustomerTest {
    public static void main(String[] args) {
        Customer customer=Customer.builder()
                .Name("ali")
                .Family("alizade")
                .Email("dughdua")
                .Phone("12344")
                .build();
        System.out.println(customer);
    }
}
