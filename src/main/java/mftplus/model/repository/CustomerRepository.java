package mftplus.model.repository;

import mftplus.model.entity.Customer;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.CustomerMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class CustomerRepository implements Repository<Customer, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final CustomerMapper Mapper = new CustomerMapper();

    public CustomerRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Customer customer) throws Exception {
        customer.setCustomerId(ConnectionProvider.getProvider().getNextId("CUSTOMER_SEQ"));
        preparedStatement = connection.prepareStatement("insert into CUSTOMER(CUSTOMER_ID,NAME,FAMILY,EMAIL,PHONE)" +" values(?,?,?,?,?)");

        preparedStatement.setInt(1, customer.getCustomerId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getFamily());
        preparedStatement.setString(4, customer.getEmail());
        preparedStatement.setString(5, customer.getPhone());
        preparedStatement.execute();

    }

    @Override
    public void edit(Customer customer) throws Exception {
        preparedStatement = connection.prepareStatement("update CUSTOMER set NAME=?,FAMILY=?,EMAIL=?,PHONE=? where CUSTOMER_ID=?");
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getFamily());
        preparedStatement.setString(3, customer.getEmail());
        preparedStatement.setString(4, customer.getPhone());
        preparedStatement.setInt(5, customer.getCustomerId());
        preparedStatement.executeUpdate();

    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("delete from customer where CUSTOMER_ID=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

    @Override
    public List<Customer> findAll() throws Exception {
        List<Customer> list = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from customer order by CUSTOMER_ID");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next())
            list.add(Mapper.map(rs));
        return list;


    }

    @Override
    public Customer findById(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from customer where CUSTOMER_ID=?");
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
