package mftplus.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import mftplus.model.entity.Order;
import mftplus.model.service.OrderService;

import java.time.LocalDate;

public class OrderController {

    @FXML
    private TextField customerIdField;
    @FXML
    private DatePicker orderDatePicker;
    @FXML
    private TextField totalAmountField;

    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, Integer> idColumn;
    @FXML
    private TableColumn<Order, Integer> customerIdColumn;
    @FXML
    private TableColumn<Order, LocalDate> dateColumn;
    @FXML
    private TableColumn<Order, Integer> amountColumn;

    private final OrderService service = OrderService.getService();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getOrderId()).asObject());
        customerIdColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getCustomerId()).asObject());
        dateColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getOrderDate()));
        amountColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getTotalAmount()).asObject());
    }

    @FXML
    public void saveOrder() {
        try {
            Order order = new Order();
            order.setCustomerId(Integer.parseInt(customerIdField.getText()));
            order.setOrderDate(orderDatePicker.getValue());
            order.setTotalAmount(Integer.parseInt(totalAmountField.getText()));
            service.save(order);
            findAllOrders();
            clearFields();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void editOrder() {
        try {
            Order selected = orderTable.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            selected.setCustomerId(Integer.parseInt(customerIdField.getText()));
            selected.setOrderDate(orderDatePicker.getValue());
            selected.setTotalAmount(Integer.parseInt(totalAmountField.getText()));
            service.edit(selected);
            findAllOrders();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void deleteOrder() {
        try {
            Order selected = orderTable.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            service.delete(selected.getOrderId());
            findAllOrders();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void findAllOrders() {
        try {
            orderTable.setItems(FXCollections.observableArrayList(service.findAll()));
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void clearFields() {
        customerIdField.clear();
        orderDatePicker.setValue(null);
        totalAmountField.clear();
    }

    private void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
        alert.setHeaderText("Error");
        alert.showAndWait();
        e.printStackTrace();
    }
}