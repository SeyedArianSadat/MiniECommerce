import mftplus.model.entity.Method;
import mftplus.model.entity.Payment;

import mftplus.model.service.PaymentService;

import java.time.LocalDate;

public class PaymentTest {
    public static void main(String[] args) throws Exception {
        Payment payment1=Payment.builder()
                .paymentId(1)
                .orderId(1)
                .paymentDate(LocalDate.of(2020,10,12))
                .method(Method.Card)
                .amount(1)
                .build();
        PaymentService.getService().save(payment1);

    }
}
