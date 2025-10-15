package mftplus.model.service;

import mftplus.model.entity.Customer;

import java.util.Collections;
import java.util.List;

public class CustomerService implements Service<Customer,Integer> {
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
