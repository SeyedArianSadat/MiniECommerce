package mftplus.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import mftplus.model.entity.Customer;
import mftplus.model.service.CustomerService;

public class CustomerController {

    @FXML private TextField nameField;
    @FXML private TextField familyField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;

    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, Integer> idColumn;
    @FXML private TableColumn<Customer, String> nameColumn;
    @FXML private TableColumn<Customer, String> familyColumn;
    @FXML private TableColumn<Customer, String> emailColumn;
    @FXML private TableColumn<Customer, String> phoneColumn;

    private final CustomerService service = CustomerService.getService();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getCustomerId()).asObject());
        nameColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getName()));
        familyColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getFamily()));
        emailColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getEmail()));
        phoneColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getPhone()));
    }

    @FXML
    public void saveCustomer() {
        try {
            Customer customer = new Customer();
            customer.setName(nameField.getText());
            customer.setFamily(familyField.getText());
            customer.setEmail(emailField.getText());
            customer.setPhone(phoneField.getText());
            service.save(customer);
            findAllCustomers();
            clearFields();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void editCustomer() {
        try {
            Customer selected = customerTable.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            selected.setName(nameField.getText());
            selected.setFamily(familyField.getText());
            selected.setEmail(emailField.getText());
            selected.setPhone(phoneField.getText());
            service.edit(selected);
            findAllCustomers();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void deleteCustomer() {
        try {
            Customer selected = customerTable.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            service.delete(selected.getCustomerId());
            findAllCustomers();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void findAllCustomers() {
        try {
            customerTable.setItems(FXCollections.observableArrayList(service.findAll()));
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void clearFields() {
        nameField.clear();
        familyField.clear();
        emailField.clear();
        phoneField.clear();
    }

    private void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
        alert.setHeaderText("Error");
        alert.showAndWait();
        e.printStackTrace();
    }
}