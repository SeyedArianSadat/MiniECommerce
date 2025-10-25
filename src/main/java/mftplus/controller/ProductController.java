package mftplus.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import mftplus.model.entity.Product;
import mftplus.model.service.ProductService;

public class ProductController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField stockField;

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Integer> priceColumn;
    @FXML
    private TableColumn<Product, Integer> stockColumn;

    private final ProductService service = ProductService.getService();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getProductId()).asObject());
        nameColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getName()));
        priceColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getPrice()).asObject());
        stockColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getStock()).asObject());
    }

    @FXML
    public void saveProduct() {
        try {
            Product product = new Product();
            product.setName(nameField.getText());
            product.setPrice(Integer.parseInt(priceField.getText()));
            product.setStock(Integer.parseInt(stockField.getText()));
            service.save(product);
            findAllProducts();
            clearFields();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void editProduct() {
        try {
            Product selected = productTable.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            selected.setName(nameField.getText());
            selected.setPrice(Integer.parseInt(priceField.getText()));
            selected.setStock(Integer.parseInt(stockField.getText()));
            service.edit(selected);
            findAllProducts();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void deleteProduct() {
        try {
            Product selected = productTable.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            service.delete(selected.getProductId());
            findAllProducts();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void findAllProducts() {
        try {
            productTable.setItems(FXCollections.observableArrayList(service.findAll()));
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    public void clearFields() {
        nameField.clear();
        priceField.clear();
        stockField.clear();
    }

    private void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
        alert.setHeaderText("Error");
        alert.showAndWait();
        e.printStackTrace();
    }
}