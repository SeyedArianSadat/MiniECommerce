package mftplus.model.service;

import mftplus.model.entity.Payment;

import java.util.Collections;
import java.util.List;

public class PaymentService implements Service<Payment,Integer> {
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
