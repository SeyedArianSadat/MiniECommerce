package mftplus.model.tools;

import mftplus.model.entity.Customer;

import java.sql.ResultSet;

public class CustomerMapper {
    public Customer map(ResultSet rs) throws Exception {
        return Customer.builder()
                .customerId(rs.getInt("CUSTOMER_ID"))
                .Name(rs.getString("NAME"))
                .Family(rs.getString("FAMILY"))
                .Email(rs.getString("EMAIL"))
                .Phone(rs.getString("PHONE"))
                .build();
    }
}
