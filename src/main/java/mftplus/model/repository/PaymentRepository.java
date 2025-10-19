package mftplus.model.repository;

import mftplus.model.entity.Payment;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.PaymentMapper;
import sun.util.calendar.BaseCalendar;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentRepository implements Repository<Payment,Integer>,AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private final PaymentMapper Mapper = new PaymentMapper();

    public  PaymentRepository() throws SQLException {
        connection= ConnectionProvider.getProvider().getOracleConnection();

    }
    @Override
    public void save(Payment payment) throws Exception {
        payment.setPaymentId(ConnectionProvider.getProvider().getNextId("PAYMENT_SEQ"));
        preparedStatement=connection.prepareStatement("insert into PAYMENT(PAYMENT_ID,ORDER_ID,PAYMENT_DATE,AMOUNT,METHOD) VALUES(?,?,?,?,?)");
        preparedStatement.setInt(1, payment.getPaymentId());
        preparedStatement.setInt(2, payment.getOrderId());
        preparedStatement.setDate(3, Date.valueOf(payment.getPaymentDate()));
        preparedStatement.setDouble(4, payment.getAmount());
        preparedStatement.setString(5, payment.getMethod().toString());
        preparedStatement.executeUpdate();
    }

    @Override
    public void edit(Payment payment) throws Exception {
        preparedStatement=connection.prepareStatement("update PAYMENT set ORDER_ID=?,PAYMENT_DATE=?,AMOUNT=?,METHOD=? where PAYMENT_ID=?");
        preparedStatement.setInt(1, payment.getOrderId());
        preparedStatement.setDate(2, Date.valueOf(payment.getPaymentDate()));
        preparedStatement.setDouble(3, payment.getAmount());
        preparedStatement.setString(4, payment.getMethod().toString());
        preparedStatement.setInt(5, payment.getPaymentId());
        preparedStatement.executeUpdate();

    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement=connection.prepareStatement("delete from PAYMENT where PAYMENT_ID=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

    @Override
    public List<Payment> findAll() throws Exception {
        List<Payment> payments = new ArrayList<>();
        preparedStatement=connection.prepareStatement("SELECT * FROM PAYMENT ORDER BY PAYMENT_ID=?");
        ResultSet rs=preparedStatement.executeQuery();
        while (rs.next())
            payments.add(Mapper.map(rs));
        return payments;
    }

    @Override
    public Payment findById(Integer id) throws Exception {
        preparedStatement=connection.prepareStatement("SELECT * FROM PAYMENT WHERE PAYMENT_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.wasNull() ? Mapper.map(rs) : null;

    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) {
            preparedStatement.close();
            connection.close();
        }

    }
}
