package mftplus.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class MainMenuController {

    private void openWindow(String fxml, String title) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/" + fxml))));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openCustomer(ActionEvent event) {
        openWindow("resources/view/CustomerView.fxml", "Customer Management");
    }

    @FXML
    public void openProduct(ActionEvent event) {
        openWindow("view/ProductView.fxml", "Product Management");
    }

    @FXML
    public void openOrder(ActionEvent event) {
        openWindow("view/OrderView.fxml", "Order Management");
    }

    @FXML
    public void openPayment(ActionEvent event) {
        openWindow("view/PaymentView.fxml", "Payment Management");
    }

    @FXML
    public void exitApp(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}