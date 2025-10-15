package mftplus.model.repository;

import mftplus.model.entity.Customer;

import java.util.Collections;
import java.util.List;

public class CustomerRepository implements Repository<Customer,Integer>,AutoCloseable {
    @Override
    public void close() throws Exception {

    }

    @Override
    public void save(Customer customer) throws Exception {

    }

    @Override
    public void edit(Customer customer) throws Exception {

    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public List<Customer> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Customer findById(Integer id) throws Exception {
        return null;
    }
}
