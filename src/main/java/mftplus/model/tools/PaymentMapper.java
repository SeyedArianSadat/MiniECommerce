package mftplus.model.tools;

import mftplus.model.entity.Method;
import mftplus.model.entity.Payment;

import java.sql.ResultSet;

public class PaymentMapper {
    public Payment map(ResultSet rs) throws Exception {
        return Payment.builder()
                .paymentId(rs.getInt("PAYMENT_ID"))
                .orderId(rs.getInt("ORDER_ID"))
                .paymentDate(rs.getDate("PAYMENT_DATE").toLocalDate())
                .amount(rs.getInt("AMOUNT"))
                .method(Method.valueOf(rs.getString("METHOD")))
                .build();
    }
}
