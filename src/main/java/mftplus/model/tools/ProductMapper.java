package mftplus.model.tools;

import mftplus.model.entity.Product;

import java.sql.ResultSet;

public class ProductMapper {
    public Product map(ResultSet rs) throws Exception {
        return Product.builder()
                .productId(rs.getInt("PRODUCT_ID"))
                .Name(rs.getString("NAME"))
                .stock(rs.getInt("STOCK"))
                .price(rs.getInt("PRICE"))
                .build();
    }
}
