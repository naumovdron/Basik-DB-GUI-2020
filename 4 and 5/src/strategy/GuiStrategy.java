package strategy;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import lab5.model.Product;

import java.util.List;

public class GuiStrategy implements OutStrategy {
    private TableView<Product> table;
    private Label infoLabel;

    public GuiStrategy(TableView<Product> table, Label infoLabel) {
        this.table = table;
        this.infoLabel = infoLabel;
    }

    @Override
    public void out(String message) {
        infoLabel.setText(message);
    }

    public void out(List<Product> products) {
        table.getItems().remove(0, table.getItems().size());
        table.getItems().addAll(products);
    }
}
