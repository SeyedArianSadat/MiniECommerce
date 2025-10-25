package mftplus.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import mftplus.model.entity.Method;
import mftplus.model.entity.Payment;
import mftplus.model.service.PaymentService;

import java.time.LocalDate;


public class PaymentController {

    @FXML
    private TextField orderIdField;
    @FXML
    private DatePicker paymentDatePicker;
    @FXML
    private TextField amountField;
    @FXML
    private ComboBox<String> methodBox;

    @FXML
    private TableView<Payment> paymentTable;
    @FXML
    private TableColumn<Payment, Integer> idColumn;
    @FXML
    private TableColumn<Payment, Integer> orderIdColumn;
    @FXML
    private TableColumn<Payment, LocalDate> paymentDateColumn;
    @FXML
    private TableColumn<Payment, Integer> amountColumn;
    @FXML
    private TableColumn<Payment, String> methodColumn;

    private final PaymentService service = PaymentService.getService();

    @FXML
    public void initialize() {
        methodBox.getItems().addAll("Card", "Cash", "Online");

        idColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getPaymentId()).asObject());
        orderIdColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getOrderId()).asObject());
        paymentDateColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getPaymentDate()));
        amountColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getAmount()).asObject());
        methodColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getMethod().toString()));
    }

    @FXML
    public void savePayment() {
        try {
            Payment payment = new Payment();
            payment.setOrderId(Integer.parseInt(orderIdField.getText()));
            payment.setPaymentDate(paymentDatePicker.getValue());
            payment.setAmount(Integer.parseInt(amountField.getText()));
            payment.setMethod(Method.valueOf(methodBox.getValue()));
            service.save(payment);
            findAllPayments();
            clearFields();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void editPayment() {
        try {
            Payment selected = paymentTable.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            selected.setOrderId(Integer.parseInt(orderIdField.getText()));
            selected.setPaymentDate(paymentDatePicker.getValue());
            selected.setAmount(Integer.parseInt(amountField.getText()));
            selected.setMethod(Method.valueOf(methodBox.getValue()));
            service.edit(selected);
            findAllPayments();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void deletePayment() {
        try {
            Payment selected = paymentTable.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            service.delete(selected.getPaymentId());
            findAllPayments();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void findAllPayments() {
        try {
            paymentTable.setItems(FXCollections.observableArrayList(service.findAll()));
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void clearFields() {
        orderIdField.clear();
        paymentDatePicker.setValue(null);
        amountField.clear();
        methodBox.getSelectionModel().clearSelection();
    }

    private void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
        alert.setHeaderText("Error");
        alert.showAndWait();
        e.printStackTrace();
    }
}