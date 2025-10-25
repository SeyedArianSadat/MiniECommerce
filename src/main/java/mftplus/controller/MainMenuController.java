package mftplus.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {

    private void openWindow(String fxml, String title) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/" + fxml))));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openCustomerView() {
        openWindow("CustomerView.fxml", "Customers");
    }

    @FXML
    public void openProductView() {
        openWindow("ProductView.fxml", "Products");
    }

    @FXML
    public void openOrderView() {
        openWindow("OrderView.fxml", "Orders");
    }

    @FXML
    public void openOrderItemView() {
        openWindow("OrderItemView.fxml", "Order Items");
    }

    @FXML
    public void openPaymentView() {
        openWindow("PaymentView.fxml", "Payments");
    }
}