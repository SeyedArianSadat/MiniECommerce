package mftplus.model.repository;

import mftplus.model.entity.Product;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.ProductMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class ProductRepository implements Repository<Product, Integer>, AutoCloseable {
    private  Connection connection;
    private PreparedStatement preparedStatement;
    private final ProductMapper Mapper = new ProductMapper();

    public ProductRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Product product) throws Exception {
    product.setProductId(ConnectionProvider.getProvider().getNextId("PRODUCT_SEQ"));
        preparedStatement = connection.prepareStatement("INSERT INTO product (PRODUCT_ID,NAME,PRICE,STOCK) VALUES(?,?,?,?)");
        preparedStatement.setInt(1, product.getProductId());
        preparedStatement.setString(2, product.getName());
        preparedStatement.setDouble(3, product.getPrice());
        preparedStatement.setInt(4, product.getStock());
        preparedStatement.execute();

    }

    @Override
    public void edit(Product product) throws Exception {
        preparedStatement=connection.prepareStatement("UPDATE product SET NAME = ?,PRICE = ?,STOCK = ? WHERE PRODUCT_ID = ?");
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setInt(3, product.getStock());
        preparedStatement.setInt(4, product.getProductId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement=connection.prepareStatement("DELETE FROM product where PRODUCT_ID = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

    @Override
    public List<Product> findAll() throws Exception {
        List<Product> products = new ArrayList<>();
        preparedStatement=connection.prepareStatement("SELECT * FROM product ORDER BY PRODUCT_ID");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next())
            products.add(Mapper.map(rs));
        return products;

    }

    @Override
    public Product findById(Integer id) throws Exception {
        preparedStatement=connection.prepareStatement("SELECT * FROM product WHERE PRODUCT_ID = ?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.next() ? Mapper.map(rs) : null;

    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) {
            preparedStatement.close();
            connection.close();
        }

    }
}
