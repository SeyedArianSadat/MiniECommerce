package mftplus.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import mftplus.model.entity.OrderItem;
import mftplus.model.service.OrderItemService;

public class OrderItemController {

    @FXML
    private TextField orderIdField;
    @FXML
    private TextField productIdField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;

    @FXML
    private TableView<OrderItem> itemTable;
    @FXML
    private TableColumn<OrderItem, Integer> idColumn;
    @FXML
    private TableColumn<OrderItem, Integer> orderIdColumn;
    @FXML
    private TableColumn<OrderItem, Integer> productIdColumn;
    @FXML
    private TableColumn<OrderItem, Integer> quantityColumn;
    @FXML
    private TableColumn<OrderItem, Integer> priceColumn;

    private final OrderItemService service = OrderItemService.getService();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getItemId()).asObject());
        orderIdColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getOrderId()).asObject());
        productIdColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getProductId()).asObject());
        quantityColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getQuantity()).asObject());
        priceColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getPrice()).asObject());
    }

    @FXML
    public void saveItem() {
        try {
            OrderItem item = new OrderItem();
            item.setOrderId(Integer.parseInt(orderIdField.getText()));
            item.setProductId(Integer.parseInt(productIdField.getText()));
            item.setQuantity(Integer.parseInt(quantityField.getText()));
            item.setPrice(Integer.parseInt(priceField.getText()));
            service.save(item);
            findAllItems();
            clearFields();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void editItem() {
        try {
            OrderItem selected = itemTable.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            selected.setOrderId(Integer.parseInt(orderIdField.getText()));
            selected.setProductId(Integer.parseInt(productIdField.getText()));
            selected.setQuantity(Integer.parseInt(quantityField.getText()));
            selected.setPrice(Integer.parseInt(priceField.getText()));
            service.edit(selected);
            findAllItems();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void deleteItem() {
        try {
            OrderItem selected = itemTable.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            service.delete(selected.getItemId());
            findAllItems();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void findAllItems() {
        try {
            itemTable.setItems(FXCollections.observableArrayList(service.findAll()));
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void clearFields() {
        orderIdField.clear();
        productIdField.clear();
        quantityField.clear();
        priceField.clear();
    }

    private void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
        alert.setHeaderText("Error");
        alert.showAndWait();
        e.printStackTrace();
    }
}