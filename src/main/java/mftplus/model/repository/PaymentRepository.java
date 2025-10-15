package mftplus.model.repository;

import mftplus.model.entity.Payment;

import java.util.Collections;
import java.util.List;

public class PaymentRepository implements Repository<Payment,Integer>,AutoCloseable {
    @Override
    public void close() throws Exception {

    }

    @Override
    public void save(Payment payment) throws Exception {

    }

    @Override
    public void edit(Payment payment) throws Exception {

    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public List<Payment> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Payment findById(Integer id) throws Exception {
        return null;
    }
}
