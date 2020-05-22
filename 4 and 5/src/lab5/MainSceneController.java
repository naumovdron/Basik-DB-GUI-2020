package lab5;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lab4.command.Command;
import lab5.command.*;
import lab5.model.Product;

public class MainSceneController {
    @FXML
    private Button showButton;
    @FXML
    private Button actionButton;
    @FXML
    private Label actionLabel;
    @FXML
    private Label startPriceLabel;
    @FXML
    private Label finalPriceLabel;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField actionTextField;
    @FXML
    private TextField startPriceTextField;
    @FXML
    private TextField finalPriceTextField;
    @FXML
    private CheckBox showAllCheckBox;
    @FXML
    private ChoiceBox<String> actionChoiceBox;
    @FXML
    private TableView<Product> table;
    @FXML
    private TableColumn<Product, String> prodIdColumn;
    @FXML
    private TableColumn<Product, String> titleColumn;
    @FXML
    private TableColumn<Product, String> priceColumn;
    @FXML
    private Label errorLabel;

    @FXML
    private void initialize() {
        prodIdColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getProdId().toString()));
        titleColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTitle()));
        priceColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPrice().toString()));
    }

    @FXML
    private void choiceBoxClicked() {
        switch (actionChoiceBox.getValue()) {
            case "Add":
                actionLabel.setText("Price:");
                actionTextField.setVisible(true);
                actionTextField.setText("");
                actionTextField.setPromptText("price");
                actionButton.setText(actionChoiceBox.getValue());
                break;
            case "Change Price":
                actionLabel.setText("New Price:");
                actionTextField.setVisible(true);
                actionTextField.setText("");
                actionTextField.setPromptText("new price");
                actionButton.setText(actionChoiceBox.getValue());
                break;
            case "Delete":
                actionLabel.setText("");
                actionTextField.setVisible(false);
                actionButton.setText(actionChoiceBox.getValue());
                break;
            case "Price":
                actionLabel.setText("");
                actionTextField.setVisible(false);
                actionButton.setText("Show Price");
                break;
        }
    }

    @FXML
    private void textFieldChanged() {
        actionButton.setDisable((actionTextField.isVisible() && actionTextField.getText().length() == 0) || titleTextField.getText().length() == 0);
    }

    @FXML
    private void priceTextFieldChanged() {
        showButton.setDisable(startPriceTextField.getText().length() == 0 || finalPriceTextField.getText().length() == 0);
    }

    @FXML
    private void showAllCheckBoxClicked() {
        boolean isShowAll = showAllCheckBox.isSelected();
        startPriceTextField.setVisible(!isShowAll);
        finalPriceTextField.setVisible(!isShowAll);
        startPriceTextField.setText("");
        finalPriceTextField.setText("");
        startPriceLabel.setVisible(!isShowAll);
        finalPriceLabel.setVisible(!isShowAll);
        showButton.setDisable(!isShowAll);
    }

    @FXML
    private void showButtonClicked() {
        errorLabel.setText("");
        table.getItems().remove(0, table.getItems().size());
        String request;
        if (showAllCheckBox.isSelected()) {
            request = "SELECT * FROM products";
        } else {
            request = "SELECT * FROM products WHERE cost BETWEEN "
                    + startPriceTextField.getText()
                    + " AND "
                    + finalPriceTextField.getText();
        }
        Command command = new QueryCommand(request, table, errorLabel);
        command.execute();
    }

    @FXML
    private void actionButtonClicked() {
        errorLabel.setText("");
        table.getItems().remove(0, table.getItems().size());
        Command command = null;
        switch (actionChoiceBox.getValue()) {
            case "Add":
                command = new UpdateCommand("INSERT INTO products(title, cost) VALUE ('"
                        + titleTextField.getText() + "', "
                        + actionTextField.getText() + ")",
                        errorLabel
                );
                break;
            case "Change Price":
                command = new UpdateCommand("UPDATE products SET cost = "
                        + actionTextField.getText() + " WHERE title = '"
                        + titleTextField.getText() + "'",
                        errorLabel
                );
                break;
            case "Delete":
                command = new UpdateCommand("DELETE FROM products WHERE title = '"
                        + titleTextField.getText() + "'",
                        errorLabel);
                break;
            case "Price":
                command = new QueryCommand("SELECT * FROM products WHERE title = '"
                        + titleTextField.getText() + "'",
                        table,
                        errorLabel);
                break;
        }
        command.execute();
    }
}
